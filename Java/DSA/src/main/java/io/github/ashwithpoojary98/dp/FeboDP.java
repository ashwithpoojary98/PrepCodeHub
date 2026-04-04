package main.java.io.github.ashwithpoojary98.dp;

public class FeboDP {
    

    public static void main(String[] args){

    System.out.println(febByDp(8888));

    }


    public static int febByDp(int n){
         int[] dp=new int[n+1];
        dp[0]=1;
        dp[1]=1;

        for(int i=2;i<=n;i++){
            dp[i]=dp[i-2]+dp[i-1];
    
        }
        return dp[n];
    }
}
