package main.java.io.github.ashwithpoojary98.sort;

import java.util.Arrays;

public class HeapSort {


    public static void main(String[] args){
    int[] arr={3,4,2,13,1,5,9};
    int n=arr.length;
    for(int i=n/2-1;i>=0;i--){
      heapSort(arr, n, i);
    }
    for(int i=n-1;i>0;i--){
     int swap=arr[0];
      arr[0]=arr[i];
      arr[i]=swap;
      heapSort(arr, i, 0);
    }
    System.out.println(Arrays.toString(arr));

    }

    public static void heapSort(int[] arr,int n, int i){
     int largest=i;
     int leftNode=2*i+1;
     int rightNode=2*i+2;
     if(leftNode<n&&arr[leftNode]>arr[largest]){
        largest=leftNode;
     }
     if(rightNode<n&&arr[rightNode]>arr[largest]){
      largest=rightNode;
    }
    if(i!=largest){
        int swap=arr[i];
        arr[i]=arr[largest];
        arr[largest]=swap;
        heapSort(arr, n, largest);
    }
    
}
}
