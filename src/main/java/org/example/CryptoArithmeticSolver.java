package org.example;

import java.util.HashMap;

public class CryptoArithmeticSolver {
    public static int getWordValue(String word, HashMap<Character, Integer> letterToDigitMap) {
        int value = 0;
        for (char c : word.toCharArray()) {
            value = value * 10 + letterToDigitMap.get(c);
        }
        return value;
    }
    public static boolean isSolution(int[] digits) {
        HashMap<Character, Integer> letterToDigitMap = new HashMap<>();

        letterToDigitMap.put('L', digits[0]);
        letterToDigitMap.put('E', digits[1]);
        letterToDigitMap.put('T', digits[2]);
        letterToDigitMap.put('S', digits[3]);
        letterToDigitMap.put('W', digits[4]);
        letterToDigitMap.put('A', digits[5]);
        letterToDigitMap.put('V', digits[6]);
        letterToDigitMap.put('R', digits[7]);

        int LETS = getWordValue("LETS", letterToDigitMap);
        int WAVE = getWordValue("WAVE", letterToDigitMap);
        int LATER = getWordValue("LATER", letterToDigitMap);

        return LETS + WAVE == LATER;
    }
    public static void permute(int[] digits, int index) {
        if (index == digits.length) {
            if (isSolution(digits) && digits[0]!=0) {
                System.out.println("Solution found:");
                System.out.println("L = " + digits[0]);
                System.out.println("E = " + digits[1]);
                System.out.println("T = " + digits[2]);
                System.out.println("S = " + digits[3]);
                System.out.println("W = " + digits[4]);
                System.out.println("A = " + digits[5]);
                System.out.println("V = " + digits[6]);
                System.out.println("R = " + digits[7]);
            }
            return;
        }
        for (int i = index; i < digits.length; i++) {
            swap(digits, index, i);
            permute(digits, index + 1);
            swap(digits, index, i);
        }
    }
    public static void swap(int[] digits, int i, int j) {
        int temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;
    }

    public static void solve() {
        int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        permute(digits, 0);
    }

    public static void main(String[] args) {
        solve();
    }
}