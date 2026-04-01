package main.java.io.github.ashwithpoojary98.recursion;

public class Subsequence {


    public static  void main(String[] args){
      checkSubString("abc", 0, "");
    }


    public static void checkSubString(String value,int index,String currentString){
        if(index==value.length()){
            System.out.println(currentString+" "+value+" "+index);
            return;
        }
        checkSubString(value, index+1, currentString+value.charAt(index));
        checkSubString(value, index+1, currentString);
    }
    
}
