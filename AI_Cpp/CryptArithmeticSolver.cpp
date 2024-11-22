#include <iostream>
#include <unordered_map>
#include <vector>
#include <string>

using namespace std;

// Function to calculate the numeric value of a word based on the letter-to-digit mapping
int getWordValue(const string& word, const unordered_map<char, int>& letterToDigitMap) {
    int value = 0;
    for (char c : word) {
        value = value * 10 + letterToDigitMap.at(c);
    }
    return value;
}

// Function to check if a given permutation of digits satisfies the cryptoarithmetic equation
bool isSolution(const vector<int>& digits) {
    unordered_map<char, int> letterToDigitMap;

    // Map letters to corresponding digits
    letterToDigitMap['B'] = digits[0];
    letterToDigitMap['A'] = digits[1];
    letterToDigitMap['S'] = digits[2];
    letterToDigitMap['I'] = digits[3];
    letterToDigitMap['C'] = digits[4];
    letterToDigitMap['L'] = digits[5];
    letterToDigitMap['O'] = digits[6];
    letterToDigitMap['G'] = digits[7];
    letterToDigitMap['P'] = digits[8];

    // Get numeric values of the words
    int BASIC = getWordValue("BASIC", letterToDigitMap);
    int LOGIC = getWordValue("LOGIC", letterToDigitMap);
    int PASCAL = getWordValue("PASCAL", letterToDigitMap);

    // Check if the equation LETS + WAVE = LATER is satisfied
    return BASIC + LOGIC == PASCAL;
}

// Recursive function to generate all permutations of digits and check for a solution
void permute(vector<int>& digits, int index) {
    if (index == digits.size()) {
        if (isSolution(digits) && digits[0] != 0) { // Ensure no leading zero for 'L'
            cout << "Solution found:" << endl;
            // cout << "L = " << digits[0] << endl;
            // cout << "E = " << digits[1] << endl;
            // cout << "T = " << digits[2] << endl;
            // cout << "S = " << digits[3] << endl;
            // cout << "W = " << digits[4] << endl;
            // cout << "A = " << digits[5] << endl;
            // cout << "V = " << digits[6] << endl;
            // cout << "R = " << digits[7] << endl;
            cout << "B = " << digits[0] << endl;
            cout << "A = " << digits[1] << endl;
            cout << "S = " << digits[2] << endl;
            cout << "I = " << digits[3] << endl;
            cout << "C = " << digits[4] << endl;
            cout << "L = " << digits[5] << endl;
            cout << "O = " << digits[6] << endl;
            cout << "G = " << digits[7] << endl;
            cout << "P = " << digits[8] << endl;
        }
        return;
    }

    // Generate permutations
    for (int i = index; i < digits.size(); i++) {
        swap(digits[index], digits[i]);
        permute(digits, index + 1);
        swap(digits[index], digits[i]);
    }
}

// Driver function to initialize the digits and start solving
void solve() {
    vector<int> digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    permute(digits, 0);
}

int main() {
    solve();
    return 0;
}
