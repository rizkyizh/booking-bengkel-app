package com.bengkel.booking.services;


import java.util.Scanner;
import com.bengkel.booking.authentication.AuthService;
import com.bengkel.booking.helper.Utils;

import static com.bengkel.booking.authentication.Session.SESSION;

public class MenuService {

	private static BengkelService bengkelService = BengkelService.getInstance();
	private static AuthService authService = AuthService.getInstance();
	private static Scanner input = new Scanner(System.in);
	public static void run() {

		boolean isLooping = true;
		do {
			if (SESSION == null){
				welcomeMenu();
			}else {
				mainMenu();
			}
		} while (isLooping);
		
	}
	
	public static void welcomeMenu() {
				String[] listMenu = {"login", "exit"};
						int menuChoice = 0;
						boolean isLooping = true;

						do {
							PrintService.printMenu(listMenu, "selamat Datang di booking bengkel app");
							menuChoice = Validation.validasiNumberWithRange("Masukan Pilihan Menu:", "Input Harus Berupa Angka!", "^[0-1]+$", listMenu.length-1, 0);
							System.out.println(menuChoice);

							switch (menuChoice) {
							case 1:
								PrintService.login(authService);
								return;
							case 0:
								System.exit(0);
								break;
							default:
								isLooping = false;
								break;
							}
						} while (isLooping);


	}
	
	public static void mainMenu() {
		String[] listMenu = {"Informasi Customer", "Booking Bengkel", "Top Up Bengkel Coin", "Informasi Booking", "Logout"};
		int menuChoice = 0;
		boolean isLooping = true;
		
		do {
			PrintService.printMenu(listMenu, "Hallo " + SESSION.getCustomer().getName() );
			menuChoice = Validation.validasiNumberWithRange("Masukan Pilihan Menu:", "Input Harus Berupa Angka!", "^[0-9]+$", listMenu.length-1, 0);

			switch (menuChoice) {
			case 1:
				PrintService.showCustomerLoggedIn();
			 	isLooping = Utils.Stopped();
				break;
			case 2:
				// TODO: 03/11/23 panggil fitur Booking Bengkel
				break;
			case 3:
				PrintService.TopupSaldoCoin(bengkelService);
				isLooping = Utils.Stopped();
				break;
			case 4:
				break;
			default:
				authService.logout();
				System.out.println("Logout");
				isLooping = false;
				break;
			}
		} while (isLooping);
		
		
	}
	

}
