package com.hotelreservation.service;

import com.hotelreservation.model.Customer;
import com.hotelreservation.model.Reservation;
import com.hotelreservation.model.Room;
import com.hotelreservation.repository.HotelReservationRepository;
import com.hotelreservation.repository.HotelReservationRepositoryImpl;
import com.hotelreservation.utils.Utils;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CustomerServiceImpl implements CustomerService {

    public static final String REGEX_CHECK_IN_CHECK_OUT_DATE_FORMAT = "^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$";
    public static final String REGEX_Y_N = "y|n";
    public static final String REGEX_ROOM_NUMBER = "[+-]?[0-9][0-9]*";
    public static final String REGEX_EMAIL = "(?i)[a-z](.{0,23}[a-z])";
    public static final String AVAILABLE = "Available";
    public static final String REGEX_NAME = "(?i)[a-z](.{0,23}[a-z])";
    private static HotelReservationRepository hotelReservationRepository = new HotelReservationRepositoryImpl();

    private String checkInDate;
    private String checkOutDate;
    private List<Room> allRooms = hotelReservationRepository.seeAllRooms();
    private List<Customer> customers = hotelReservationRepository.seeAllCustomers();
    private List<Reservation> allReservation = hotelReservationRepository.seeAllReservations();
    private Customer existingCustomer = null;
    private Room availableRoom = null;

    @Override
    public boolean createCustomerAccount() {
        Scanner scanner;
        System.out.println("Enter First Name");
        scanner = new Scanner(System.in);
        String firstName = scanner.nextLine();
        boolean isFirstNameValid = firstName.matches( REGEX_NAME);
        firstName = Utils.isUserInputValid(isFirstNameValid, "Enter First Name", firstName,  REGEX_NAME);
        System.out.println("Enter Last Name");
        scanner = new Scanner(System.in);
        String lastName = scanner.nextLine();
        boolean isLastNameValid = lastName.matches( REGEX_NAME);
        lastName = Utils.isUserInputValid(isLastNameValid, "Enter Last Name", lastName,  REGEX_NAME);
        System.out.println("Enter Email Address");
        scanner = new Scanner(System.in);
        String emailID = scanner.nextLine();
        boolean isEmailIDValid = emailID.matches( REGEX_NAME);
        emailID = Utils.isUserInputValid(isEmailIDValid, "Enter Email Address", emailID,  REGEX_NAME);
        Customer customer = new Customer(firstName, lastName, emailID);
        hotelReservationRepository.createCustomerAccount(customer);
        System.out.println("Thanks for creating customer account.Press 6 or any key to go back to main menu.");
        scanner = new Scanner(System.in);
        while (!scanner.hasNext()) {
            System.out.println("Thanks for creating customer account.Press 6 or any key to go back to main menu.");
            if (scanner.hasNext()) {
                break;
            }
        }
        return true;
    }

    public List<Room> searchForAvailableRooms() {
        Scanner scanner;
        System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2020");
        scanner = new Scanner(System.in);
        checkInDate = scanner.nextLine();
        boolean isCheckInDateValid = checkInDate.matches( REGEX_CHECK_IN_CHECK_OUT_DATE_FORMAT);
        checkInDate = Utils.isUserInputValid(isCheckInDateValid, "Please select a valid date format which should be in mm/dd/yyy", checkInDate,  REGEX_CHECK_IN_CHECK_OUT_DATE_FORMAT);
        if (!(Utils.isCheckInDateValid(checkInDate))) {
            do {
                System.out.println("checkin date can't be in past and should be greater than today's date.");
                System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2020");
                scanner = new Scanner(System.in);
                checkInDate = scanner.nextLine();
                isCheckInDateValid = checkInDate.matches( REGEX_CHECK_IN_CHECK_OUT_DATE_FORMAT);
                checkInDate = Utils.isUserInputValid(isCheckInDateValid, "Please select a valid date format which should be in mm/dd/yyy", checkInDate,  REGEX_CHECK_IN_CHECK_OUT_DATE_FORMAT);

            } while (!(Utils.isCheckInDateValid(checkInDate)));
        }
        System.out.println("Enter CheckOut Date mm/dd/yyyy example 04/16/2021");
        scanner = new Scanner(System.in);
        checkOutDate = scanner.nextLine();
        boolean isCheckOutDateValid = checkOutDate.matches( REGEX_CHECK_IN_CHECK_OUT_DATE_FORMAT);
        checkOutDate = Utils.isUserInputValid(isCheckOutDateValid, "Please select a valid date format which should be in mm/dd/yyy", checkOutDate,  REGEX_CHECK_IN_CHECK_OUT_DATE_FORMAT);
        if (!(Utils.isCheckOutDateValid(checkOutDate, checkInDate))) {
            do {
                System.out.println("CheckOut date can't be on or before check In Date.");
                System.out.println("Enter CheckOut Date mm/dd/yyyy example 02/01/2020");
                scanner = new Scanner(System.in);
                checkOutDate = scanner.nextLine();
                isCheckInDateValid = checkOutDate.matches( REGEX_CHECK_IN_CHECK_OUT_DATE_FORMAT);
                checkOutDate = Utils.isUserInputValid(isCheckInDateValid, "Please select a valid date format which should be in mm/dd/yyy", checkOutDate,  REGEX_CHECK_IN_CHECK_OUT_DATE_FORMAT);
            } while (!(Utils.isCheckOutDateValid(checkOutDate, checkInDate)));
        }
        List<Room> allRooms = hotelReservationRepository.seeAllRooms();
        if (allRooms.size() == 0) {
            System.out.println("There are no available rooms to display. Sorry for the inconvenience");
        } else {
            for (Room availableRooms : allRooms) {
                if (availableRooms.getStatus().equals(Utils.AVAILABLE)) {
                    String titleCase = availableRooms.getRoomType().toString().toLowerCase();
                    titleCase = availableRooms.getRoomType().toString().substring(0, 1).toUpperCase() + availableRooms.getRoomType().toString().toLowerCase().substring(1, titleCase.length());
                    System.out.println("Room Number: " + availableRooms.getRoomNumber() + " " + titleCase + " bed Room Price: $ " + availableRooms.getRoomPrice());

                }
            }
        }
        return hotelReservationRepository.seeAllRooms();
    }

    public boolean bookRoom() {
        Scanner scanner;
        System.out.println("Would you like to book a room? y/n");
        scanner = new Scanner(System.in);
        String bookRoom = scanner.nextLine();
        boolean isBookRoomValid = bookRoom.matches( REGEX_Y_N);
        bookRoom = Utils.isUserInputValid(isBookRoomValid, "Please enter y (Yes) or n (No)", bookRoom,  REGEX_Y_N);
        if (bookRoom.equalsIgnoreCase("y")) {
            System.out.println("Do you have an account with us? y/n");
            scanner = new Scanner(System.in);
            String haveAccount = scanner.nextLine();
            boolean haveAccountValid = haveAccount.matches( REGEX_Y_N);
            haveAccount = Utils.isUserInputValid(haveAccountValid, "Please enter y (Yes) or n (No)", haveAccount,  REGEX_Y_N);
            if (haveAccount.equalsIgnoreCase("y")) {
                System.out.println("Enter the email id you used when creating the account");
                scanner = new Scanner(System.in);
                String emailID = scanner.nextLine();
                boolean isEmailIDValid = emailID.matches( REGEX_NAME);
                emailID = Utils.isUserInputValid(isEmailIDValid, "Enter Email format: name@domain.com", emailID,  REGEX_EMAIL);
                for (Customer customer : customers) {
                    if (customer.getEmail().equalsIgnoreCase(emailID)) {
                        existingCustomer = customer;
                        break;
                    }
                    if (Objects.isNull(availableRoom)) {
                        System.out.println("There is no customer in our system with this email ID.Please create a user account");
                        return false;
                    }
                }
                System.out.println("What room number would you like to reserve ? Please enter room number for example 100 or 200");
                scanner = new Scanner(System.in);
                String roomToBeReserved = scanner.nextLine();
                boolean isRoomToBeReservedValid = roomToBeReserved.matches( REGEX_ROOM_NUMBER);
                roomToBeReserved = Utils.isUserInputValid(isRoomToBeReservedValid, "Please enter a valid room number example 100 or 200", roomToBeReserved,  REGEX_ROOM_NUMBER);
                for (Room room : allRooms) {
                    if (room.getRoomNumber().equalsIgnoreCase(roomToBeReserved) && room.getStatus().equals( AVAILABLE)) {
                        availableRoom = room;
                        break;
                    }
                }
                if (Objects.isNull(availableRoom)) {
                    System.out.println("There is no room available with this room number.");
                    return false;
                }
                Reservation reservation = new Reservation(existingCustomer, availableRoom, Utils.formatDate(checkInDate), Utils.formatDate(checkOutDate));
                hotelReservationRepository.createReservation(reservation);
                System.out.println("Reservation");
                System.out.println(existingCustomer.getFirstName() + " " + existingCustomer.getLastName());
                System.out.println("Room: " + availableRoom.getRoomNumber() + " " + availableRoom.getRoomType().toString() + " Bed");
                System.out.println("Price: " + availableRoom.getRoomPrice() + " price per night");
                System.out.println("Checkin Date :" + checkInDate);
                System.out.println("CheckOut Date :" + checkOutDate);
                int index = allRooms.indexOf(availableRoom);
                allRooms.get(index).setStatus(Utils.BOOKED);
                return true;
            } else {
                System.out.println("Would you like to create an account with us? y/n");
                scanner = new Scanner(System.in);
                String createAccount = scanner.nextLine();
                boolean isCreateAccountValid = createAccount.matches( REGEX_Y_N);
                createAccount = Utils.isUserInputValid(isCreateAccountValid, "Please enter y (Yes) or n (No)", createAccount,  REGEX_Y_N);
                if (createAccount.equalsIgnoreCase("y")) {
                    createCustomerAccount();
                } else {
                    System.out.println("It is mandatory to create a customer account in order to book a room.");
                    return false;
                }
            }
        } else {
            System.out.println("Thanks for visiting our find and book a room services.Press 6 or any key to go back to main menu.");
            scanner = new Scanner(System.in);
            while (!scanner.hasNext()) {
                System.out.println("TThanks for visiting our find and book a room services.Press 6 or any key to go back to main menu.");
                if (scanner.hasNext()) {
                    break;
                }
            }
        }
        return false;

    }

    @Override
    public void viewReservations() {
        System.out.println("Please enter your user account email id to view your reservations");
        System.out.println("Enter Email format: name@domain.com");
        Scanner scanner = new Scanner(System.in);
        String emailID = scanner.nextLine();
        boolean isEmailIDValid = emailID.matches( REGEX_NAME);
        emailID = Utils.isUserInputValid(isEmailIDValid, "Enter Email format: name@domain.com", emailID,  REGEX_EMAIL);
        String temp = null;
        for (Reservation reservation : allReservation) {
            if (reservation.getCustomer().getEmail().equalsIgnoreCase(emailID)) {
                temp = reservation.getCustomer().getEmail();
                System.out.println("********************************************************");
                System.out.println("Reservation");
                System.out.println(reservation.getCustomer().getFirstName() + " " + reservation.getCustomer().getLastName());
                System.out.println("Room: " + reservation.getRoom().getRoomNumber() + " " + reservation.getRoom().getRoomType().toString() + " Bed");
                System.out.println("Price: " + reservation.getRoom().getRoomPrice() + " price per night");
                System.out.println("Checkin Date :" + reservation.getCheckInDate());
                System.out.println("CheckOut Date :" + reservation.getCheckOutDate());
                System.out.println("********************************************************");
            }

        }
        if (Objects.isNull(temp)) {
            System.out.println("There are no reservations to display.");
        }
    }

}
