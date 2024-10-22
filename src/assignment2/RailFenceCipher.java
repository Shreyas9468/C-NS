package assignment2;

public class RailFenceCipher {
    public static String encrypt(String text, int key) {
        char[] result = new char[text.length()];
        int index = 0;

        for (int i = 0; i < key; i++) {
            int step = (key - i - 1) * 2;
            for (int j = i; j < text.length(); j += (key - 1) * 2) {
                result[index++] = text.charAt(j);
                if (step > 0 && step < (key - 1) * 2 && j + step < text.length()) {
                    result[index++] = text.charAt(j + step);
                }
            }
        }
        return new String(result);
    }

    public static String decrypt(String text, int key) {
        char[] result = new char[text.length()];
        int index = 0;

        for (int i = 0; i < key; i++) {
            int step = (key - i - 1) * 2;
            for (int j = i; j < text.length(); j += (key - 1) * 2) {
                result[j] = text.charAt(index++);
                if (step > 0 && step < (key - 1) * 2 && j + step < text.length()) {
                    result[j + step] = text.charAt(index++);
                }
            }
        }
        return new String(result);
    }
    public static void main(String[] args) {
        String text = "HELLO";
        int key = 2;

        String encryptedText = RailFenceCipher.encrypt(text, key);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = RailFenceCipher.decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
