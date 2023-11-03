package com.bengkel.booking.services;

import com.bengkel.booking.models.MemberCustomer;
import com.bengkel.booking.repositories.CustomerRepository;
import com.bengkel.booking.repositories.ItemServiceRepository;

public class BengkelService {

    CustomerRepository customerRepository;

    ItemServiceRepository serviceRepository;

    private BengkelService(CustomerRepository customerRepository, ItemServiceRepository serviceRepository) {
        this.customerRepository = customerRepository;
        this.serviceRepository = serviceRepository;
    }

    //Silahkan tambahkan fitur-fitur utama aplikasi disini

	//Info Customer
	
	//Booking atau Reservation
	
	//Top Up Saldo Coin Untuk Member Customer
	

    private static BengkelService INSTANCE;

    public static BengkelService getInstance() {
         if (INSTANCE == null){
             INSTANCE = new BengkelService(
                     CustomerRepository.getInstance(),
                     ItemServiceRepository.getInstance()
             );
          }
          return INSTANCE;
    }


    public boolean topUpSaldoCoinById(String customerId, double newTopUp) {
        MemberCustomer memberCustomer = getMemberCustomerById(customerId);
        if (memberCustomer != null){
            double currentSaldo = memberCustomer.getSaldoCoin();
            memberCustomer.setSaldoCoin(currentSaldo + newTopUp);
            return true;
        }else {
            return false;
        }
    }

    private MemberCustomer getMemberCustomerById(String customerId) {
       return customerRepository.getAll()
                .stream()
                .filter(customer -> customer instanceof MemberCustomer)
                .map(customer -> (MemberCustomer) customer)
                .filter(customer -> customer.getCustomerId().equalsIgnoreCase(customerId))
                .findFirst().orElse(null);

    }
}
