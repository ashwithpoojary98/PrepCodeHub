package io.github.ashwith.solid.lsp;

public class SMSNotification implements INotify {
    @Override
    public void notify(String message) {
        System.out.println("SMS Notification: " + message);
    }
}
