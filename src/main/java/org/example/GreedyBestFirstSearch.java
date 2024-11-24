package org.example;

import java.util.*;

class GreedyBestFirstSearch {
    static class Node implements Comparable<Node> {
        String name;
        int heuristic;

        Node(String name, int heuristic) {
            this.name = name;
            this.heuristic = heuristic;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.heuristic, other.heuristic);
        }
    }
    static Map<String, List<String>> graph = new HashMap<>();
    static Map<String, Integer> heuristics = new HashMap<>();
    public static void greedyBestFirstSearch(String start, String goal) {
        PriorityQueue<Node> openList = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();
        openList.add(new Node(start, heuristics.get(start)));

        System.out.println("Exploring path: ");
        while (!openList.isEmpty()) {
            Node current = openList.poll();
            if (visited.contains(current.name)) {
                continue;
            }

            System.out.println(current.name);
            visited.add(current.name);

            if (current.name.equals(goal)) {
                System.out.println("Goal reached: " + goal);
                return;
            }

            for (String neighbor : graph.getOrDefault(current.name, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    openList.add(new Node(neighbor, heuristics.get(neighbor)));
                }
            }
        }
        System.out.println("Goal not reachable.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of nodes: ");
        int nodes = scanner.nextInt();

        System.out.println("Enter the edges (format: from to), type 'done' to stop:");
        scanner.nextLine();
        while (true) {
            String edge = scanner.nextLine();
            if (edge.equalsIgnoreCase("done")) break;
            String[] parts = edge.split(" ");
            String from = parts[0];
            String to = parts[1];

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);
        }

        // Input heuristic values
        System.out.println("Enter heuristic values for each node (format: node value):");
        for (int i = 0; i < nodes; i++) {
            String[] parts = scanner.nextLine().split(" ");
            heuristics.put(parts[0], Integer.parseInt(parts[1]));
        }

        // Input start and goal nodes
        System.out.print("Enter the start node: ");
        String start = scanner.nextLine();
        System.out.print("Enter the goal node: ");
        String goal = scanner.nextLine();

        // Perform the search
        greedyBestFirstSearch(start, goal);

        scanner.close();
    }
}

