    package assignment1;

    public class CaesarCipher {
        public static String encrypt(String text, int shift) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                if (Character.isUpperCase(ch)) {
                    char newChar = (char)(((ch - 'A' + shift) % 26) + 'A');
                    result.append(newChar);
                } else if (Character.isLowerCase(ch)) {
                    char newChar = (char)(((ch - 'a' + shift) % 26) + 'a');
                    result.append(newChar);
                } else {
                    result.append(ch);
                }
            }
            return result.toString();
        }

        public static String decrypt(String text, int shift) {
            return encrypt(text, 26 - shift);
        }
        public static void main(String[] args) {
            String text = "HELLO";
            int shift = 3;

            String encryptedText = CaesarCipher.encrypt(text, shift);
            System.out.println("Encrypted Text: " + encryptedText);

            String decryptedText = CaesarCipher.decrypt(encryptedText, shift);
            System.out.println("Decrypted Text: " + decryptedText);
        }
    }
