package io.github.ashwith.solid.isp.correct;

public class SMSNotification implements INotify, ISchedule {

    @Override
    public void notify(String message) {
        System.out.println("SMSNotification " + message);
    }


    @Override
    public void schedule(String message) {
        System.out.println("SMS Scheduled " + message);
    }
}
