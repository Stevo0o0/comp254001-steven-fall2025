package comp254.lab3.ex2;
import java.util.Scanner;

public class PalindromeCheck {
    public static boolean isPalindrome(String s) {
        // Base case
        if (s.length() <= 1) return true;
        // Compare first and last characters
        if (s.charAt(0) != s.charAt(s.length() - 1))
            return false;
        // Recursive call on substring without first and last char
        return isPalindrome(s.substring(1, s.length() - 1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string to check: ");
        String input = sc.nextLine().toLowerCase();
        System.out.println(input + " → " + (isPalindrome(input) ? "Palindrome" : "Not Palindrome"));
        sc.close();
    }
}
