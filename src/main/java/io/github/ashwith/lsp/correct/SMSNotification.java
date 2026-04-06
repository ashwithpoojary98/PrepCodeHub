package io.github.ashwith.lsp.correct;

import io.github.ashwith.lsp.INotify;

public class SMSNotification implements INotify {
    @Override
    public void notify(String message) {
        System.out.println("SMS notification received: " + message);
    }
}
