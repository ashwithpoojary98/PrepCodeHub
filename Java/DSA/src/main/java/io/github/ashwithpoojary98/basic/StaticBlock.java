package main.java.io.github.ashwithpoojary98.basic;

public class StaticBlock {

    static {
        System.out.println("My first static block");
    }

    static {
        System.out.println("My second static block");
    }

    public static void main(String[] args) {

    }

    static {
        System.out.println(
                "My final static block");
    }
    int x;

   
}
