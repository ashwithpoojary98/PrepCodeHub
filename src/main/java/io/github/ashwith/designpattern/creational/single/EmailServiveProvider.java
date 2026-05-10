package io.github.ashwith.designpattern.creational.single;

public class EmailServiveProvider {

    private static EmailService instance;

    public synchronized static EmailService getInstance() {
        if (instance == null) {
            instance = new EmailService();
        }
        return instance;
    }

    static class Holder {
        static EmailService emailService = new EmailService();
    }

    public static EmailService getEmailService() {
        return EmailServiveProvider.Holder.emailService;
    }
}
