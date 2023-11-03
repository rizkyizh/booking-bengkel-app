package com.bengkel.booking.services;

import com.bengkel.booking.models.BookingOrder;
import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.models.MemberCustomer;
import com.bengkel.booking.repositories.BookingOrderRepository;
import com.bengkel.booking.repositories.CustomerRepository;
import com.bengkel.booking.repositories.ItemServiceRepository;

import java.util.List;
import java.util.stream.Collectors;

public class BengkelService {

    CustomerRepository customerRepository;

    ItemServiceRepository serviceRepository;

    BookingOrderRepository bookingOrderRepository;

    public BengkelService(CustomerRepository customerRepository, ItemServiceRepository serviceRepository, BookingOrderRepository bookingOrderRepository) {
        this.customerRepository = customerRepository;
        this.serviceRepository = serviceRepository;
        this.bookingOrderRepository = bookingOrderRepository;
    }

    private static BengkelService INSTANCE;

    public static BengkelService getInstance() {
         if (INSTANCE == null){
             INSTANCE = new BengkelService(
                     CustomerRepository.getInstance(),
                     ItemServiceRepository.getInstance(),
                     BookingOrderRepository.getInstance()
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

    public List<ItemService> getItemServiceListByType(String vehicleType) {
        return serviceRepository.getAll()
                .stream()
                .filter(itemService -> itemService.getVehicleType().equalsIgnoreCase(vehicleType))
                .collect(Collectors.toList());
    }

    public ItemService getItemServiceById(String inputServiceId) {
        return serviceRepository.getAll()
                .stream()
                .filter(itemService -> itemService.getServiceId().equalsIgnoreCase(inputServiceId))
                .findFirst().orElse(null);
    }


    public boolean checkSaldoCoinByMemberId(MemberCustomer customerSession, double totalPayment) {
      return   customerSession.getSaldoCoin() >= totalPayment;
    }

    public boolean saveOrderBooking(BookingOrder newBookingOrder) {
        String bookingOrderId = newBookingOrder.getBookingId();
        bookingOrderRepository.getAll().add(newBookingOrder);
        BookingOrder isExist = getBookingOrderById(bookingOrderId);
        if(isExist != null){
            return true;
        }else {
            return false;
        }

    }

    private BookingOrder getBookingOrderById(String bookingOrderId) {
        return bookingOrderRepository.getAll()
                .stream()
                .filter(bookingOrder -> bookingOrder.getBookingId().equalsIgnoreCase(bookingOrderId))
                .findFirst().orElse(null);
    }

    public void decreaseSaldoCoinByMemberId(String customerId, double totalPayment) {
        MemberCustomer memberCustomer = getMemberCustomerById(customerId);
        memberCustomer.setSaldoCoin(memberCustomer.getSaldoCoin() - totalPayment);
    }
}
