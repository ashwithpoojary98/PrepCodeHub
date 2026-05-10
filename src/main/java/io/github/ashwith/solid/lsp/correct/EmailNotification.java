package io.github.ashwith.solid.lsp.correct;

import io.github.ashwith.solid.lsp.INotify;

public class EmailNotification implements INotify {
    @Override
    public void notify(String message) {
        System.out.println("Email Notification: " + message);
    }
}
