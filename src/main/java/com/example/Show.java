package com.example;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Show {
    private String id;
    private String title;
    private SeatManager seatManager;
    private Bookings bookings;
    private int cancellationWindow;

    public Show(String id, String title, SeatManager seatManager, int cancellationWindow) {
        this.id = id;
        this.title = title;
        this.seatManager = seatManager;
        this.bookings = new Bookings();
        this.cancellationWindow = cancellationWindow;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public Bookings getBookings() {
        return this.bookings;
    }

    public SeatManager getSeatManager() {
        return this.seatManager;
    }

    public int getCancellationWindow() {
        return this.cancellationWindow;
    }

    public boolean bookSeat(int[][] selectedSeats, String username) {
        boolean allBookedSeatsIsAvailable = seatManager.isSeatAvailable(selectedSeats);
        if (allBookedSeatsIsAvailable) {
            String seatIdentifier = "";
            for (int[] selectedSeat : selectedSeats) {
                int row = selectedSeat[0];
                int column = selectedSeat[1];
                boolean booked = seatManager.bookSeat(row, column);
                if (booked) {
                    seatIdentifier = seatIdentifier.concat(",").concat(generateSeatIdentifier(row, column));
                }
            }
            seatIdentifier = seatIdentifier.substring(1);
            bookings.addBooking(this.id, username, seatIdentifier, UtilityHelper.getDateXminutesFromNow(cancellationWindow));
        } else {
            System.out.println("One or more selected seats are not available.");
        }
        return allBookedSeatsIsAvailable;
    }

    private String generateSeatIdentifier(int row, int column) {
        char rowLetter = (char) ('A' + row);
        return rowLetter + String.valueOf(column + 1);
    }

    public void cancelBookedSeats(String username){
        BookingInfo info = bookings.getBookingInfoForUser(this.getId(), username);
        String bookedSeats = info.getSeatIdentifier();
        Date effectivity = info.getEffectivity();
        if (effectivity.after(new Date())) {
            int [][] bookedSeatsArray = UtilityHelper.parseSeatNumbers(bookedSeats.split(","));
            for (int[] bookedSeat : bookedSeatsArray) {
                seatManager.clearSeat(bookedSeat[0], bookedSeat[1]);
            }
            bookings.cancelBooking(this.getId(), username);
            System.out.println("Booking cancelled successfully.");
        } else {
            System.out.println("Cannot cancel booking, cancellation period reached.");
        }
    }

    public void printShowInfo() {
        System.out.println("Show Number: " + this.getId());
        System.out.println("Title: " + this.getTitle());
        System.out.println("Seat Size: " + this.seatManager.getSeatsSize());
        System.out.println("Cancellation Window: " + this.getCancellationWindow());
        displaySeatingArrangement();
    }

    public void displaySeatingArrangement() {
        int rowSize = seatManager.getRowSize();
        int colSize = seatManager.getColSize();
        boolean[][] seatingArrangement = new boolean[rowSize][colSize];
        List<Seat> seats = seatManager.getSeats();
        for (Seat seat : seats) {
            int row = seat.getRow();
            int col = seat.getColumn();
            seatingArrangement[row][col] = seat.isBooked();
        }
        System.out.println("Available Seating Arrangement:");
        System.out.print("   ");
        for (int col = 0; col < colSize; col++) {
            System.out.print((col + 1) + " ");
        }
        System.out.println();

        for (int row = 0; row < rowSize; row++) {
            System.out.print((char) ('A' + row) + " ");
            for (int col = 0; col < colSize; col++) {
                if (seatingArrangement[row][col]) {
                    System.out.print("X ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Available Seat: " + this.seatManager.getAvailableSeatCount());
    }

    public boolean canBookShow(String bookingUsername) {
        if (seatManager.getAvailableSeatCount() <= 0) {
            return false;
        }

        if (getBookings().getBookingsByShow().containsKey(this.getId())) {
            Map<String, BookingInfo> bookings = getBookings().getBookingsByShow()
                    .get(this.getId());
            return !bookings.containsKey(bookingUsername);
        }

        return true;
    }

}
