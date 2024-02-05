package com.example;

import java.util.Date;

public class UtilityHelper {
    
    public static int[][] parseSeatNumbers(String[] seatNumbers) {
        int[][] result = new int[seatNumbers.length][2];
        for (int i = 0; i < result.length; i++) {
            result[i]= parseSeatNumber(seatNumbers[i]);
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
                System.out.println("Invalid choice. Please select 1 to " + menu.length);
                return false;
            }
        } catch (Exception e) {
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
