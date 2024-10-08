package assignment3;

public class EuclideanAlgorithm {
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        int a = 56;
        int b = 98;

        int gcd = EuclideanAlgorithm.gcd(a, b);
        System.out.println("GCD of " + a + " and " + b + " is: " + gcd);
    }
}
