package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class BFS1 {
    int vertices;
    LinkedList<Integer>[] adjList;

    BFS1(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i)
            adjList[i] = new LinkedList<>();
    }

    void addEdge(int u, int v) {
        adjList[u].add(v);
    }

    void bfs(int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertices];

        visited[startNode] = true;
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            System.out.print(currentNode + " ");

            for (int neighbor : adjList[currentNode]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}

public class BFS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();
        BFS1 graph = new BFS1(vertices);

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        System.out.println("Enter each edge in the format 'u v':");
        for (int i = 0; i < edges; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.addEdge(u, v);
        }

        System.out.print("Enter the starting vertex for BFS: ");
        int startNode = scanner.nextInt();

        System.out.print("Breadth First Traversal starting from vertex " + startNode + ": ");
        graph.bfs(startNode);

        scanner.close();
    }
}
