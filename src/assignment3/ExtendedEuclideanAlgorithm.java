package assignment3;

public class ExtendedEuclideanAlgorithm {
    public static int[] gcdExtended(int a, int b) {
        if (a == 0) {
            return new int[]{b, 0, 1};
        }
        int[] gcd = gcdExtended(b % a, a);
        return new int[]{gcd[0], gcd[2] - (b / a) * gcd[1], gcd[1]};
    }

    public static void main(String[] args) {
        int a = 1759;
        int b = 550;
        int[] result = gcdExtended(a, b);
        System.out.println(a + "x" + result[1] + " + " + b + "x" + result[2] + " = " + (a * result[1] + b * result[2]) + " = gcd(" + a + "," + b + ") = " + result[0]);
    }
}

