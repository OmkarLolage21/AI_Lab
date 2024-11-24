package org.example;

import java.util.*;

public class IDDFS {
    static class Graph {
        private final int vertices;
        private final List<List<Integer>> adj;

        public Graph(int vertices) {
            this.vertices = vertices;
            adj = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adj.add(new ArrayList<>());
            }
        }

        public void addEdge(int src, int dest) {
            adj.get(src).add(dest);
        }
        public List<Integer> getAdj(int vertex) {
            return adj.get(vertex);
        }
    }

    public static List<Integer> iddfs(Graph graph, int src, int dest, int maxDepth) {
        for (int depth = 0; depth <= maxDepth; depth++) {
            List<Integer> path = new ArrayList<>();
            if (dls(graph, src, dest, depth, path)) {
                return path;
            }
        }
        return null;
    }

    private static boolean dls(Graph graph, int src, int dest, int depth, List<Integer> path) {
        path.add(src);
        if (depth == 0 && src == dest) {
            return true;
        }
        if (depth > 0) {
            for (int neighbor : graph.getAdj(src)) {
                if (dls(graph, neighbor, dest, depth - 1, path)) {
                    return true;
                }
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of vertices:");
        int vertices = scanner.nextInt();
        Graph graph = new Graph(vertices);
        System.out.println("Enter the number of edges:");
        int edges = scanner.nextInt();
        System.out.println("Enter the edges (source and destination pairs):");
        for (int i = 0; i < edges; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            graph.addEdge(src, dest);
        }
        System.out.println("Enter the source node:");
        int src = scanner.nextInt();
        System.out.println("Enter the destination node:");
        int dest = scanner.nextInt();
        System.out.println("Enter the maximum depth:");
        int maxDepth = scanner.nextInt();
        List<Integer> path = iddfs(graph, src, dest, maxDepth);
        if (path != null) {
            System.out.println("Path exists: " + path);
        } else {
            System.out.println("No path exists between " + src + " and " + dest);
        }
        scanner.close();
    }
}

