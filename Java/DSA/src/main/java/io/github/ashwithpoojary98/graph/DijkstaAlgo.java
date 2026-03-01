package main.java.io.github.ashwithpoojary98.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DijkstaAlgo {

    private static class EdgeWeight {
        int destination;
        int weight;

        EdgeWeight(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void addEdges(List<List<EdgeWeight>> listgraph, int source, int dest, int weight) {
        listgraph.get(source).add(new EdgeWeight(dest, weight));
        listgraph.get(dest).add(new EdgeWeight(source, weight));
    }

    public static int findNeartestMinimumVertex(int[] distance, boolean[] isVisited, int vertexCount) {
        int minVertex = -1;
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < vertexCount; i++) {

            if (!isVisited[i] && distance[i] < minDist) {
                minDist = distance[i];
                minVertex = i;
            }
        }
        return minVertex;
    }

    public static void findshortDist(List<List<EdgeWeight>> listGraphMatrix, int vertexCount, int source) {
        boolean[] isVisited = new boolean[vertexCount];
        int[] distance = new int[vertexCount];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;
        for (int i = 0; i < vertexCount - 1; i++) {
            int minDest = findNeartestMinimumVertex(distance, isVisited, vertexCount);
            if (minDest == -1) {
                break;
            }
            isVisited[minDest] = true;
            for (EdgeWeight e : listGraphMatrix.get(minDest)) {
                int dest = e.destination;
                int weight = e.weight;
                if (!isVisited[dest] && distance[minDest] != Integer.MAX_VALUE
                        && distance[minDest] + weight < distance[dest]) {
                    distance[dest] = distance[minDest] + weight;
                }
            }
        }
        System.out.println("Shortest distances from source " + source);
        for (int i = 0; i < vertexCount; i++) {
            System.out.println(source + "->" + i + " = " + distance[i]);
        }

    }

    public static void main(String[] args) {

        int vertices = 5;

        List<List<EdgeWeight>> graph = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        addEdges(graph, 0, 1, 4);
        addEdges(graph, 0, 2, 1);
        addEdges(graph, 2, 1, 2);
        addEdges(graph, 1, 3, 1);
        addEdges(graph, 2, 3, 5);
        addEdges(graph, 3, 4, 3);

        findshortDist(graph, vertices, 3);

    }

}
