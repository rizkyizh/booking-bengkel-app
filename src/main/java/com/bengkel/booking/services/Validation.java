package com.bengkel.booking.services;

import com.bengkel.booking.helper.Utils;
import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.models.MemberCustomer;
import com.bengkel.booking.models.Vehicle;

import java.util.*;

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

    public static Vehicle validasiInputVehicleById(List<Vehicle> vehicles) {
        Scanner input = new Scanner(System.in);

        String vehicleId;
        Vehicle vehicleIsExist;

        do {

            System.out.println("Masukkan vehicle id: ");
            vehicleId = input.nextLine();

            String finalVehicleId = vehicleId;
            vehicleIsExist = vehicles.stream()
                    .filter(vehicle -> vehicle.getVehiclesId().equalsIgnoreCase(finalVehicleId))
                    .findAny().orElse(null);

            if (vehicleIsExist == null) {
                System.err.println("Kendaraan Tidak ditemukan");
            }

        } while (vehicleIsExist == null);

        return vehicleIsExist;

    }

    public static List<ItemService> validasiInputServiceSelectedById(int maxOfServices, BengkelService bengkelService) {
        Scanner input = new Scanner(System.in);
        Set<ItemService> serviceSet = new HashSet<>();
        boolean isContinue;

        int maxService = maxOfServices;

        while (maxService > 0) {

            ItemService itemServiceIsExist;

            do {

                System.out.println("Masukkan id Service: ");
                String inputServiceId = input.nextLine();

                itemServiceIsExist = bengkelService.getItemServiceById(inputServiceId);

                if (itemServiceIsExist != null) {

                    String itemServiceId = itemServiceIsExist.getServiceId();
                    ItemService isSelected = serviceSet.
                            stream().filter(itemService -> itemService.getServiceId().equalsIgnoreCase(itemServiceId)).findFirst().orElse(null);

                    if (isSelected != null) {
                        System.err.println("service sudah dipilih");
                    } else {
                        serviceSet.add(itemServiceIsExist);

                        if (maxService > 1) {
                            isContinue = Utils.yesOrNo("ingin pilih service yang lain");

                            if (!isContinue) {
                                maxService = 0;
                            } else {
                                maxService -= 1;
                            }

                        } else {
                            maxService = 0;
                        }

                    }


                } else {
                    System.err.println("service tidak ditemukan");
                }

            } while (itemServiceIsExist == null);

        }


        return serviceSet.stream().toList();
    }

    public static String validasiInputPaymentMethod(Customer customerSession) {
        String[] paymentMethodArr = {"Cash", "Saldo Coin"};
        String result;
        String regex = "^[1-1]+$";

        System.out.println("pilih MethodPayment");
        System.out.println("1. Cash");
        if (customerSession instanceof MemberCustomer) {
            System.out.println("2. Saldo Coin");
            regex = "^[0-9]+$";
        }
        int indexPayment = Integer.parseInt(validasiInput("masukkan payment method: ", "payment method tidak ada", regex));
        result = paymentMethodArr[indexPayment - 1];

        return result;

    }
}
