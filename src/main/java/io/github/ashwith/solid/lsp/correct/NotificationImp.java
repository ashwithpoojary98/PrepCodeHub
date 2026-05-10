package io.github.ashwith.solid.lsp.correct;

import io.github.ashwith.solid.lsp.EmailNotification;
import io.github.ashwith.solid.lsp.INotify;
import io.github.ashwith.solid.lsp.SMSNotification;

public class NotificationImp {

    public static void main(String[] args) {
        NotificationImp notificationImp = new NotificationImp();
        notificationImp.audit(new DNDNotification(),"Audit","No Notification");
        notificationImp.notify(new EmailNotification(),"Email Notification");
        notificationImp.notify(new SMSNotification(),"SMS Notification");

    }

    public void notify(INotify notify,String message) {
        notify.notify(message);
    }

    public void audit(IAudit audit, String eventType, String message) {
        audit.logEvent(eventType, message);
    }
}
