package dannelysbeth.jetbrains_academy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Input string:");
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        System.out.println();
        System.out.println("The result:");
        String encodedMessage = decode(str1);
        System.out.println(encodedMessage);


    }
    public static String decode (String str1) throws Exception {
        int space_num = 0;
        String addString = "";
        String zeroOrOne = "";
        for (char c : str1.toCharArray()) {
            if (c == ' ') {
                space_num++;
                if(space_num % 2 == 0) {
                    zeroOrOne = "";
                }

            }
            else if (space_num % 2 == 0) {
                zeroOrOne = zeroOrOne + "0";
            } else {
                if (zeroOrOne.equals("0")) {
                    addString = addString + "1";
                } else {
                    addString = addString + "0";
                }
            }
        }
        int number = 0;
        char symbol;
        String encodedMessage = "";
        for (int i = 0; i < addString.length(); i++) {
            int remainder = i % 7;
            if (addString.charAt(i)=='1'){
                switch(remainder) {
                    case 0 :
                        number = 64;
                        break;
                    case 1:
                        number = number + 32;
                        break;
                    case 2:
                        number += 16;
                        break;
                    case 3:
                        number += 8;
                        break;
                    case 4:
                        number += 4;
                        break;
                    case 5:
                        number += 2;
                        break;
                    case 6:
                        number++;
                        symbol = (char) number;
                        encodedMessage = encodedMessage + symbol;
                        break;
                    default:
                        break;
                }
            } else {
                if (remainder == 0) {
                    number = 0;
                } else if (remainder == 6) {
                    symbol = (char) number;
                    encodedMessage = encodedMessage + symbol;
                } else {
                    throw new Exception("Not found");
                }
            }
        }
        return encodedMessage;
    }

    public static String encode(String str1) {
        String binaryString = "";
        String str2 = "";
        String str3 = "";
        int number;
        for(char c : str1.toCharArray()) {
            number = c;
            binaryString = binaryString + String.format("%7s", Integer.toBinaryString(number)).replace(' ', '0');
        }

        String s = new String();
        char [] charArray = binaryString.toCharArray();
        if (charArray[0] == '0') {
            s = "00 0";
        } else {
            s = "0 0";
        }
        char prev = charArray[0];

        for (int i = 1; i < charArray.length; i++) {
            if (charArray[i] == prev){
                s = s + "0";
            } else {
                if (charArray[i] == '0') {
                    s = s + " 00 0";
                } else {
                    s = s + " 0 0";
                }
            }
            prev = charArray[i];
        }
        return s;
    }
}