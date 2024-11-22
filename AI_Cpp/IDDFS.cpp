#include <iostream>
#include <vector>
#include <unordered_map>
#include <list>

using namespace std;

class Graph {
private:
    unordered_map<int, list<int>> adjList;
public:
    Graph(int vertices) {
        for (int i = 0; i < vertices; i++) {
            adjList[i] = list<int>();
        }
    }

    void addEdge(int source, int destination) {
        adjList[source].push_back(destination); 
    }

    void iddfs(int startVertex, int targetVertex, int maxDepth) {

        for (int depth = 0; depth <= maxDepth; ++depth) {
       
            cout << "Searching at depth " << depth << ":\n";
       
            vector<bool> visited(adjList.size(), false);
       
            if (iddfsUtil(startVertex, targetVertex, depth, visited)) {
       
                cout << "Target found at depth " << depth << endl;
                return; 
            }
        }
        cout << "Target not found within depth " << maxDepth << endl;
    }

private:
    bool iddfsUtil(int vertex, int targetVertex, int depth, vector<bool>& visited) {
        if (depth == 0) {
            if (vertex == targetVertex) {
                cout << vertex << " ";
                return true; 
            }
            return false;
        }
        visited[vertex] = true;
        bool found = false;

        for (int neighbor : adjList[vertex]) {
            if (!visited[neighbor]) {
                cout << vertex << " ";
                found = iddfsUtil(neighbor, targetVertex, depth - 1, visited);
                if (found) break; 
            }
        }
        return found;
    }
};

int main() {
    int vertices, edges;
    cout << "Enter the number of vertices: ";
    cin >> vertices;

    Graph graph(vertices);
    
    cout << "Enter the number of edges: ";
    cin >> edges;

    cout << "Enter the edges (source destination):" << endl;
    for (int i = 0; i < edges; i++) {
        
        int source, destination;
        
        cin >> source >> destination;
        
        graph.addEdge(source, destination);
    }

    int startVertex, targetVertex, maxDepth;
    cout << "Enter the starting vertex for IDDFS: ";
    cin >> startVertex;

    cout << "Enter the target vertex for IDDFS: ";
    cin >> targetVertex;

    cout << "Enter the maximum depth for IDDFS: ";
    cin >> maxDepth;

    graph.iddfs(startVertex, targetVertex, maxDepth);

    return 0;
}
