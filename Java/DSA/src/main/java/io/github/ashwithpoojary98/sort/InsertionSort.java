package main.java.io.github.ashwithpoojary98.sort;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {

        int arr[] = { 4, 5, 3, 2, 1, 9, 5 };

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                System.out.println("Element is " + Arrays.toString(arr));
                arr[j + 1] = arr[j];
                j--;
            }
            System.out.println("Final sort");
            System.out.println("Element is " + Arrays.toString(arr));
            System.out.println("------------");

            arr[j + 1] = key;
        }

        System.out.println("Sorted arrays is " + Arrays.toString(arr));

    }

}
