package io.github.ashwith.systemdesign.normalhashing;

import io.github.ashwith.systemdesign.normalhashing.core.NormalHashRouter;
import io.github.ashwith.systemdesign.normalhashing.model.RedisConfiguration;
import io.github.ashwith.systemdesign.normalhashing.model.RedisServer;

import java.util.Arrays;
import java.util.List;

public class HashingRunner {

    public static void main(String[] args) throws InterruptedException {

        RedisConfiguration config = new RedisConfiguration.RedisConfigurationBuilder()
                .nodeCount(3)
                .cacheSize(5)
                .defaultExpireTime(5000)
                .loadFactor(0.75)
                .build();

        RedisServer<String> server1 = new RedisServer<>("192.168.1.1", 6379, config);
        RedisServer<String> server2 = new RedisServer<>("192.168.1.2", 6379, config);
        RedisServer<String> server3 = new RedisServer<>("192.168.1.3", 6379, config);

        NormalHashRouter<String> router = new NormalHashRouter<>(
                Arrays.asList(server1, server2, server3)
        );

        // ----------------------------------------------------------------
        // Demo 1: Basic routing — shows hash(key) % N in action
        // ----------------------------------------------------------------
        System.out.println("=== Demo 1: Key Routing [hash(key) % N] ===");
        String[] keys = {"user:1", "user:2", "user:3", "product:A", "order:100"};
        for (String key : keys) {
            RedisServer<String> target = router.getServer(key);
            System.out.printf("  %-15s -> %s:%d%n", key, target.getServerIP(), target.getServerPort());
        }

        // ----------------------------------------------------------------
        // Demo 2: Get / Set / Delete
        // ----------------------------------------------------------------
        System.out.println("\n=== Demo 2: Get / Set / Delete ===");
        router.set("user:1", "Alice");
        router.set("user:2", "Bob");
        router.set("user:3", "Charlie");
        System.out.println("  Set user:1=Alice, user:2=Bob, user:3=Charlie");
        System.out.println("  Get user:1  -> " + router.get("user:1"));
        System.out.println("  Get user:2  -> " + router.get("user:2"));
        System.out.println("  Get user:99 -> " + router.get("user:99") + "  (cache miss)");
        router.delete("user:2");
        System.out.println("  Delete user:2");
        System.out.println("  Get user:2  -> " + router.get("user:2") + "  (deleted)");

        // ----------------------------------------------------------------
        // Demo 3: Health check — unhealthy server is skipped in routing
        // ----------------------------------------------------------------
        System.out.println("\n=== Demo 3: Health Check Routing ===");
        System.out.println("  Marking 192.168.1.2 as UNHEALTHY...");
        server2.setHealthy(false);
        for (String key : keys) {
            RedisServer<String> target = router.getServer(key);
            System.out.printf("  %-15s -> %s:%d%n", key, target.getServerIP(), target.getServerPort());
        }
        server2.setHealthy(true);
        System.out.println("  Restored 192.168.1.2 to healthy.");

        // ----------------------------------------------------------------
        // Demo 4: LRU eviction — oldest entry dropped when cache is full
        // ----------------------------------------------------------------
        System.out.println("\n=== Demo 4: LRU Eviction (cacheSize=3) ===");
        RedisConfiguration smallConfig = new RedisConfiguration.RedisConfigurationBuilder()
                .cacheSize(3).defaultExpireTime(60000).build();
        RedisServer<String> lruServer = new RedisServer<>("10.0.0.1", 6379, smallConfig);

        lruServer.setValue("k1", "v1");
        lruServer.setValue("k2", "v2");
        lruServer.setValue("k3", "v3");
        System.out.println("  Inserted k1, k2, k3 — cache full at 3");
        lruServer.getValue("k1");  // access k1, making k2 the least-recently-used
        System.out.println("  Accessed k1 — k2 is now LRU");
        lruServer.setValue("k4", "v4");  // k2 should be evicted
        System.out.println("  Insert k4 -> k2 evicted");
        System.out.printf("  k1=%s  k2=%s(evicted)  k3=%s  k4=%s%n",
                lruServer.getValue("k1"),
                lruServer.getValue("k2"),
                lruServer.getValue("k3"),
                lruServer.getValue("k4"));

        // ----------------------------------------------------------------
        // Demo 5: TTL expiry
        // ----------------------------------------------------------------
        System.out.println("\n=== Demo 5: TTL Expiry ===");
        RedisConfiguration ttlConfig = new RedisConfiguration.RedisConfigurationBuilder()
                .cacheSize(5).defaultExpireTime(100).build();  // 100ms TTL
        RedisServer<String> ttlServer = new RedisServer<>("10.0.0.2", 6379, ttlConfig);

        ttlServer.setValue("session:abc", "token123");
        System.out.println("  Immediately:  session:abc = " + ttlServer.getValue("session:abc"));
        Thread.sleep(150);
        System.out.println("  After 150ms:  session:abc = " + ttlServer.getValue("session:abc") + "  (expired)");

        // ----------------------------------------------------------------
        // Demo 6: clearExpiredKeys — batch purge
        // ----------------------------------------------------------------
        System.out.println("\n=== Demo 6: clearExpiredKeys ===");
        RedisConfiguration purgeConfig = new RedisConfiguration.RedisConfigurationBuilder()
                .cacheSize(10).defaultExpireTime(100).build();
        RedisServer<String> purgeServer = new RedisServer<>("10.0.0.3", 6379, purgeConfig);

        purgeServer.setValue("a", "1");
        purgeServer.setValue("b", "2");
        purgeServer.setValue("c", "3");
        System.out.println("  Inserted 3 keys with 100ms TTL. Size = " + purgeServer.getCurrentCacheSize());
        Thread.sleep(150);
        int cleared = purgeServer.clearExpiredKeys();
        System.out.println("  After 150ms: cleared " + cleared + " expired keys. Size = " + purgeServer.getCurrentCacheSize());

        // ----------------------------------------------------------------
        // Demo 7: Reshuffling — THE core weakness of normal hashing
        // ----------------------------------------------------------------
        System.out.println("\n=== Demo 7: Reshuffling Impact (core weakness) ===");
        List<String> testKeys = Arrays.asList(
                "user:1", "user:2", "user:3", "user:4", "user:5",
                "user:6", "user:7", "user:8", "user:9", "user:10",
                "order:1", "order:2", "order:3", "order:4", "order:5",
                "product:A", "product:B", "product:C", "product:D", "product:E"
        );

        System.out.println("  Current cluster: 3 servers");

        RedisServer<String> server4 = new RedisServer<>("192.168.1.4", 6379, config);
        NormalHashRouter.ReshufflingReport addReport = router.simulateAddServerImpact(testKeys, server4);
        System.out.println("  Simulate ADD 1 server    -> " + addReport);

        NormalHashRouter.ReshufflingReport removeReport = router.simulateRemoveServerImpact(testKeys, server3);
        System.out.println("  Simulate REMOVE 1 server -> " + removeReport);

        System.out.println();
        System.out.println("  With N=3 servers, adding 1 remaps ~N/(N+1) = ~75% of keys.");
        System.out.println("  Consistent Hashing fixes this — only ~1/N (~25%) of keys remap.");

        // ----------------------------------------------------------------
        // Demo 8: Load factor per server
        // ----------------------------------------------------------------
        System.out.println("\n=== Demo 8: Load Factor Per Server ===");
        for (RedisServer<String> s : router.getAllServers()) {
            System.out.printf("  %s  loadFactor=%.2f  full=%b%n",
                    s, s.checkCurrentLoadFactor(), s.isLoadFactorFull());
        }
    }
}

