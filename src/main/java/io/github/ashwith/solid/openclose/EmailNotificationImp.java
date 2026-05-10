package io.github.ashwith.solid.openclose;

public class EmailNotificationImp implements INotifier {
    @Override
    public void notify(String message) {
        System.out.println("Email Notification Service");
    }

    @Override
    public NotifierType getNotificationProvider() {
        return NotifierType.EMAIL;
    }
}
