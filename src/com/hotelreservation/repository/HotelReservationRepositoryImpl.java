package com.hotelreservation.repository;

import com.hotelreservation.model.Customer;
import com.hotelreservation.model.Reservation;
import com.hotelreservation.model.Room;
import com.hotelreservation.model.RoomType;
import com.hotelreservation.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class HotelReservationRepositoryImpl implements HotelReservationRepository {
    private static List<Customer> customersList = new ArrayList<>();
    private static List<Room> roomsList = new ArrayList<>();
    private static List<Reservation> reservationsList = new ArrayList<>();

    @Override
    public List<Customer> seeAllCustomers() {
        return customersList;
    }

    @Override
    public List<Room> seeAllRooms() {
        return roomsList;
    }

    @Override
    public List<Reservation> seeAllReservations() {
        return reservationsList;
    }

    @Override
    public boolean createCustomerAccount(Customer customer) {

        return customersList.add(customer);

    }

    @Override
    public boolean addRoom(Room room) {

        return roomsList.add(room);

    }

    @Override
    public boolean createReservation(Reservation reservation) {
        return reservationsList.add(reservation);
    }

    public void addTestData(){
        Customer cust1 = new Customer("Osman","Mohammed","Osmohammed@deloitte.com");
        Customer cust2 = new Customer("firdous","fatima","firdous@deloitte.com");
        Customer cust3 = new Customer("khalid","Mohammed","khalid@deloitte.com");
        Customer cust4 = new Customer("Musab","Mohammed","musab@deloitte.com");
        customersList.add(cust1);
        customersList.add(cust2);
        customersList.add(cust3);
        customersList.add(cust4);
        Room room1 = new Room("100",135.0, RoomType.SINGLE, Utils.AVAILABLE);
        Room room2 = new Room("101",136.0, RoomType.DOUBLE, Utils.AVAILABLE);
        Room room3 = new Room("102",137.0, RoomType.SINGLE, Utils.AVAILABLE);
        Room room4 = new Room("103",138.0, RoomType.DOUBLE, Utils.AVAILABLE);
        roomsList.add(room1);
        roomsList.add(room2);
        roomsList.add(room3);
        roomsList.add(room4);
    };

}
