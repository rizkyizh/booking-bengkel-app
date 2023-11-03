package com.bengkel.booking.authentication;

import com.bengkel.booking.models.Customer;
import com.bengkel.booking.repositories.CustomerRepository;

public class AuthService {

   CustomerRepository customerRepository;

    private AuthService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private static AuthService INSTANCE;

   public static AuthService getInstance() {
        if (INSTANCE == null){
            INSTANCE = new AuthService(
                    CustomerRepository.getInstance()
            );
         }
         return INSTANCE;
   }

    public Customer inputValidationCustomerLogin(String customerIdLogin) {
       return customerRepository.getAll()
               .stream()
               .filter(customer -> customer.getCustomerId().equalsIgnoreCase(customerIdLogin))
               .findFirst()
               .orElse(null);
    }

    public boolean inputValidationPasswordLogin(Customer customerLoginExist, String passwordLogin) {
       return customerLoginExist.getPassword().equalsIgnoreCase(passwordLogin);
    }

    public void login(Customer customerLoginExist, boolean isPasswordValid) {
       if (customerLoginExist != null && isPasswordValid){
           Session.createSession(customerLoginExist);
       }
    }

    public void logout() {
       Session.deleteSession();
    }


    // TODO: 03/11/23 login logic

}
