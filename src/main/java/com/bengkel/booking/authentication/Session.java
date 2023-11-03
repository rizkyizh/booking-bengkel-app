package com.bengkel.booking.authentication;

import com.bengkel.booking.models.Customer;


public class Session {
    private final Customer customer;

    private Session(Customer customer) {
        this.customer = customer;
    }


    public Customer getCustomer() {
        return customer;
    }

    public static Session SESSION;

    public static void createSession(Customer customer) {
         if (SESSION == null){
             SESSION = new Session(customer);
          }
    }
    public static void deleteSession(){
        SESSION = null;
    }

}
