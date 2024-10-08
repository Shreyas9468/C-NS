package assignment1;

public class HillCipher {
    private static int[][] keyMatrix;
    private static int[] textVector;

    public static void setKeyMatrix(String key) {
        keyMatrix = new int[2][2];
        int k = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                keyMatrix[i][j] = (key.charAt(k) - 'a') % 26;
                k++;
            }
        }
    }

    public static void setTextVector(String text) {
        textVector = new int[2];
        for (int i = 0; i < 2; i++) {
            textVector[i] = (text.charAt(i) - 'a') % 26;
        }
    }

    public static String encrypt(String text, String key) {
        setKeyMatrix(key);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            setTextVector(text.substring(i, i + 2));
            int[] encryptedVector = new int[2];
            for (int row = 0; row < 2; row++) {
                encryptedVector[row] = 0;
                for (int col = 0; col < 2; col++) {
                    encryptedVector[row] += keyMatrix[row][col] * textVector[col];
                }
                encryptedVector[row] = encryptedVector[row] % 26;
                result.append((char)(encryptedVector[row] + 'a'));
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, String key) {
        // Hill cipher decryption requires the inverse of the key matrix, which
        // is more complex to implement and would require matrix algebra.
        return "Decryption requires inverse of key matrix";
    }
    public static void main(String[] args) {
        String text = "hill";
        String key = "gybn";

        String encryptedText = HillCipher.encrypt(text, key);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = HillCipher.decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}

