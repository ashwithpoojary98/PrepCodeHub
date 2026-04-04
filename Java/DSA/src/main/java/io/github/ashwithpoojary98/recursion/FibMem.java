package main.java.io.github.ashwithpoojary98.recursion;

public class FibMem {

    public static void main(String[] args) {
        int n=5;
        int[] mem=new int[n+1];

        //n
        for(int i=0;i<=n;i++){
          mem[i]=-1;
        }
        System.out.println(fib(5, mem));

    }

    public static int fib(int n, int[] mem) {

        if (n == 0 || n == 1) {
            return 1;
        }
        if(mem[n]!=-1) return mem[n];

        mem[n] = fib(n - 1, mem) + fib(n - 2, mem);
        return mem[n];
    }

}
