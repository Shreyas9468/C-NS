package assignment1;

public class VigenereCipher {
//    public static String encrypt(String text, String key) {
//        StringBuilder result = new StringBuilder();
//        key = key.toLowerCase();
//        for (int i = 0, j = 0; i < text.length(); i++) {
//            char ch = text.charAt(i);
//            if (Character.isLetter(ch)) {
//                char shift = key.charAt(j % key.length());
//                char encryptedChar = (char)((ch + shift - 2 * 'a') % 26 + 'a');
//                result.append(encryptedChar);
//                j++;
//            } else {
//                result.append(ch);
//            }
//        }
//        return result.toString();
//    }
//
//    public static String decrypt(String text, String key) {
//        StringBuilder result = new StringBuilder();
//        key = key.toLowerCase();
//        for (int i = 0, j = 0; i < text.length(); i++) {
//            char ch = text.charAt(i);
//            if (Character.isLetter(ch)) {
//                char shift = key.charAt(j % key.length());
//                char decryptedChar = (char)((ch - shift + 26) % 26 + 'a');
//                result.append(decryptedChar);
//                j++;
//            } else {
//                result.append(ch);
//            }
//        }
//        return result.toString();
//    }

    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toLowerCase(); // Ensure key is in lowercase
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                // Normalize character to lowercase for processing
                char lowerC = Character.toLowerCase(c);
                int shift = key.charAt(j % key.length()) - 'a'; // Use modulo to cycle through key
                int ascii = (lowerC + shift - 'a') % 26 + 'a'; // Encrypt
                // Maintain original case
                result.append(Character.isUpperCase(c) ? Character.toUpperCase((char) ascii) : (char) ascii);
                j++; // Increment j only if we processed a letter
            } else {
                result.append(c); // Non-letter characters are added unchanged
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toLowerCase(); // Ensure key is in lowercase
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                // Normalize character to lowercase for processing
                char lowerC = Character.toLowerCase(c);
                int shift = key.charAt(j % key.length()) - 'a'; // Use modulo to cycle through key
                int ascii = ((lowerC - shift - 'a' + 26) % 26 + 'a'); // Decrypt
                // Maintain original case
                result.append(Character.isUpperCase(c) ? Character.toUpperCase((char) ascii) : (char) ascii);
                j++; // Increment j only if we processed a letter
            } else {
                result.append(c); // Non-letter characters are added unchanged
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String text = "GIVE";
        String key = "LOCK";

        String encryptedText = VigenereCipher.encrypt(text, key);
        System.out.println("Encrypted Text: " + encryptedText); // Should print RWXO

        String decryptedText = VigenereCipher.decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText); // Should print GIVE
    }
}
