package io.github.ashwith.lsp.correct;

import io.github.ashwith.lsp.INotify;

public class EmailNotification implements INotify {
    @Override
    public void notify(String message) {
        System.out.println("Email Notification: " + message);
    }
}
