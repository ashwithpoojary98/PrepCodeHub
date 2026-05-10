package io.github.ashwith.solid.isp.wrong;

public class EmailNotification implements INotifier {

    @Override
    public void audit(String event, String action, String message) {
        System.out.println("Audit Event: " + event);
    }

    @Override
    public void notify(String message) {
        System.out.println("Email Notification");
    }

    @Override
    public void publishNotification(String message) {
        throw new UnsupportedOperationException("Not supported yet.");

    }

    @Override
    public void schedule(String message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
