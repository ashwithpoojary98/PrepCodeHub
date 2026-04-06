package io.github.ashwith.openclose;

public interface INotifier {

    void notify(String message);

    NotifierType getNotificationProvider();

}
