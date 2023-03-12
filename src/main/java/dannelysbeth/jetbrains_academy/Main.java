package dannelysbeth.jetbrains_academy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();


    }
    public static void menu(){
        System.out.println("Please input operation (encode/decode/exit):");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        switch (option) {
            case "encode" -> {
                encode();
                System.out.println();
                menu();
            }
            case "decode" -> {
                decode();
                System.out.println();
                menu();
            }
            case "exit" -> sayBye();
            default -> {
                System.out.println("There is no '" + option + "' operation");
                System.out.println();
                menu();
            }
        }
    }

    public static void sayBye(){
        System.out.println("Bye!");
    }

    public static void decode () {
        System.out.println("Input encoded string:");
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        System.out.println();
        int space_num = 0;
        StringBuilder addString = new StringBuilder();
        StringBuilder zeroOrOne = new StringBuilder();
        char c;
        if(str1.charAt(0) != '0'){
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
                if (str1.charAt(i-1) == ' ') {
                    System.out.println("Encoded string is not valid.");
                    return;
                }
                space_num++;
                if(space_num % 2 == 0) {
                    zeroOrOne = new StringBuilder();
                }
            }
            else if (space_num % 2 == 0) {
                zeroOrOne.append("0");
            } else {
                if (zeroOrOne.toString().equals("0")) {
                    addString.append("1");
                } else if (zeroOrOne.toString().equals("00")){
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
        int number = 0;
        char symbol;
        StringBuilder encodedMessage = new StringBuilder();
        if(addString.length() % 7 != 0) {
            System.out.println("Encoded string is not valid.");
            return;
        }
        for (int i = 0; i < addString.length(); i++) {
            int remainder = i % 7;
            if (addString.charAt(i)=='1'){
                switch (remainder) {
                    case 0 -> number = 64;
                    case 1 -> number = number + 32;
                    case 2 -> number += 16;
                    case 3 -> number += 8;
                    case 4 -> number += 4;
                    case 5 -> number += 2;
                    case 6 -> {
                        number++;
                        symbol = (char) number;
                        encodedMessage.append(symbol);
                    }
                    default -> {
                    }
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
        System.out.println("The decoded string:");
        System.out.println(encodedMessage);
    }

    public static void encode() {
        System.out.println("Input string:");
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        System.out.println("The encoded string:");

        StringBuilder binaryString = new StringBuilder();
        int number;
        for(char c : str1.toCharArray()) {
            number = c;
            binaryString.append(String.format("%7s", Integer.toBinaryString(number)).replace(' ', '0'));
        }

        StringBuilder s;
        char [] charArray = binaryString.toString().toCharArray();
        if (charArray[0] == '0') {
            s = new StringBuilder("00 0");
        } else {
            s = new StringBuilder("0 0");
        }
        char prev = charArray[0];

        for (int i = 1; i < charArray.length; i++) {
            if (charArray[i] == prev){
                s.append("0");
            } else {
                if (charArray[i] == '0') {
                    s.append(" 00 0");
                } else {
                    s.append(" 0 0");
                }
            }
            prev = charArray[i];
        }
        System.out.println(s);
    }
}