package main.java.io.github.ashwithpoojary98.search;

public class BinarySearch {

public static void main(String[] args){


    int target=10;
    int[] arr=new int[]{1,3,3,5,6,7,10,13};

    int lowerBound=0;
    int higherBound=arr.length-1;
    boolean isFound=false;

    while(lowerBound<=higherBound){
        int mid=lowerBound+(higherBound-lowerBound)/2;

        if(arr[mid]==target){
            System.out.println("Element found at "+mid);
            isFound=true;
            break;
        }
        else if(arr[mid]>target){
            higherBound=mid-1;
        }
        else{
            lowerBound=mid+1;
        }
    }

    if(!isFound){
        System.out.println("Element not there");
    }

}
    
}



