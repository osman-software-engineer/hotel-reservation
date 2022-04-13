package com.hotelreservation.repository;

import com.hotelreservation.model.Customer;
import com.hotelreservation.model.Reservation;
import com.hotelreservation.model.Room;

import java.util.List;

public interface HotelReservationRepository {

    List<Customer> seeAllCustomers();

    List<Room> seeAllRooms();

    List<Reservation> seeAllReservations();

    boolean createCustomerAccount(Customer customer);

    boolean addRoom(Room room);

    boolean createReservation(Reservation reservation);

    void addTestData();


}
