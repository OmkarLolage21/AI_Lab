//BFSDFS
package org.example;

import java.util.*;

public class GraphTraversal {
    private Map<Integer, List<Integer>> adjList;

    public GraphTraversal(int vertices) {
        adjList = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            adjList.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination) {
        adjList.get(source).add(destination);  // Only add edge from source to destination
    }

    public void bfs(int startVertex) {
        boolean[] visited = new boolean[adjList.size()];
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        System.out.print("BFS Traversal: ");

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");

            for (int neighbor : adjList.get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void dfs(int startVertex) {
        boolean[] visited = new boolean[adjList.size()];
        System.out.print("DFS Traversal: ");
        dfsUtil(startVertex, visited);
        System.out.println();
    }

    private void dfsUtil(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        for (int neighbor : adjList.get(vertex)) {
            if (!visited[neighbor]) {
                dfsUtil(neighbor, visited);
            }
        }
    }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the number of vertices: ");
            int vertices = scanner.nextInt();

            GraphTraversal graph = new GraphTraversal(vertices);

            System.out.print("Enter the number of edges: ");
            int edges = scanner.nextInt();

            System.out.println("Enter the edges (source destination):");
            for (int i = 0; i < edges; i++) {
                int source = scanner.nextInt();
                int destination = scanner.nextInt();
                graph.addEdge(source, destination);
            }

            System.out.print("Enter the starting vertex for BFS and DFS: ");
            int startVertex = scanner.nextInt();

            graph.bfs(startVertex);
            graph.dfs(startVertex);
        }
}
//6 6

//0 1
//0 2
//1 3
//2 3
//3 4
//4 0