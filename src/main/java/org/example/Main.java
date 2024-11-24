package org.example;
import java.util.Scanner;

public class Main {
    static char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    static char player = 'X', ai = 'O';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printBoard();

        while (true) {
            playerMove(scanner);
            if (isGameOver()) {
                break;
            }
            aiMove();
            if (isGameOver()) {
                break;
            }
        }
    }
// X 0 X
// 0 X 0
//
    private static void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < 9; i += 3) {
            System.out.println("| " + board[i] + " " + board[i + 1] + " " + board[i + 2] + " |");
        }
        System.out.println("---------");
    }

    private static void playerMove(Scanner scanner) {
        int move;
        while (true) {
            System.out.print("Enter your move (1-9): ");
            move = scanner.nextInt() - 1;
            if (move >= 0 && move < 9 && board[move] == ' ') {
                board[move] = player;
                break;
            }
            System.out.println("Invalid move, try again.");
        }
        printBoard();
    }

    private static boolean isGameOver() {
        if (hasWon(player)) {
            System.out.println("Player wins!");
            return true;
        }
        if (hasWon(ai)) {
            System.out.println("AI wins!");
            return true;
        }
        if (isBoardFull()) {
            System.out.println("It's a tie!");
            return true;
        }
        return false;
    }
// 0 1 2
// 3 4 5
// 6 7 8
    private static boolean hasWon(char currentPlayer) {
        return (board[0] == currentPlayer && board[1] == currentPlayer && board[2] == currentPlayer) ||
                (board[3] == currentPlayer && board[4] == currentPlayer && board[5] == currentPlayer) ||
                (board[6] == currentPlayer && board[7] == currentPlayer && board[8] == currentPlayer) ||
                (board[0] == currentPlayer && board[3] == currentPlayer && board[6] == currentPlayer) ||
                (board[1] == currentPlayer && board[4] == currentPlayer && board[7] == currentPlayer) ||
                (board[2] == currentPlayer && board[5] == currentPlayer && board[8] == currentPlayer) ||
                (board[0] == currentPlayer && board[4] == currentPlayer && board[8] == currentPlayer) ||
                (board[2] == currentPlayer && board[4] == currentPlayer && board[6] == currentPlayer);
    }
    private static void aiMove() {
        int bestScore = Integer.MIN_VALUE;
        int move = -1;

        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = ai;
                int score = minimax(board, false);
                board[i] = ' ';
                if (score > bestScore) {
                    bestScore = score;
                    move = i;
                }
            }
        }
        board[move] = ai;
        System.out.println("AI chose position " + (move + 1));
        printBoard();
    }
    private static boolean isBoardFull() {
        for (char c : board) {
            if (c == ' ') {
                return false;
            }
        }
        return true;
    }
    private static int minimax(char[] board, boolean isMaximizing) {

        if (hasWon(ai)) return 1;
        if (hasWon(player)) return -1;
        if (isBoardFull()) return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = isMaximizing ? ai : player;
                int score = minimax(board, !isMaximizing);
                board[i] = ' ';
                bestScore = isMaximizing ? Math.max(score, bestScore) : Math.min(score, bestScore);
            }
        }
        return bestScore;
    }
}
