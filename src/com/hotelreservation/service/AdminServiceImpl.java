package com.hotelreservation.service;

import com.hotelreservation.model.Customer;
import com.hotelreservation.model.Reservation;
import com.hotelreservation.model.Room;
import com.hotelreservation.model.RoomType;
import com.hotelreservation.repository.HotelReservationRepository;
import com.hotelreservation.repository.HotelReservationRepositoryImpl;
import com.hotelreservation.utils.Utils;


import java.util.List;
import java.util.Scanner;

public class AdminServiceImpl implements AdminService {

    private static HotelReservationRepository hotelReservationRepository = new HotelReservationRepositoryImpl();
    private List<Reservation> allReservation = hotelReservationRepository.seeAllReservations();
    public static final String REGEX_Y_N = "y|n";
    public static final String REGEX_ROOM_NUMBER = "[+-]?[0-9][0-9]*";
    public static final String AVAILABLE = "Available";

    @Override
    public List<Customer> seeAllCustomers() {
        List<Customer> customerList = hotelReservationRepository.seeAllCustomers();
        if (customerList.size() == 0) {
            System.out.println("There are no Customers to display.");
        } else {
            for (Customer customer : customerList) {
                System.out.println("First Name: " + customer.getFirstName() + " Last Name: " + customer.getLastName() + " Email : " + customer.getEmail());
            }
        }
        return customerList;
    }

    @Override
    public List<Room> seeAllRooms() {
        List<Room> roomList = hotelReservationRepository.seeAllRooms();
        if (roomList.size() == 0) {
            System.out.println("There are no rooms to display. Please add rooms.");
        } else {
            for (Room room : roomList) {
                String titleCase = room.getRoomType().toString().toLowerCase();
                titleCase = room.getRoomType().toString().substring(0, 1).toUpperCase() + room.getRoomType().toString().toLowerCase().substring(1, titleCase.length());
                System.out.println("Room Number: " + room.getRoomNumber() + " " + titleCase + " bed Room Price: $ " + room.getRoomPrice());
            }
        }

        return roomList;
    }

    @Override
    public void seeAllReservations() {
        if (allReservation.size() == 0) {
            System.out.println("There are no reservations.");
            return;
        }
        for (Reservation reservation : allReservation) {
            System.out.println("Reservation");
            System.out.println(reservation.getCustomer().getFirstName() + " " + reservation.getCustomer().getLastName());
            System.out.println("Room: " + reservation.getRoom().getRoomNumber() + " " + reservation.getRoom().getRoomType().toString() + " Bed");
            System.out.println("Price: " + reservation.getRoom().getRoomPrice() + " price per night");
            System.out.println("Checkin Date :" + reservation.getCheckInDate());
            System.out.println("CheckOut Date :" + reservation.getCheckOutDate());

        }
    }

    @Override
    public boolean addRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter room number");
        String roomNumber = scanner.nextLine();
        boolean isRoomNumberValid = roomNumber.matches(REGEX_ROOM_NUMBER);
        roomNumber =  Utils.isUserInputValid(isRoomNumberValid, "Please Enter a valid room number which should be numeric", roomNumber, REGEX_ROOM_NUMBER);
        scanner = new Scanner(System.in);
        System.out.println("Enter price per night");
        String roomPrice = scanner.nextLine();
        boolean isRoomPriceValid = roomPrice.matches(REGEX_ROOM_NUMBER);
        roomPrice =  Utils.isUserInputValid(isRoomNumberValid, "Please Enter a valid room price per night which should be numeric", roomPrice, REGEX_ROOM_NUMBER);
        System.out.println("Enter room type: 1 for single bed, 2 for double bed");
        scanner = new Scanner(System.in);
        String roomType = scanner.nextLine();
        boolean isRoomTypeValid = roomType.matches("1|2");
        roomType =  Utils.isUserInputValid(isRoomTypeValid, "Please enter a valid room type, which should either 1 or 2.", roomType, "1|2");
        RoomType type = roomType.equals("1") ? RoomType.SINGLE : RoomType.DOUBLE;
        Room room = new Room(roomNumber, Double.valueOf(roomPrice), type, AVAILABLE);
        hotelReservationRepository.addRoom(room);
        System.out.println("Would you like to add another room y/n");
        scanner = new Scanner(System.in);
        String anotherRoom = scanner.nextLine();
        boolean isAnotherRoomValid = anotherRoom.matches(REGEX_Y_N);
        anotherRoom =  Utils.isUserInputValid(isAnotherRoomValid, "Please enter Y (yes) or n (No)", anotherRoom, REGEX_Y_N);
        while (!anotherRoom.equals("n")) {
            scanner = new Scanner(System.in);
            System.out.println("Enter room number");
            roomNumber = scanner.nextLine();
            isRoomNumberValid = roomNumber.matches(REGEX_ROOM_NUMBER);
            roomNumber =  Utils.isUserInputValid(isRoomNumberValid, "Please Enter a valid room number which should be numeric", roomNumber, REGEX_ROOM_NUMBER);
            List<Room> availableRooms = hotelReservationRepository.seeAllRooms();
            for (Room availableRoom : availableRooms) {
                if (room.getRoomNumber().equals(roomNumber)) {
                    do {
                        System.out.println("Room Number ::" + availableRoom.getRoomNumber() + " already exists please choose a different Room Number");
                        scanner = new Scanner(System.in);
                        System.out.println("Enter a different room number which shouldn't be equal to " + availableRoom.getRoomNumber());
                        roomNumber = scanner.nextLine();
                        isRoomNumberValid = roomNumber.matches(REGEX_ROOM_NUMBER);
                        roomNumber =  Utils.isUserInputValid(isRoomNumberValid, "Please Enter a valid room number which should be numeric", roomNumber, REGEX_ROOM_NUMBER);

                    } while (room.getRoomNumber().equals(roomNumber));

                }
            }
            scanner = new Scanner(System.in);
            System.out.println("Enter price per night");
            roomPrice = scanner.nextLine();
            isRoomPriceValid = roomPrice.matches(REGEX_ROOM_NUMBER);
            roomPrice =  Utils.isUserInputValid(isRoomNumberValid, "Please Enter a valid room price per night which should be numeric", roomPrice, "(?i)[a-z](.{0,23}[a-z])");
            System.out.println("Enter room type: 1 for single bed, 2 for double bed");
            scanner = new Scanner(System.in);
            roomType = scanner.nextLine();
            isRoomTypeValid = roomType.matches("1|2");
            roomType =  Utils.isUserInputValid(isRoomTypeValid, "Please enter a valid room type, which should either 1 or 2.", roomType, "1|2");
            type = roomType.equals("1") ? RoomType.SINGLE : RoomType.DOUBLE;
            hotelReservationRepository.addRoom(new Room(roomNumber, Double.valueOf(roomPrice), type, AVAILABLE));
            System.out.println("Would you like to add another room y/n");
            scanner = new Scanner(System.in);
            anotherRoom = scanner.nextLine();
            isAnotherRoomValid = anotherRoom.matches(REGEX_Y_N);
            anotherRoom =  Utils.isUserInputValid(isRoomTypeValid, "Please enter Y (yes) or n (No)", anotherRoom, REGEX_Y_N);
        }
        System.out.println("Thanks for adding a room. Press 6 or any key to go back to admin menu.");
        scanner = new Scanner(System.in);
        while (!scanner.hasNext()) {
            System.out.println("Thanks for adding a room. Press 6 or any key to go back to admin menu.");
            if (scanner.hasNext()) {
                break;
            }
        }

        return true;
    }

    @Override
    public void addTestData() {
        hotelReservationRepository.addTestData();
    }

}
