package io.github.ashwith.systemdesign.normalhashing;

import io.github.ashwith.systemdesign.normalhashing.core.ConsistentHashRouter;
import io.github.ashwith.systemdesign.normalhashing.core.NormalHashRouter;
import io.github.ashwith.systemdesign.normalhashing.model.RedisConfiguration;
import io.github.ashwith.systemdesign.normalhashing.model.RedisServer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsistentHashingRunner {

    static final List<String> KEYS = List.of(
            "user:1", "user:2", "user:3", "user:4", "user:5",
            "order:1", "order:2", "order:3", "order:4", "order:5",
            "product:A", "product:B", "product:C", "product:D", "product:E",
            "session:1", "session:2", "session:3", "session:4", "session:5"
    );

    public static void main(String[] args) {

        RedisConfiguration config = new RedisConfiguration.RedisConfigurationBuilder()
                .nodeCount(3)
                .cacheSize(100)
                .defaultExpireTime(60000)
                .loadFactor(0.75)
                .build();

        RedisServer<String> s1 = new RedisServer<>("192.168.1.1", 6379, config);
        RedisServer<String> s2 = new RedisServer<>("192.168.1.2", 6379, config);
        RedisServer<String> s3 = new RedisServer<>("192.168.1.3", 6379, config);
        RedisServer<String> s4 = new RedisServer<>("192.168.1.4", 6379, config);

        // ----------------------------------------------------------------
        // Demo 1: Normal hashing — how many keys remap when adding a server
        // ----------------------------------------------------------------
        System.out.println("=== Demo 1: Normal Hashing — Add 1 Server (3 → 4) ===");
        NormalHashRouter<String> normalRouter = new NormalHashRouter<>(Arrays.asList(s1, s2, s3));
        NormalHashRouter.ReshufflingReport normalReport = normalRouter.simulateAddServerImpact(KEYS, s4);
        System.out.println("  " + normalReport);
        System.out.printf("  Expected ~75%% remap (N/N+1 = 3/4)%n");

        // ----------------------------------------------------------------
        // Demo 2: Consistent hashing — same operation, far fewer remaps
        // ----------------------------------------------------------------
        System.out.println("\n=== Demo 2: Consistent Hashing — Add 1 Server (3 → 4) ===");
        ConsistentHashRouter<String> chRouter = new ConsistentHashRouter<>(
                Arrays.asList(s1, s2, s3), 100
        );
        System.out.println("  Ring size before add: " + chRouter.getRingSize() + " (3 servers × 100 vnodes)");

        Map<String, String> before = captureMapping(chRouter);
        chRouter.addServer(s4);
        Map<String, String> after = captureMapping(chRouter);

        System.out.println("  Ring size after add:  " + chRouter.getRingSize() + " (4 servers × 100 vnodes)");
        printRemapReport(before, after);
        System.out.printf("  Expected ~25%% remap (1/N = 1/4)%n");

        // ----------------------------------------------------------------
        // Demo 3: Consistent hashing — remove a server
        // ----------------------------------------------------------------
        System.out.println("\n=== Demo 3: Consistent Hashing — Remove 1 Server (4 → 3) ===");
        Map<String, String> beforeRemove = captureMapping(chRouter);
        chRouter.removeServer(s4);
        Map<String, String> afterRemove = captureMapping(chRouter);

        System.out.println("  Ring size after remove: " + chRouter.getRingSize());
        printRemapReport(beforeRemove, afterRemove);

        // ----------------------------------------------------------------
        // Demo 4: Get / Set / Delete
        // ----------------------------------------------------------------
        System.out.println("\n=== Demo 4: Get / Set / Delete ===");
        chRouter.set("user:1", "Alice");
        chRouter.set("user:2", "Bob");
        chRouter.set("user:3", "Charlie");
        System.out.println("  Set user:1=Alice, user:2=Bob, user:3=Charlie");
        System.out.println("  Get user:1  -> " + chRouter.get("user:1"));
        System.out.println("  Get user:99 -> " + chRouter.get("user:99") + "  (cache miss)");
        chRouter.delete("user:2");
        System.out.println("  Delete user:2");
        System.out.println("  Get user:2  -> " + chRouter.get("user:2") + "  (deleted)");

        // ----------------------------------------------------------------
        // Demo 5: Health failover — key routes to next server on ring
        // ----------------------------------------------------------------
        System.out.println("\n=== Demo 5: Health Failover ===");
        String testKey = "user:1";
        RedisServer<String> primary = chRouter.getServer(testKey);
        System.out.println("  Primary for user:1   -> " + primary.getServerIP());

        primary.setHealthy(false);
        RedisServer<String> fallback = chRouter.getServer(testKey);
        System.out.println("  Primary down. Routes to -> " + fallback.getServerIP() + "  (next on ring)");
        primary.setHealthy(true);

        // ----------------------------------------------------------------
        // Demo 6: Virtual node distribution — how evenly keys spread
        // ----------------------------------------------------------------
        System.out.println("\n=== Demo 6: Key Distribution Across Servers ===");
        Map<String, Integer> distribution = new HashMap<>();
        for (String key : KEYS) {
            String ip = chRouter.getServer(key).getServerIP();
            distribution.merge(ip, 1, Integer::sum);
        }
        distribution.forEach((ip, count) ->
                System.out.printf("  %s -> %d keys%n", ip, count));
    }

    private static Map<String, String> captureMapping(ConsistentHashRouter<String> router) {
        Map<String, String> mapping = new HashMap<>();
        for (String key : KEYS) {
            mapping.put(key, router.getServer(key).getServerIP());
        }
        return mapping;
    }

    private static void printRemapReport(Map<String, String> before, Map<String, String> after) {
        long remapped = KEYS.stream()
                .filter(k -> !before.get(k).equals(after.get(k)))
                .count();
        System.out.printf("  Remapped: %d/%d (%.1f%%)%n",
                remapped, KEYS.size(), remapped * 100.0 / KEYS.size());
    }
}
