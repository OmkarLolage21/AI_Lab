#include <iostream>
#include <vector>
#include <queue>
#include <unordered_map>
#include <list>

using namespace std;

class GraphTraversal {
private:
    unordered_map<int, list<int>> adjList; 
    
public:
    GraphTraversal(int vertices) {
        for (int i = 0; i < vertices; i++) {
            adjList[i] = list<int>();
        }
    }

    void addEdge(int source, int destination) {
        adjList[source].push_back(destination); 
    }

    void bfs(int startVertex) {
        vector<bool> visited(adjList.size(), false);
        queue<int> q;

        visited[startVertex] = true;
        q.push(startVertex);

        cout << "BFS Traversal: ";
        while (!q.empty()) {
            int vertex = q.front();
            q.pop();
            cout << vertex << " ";

            for (int neighbor : adjList[vertex]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.push(neighbor);
                }
            }
        }
        cout << endl;
    }

    void dfs(int startVertex) {
        vector<bool> visited(adjList.size(), false);
        cout << "DFS Traversal: ";
        dfsUtil(startVertex, visited);
        cout << endl;
    }

private:
    void dfsUtil(int vertex, vector<bool>& visited) {
        visited[vertex] = true;
        cout << vertex << " ";

        for (int neighbor : adjList[vertex]) {
            if (!visited[neighbor]) {
                dfsUtil(neighbor, visited);
            }
        }
    }
};

int main() {
    int vertices, edges;
    cout << "Enter the number of vertices: ";
    cin >> vertices;

    GraphTraversal graph(vertices);
    
    cout << "Enter the number of edges: ";
    cin >> edges;

    cout << "Enter the edges (source destination):" << endl;
    for (int i = 0; i < edges; i++) {
        int source, destination;
        cin >> source >> destination;
        graph.addEdge(source, destination);
    }

    int startVertex;
    cout << "Enter the starting vertex for BFS and DFS: ";
    cin >> startVertex;

    graph.bfs(startVertex);
    graph.dfs(startVertex);

    return 0;
}
