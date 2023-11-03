package com.bengkel.booking.helper;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Utils {
    public static String formatCurrency(double numerik) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###,###,###.00");
        if (decimalFormat.format(numerik).equalsIgnoreCase(".00")) {
            return "-";
        }
        return decimalFormat.format(numerik);
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.println("\003\143");
            }
        } catch (Exception ex) {
            System.err.println("Tidak bisa clear Screen");
        }
    }

    public static boolean yesOrNo() {
        Scanner input = new Scanner(System.in);
        System.out.print("\napakah anda ingin melanjutkan [y/n]: ");
        String inputUser = input.next();
        while (!inputUser.equalsIgnoreCase("y") && !inputUser.equalsIgnoreCase("n")) {
            System.out.println("Pilihan anda bukan [y/n]");
            System.out.print("\napakah anda ingin melanjutkan [y/n]: ");
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

    public static boolean Stopped() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("\ninput apapun untuk melanjutkan: ");
        input.nextLine();
        return true;
    }
}
