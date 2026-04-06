package io.github.ashwith.scresponsible;

/**
 * This class violate the Single class Responsible because it consists of
 * different
 */
public class SingleClassViolation {

    private String name;
    private String email;

    public SingleClassViolation(String name, String email) {
        this.name = name;
        this.email = email;
    }


    public void saveInDB() {
        //Store this in DB
    }

    public void notifyUser() {
        //Notify the User
    }

}
