package assignment8;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class DiffieHellmanServer {
    public static void main(String[] args) {
        long P, G, y1, y2, ka, kb;
        Scanner sc = new Scanner(System.in);


        System.out.println("Server: Both users should agree upon the public keys G and P.");
        System.out.println("Enter value for public key G:");
        G = sc.nextLong();
        System.out.println("Enter value for public key P:");
        P = sc.nextLong();

        try {

            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server is waiting for clients to connect...");


            Socket client1 = serverSocket.accept();
            System.out.println("Client1 connected.");


            Socket client2 = serverSocket.accept();
            System.out.println("Client2 connected.");


            DataInputStream in1 = new DataInputStream(client1.getInputStream());
            DataInputStream in2 = new DataInputStream(client2.getInputStream());

            y1 = in1.readLong();
            y2 = in2.readLong();


            System.out.println("Enter User1's private key a:");
            long a = sc.nextLong();
            System.out.println("Enter User2's private key b:");
            long b = sc.nextLong();

            ka = calculatePower(y2, a, P);
            kb = calculatePower(y1, b, P);

            DataOutputStream out1 = new DataOutputStream(client1.getOutputStream());
            DataOutputStream out2 = new DataOutputStream(client2.getOutputStream());
            out1.writeLong(ka);
            out2.writeLong(kb);

            System.out.println("Secret key for User1 (ka): " + ka);
            System.out.println("Secret key for User2 (kb): " + kb);

            client1.close();
            client2.close();
            serverSocket.close();

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
