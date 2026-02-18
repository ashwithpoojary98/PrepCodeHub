package main.java.io.github.ashwithpoojary98.sort;

import java.util.Arrays;
public class SelectionSort {
    

    public static void main(String[] abc){

      int[] arr={3,2, 3,5,8,6};

      for(int i=0;i<arr.length;i++){
        int minmumIndex=i;

        for(int j=i+1;j<arr.length;j++){

            if(arr[j]<arr[minmumIndex]){
                minmumIndex=j;
            }
        }

        int temp=arr[i];
         arr[i]=arr[minmumIndex];
         arr[minmumIndex]=temp;
      }

     System.out.println("Sort array is "+Arrays.toString(arr));

    }
}
