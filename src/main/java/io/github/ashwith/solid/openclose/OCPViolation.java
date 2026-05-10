package io.github.ashwith.solid.openclose;

public class OCPViolation {


    public void notifyUser(String type, String message) {
        if (type.equals("email")) {
            System.out.println("Sending email to " + message);
        } else if (type.equals("phone")) {
            System.out.println("Sending phone number to " + message);
        } else {
            throw new IllegalArgumentException("Invalid type");
        }
    }
}
