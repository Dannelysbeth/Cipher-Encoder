package dannelysbeth.jetbrains_academy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        System.out.println("Please input operation (encode/decode/exit):");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        switch (option) {
            case "encode" -> encode();
            case "decode" -> decode();
            case "exit" -> {
                System.out.println("Bye!");
                return;
            }
            default -> System.out.println("There is no '" + option + "' operation");
        }
        System.out.println();
        menu();
    }

    public static void decode() {
        System.out.println("Input encoded string:");
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        System.out.println();
        int space_num = 0;
        StringBuilder addString = new StringBuilder();
        StringBuilder zeroOrOne = new StringBuilder();
        char c;
        if (str1.charAt(0) != '0') {
            System.out.println("Encoded string is not valid.");
            return;
        }
        for (int i = 0; i < str1.length(); i++) {
            c = str1.charAt(i);
            if (c != ' ' && c != '0') {
                System.out.println("Encoded string is not valid.");
                return;
            }
            if (c == ' ') {
                if (str1.charAt(i - 1) == ' ') {
                    System.out.println("Encoded string is not valid.");
                    return;
                }
                space_num++;
                if (space_num % 2 == 0) {
                    zeroOrOne = new StringBuilder();
                }
            } else if (space_num % 2 == 0) {
                zeroOrOne.append("0");
            } else {
                if (zeroOrOne.toString().equals("0")) {
                    addString.append("1");
                } else if (zeroOrOne.toString().equals("00")) {
                    addString.append("0");
                } else {
                    System.out.println("Encoded string is not valid.");
                    return;
                }
            }
        }
        if (space_num % 2 == 0) {
            System.out.println("Encoded string is not valid.");
            return;
        }


        if (addString.length() % 7 != 0) {
            System.out.println("Encoded string is not valid.");
            return;
        }

        System.out.println("The decoded string:");
        System.out.println(binaryStringToMessage(addString.toString()));
    }

    public static String binaryStringToMessage(String binaryString) {
        int number = 0;
        int [] values = {64, 32, 16, 8, 4, 2};
        char symbol;
        StringBuilder encodedMessage = new StringBuilder();
        for (int i = 0; i < binaryString.length(); i++) {
            int remainder = i % 7;                                      //checking what part of binaryString is that
            if (binaryString.charAt(i) == '1') {
                if (remainder >= 0 && remainder < 6) {
                    number += values[remainder];
                } else {
                    number++;
                    symbol = (char) number;
                    encodedMessage.append(symbol);
                }
            } else {
                if (remainder == 0) {
                    number = 0;
                } else if (remainder == 6) {
                    symbol = (char) number;
                    encodedMessage.append(symbol);
                }
            }
        }
        return encodedMessage.toString();
    }

    /**
     * Method takes string input and encodes it to Chuck Norris Code
     */
    public static void encode() {
        System.out.println("Input string:");
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        String encodedMessage = convertBinaryStringToChuckNorrisCode(
                convertStringToBinaryString(message)
        );
        System.out.println("The encoded string:" + "\n" + encodedMessage);
    }

    /**
     * Converts input message to binary string
     * @param message the input string, that should be encoded
     * @return the binary string
     */
    private static String convertStringToBinaryString(String message) {
        StringBuilder binaryString = new StringBuilder();
        int number;
        for (char c : message.toCharArray()) {
            number = c;
            binaryString.append(String.format("%7s", Integer.toBinaryString(number)).replace(' ', '0'));
        }
        return binaryString.toString();
    }

    /**
     * Takes binary string and coverts it to Chuck Norris Code
     * @param binaryString provided string, that consists of '0' and '1'
     * @return the encoded string
     */
    private static String convertBinaryStringToChuckNorrisCode(String binaryString) {
        StringBuilder encodedString;

        //checking out first character in provided string and adding to sequence
        if (binaryString.charAt(0) == '0') {
            encodedString = new StringBuilder("00 0");
        } else {
            encodedString = new StringBuilder("0 0");
        }

        char prevChar;
        for (int i = 1; i < binaryString.length(); i++) {
            prevChar = binaryString.charAt(i-1);
            if (binaryString.charAt(i) == prevChar) {
                encodedString.append("0");
            } else {
                if (binaryString.charAt(i) == '0') {
                    encodedString.append(" 00 0");
                } else {
                    encodedString.append(" 0 0");
                }
            }
        }
       return encodedString.toString();
    }
}