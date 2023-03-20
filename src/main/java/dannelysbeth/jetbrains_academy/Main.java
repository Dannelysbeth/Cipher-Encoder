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
        String inputString = scanner.nextLine();
        System.out.println();




        int spacesAmounts = 0;
        StringBuilder addString = new StringBuilder();
        StringBuilder zeroOrOne = new StringBuilder();
        char c;
        if (inputString.charAt(0) != '0') {
            System.out.println("Encoded string is not valid.");
            return;
        }
        for (int i = 0; i < inputString.length(); i++) {
            c = inputString.charAt(i);
            if (c != ' ' && c != '0') {
                System.out.println("Encoded string is not valid.");
                return;
            }
            if (c == ' ') {
                if (inputString.charAt(i - 1) == ' ') {
                    System.out.println("Encoded string is not valid.");
                    return;
                }
                spacesAmounts++;
                if (spacesAmounts % 2 == 0) {
                    zeroOrOne = new StringBuilder();
                }
            } else if (spacesAmounts % 2 == 0) {
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
        if (spacesAmounts % 2 == 0) {
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

    /**
     * Converts given binaryString to real message
     * @param binaryString the provided binary string
     * @return decoded message
     */
    public static String binaryStringToMessage(String binaryString) {
        int number = 0;
        StringBuilder encodedMessage = new StringBuilder();
        for (int i = 0; i < binaryString.length(); i++) {
            int remainder = i % 7;   //checking what part of binaryString is it
            if (remainder == 0) {
                number = (binaryString.charAt(i) == '1') ? 64 : 0;
            }
            number += (binaryString.charAt(i) == '1') ? 1 << (6 - remainder) : 0;

            if (remainder == 6) {
                encodedMessage.append((char) number);
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
        String encodedString = (binaryString.charAt(0) == '0') ? "00 0" : "0 0";         //checking out first character in provided string and adding to sequence
        char prevChar;
        for (int i = 1; i < binaryString.length(); i++) {
            prevChar = binaryString.charAt(i-1);
            encodedString = (binaryString.charAt(i) == prevChar) ?
                    encodedString + "0" :
                    ((binaryString.charAt(i) == '0') ?
                            encodedString + " 00 0" :
                            encodedString + " 0 0");
        }
       return encodedString;
    }
}