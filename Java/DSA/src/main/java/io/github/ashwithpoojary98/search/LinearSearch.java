package main.java.io.github.ashwithpoojary98.search;

public class LinearSearch {

    public static void main(String[] args) {
        int target = 10;

        int[] arr = new int[] { 1, 2, 10, 6, 4 };
        boolean isFound = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                System.out.println(String.format("%d found at index %d", target, i));
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            System.out.print("Elemement is not found");
        }

    }
}
