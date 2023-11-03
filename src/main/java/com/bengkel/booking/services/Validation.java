package com.bengkel.booking.services;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation {

    public static String validasiInput(String question, String errorMessage, String regex) {
        Scanner input = new Scanner(System.in);
        String result;
        boolean isLooping = true;
        do {
            System.out.print(question);
            result = input.nextLine();

            if (result.matches(regex)) {
                isLooping = false;
            } else {
                System.out.println(errorMessage);
            }

        } while (isLooping);

        return result;
    }

    public static int validasiNumberWithRange(String question, String errorMessage, String regex, int max, int min) {
        int result;
        boolean isLooping = true;
        do {
            result = Integer.parseInt(validasiInput(question, errorMessage, regex));
            if (result >= min && result <= max) {
                isLooping = false;
            } else {
                System.out.println("Pilihan angka " + min + " s.d " + max);
            }
        } while (isLooping);

        return result;
    }

    public static String validasiNumberDouble(String question) {
        try {
            Scanner input = new Scanner(System.in);
            double inputDouble;
            System.out.print(question);
            inputDouble = input.nextDouble();

            return String.valueOf(inputDouble);
        } catch (InputMismatchException errorMessage) {
            return "Silahkan masukkan angka!!";
        }
    }
}
