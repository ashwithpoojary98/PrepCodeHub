package main.java.io.github.ashwithpoojary98.array;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SumArrayKValue {


    public static void main(String[] args){

        int[] arr={3,2,5,7,4,1,10,4,0,-1};
        List<MaxArray> maxArrays=new LinkedList<>();
        for(int i=0;i<arr.length;i++){
          maxArrays.add(new MaxArray(i, arr[i]));
        }
       Collections.sort(maxArrays, Collections.reverseOrder());
       int sum=0;
        for(int i=0;i<3;i++){
            sum+=maxArrays.get(i).data;
        }
        System.out.println(sum);

    }


    private static class MaxArray implements Comparable<MaxArray>{
        private int index;
        private int data;

        public MaxArray(int index, int data){
            this.index=index;
            this.data=data;
        }

        @Override
        public int compareTo(MaxArray maxArray) {
           return this.data-maxArray.data;
          
        }

        @Override
        public String toString(){
            return String.format("index=%s data=%s", index,data);
        }
    }
    
}
