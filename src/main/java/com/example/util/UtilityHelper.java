package com.example.util;

import java.util.Date;
import java.util.Scanner;

public class UtilityHelper {

    private UtilityHelper() {
    }

    public static String[] showMenu(String[] menu) {
        for (int i = 0; i < menu.length; i++) {
            System.out.println(String.valueOf(i + 1).concat(". ").concat(menu[i]));
        }
        System.out.print("Please select an option: ");
        return menu;
    }

    public static String getValidChoice(String[] menu, Scanner scanner) {
        String choice = scanner.nextLine();
        while (!UtilityHelper.isChoiceValid(menu, choice)) {
            showMenu(menu);
            choice = scanner.nextLine();
        }
        return choice;
    }

    public static int[][] parseSeatNumbers(String[] seatNumbers) {
        int[][] result = new int[seatNumbers.length][2];
        for (int i = 0; i < result.length; i++) {
            result[i] = parseSeatNumber(seatNumbers[i]);
        }
        return result;
    }

    public static int[] parseSeatNumber(String seatNumber) {
        if (seatNumber.length() < 2) {
            throw new IllegalArgumentException("Invalid seat number: " + seatNumber);
        }

        char rowChar = seatNumber.charAt(0);
        int row = Character.toUpperCase(rowChar) - 'A';
        int column;
        try {
            column = Integer.parseInt(seatNumber.substring(1)) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid seat number: " + seatNumber);
        }
        return new int[] { row, column };
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static boolean isChoiceValid(String[] menu, String choice) {
        try {
            int index = Integer.parseInt(choice);
            if (!(index >= 1 && index <= menu.length)) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            System.out.println("Invalid choice. Please select from 1 to " + menu.length);
            return false;
        }
        return true;
    }

    public static Date getDateXminutesFromNow(int minutes) {
        Date currentTime = new Date();
        long timeInMillis = currentTime.getTime() + (minutes * 60 * 1000);
        Date result = new Date(timeInMillis);
        return result;
    }
}
