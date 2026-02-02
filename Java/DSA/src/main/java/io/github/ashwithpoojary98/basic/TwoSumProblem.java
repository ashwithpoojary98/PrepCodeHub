package main.java.io.github.ashwithpoojary98.basic;

import java.util.Map;
import java.util.HashMap;
public class TwoSumProblem {
    

    public static void main(String[] args){
        Map<Integer,Integer> sumChecker=new HashMap<>();
        int[] arr=new int[]{1,2,3,4,7};

        int target=5;
       
        for(int i=0;i<arr.length;i++){
            int complement=arr[i]-target;

            if(sumChecker.containsKey(complement)){
                System.out.println(String.format("Sum index is %d %d",sumChecker.get(complement),i));
                break;
            }
            sumChecker.put(arr[i], i);

        }
    }
    
}
