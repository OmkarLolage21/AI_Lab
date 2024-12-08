//AStar
package org.example;

import java.util.*;

class Node implements Comparable<Node> {
    int x, y;
    int gCost, hCost, fCost;
    Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.fCost, other.fCost);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class AStarAlgorithm {
    private static final int[][] DIRECTIONS = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    public static List<Node> findPath(int[][] grid, Node start, Node end) {
        PriorityQueue<Node> openList = new PriorityQueue<>();
        Set<Node> closedList = new HashSet<>();

        start.gCost = 0;
        start.hCost = heuristic(start, end);
        start.fCost = start.gCost + start.hCost;
        openList.add(start);

        while (!openList.isEmpty()) {
            Node current = openList.poll();
            closedList.add(current);

            if (current.equals(end)) {
                return reconstructPath(current);
            }

            for (int[] direction : DIRECTIONS) {
                int newX = current.x + direction[0];
                int newY = current.y + direction[1];

                if (isValid(grid, newX, newY) && !isObstacle(grid, newX, newY)) {
                    Node neighbor = new Node(newX, newY);
                    if (closedList.contains(neighbor)) continue;

                    int tentativeGCost = current.gCost + 1;
                    boolean inOpenList = openList.contains(neighbor);

                    if (!inOpenList || tentativeGCost < neighbor.gCost) {
                        neighbor.gCost = tentativeGCost;
                        neighbor.hCost = heuristic(neighbor, end);
                        neighbor.fCost = neighbor.gCost + neighbor.hCost;
                        neighbor.parent = current;

                        if (!inOpenList) {
                            openList.add(neighbor);
                        }
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    private static int heuristic(Node node, Node end) {
        return Math.abs(node.x - end.x) + Math.abs(node.y - end.y);
    }

    private static boolean isValid(int[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    private static boolean isObstacle(int[][] grid, int x, int y) {
        return grid[x][y] == 1;
    }

    private static List<Node> reconstructPath(Node current) {
        List<Node> path = new ArrayList<>();
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }

    public static void displayGrid(int[][] grid, Node start, Node end) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == start.x && j == start.y) {
                    System.out.print("S ");
                } else if (i == end.x && j == end.y) {
                    System.out.print("D ");
                } else if (grid[i][j] == 1) {
                    System.out.print("X ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows and columns in the grid (0-based indexing):");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int[][] grid = new int[rows][cols];

        System.out.println("Enter the start point (x, y) (0-based indexing):");
        Node start = new Node(scanner.nextInt(), scanner.nextInt());
        System.out.println("Enter the end point (x, y) (0-based indexing):");
        Node end = new Node(scanner.nextInt(), scanner.nextInt());

        System.out.println("Enter the number of obstacles:");
        int numObstacles = scanner.nextInt();
        System.out.println("Enter the obstacle positions (x, y) (0-based indexing):");
        for (int i = 0; i < numObstacles; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            grid[x][y] = 1;
        }

        System.out.println("Initial Grid:");
        displayGrid(grid, start, end);

        List<Node> path = findPath(grid, start, end);
        if (path.isEmpty()) {
            System.out.println("No path found.");
        } else {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.println("(" + node.x + ", " + node.y + ")");
            }
        }
    }
}
