package com.bengkel.booking.repositories;

import com.bengkel.booking.models.BookingOrder;

import java.util.ArrayList;
import java.util.List;

public class BookingOrderRepository {

    final List<BookingOrder> bookingOrderList;

    private BookingOrderRepository (){
       bookingOrderList = new ArrayList<>();
    }

    public List<BookingOrder> getAll(){
        return bookingOrderList;
    }

    private static BookingOrderRepository INSTANCE;

    public static BookingOrderRepository getInstance() {
         if (INSTANCE == null){
             INSTANCE = new BookingOrderRepository();
          }
          return INSTANCE;
    }
}
