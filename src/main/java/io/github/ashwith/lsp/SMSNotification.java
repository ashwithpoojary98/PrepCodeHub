package io.github.ashwith.lsp;

public class SMSNotification implements INotify {
    @Override
    public void notify(String message) {
        System.out.println("SMS Notification: " + message);
    }
}
