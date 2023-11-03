package com.bengkel.booking.authentication;

import com.bengkel.booking.models.Car;
import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.Motorcyle;
import com.bengkel.booking.models.Vehicle;

import java.util.Arrays;
import java.util.List;

public class Session {
    private Customer customer;

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
