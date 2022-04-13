package com.hotelreservation.service;

import com.hotelreservation.model.Customer;
import com.hotelreservation.model.Reservation;
import com.hotelreservation.model.Room;

import java.util.List;


public interface AdminService {

    List<Customer> seeAllCustomers();

    List<Room> seeAllRooms();

    void seeAllReservations();

    boolean addRoom();

    void addTestData();
}
