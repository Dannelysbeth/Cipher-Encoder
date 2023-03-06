package dannelysbeth.jetbrains_academy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Input string:");
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        int number;

        System.out.println("\nThe result:");

        for (char c : str1.toCharArray()) {
            number = c;
            String binaryString = String.format("%7s", Integer.toBinaryString(number)).replace(' ', '0');
            System.out.println(c + " = " + binaryString);
        }
    }
}