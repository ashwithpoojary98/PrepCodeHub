package io.github.ashwith.solid.isp.correct;



public class NotificationService {

    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();
        EmailNotification emailNotification = new EmailNotification();
        SMSNotification smsNotification = new SMSNotification();
        notificationService.notify(emailNotification, "Email Notification");
        notificationService.auditLog(emailNotification,"Audit log");
        notificationService.schedule(smsNotification, "SMS Notification");

    }

    public void notify(INotify notify, String message) {
        notify.notify(message);
    }

    public void auditLog(IAudit audit, String message) {
        audit.audit(message);
    }

    public void schedule(ISchedule schedule, String message) {
        schedule.schedule(message);
    }

}
