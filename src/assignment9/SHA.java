package assignment9;

import java.security.*;
import java.util.Scanner;

class SHA {
    public static void main(String[] a) {
        Scanner sc= new Scanner(System.in);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            System.out.println("Enter the Message: ");
            String input = sc.nextLine();
            md.update(input.getBytes());
            byte[] output = md.digest();
            System.out.println();
            System.out.println("SHA of \""+input+"\" = " +bytesToHex(output));
            System.out.println(" ");
            System.out.println("Enter the Message: ");
            String input1 = sc.nextLine();
            md.update(input1.getBytes());
            output = md.digest();
            System.out.println();
            System.out.println("SHA of \""+input1+"\" = " +bytesToHex(output));
            System.out.println(" ");
            System.out.println("Enter the Message: ");
            String input2 = sc.nextLine();
            md.update(input2.getBytes());
            output = md.digest();
            System.out.println();
            System.out.println("SHA of \"" +input2+"\" = " +bytesToHex(output));
            System.out.println(""); }
        catch (Exception e) {
            System.out.println("Exception: " +e);
        }
    }
    public static String bytesToHex(byte[] b) {
        char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuffer buf = new StringBuffer();
        for (int j=0; j<b.length; j++) {
            buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
            buf.append(hexDigit[b[j] & 0x0f]); }
        return buf.toString(); }
}