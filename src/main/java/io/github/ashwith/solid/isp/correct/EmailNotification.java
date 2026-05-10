package io.github.ashwith.solid.isp.correct;


public class EmailNotification implements INotify, IAudit {


    @Override
    public void notify(String message) {
        System.out.println(message);
    }

    @Override
    public void audit(String message) {
        System.out.println("Audit log");
    }
}
