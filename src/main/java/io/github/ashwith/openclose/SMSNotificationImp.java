package io.github.ashwith.openclose;

public class SMSNotificationImp implements INotifier {
    @Override
    public void notify(String message) {
        System.out.println("SMS Notification Service");
    }

    @Override
    public NotifierType getNotificationProvider() {
        return NotifierType.SMS;
    }
}
