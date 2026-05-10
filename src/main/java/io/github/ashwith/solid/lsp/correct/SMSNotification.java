package io.github.ashwith.solid.lsp.correct;

import io.github.ashwith.solid.lsp.INotify;

public class SMSNotification implements INotify {
    @Override
    public void notify(String message) {
        System.out.println("SMS notification received: " + message);
    }
}
