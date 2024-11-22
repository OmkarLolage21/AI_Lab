#include <iostream>
#include <vector>
#include <queue>
#include <unordered_set>
#include <cmath>
#include <algorithm>

using namespace std;

struct Node {
    int x, y;
    int gCost, hCost, fCost;
    Node* parent;

    Node(int x, int y) : x(x), y(y), gCost(0), hCost(0), fCost(0), parent(nullptr) {}

    bool operator==(const Node& other) const {
        return x == other.x && y == other.y;
    }
};

struct CompareNode {
    bool operator()(const Node* a, const Node* b) const {
        return a->fCost > b->fCost;
    }
};

struct HashNode {
    size_t operator()(const Node& node) const {
        return node.x * 31 + node.y;
    }
};

const int DIRECTIONS[8][2] = {
    {0, 1}, {1, 0}, {0, -1}, {-1, 0}, 
    {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
};

int heuristic(const Node& node, const Node& end) {
    return abs(node.x - end.x) + abs(node.y - end.y);
}

bool isValid(int x, int y, const vector<vector<int>>& grid) {
    return x >= 0 && x < grid.size() && y >= 0 && y < grid[0].size();
}

bool isObstacle(int x, int y, const vector<vector<int>>& grid) {
    return grid[x][y] == 1;
}

vector<Node> reconstructPath(Node* current) {
    vector<Node> path;
    while (current != nullptr) {
        path.push_back(*current);
        current = current->parent;
    }
    reverse(path.begin(), path.end());
    return path;
}

vector<Node> findPath(vector<vector<int>>& grid, Node start, Node end) {
    priority_queue<Node*, vector<Node*>, CompareNode> openList;
    unordered_set<Node, HashNode> closedList;
    unordered_set<Node, HashNode> openSet;

    start.gCost = 0;
    start.hCost = heuristic(start, end);
    start.fCost = start.gCost + start.hCost;
    openList.push(&start);
    openSet.insert(start);

    while (!openList.empty()) {
        Node* current = openList.top();
        openList.pop();
        openSet.erase(*current);
        closedList.insert(*current);

        if (*current == end) {
            return reconstructPath(current);
        }

        for (const auto& direction : DIRECTIONS) {
            int newX = current->x + direction[0];
            int newY = current->y + direction[1];

            if (isValid(newX, newY, grid) && !isObstacle(newX, newY, grid)) {
                Node neighbor(newX, newY);
                if (closedList.count(neighbor)) continue;

                int tentativeGCost = current->gCost + 1;

                if (openSet.count(neighbor) == 0 || tentativeGCost < neighbor.gCost) {
                    neighbor.gCost = tentativeGCost;
                    neighbor.hCost = heuristic(neighbor, end);
                    neighbor.fCost = neighbor.gCost + neighbor.hCost;
                    neighbor.parent = current;

                    if (openSet.count(neighbor) == 0) {
                        openList.push(new Node(neighbor));
                        openSet.insert(neighbor);
                    }
                }
            }
        }
    }
    return {};
}

void displayGrid(const vector<vector<int>>& grid, const Node& start, const Node& end) {
    for (int i = 0; i < grid.size(); i++) {
        for (int j = 0; j < grid[0].size(); j++) {
            if (i == start.x && j == start.y) {
                cout << "S ";
            } else if (i == end.x && j == end.y) {
                cout << "D ";
            } else if (grid[i][j] == 1) {
                cout << "X ";
            } else {
                cout << ". ";
            }
        }
        cout << endl;
    }
}

int main() {
    int rows, cols;
    cout << "Enter the number of rows and columns in the grid: ";
    cin >> rows >> cols;

    vector<vector<int>> grid(rows, vector<int>(cols, 0));

    int startX, startY, endX, endY;
    cout << "Enter the start point (x, y): ";
    cin >> startX >> startY;
    cout << "Enter the end point (x, y): ";
    cin >> endX >> endY;

    Node start(startX, startY);
    Node end(endX, endY);

    int numObstacles;
    cout << "Enter the number of obstacles: ";
    cin >> numObstacles;

    cout << "Enter the obstacle positions (x, y):" << endl;
    for (int i = 0; i < numObstacles; i++) {
        int x, y;
        cin >> x >> y;
        grid[x][y] = 1;
    }

    cout << "Initial Grid:" << endl;
    displayGrid(grid, start, end);

    vector<Node> path = findPath(grid, start, end);
    if (path.empty()) {
        cout << "No path found." << endl;
    } else {
        cout << "Path found:" << endl;
        for (const auto& node : path) {
            cout << "(" << node.x << ", " << node.y << ")" << endl;
        }
    }

    return 0;
}
