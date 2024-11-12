package assignment2;// Java program to illustrate Rail Fence Cipher
// Encryption and Decryption
import java.util.Arrays;
class RailFenceCipher {

    // function to encrypt a message
    public static String encryptRailFence(String text,
                                          int key)
    {

        // create the matrix to cipher plain text
        // key = rows , length(text) = columns
        char[][] rail = new char[key][text.length()];

        // filling the rail matrix to distinguish filled
        // spaces from blank ones
        for (int i = 0; i < key; i++)
            Arrays.fill(rail[i], '\n');

        boolean dirDown = false;
        int row = 0, col = 0;

        for (int i = 0; i < text.length(); i++) {

            // check the direction of flow
            // reverse the direction if we've just
            // filled the top or bottom rail
            if (row == 0 || row == key - 1)
                dirDown = !dirDown;

            // fill the corresponding alphabet
            rail[row][col++] = text.charAt(i);

            // find the next row using direction flag
            if (dirDown)
                row++;
            else
                row--;
        }

        // now we can construct the cipher using the rail
        // matrix
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < key; i++)
            for (int j = 0; j < text.length(); j++)
                if (rail[i][j] != '\n')
                    result.append(rail[i][j]);

        return result.toString();
    }

    // This function receives cipher-text and key
    // and returns the original text after decryption
    public static String decryptRailFence(String cipher,
                                          int key)
    {

        // create the matrix to cipher plain text
        // key = rows , length(text) = columns
        char[][] rail = new char[key][cipher.length()];

        // filling the rail matrix to distinguish filled
        // spaces from blank ones
        for (int i = 0; i < key; i++)
            Arrays.fill(rail[i], '\n');

        // to find the direction
        boolean dirDown = true;

        int row = 0, col = 0;

        // mark the places with '*'
        for (int i = 0; i < cipher.length(); i++) {
            // check the direction of flow
            if (row == 0)
                dirDown = true;
            if (row == key - 1)
                dirDown = false;

            // place the marker
            rail[row][col++] = '*';

            // find the next row using direction flag
            if (dirDown)
                row++;
            else
                row--;
        }

        // now we can construct the fill the rail matrix
        int index = 0;
        for (int i = 0; i < key; i++)
            for (int j = 0; j < cipher.length(); j++)
                if (rail[i][j] == '*'
                        && index < cipher.length())
                    rail[i][j] = cipher.charAt(index++);

        StringBuilder result = new StringBuilder();

        row = 0;
        col = 0;
        for (int i = 0; i < cipher.length(); i++) {
            // check the direction of flow
            if (row == 0)
                dirDown = true;
            if (row == key - 1)
                dirDown = false;

            // place the marker
            if (rail[row][col] != '*')
                result.append(rail[row][col++]);

            // find the next row using direction flag
            if (dirDown)
                row++;
            else
                row--;
        }
        return result.toString();
    }

    // driver program to check the above functions
    public static void main(String[] args)
    {

        // Encryption
        System.out.println("Encrypted Message: ");
        System.out.println(
                encryptRailFence("attack at once", 2));
        System.out.println(
                encryptRailFence("GeeksforGeeks ", 3));
        System.out.println(
                encryptRailFence("defend the east wall", 3));

        // Now decryption of the same cipher-text
        System.out.println("\nDecrypted Message: ");
        System.out.println(
                decryptRailFence("atc toctaka ne", 2));
        System.out.println(
                decryptRailFence("GsGsekfrek eoe", 3));
        System.out.println(
                decryptRailFence("dnhaweedtees alf tl", 3));
    }
}

