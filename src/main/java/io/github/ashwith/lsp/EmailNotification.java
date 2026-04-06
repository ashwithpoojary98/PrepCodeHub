package io.github.ashwith.lsp;

public class EmailNotification implements INotify {
    @Override
    public void notify(String message) {
        System.out.println("Email Notification: " + message);
    }


}
