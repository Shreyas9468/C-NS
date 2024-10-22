package assignment1;

import java.util.ArrayList;
import java.util.List;

public class PlayfairCipher {

    private static char[][] matrix = new char[5][5];

    // Generate the Playfair cipher key matrix
    public static void generateKeyMatrix(String key) {
        boolean[] used = new boolean[26]; // To track used characters
        int row = 0, col = 0;
        key = key.replaceAll("j", "i").toLowerCase();

        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (!used[ch - 'a']) {
                matrix[row][col] = ch;
                used[ch - 'a'] = true;
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }

        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (ch == 'j') continue; // Skip 'j' as it's combined with 'i'
            if (!used[ch - 'a']) {
                matrix[row][col] = ch;
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    // Form digraphs from the input text
    private static String[] formDigraphs(String text) {
        text = text.replaceAll("j", "i").toLowerCase().replaceAll("[^a-z]", ""); // Normalize text
        List<String> digraphs = new ArrayList<>();

        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = (i + 1 < text.length()) ? text.charAt(i + 1) : 'x'; // Add 'x' if there's a single character left

            if (a == b) {
                b = 'x'; // If both characters are the same, replace second with 'x'
                digraphs.add("" + a + b);
                i--; // Decrement i to consider the second character again
            } else {
                digraphs.add("" + a + b);
            }
        }
        return digraphs.toArray(new String[0]);
    }

    // Process digraphs for encryption or decryption
    private static String processDigraphs(String[] digraphs, boolean encrypt) {
        StringBuilder result = new StringBuilder();

        for (String digraph : digraphs) {
            char a = digraph.charAt(0), b = digraph.charAt(1);
            int[] posA = findPosition(a), posB = findPosition(b);

            // Same row
            if (posA[0] == posB[0]) {
                result.append(matrix[posA[0]][(posA[1] + (encrypt ? 1 : 4)) % 5]);
                result.append(matrix[posB[0]][(posB[1] + (encrypt ? 1 : 4)) % 5]);
            }
            // Same column
            else if (posA[1] == posB[1]) {
                result.append(matrix[(posA[0] + (encrypt ? 1 : 4)) % 5][posA[1]]);
                result.append(matrix[(posB[0] + (encrypt ? 1 : 4)) % 5][posB[1]]);
            }
            // Rectangle (different row and column)
            else {
                result.append(matrix[posA[0]][posB[1]]);
                result.append(matrix[posB[0]][posA[1]]);
            }
        }
        return result.toString();
    }

    // Find the position of a character in the matrix
    private static int[] findPosition(char ch) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == ch) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // Should never happen
    }

    // Encrypt the input text using Playfair Cipher
    public static String encrypt(String text, String key) {
        generateKeyMatrix(key);
        String[] digraphs = formDigraphs(text);
        return processDigraphs(digraphs, true);
    }

    // Decrypt the encrypted text using Playfair Cipher
    // Improved Decrypt method to remove padding 'x' between repeated letters
    public static String decrypt(String text, String key) {
        generateKeyMatrix(key);
        String[] digraphs = formDigraphs(text);
        String decryptedText = processDigraphs(digraphs, false);

        // Remove 'x' used as padding between repeated characters in the decrypted text
        StringBuilder cleanedText = new StringBuilder();
        for (int i = 0; i < decryptedText.length(); i++) {
            if (i > 0 && decryptedText.charAt(i) == 'x' && decryptedText.charAt(i - 1) == decryptedText.charAt(i + 1)) {
                continue; // Skip the 'x' if it's between repeated letters
            }
            cleanedText.append(decryptedText.charAt(i));
        }
        return cleanedText.toString();
    }


    // Main method to test the implementation
    public static void main(String[] args) {
        String text = "HELLO";
        String key = "KEYWORD";

        // Encrypt
        String encryptedText = PlayfairCipher.encrypt(text, key);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt
        String decryptedText = PlayfairCipher.decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
