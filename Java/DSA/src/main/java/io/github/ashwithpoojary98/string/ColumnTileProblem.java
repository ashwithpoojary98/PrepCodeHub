package main.java.io.github.ashwithpoojary98.string;

public class ColumnTileProblem {


    public static void main(String[] args){
        System.out.println(getColumnIndex("AA"));

    }

    public static int getColumnIndex(String ch){


        int sum=0;
        for(char c: ch.toCharArray()){
          
        int value=c-'A'+1;
          sum=sum*26+value;
        }
        return sum;

    }
    
}
