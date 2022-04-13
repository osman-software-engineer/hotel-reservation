package com.hotelreservation;

import com.hotelreservation.controller.AdminController;
import com.hotelreservation.controller.CustomerController;
import com.hotelreservation.utils.Utils;

import java.util.Scanner;

public class HotelReservationApplication {

    public static void main(String[] args) throws Exception {
        CustomerController customerController = new CustomerController();
        AdminController adminController = new AdminController();
        mainMenu(customerController, adminController);
    }

    public static void mainMenu(CustomerController customerController, AdminController adminController) throws Exception {
        try {
            int optionChoosed;
            do {
                System.out.println("\nWelcome to Hotel Reservation Console Application");
                System.out.println("------------------------------------------------");
                System.out.println("1. Find and reserve a room");
                System.out.println("2. See my reservations");
                System.out.println("3. Create an account");
                System.out.println("4. Admin");
                System.out.println("5. Exit");
                System.out.println("------------------------------------------------");
                System.out.println("Please select a number for the menu option");
                Scanner scanner = new Scanner(System.in);
                String option = scanner.nextLine();
                boolean isOptionValid = option.matches(Utils.REGEX_MAIN_MENU_OPTIONS);
                option = Utils.isUserInputValid(isOptionValid, "Please choose valid menu option ranging from 1-5", option, Utils.REGEX_MAIN_MENU_OPTIONS);
                optionChoosed = Integer.valueOf(option);
                switch (optionChoosed) {
                    case 1:
                        customerController.searchForAvailableRooms();
                        customerController.bookRoom();
                        break;
                    case 2:
                        customerController.seeMyReservations();
                        break;

                    case 3:
                        customerController.createCustomerAccount();
                        break;

                    case 4:
                        adminMenu(customerController, adminController);
                        break;

                    case 5:
                        System.out.println("Thank you for using Hotel Reservation Console Application. Have a good one.");
                        System.exit(1);
                        break;
                    case 6:
                     //adminController.addTestData();
                        break;
                }
            } while (optionChoosed != 5);
        } catch (Exception e) {
            throw  new Exception("Sorry we couldn't process your request at this time due to a system issue. Please contact customer service number xxx-xxx-xxxx");
        }

    }


    private static void adminMenu(CustomerController customerController, AdminController adminController) {
        int optionChoosed;
        do {
            Scanner scanner;
            System.out.println("\nAdmin Menu");
            System.out.println("------------------------------------------------");
            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. see all Reservations");
            System.out.println("4. Add a Room");
            System.out.println("5. Add Test Data");
            System.out.println("6. Back to Main Menu");
            System.out.println("------------------------------------------------");
            System.out.println("Please select a number for the menu option");
            scanner = new Scanner(System.in);
            optionChoosed = scanner.nextInt();
            switch (optionChoosed) {
                case 1:
                    adminController.seeAllCustomers();
                    break;
                case 2:
                    adminController.seeAllRooms();
                case 3:
                    adminController.seeAllReservations();
                    break;
                case 4:
                    adminController.addRoom();
                    break;
                case 5:
                    System.out.println("This Scenario is not covered in Admin Scenarios Project Requirements.");
                    break;

            }
        } while (optionChoosed != 6);

    }
}
