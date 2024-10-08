package assignment2;

import java.util.Arrays;

public class RowColumnCipher {
    public static String encrypt(String text, int[] key) {
        int numRows = (int) Math.ceil((double) text.length() / key.length);
        char[][] grid = new char[numRows][key.length];

        for (int i = 0, k = 0; i < numRows; i++) {
            for (int j = 0; j < key.length && k < text.length(); j++) {
                grid[i][j] = text.charAt(k++);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int col : key) {
            for (int i = 0; i < numRows; i++) {
                if (grid[i][col] != '\0') {
                    result.append(grid[i][col]);
                }
            }
        }

        return result.toString();
    }

    public static String decrypt(String text, int[] key) {
        int numRows = (int) Math.ceil((double) text.length() / key.length);
        char[][] grid = new char[numRows][key.length];

        int[] inverseKey = new int[key.length];
        for (int i = 0; i < key.length; i++) {
            inverseKey[key[i]] = i;
        }

        for (int col = 0, k = 0; col < key.length; col++) {
            int actualCol = inverseKey[col];
            for (int i = 0; i < numRows && k < text.length(); i++) {
                grid[i][actualCol] = text.charAt(k++);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < key.length; j++) {
                if (grid[i][j] != '\0') {
                    result.append(grid[i][j]);
                }
            }
        }

        return result.toString();
    }
    public static void main(String[] args) {
        String text = "HELLOWORLD";
        int[] key = { 2, 0, 3, 1 }; // Example key

        String encryptedText = RowColumnCipher.encrypt(text, key);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = RowColumnCipher.decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}

