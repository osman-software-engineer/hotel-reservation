package com.hotelreservation.controller;

import com.hotelreservation.model.Customer;
import com.hotelreservation.model.Reservation;
import com.hotelreservation.model.Room;
import com.hotelreservation.service.AdminService;
import com.hotelreservation.service.AdminServiceImpl;

import java.util.List;

public class AdminController {

    private AdminService adminService;

    public AdminController() {
        this.adminService = new AdminServiceImpl();
    }

    public List<Customer> seeAllCustomers() {
        return adminService.seeAllCustomers();
    }

    public boolean addRoom() {
        return adminService.addRoom();
    }

    public List<Room> seeAllRooms() {
        return adminService.seeAllRooms();
    }

    public List<Room> seeAllAvailableRooms() {
        return adminService.seeAllRooms();
    }

    public void seeAllReservations() {
        adminService.seeAllReservations();
    }

    public void addTestData() {
        adminService.addTestData();
    }

}
