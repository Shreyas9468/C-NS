package assignment4;

public class ChineseRemainderTheorem {

    // Function to find the modular inverse
    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1; // In case no modular inverse is found, return 1
    }

    // Function to solve the system of congruences using the Chinese Remainder Theorem
    public static int findX(int[] num, int[] rem, int k) {
        int prod = 1;
        for (int i = 0; i < k; i++) {
            prod *= num[i];
        }

        int result = 0;

        for (int i = 0; i < k; i++) {
            int pp = prod / num[i];
            result += rem[i] * modInverse(pp, num[i]) * pp;
        }

        return result % prod;
    }

    public static void main(String[] args) {
        int[] num = { 3, 4, 5 };  // Moduli (m1, m2, m3)
        int[] rem = { 2, 3, 1 };  // Remainders (a1, a2, a3)
        int k = num.length;       // Number of equations

        int x = ChineseRemainderTheorem.findX(num, rem, k);
        System.out.println("x is: " + x);
    }
}
