package comp254.lab3.ex1;
import java.util.Scanner;

public class RecursiveProduct {
    public static int product(int m, int n) {
        // Base case
        if (n == 0) return 0;
        // Recursive case
        return m + product(m, n - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first positive integer (m): ");
        int m = sc.nextInt();
        System.out.print("Enter second positive integer (n): ");
        int n = sc.nextInt();
        System.out.println("Product of " + m + " and " + n + " = " + product(m, n));
        sc.close();
    }
}
