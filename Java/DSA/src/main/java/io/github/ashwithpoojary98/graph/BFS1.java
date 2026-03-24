package main.java.io.github.ashwithpoojary98.graph;

import java.util.LinkedList;
import java.util.Queue;

public class BFS1 {
    
    public static void addEdges(int[][] arr,int src,int dest){
        arr[src][dest]=1;
        //arr[dest][src]=1;
    } 

    public static void printNode(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(arr[i][j]==1&&i<j){
                     System.out.print(i + " <--> " + j + " ");
                }
            }
            System.out.println();
        }
    }


    public static void main(String[] args){


        int[][] matrix=new int[5][5];
        addEdges(matrix, 0, 1);
        addEdges(matrix, 0, 3);
        addEdges(matrix, 0, 4);
        addEdges(matrix, 1, 2);
        addEdges(matrix, 1, 4);
        addEdges(matrix, 2, 3);
        addEdges(matrix, 2, 4);
        addEdges(matrix, 3, 4);
        printNode(matrix);
        bfs(matrix, 0);
        bfs(matrix, 3);


    }


    public static void bfs(int[][] matrix,int source){
     boolean[] isVisited=new boolean[matrix.length];
     Queue<Integer> nextNode=new LinkedList<>();
     nextNode.add(source);
     isVisited[source] = true;
     while(!nextNode.isEmpty()){
      int currentNode=nextNode.poll();
      System.out.println(currentNode+" ");
      for(int i=0;i<matrix.length;i++){
        if(matrix[currentNode][i]==1&&!isVisited[i]){
            nextNode.add(i);
            isVisited[i]=true;
        }
      }
     }
     System.out.println("----------------");
    }
}
