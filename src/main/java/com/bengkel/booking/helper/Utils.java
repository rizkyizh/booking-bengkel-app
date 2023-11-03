package com.bengkel.booking.helper;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Utils {


    public static boolean yesOrNo(String message) {
        Scanner input = new Scanner(System.in);
        System.out.print("\n"+ message+ " [y/n]: ");
        String inputUser = input.next();

        while (!inputUser.equalsIgnoreCase("y") && !inputUser.equalsIgnoreCase("n")) {
            System.out.println("Pilihan anda bukan [y/n]");
            System.out.print("\n"+ message+ " [y/n]: ");
            inputUser = input.next();
        }

        return inputUser.equalsIgnoreCase("y");
    }

    public static String toTitleCase(String title) {
        String[] words = title.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : words) {
            char firstChar = Character.toUpperCase(word.charAt(0));
            String afterFirstChar = word.toLowerCase().substring(1, word.length());
            stringBuilder.append(firstChar).append(afterFirstChar);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    @SuppressWarnings("SameReturnValue")
    public static boolean Stopped() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("\ninput apapun untuk melanjutkan: ");
        input.nextLine();
        return true;
    }
}
