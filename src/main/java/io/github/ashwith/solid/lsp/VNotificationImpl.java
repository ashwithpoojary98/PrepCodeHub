package io.github.ashwith.solid.lsp;

public class VNotificationImpl {

    public static void main(String[] args) {
        VNotificationImpl vNotificationImpl = new VNotificationImpl();
        vNotificationImpl.notify(new EmailNotification(),"Email Notification");
        vNotificationImpl.notify(new SMSNotification(),"SMS Notification");
        vNotificationImpl.notify(new DNDNotification(),"DND Notification");
    }


    public void notify(INotify iNotify,String message){
        iNotify.notify(message);
    }
}
