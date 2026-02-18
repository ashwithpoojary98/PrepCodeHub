package main.java.io.github.ashwithpoojary98.graph;

import java.util.Arrays;

public class AdjacencyMatrix {
    

    public static void addEdage(int[][] matrix,int v,int u){
       matrix[v][u]=1;
       matrix[u][v]=1;
    }

    public static void main(String[] absdd){
        int[][] matrix=new int[5][5];
        addEdage(matrix, 0, 1);
        addEdage(matrix,1,2);
        addEdage(matrix,2,3);
        addEdage(matrix, 2, 4);
        addEdage(matrix, 3, 4);

System.out.println(Arrays.deepToString(matrix));


    }
}
