package assignment1;

public class PlayfairCipher {
    private static char[][] matrix = new char[5][5];

    public static void generateKeyMatrix(String key) {
        boolean[] used = new boolean[26];
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
            if (ch == 'j') continue;
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

    private static String[] formDigraphs(String text) {
        text = text.replaceAll("j", "i").toLowerCase().replaceAll("[^a-z]", "");
        if (text.length() % 2 != 0) text += 'x';
        String[] digraphs = new String[text.length() / 2];
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i), b = text.charAt(i + 1);
            if (a == b) b = 'x';
            digraphs[i / 2] = "" + a + b;
        }
        return digraphs;
    }

    private static String processDigraphs(String[] digraphs, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        for (String digraph : digraphs) {
            char a = digraph.charAt(0), b = digraph.charAt(1);
            int[] posA = findPosition(a), posB = findPosition(b);
            if (posA[0] == posB[0]) {
                result.append(matrix[posA[0]][(posA[1] + (encrypt ? 1 : 4)) % 5]);
                result.append(matrix[posB[0]][(posB[1] + (encrypt ? 1 : 4)) % 5]);
            } else if (posA[1] == posB[1]) {
                result.append(matrix[(posA[0] + (encrypt ? 1 : 4)) % 5][posA[1]]);
                result.append(matrix[(posB[0] + (encrypt ? 1 : 4)) % 5][posB[1]]);
            } else {
                result.append(matrix[posA[0]][posB[1]]);
                result.append(matrix[posB[0]][posA[1]]);
            }
        }
        return result.toString();
    }

    private static int[] findPosition(char ch) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == ch) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static String encrypt(String text, String key) {
        generateKeyMatrix(key);
        String[] digraphs = formDigraphs(text);
        return processDigraphs(digraphs, true);
    }

    public static String decrypt(String text, String key) {
        generateKeyMatrix(key);
        String[] digraphs = formDigraphs(text);
        return processDigraphs(digraphs, false);
    }
    public static void main(String[] args) {
        String text = "HELLO";
        String key = "KEYWORD";

        String encryptedText = PlayfairCipher.encrypt(text, key);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = PlayfairCipher.decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
