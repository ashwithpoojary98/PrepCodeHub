package io.github.ashwith.solid.openclose;

public class NotificationService {

    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();
        notificationService.invokeNotification(new EmailNotificationImp(), "Email");
        notificationService.invokeNotification(new SMSNotificationImp(), "SMS");
    }

    public void invokeNotification(INotifier notifier, String message) {
        notifier.notify(message);
    }
}
