package main.java.io.github.ashwithpoojary98.dfs;

import java.util.LinkedList;
import java.util.List;
public class DFSTest {
    


    public static void addEdges(List<List<Integer>> graph,int source, int dest){
        graph.get(source).add(dest);
        graph.get(dest).add(source);
    }



    public static void main(String[] args){

        List<List<Integer>> graph=new LinkedList<>();
        int v=5;
        for(int i=0;i<v;i++){
            graph.add(new LinkedList<>());
        }
        addEdges(graph, 0, 2);
        addEdges(graph, 0, 4);

        addEdges(graph, 1, 2);
        addEdges(graph, 1, 3);
        addEdges(graph, 1, 4);

        addEdges(graph, 2, 3);

        addEdges(graph, 3, 4);

        System.out.println(graph);

        dfs(graph, new boolean[v], 1);

    }


    public static void dfs(List<List<Integer>> graph,boolean[] isVisited, int source){
        isVisited[source]=true;
         System.out.print(source+" ");
          List<Integer> nextNodes=graph.get(source);
          for(int n:nextNodes){
            if(!isVisited[n]){
          dfs(graph, isVisited, n);
          }
        }
        }


    }
