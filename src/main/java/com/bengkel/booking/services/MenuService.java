package com.bengkel.booking.services;


import com.bengkel.booking.authentication.AuthService;
import com.bengkel.booking.helper.Utils;

import static com.bengkel.booking.authentication.Session.SESSION;

public class MenuService {

	private static final BengkelService bengkelService = BengkelService.getInstance();
	private static final AuthService authService = AuthService.getInstance();
	public static void run() {

		//noinspection InfiniteLoopStatement
		do {
			if (SESSION == null){
				welcomeMenu();
			}else {
				mainMenu();
			}
		} while (true);
		
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
				PrintService.BookingBengkel(bengkelService);
				isLooping = Utils.Stopped();
				break;
			case 3:
				PrintService.TopupSaldoCoin(bengkelService);
				isLooping = Utils.Stopped();
				break;
			case 4:
				PrintService.showInformationBookingOrderMenu(bengkelService);
				isLooping = Utils.Stopped();
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
