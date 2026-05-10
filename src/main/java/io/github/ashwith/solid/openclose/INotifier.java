package io.github.ashwith.solid.openclose;

public interface INotifier {

    void notify(String message);

    NotifierType getNotificationProvider();

}
