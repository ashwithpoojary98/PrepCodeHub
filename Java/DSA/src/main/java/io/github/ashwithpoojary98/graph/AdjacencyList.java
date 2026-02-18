package main.java.io.github.ashwithpoojary98.graph;

import java.util.List;
import java.util.LinkedList;
public class AdjacencyList {

    

    public static void addEdage(List<List<Integer>> listMatrix,int u, int v){
        listMatrix.get(u).add(v);
        listMatrix.get(v).add(u);

    }

    public static void main(String[] args){
     List<List<Integer>> listMatrix=new LinkedList<>();
     int vertexCount=5;
     for(int i=0;i<vertexCount;i++){
        listMatrix.add(new LinkedList<>());
     }
     addEdage(listMatrix, 1, 2);
     addEdage(listMatrix, 0, 1);
     addEdage(listMatrix, 0, 4);
     addEdage(listMatrix, 1, 3);
     addEdage(listMatrix, 4, 3);
     addEdage(listMatrix, 2, 3);

     System.out.println(listMatrix);


    }
}