package com.bengkel.booking.services;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.bengkel.booking.authentication.AuthService;

import static com.bengkel.booking.authentication.Session.SESSION;

import com.bengkel.booking.helper.Utils;
import com.bengkel.booking.models.Car;
import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.MemberCustomer;
import com.bengkel.booking.models.Vehicle;

import static com.bengkel.booking.authentication.Session.SESSION;

public class PrintService {

    public static void printMenu(String[] listMenu, String title) {
        String line = "+---------------------------------+";
        int number = 1;
        String formatTable = " %-2s. %-25s %n";

        System.out.printf("%-25s %n", Utils.toTitleCase(title));
        System.out.println(line);

        for (String data : listMenu) {
            if (number < listMenu.length) {
                System.out.printf(formatTable, number, data);
            } else {
                System.out.printf(formatTable, 0, data);
            }
            number++;
        }
        System.out.println(line);
        System.out.println();
    }

    public static void printVechicle(List<Vehicle> listVehicle) {
        String formatTable = "| %-2s | %-15s | %-10s | %-15s | %-15s | %-5s | %-15s |%n";
        String line = "+----+-----------------+------------+-----------------+-----------------+-------+-----------------+%n";
        System.out.format(line);
        System.out.format(formatTable, "No", "Vechicle Id", "Warna", "Brand", "Transmisi", "Tahun", "Tipe Kendaraan");
        System.out.format(line);
        int number = 1;
        String vehicleType = "";
        for (Vehicle vehicle : listVehicle) {
            if (vehicle instanceof Car) {
                vehicleType = "Mobil";
            } else {
                vehicleType = "Motor";
            }
            System.out.format(formatTable, number, vehicle.getVehiclesId(), vehicle.getColor(), vehicle.getBrand(), vehicle.getTransmisionType(), vehicle.getYearRelease(), vehicleType);
            number++;
        }
        System.out.printf(line);
    }


    public static void login(AuthService authService) {
        Scanner input = new Scanner(System.in);

        Customer customerLoginExist;
        String customerIdLogin;
        String passwordLogin;
        boolean isPasswordValid;


        int kesempatan = 3;
        do {
            System.out.println("masukkan Customer id: ");
            customerIdLogin = input.nextLine();
            customerLoginExist = authService.inputValidationCustomerLogin(customerIdLogin);

            if (customerLoginExist == null) {
                System.err.println("Customer Id Tidak Ditemukan atau Salah!");
                kesempatan -= 1;
            } else {
                while (kesempatan > 0) {

                    System.out.println("Masukkan Password: ");
                    passwordLogin = input.nextLine();
                    isPasswordValid = authService.inputValidationPasswordLogin(customerLoginExist, passwordLogin);

                    if (!isPasswordValid) {
                        System.err.println("Password yang anda Masukan Salah!");
                        kesempatan -= 1;
                    } else {
                        authService.login(customerLoginExist, isPasswordValid);
                        return;
                    }
                }
            }

        } while (kesempatan > 0);

        System.err.println("OOPS KESEMPATAN HABIS");
        System.exit(0);

    }

    public static void showCustomerLoggedIn() {
        Customer customer = SESSION.getCustomer();
        String status = "Non Member";

        System.out.println(Utils.toTitleCase("customer profile"));
        System.out.println(Utils.toTitleCase("customer id: " + customer.getCustomerId()));
        System.out.println(Utils.toTitleCase("nama: " + customer.getName()));
        if (customer instanceof MemberCustomer) {
            status = "Member";
        }
        System.out.println(Utils.toTitleCase("customer status: " + status));
        System.out.println(Utils.toTitleCase("alamat: " + customer.getAddress()));

        if (status.equalsIgnoreCase("Member")) {
            System.out.println((Utils.toTitleCase("saldo coin: ") + ((MemberCustomer) customer).getSaldoCoin()));
        }

        System.out.println(Utils.toTitleCase("list kendaraan: "));
        System.out.println("+---------------------------------------------------------------------+");
        System.out.printf("| %-4s | %-10s | %-11s | %-15s | %-15s |\n", "No.", "ID", "Warna", "Tipe Kendaraan", "Tahun");
        System.out.println("+---------------------------------------------------------------------+");
        int num = 1;
        for (Vehicle vehicle : customer.getVehicles()) {
            System.out.printf("| %-4s | %-10s | %-11s | %-15s | %-15s |\n",
                    num,
                    vehicle.getVehiclesId(),
                    vehicle.getColor(),
                    vehicle.getVehicleType(),
                    vehicle.getYearRelease());
            num++;
        }
        System.out.println("+---------------------------------------------------------------------+");

    }

    public static void TopupSaldoCoin(BengkelService bengkelService) {
        Scanner input = new Scanner(System.in);
        Customer customer = SESSION.getCustomer();

        if (customer instanceof MemberCustomer memberCustomer) {
            String resultValidation;
            double newTopUp = 0;
            boolean isTopUpSuccess;

            System.out.println("Saldo : " + memberCustomer.getSaldoCoin());

            boolean isInputValid = true;
            do {
                    resultValidation = Validation.validasiNumberDouble("Masukkan besaran Top Up: ");

                    if (!resultValidation.equalsIgnoreCase("Silahkan masukkan angka!")){
                       newTopUp = Double.parseDouble(resultValidation);
                       isInputValid = false;
                    }

            } while (isInputValid);

            System.out.println(newTopUp);

            isTopUpSuccess = bengkelService.topUpSaldoCoinById(memberCustomer.getCustomerId(), newTopUp);

            if (isTopUpSuccess){
                System.out.println("Berhasil Top Up");
            }else {
                System.err.println("Gagal Top Up");
            }

        } else {
            System.err.println("Maaf fitur ini hanya untuk Member saja!");
        }

    }
}
