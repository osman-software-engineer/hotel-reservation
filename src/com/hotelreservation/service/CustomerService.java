package com.hotelreservation.service;

import com.hotelreservation.model.Customer;
import com.hotelreservation.model.Reservation;
import com.hotelreservation.model.Room;

import java.util.List;

public interface CustomerService {

    boolean createCustomerAccount();

    List<Room> searchForAvailableRooms();

    boolean bookRoom();

    void viewReservations();

}
