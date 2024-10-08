package assignment1;

public class VigenereCipher {
    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toLowerCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                char shift = key.charAt(j % key.length());
                char encryptedChar = (char)((ch + shift - 2 * 'a') % 26 + 'a');
                result.append(encryptedChar);
                j++;
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toLowerCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                char shift = key.charAt(j % key.length());
                char decryptedChar = (char)((ch - shift + 26) % 26 + 'a');
                result.append(decryptedChar);
                j++;
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
    public static void main(String[] args) {
        String text = "HELLO";
        String key = "KEY";

        String encryptedText = VigenereCipher.encrypt(text.toLowerCase(), key);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = VigenereCipher.decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
