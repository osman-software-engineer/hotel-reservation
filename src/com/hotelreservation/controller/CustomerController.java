package com.hotelreservation.controller;

import com.hotelreservation.model.Customer;
import com.hotelreservation.model.Reservation;
import com.hotelreservation.model.Room;
import com.hotelreservation.service.CustomerService;
import com.hotelreservation.service.CustomerServiceImpl;

import java.util.List;

public class CustomerController {

    private CustomerService customerService;

    public CustomerController() {
        this.customerService = new CustomerServiceImpl();
    }

    public boolean createCustomerAccount() {
        return customerService.createCustomerAccount();
    }

    public List<Room> searchForAvailableRooms() {
        return customerService.searchForAvailableRooms();
    }

    public boolean bookRoom() {
        return customerService.bookRoom();
    }

    public void seeMyReservations() {
        customerService.viewReservations();
    }
}
