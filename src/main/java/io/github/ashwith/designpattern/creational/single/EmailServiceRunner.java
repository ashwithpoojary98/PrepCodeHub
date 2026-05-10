package io.github.ashwith.designpattern.creational.single;

public class EmailServiceRunner {

    public static void main(String[] args) {
        EmailService emailService = EmailServiveProvider.getInstance();
        emailService.sendEmail("ashwith","Single tone pattern","Automation");
    }
}
