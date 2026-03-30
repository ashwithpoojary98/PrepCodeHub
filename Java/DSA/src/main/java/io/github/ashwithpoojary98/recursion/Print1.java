package main.java.io.github.ashwithpoojary98.recursion;

public class Print1 {
    

    public static void main(String[] args){
    
   printRecu(6, 0);

    }


    public static void printRecu(int n,int start){
        if(n==start) return;

        System.out.println(start);
        printRecu(n, start+1);
       

    }
}
