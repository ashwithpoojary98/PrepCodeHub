package io.github.ashwith.lsp.correct;

public class DNDNotification implements IAudit {

    @Override
    public void logEvent(String eventType, String message) {
        System.out.println("[DND] " + eventType + " : " + message);
    }
}
