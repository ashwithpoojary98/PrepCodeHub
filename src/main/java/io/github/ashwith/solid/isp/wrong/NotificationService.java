package io.github.ashwith.solid.isp.wrong;

public class NotificationService {

    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();
        notificationService.audit(new EmailNotification(),"Email Notification");
        notificationService.notify(new EmailNotification(),"Email Notification");
        notificationService.schedule(new EmailNotification(),"Email Notification");
        notificationService.publish(new EmailNotification(),"Email Notification");

    }

    public void  notify(INotifier notifier,String message){
        notifier.notify(message);
    }

    public void schedule(INotifier notifier,String message){
        notifier.schedule(message);
    }

    public void audit(INotifier notifier,String message){
        notifier.audit("Notification","Notification",message);
    }

    public void publish(INotifier notifier,String message){
        notifier.publishNotification(message);
    }

}
