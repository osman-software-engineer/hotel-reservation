package com.hotelreservation.utils;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Utils {
    public static final String REGEX_MAIN_MENU_OPTIONS = "1|2|3|4|5";
    public static final String AVAILABLE = "Available";
    public static final String BOOKED = "Booked";
    public static final String REGEX_NAME = "(?i)[a-z](.{0,23}[a-z])";

    public static String isUserInputValid(boolean valid, String userInputFieldName, String userInput, String regex) {
        Scanner scanner;
        while (!valid) {
            System.out.println(userInputFieldName);
            scanner = new Scanner(System.in);
            userInput = scanner.nextLine();
            valid = userInput.matches(regex);
            if (valid) {
                break;
            }
        }
        return userInput;
    }

    public static boolean isCheckInDateValid(String checkInDate) {
        String[] temp = checkInDate.split("/");
        LocalDate checkedInDate = LocalDate.of(Integer.valueOf(temp[2]), Integer.valueOf(temp[0]), Integer.valueOf(temp[1]));
        LocalDate currentDate = LocalDate.now();
        return currentDate.isBefore(checkedInDate);
    }
    public static boolean isCheckOutDateValid(String checkOutDate,String checkInDate) {
        String[] temp1 = checkOutDate.split("/");
        String[] temp2 = checkInDate.split("/");
        LocalDate checkedOutDate = LocalDate.of(Integer.valueOf(temp1[2]), Integer.valueOf(temp1[0]), Integer.valueOf(temp1[1]));
        LocalDate checkedInDate = LocalDate.of(Integer.valueOf(temp2[2]), Integer.valueOf(temp2[0]), Integer.valueOf(temp2[1]));
        return checkedOutDate.isAfter(checkedInDate);
    }

    public static LocalDate formatDate(String date) {
        String[] temp1 = date.split("/");
        LocalDate formatedDate = LocalDate.of(Integer.valueOf(temp1[2]), Integer.valueOf(temp1[0]), Integer.valueOf(temp1[1]));

        return formatedDate;
    }
    public static void main(String[] args) {
        System.out.println(isCheckInDateValid("04/17/2021"));
        System.out.println(isCheckOutDateValid("04/16/2021","04/15/2021"));
    }
}
