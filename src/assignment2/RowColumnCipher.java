package assignment2;

import java.util.Arrays;

public class RowColumnCipher {

    // Method to encrypt the given text using a row-column cipher with a specified key
    public static String encrypt(String text, int[] key) {
        // Calculate the number of rows needed based on text length and key length
        int numRows = (int) Math.ceil((double) text.length() / key.length);
        char[][] grid = new char[numRows][key.length];

        // Fill the grid with characters from the text
        for (int i = 0, k = 0; i < numRows; i++) {
            for (int j = 0; j < key.length && k < text.length(); j++) {
                grid[i][j] = text.charAt(k++);
            }
        }

        // Create a StringBuilder to store the encrypted text
        StringBuilder encryptedText = new StringBuilder();

        // Iterate over the key to determine the order of columns for encryption
        for (int col : key) {
            for (int i = 0; i < numRows; i++) {
                if (col < grid[i].length) { // Check if column index is within bounds
                    encryptedText.append(grid[i][col]);
                }
            }
        }

        return encryptedText.toString(); // Return the encrypted text
    }

    // Method to decrypt the given text using a row-column cipher with a specified key
    public static String decrypt(String text, int[] key) {
        int numRows = (int) Math.ceil((double) text.length() / key.length);
        char[][] grid = new char[numRows][key.length];

        // Fill the grid with placeholders initially
        for (int i = 0; i < numRows; i++) {
            Arrays.fill(grid[i], '\0'); // Use '\0' as a placeholder
        }

        // Fill in the grid according to the order of columns defined by the key
        int k = 0;
        for (int col : key) {
            for (int i = 0; i < numRows && k < text.length(); i++) {
                grid[i][col] = text.charAt(k++);
            }
        }

        // Create a StringBuilder to store the decrypted text
        StringBuilder decryptedText = new StringBuilder();

        // Read characters from the grid row by row
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < key.length; j++) {
                if (grid[i][j] != '\0') { // Check if it's not a placeholder
                    decryptedText.append(grid[i][j]);
                }
            }
        }

        return decryptedText.toString(); // Return decrypted text
    }

    public static void main(String[] args) {
        String text = "HELLOWORLD"; // The input string to be encrypted
        int[] key = { 2, 0, 3, 1 }; // Example key

        // Encrypt the input text and print it
        String encryptedText = RowColumnCipher.encrypt(text, key);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt the previously encrypted text and print it
        String decryptedText = RowColumnCipher.decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}