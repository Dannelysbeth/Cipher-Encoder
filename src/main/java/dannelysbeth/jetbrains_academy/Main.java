package dannelysbeth.jetbrains_academy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Input string:");
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        int number;
        System.out.println();
        System.out.println("The result:");

        String binaryString = "";
        for(char c : str1.toCharArray()) {
            number = c;
            binaryString = binaryString + String.format("%7s", Integer.toBinaryString(number)).replace(' ', '0');
        }

        String s;
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
        System.out.println(s);


    }
}


    }
}