package assignment8;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class DiffieHellmanClient {
    public static void main(String[] args) {
        long P, G, a, x;
        Scanner sc = new Scanner(System.in);


        System.out.println("Client: Enter the agreed upon value for public key G:");
        G = sc.nextLong();
        System.out.println("Client: Enter the agreed upon value for public key P:");
        P = sc.nextLong();


        System.out.println("Enter the private key (a) selected by User:");
        a = sc.nextLong();


        x = calculatePower(G, a, P);

        try {

            Socket socket = new Socket("localhost", 8080);


            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeLong(x);


            DataInputStream in = new DataInputStream(socket.getInputStream());
            long secretKey = in.readLong();
            System.out.println("Secret key for this client is: " + secretKey);


            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static long calculatePower(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            exp = exp >> 1;
            base = (base * base) % mod;
        }
        return result;
    }
}
