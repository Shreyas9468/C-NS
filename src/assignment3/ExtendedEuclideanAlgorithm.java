package assignment3;

public class ExtendedEuclideanAlgorithm {
    public static int extendedGCD(int a, int b, int[] result) {
        if (b == 0) {
            result[0] = 1; // x = 1
            result[1] = 0; // y = 0
            return a;
        }
        int[] tempResult = new int[2];
        int gcd = extendedGCD(b, a % b, tempResult);

        result[0] = tempResult[1];
        result[1] = tempResult[0] - (a / b) * tempResult[1];

        return gcd;
    }

    public static void main(String[] args) {
        int a = 56;
        int b = 98;

        int[] result = new int[2]; // To hold x and y
        int gcd = ExtendedEuclideanAlgorithm.extendedGCD(a, b, result);

        System.out.println("GCD of " + a + " and " + b + " is: " + gcd);
        System.out.println("Coefficients x and y are: " + result[0] + ", " + result[1]);
    }
}

