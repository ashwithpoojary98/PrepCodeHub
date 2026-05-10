package io.github.ashwith.solid.isp.wrong;

public interface INotifier {

    void audit(String event,String action,String message);

    void notify(String message);

    void publishNotification(String message);

    void schedule(String message);
}
