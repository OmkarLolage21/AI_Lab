package org.example;

import java.util.*;

public class DFS {
    private int numVertices;
    private LinkedList<Integer> adjacencyList[];

    DFS(int numVertices) {
        this.numVertices = numVertices;
        adjacencyList = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; ++i)
            adjacencyList[i] = new LinkedList();
    }

    void addEdge(int v, int w) {
        adjacencyList[v].add(w);
    }

    void DFSUtil(int v, boolean visited[]) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int n : adjacencyList[v]) {
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    void DFS(int v) {
        boolean visited[] = new boolean[numVertices];
        DFSUtil(v, visited);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();

        DFS g = new DFS(numVertices);

        System.out.print("Enter the number of edges: ");
        int numEdges = scanner.nextInt();

        System.out.println("Enter the edges (source destination): ");
        for (int i = 0; i < numEdges; i++) {
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            g.addEdge(v, w);
        }

        System.out.print("Enter the start node for DFS: ");
        int startNode = scanner.nextInt();

        System.out.println("Depth First Traversal starting from node " + startNode + ":");
        g.DFS(startNode);
    }
}

//0 1
//0 2
//1 3
//1 4
//2 5
//3 4
//4 5
//6
//7