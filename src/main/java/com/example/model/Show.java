package com.example.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.manager.BookingManager;
import com.example.manager.SeatManager;
import com.example.util.UtilityHelper;

public class Show {
    private String id;
    private String title;
    private SeatManager seatManager;
    private BookingManager bookingManager;
    private int cancellationWindow;
    private static final int MAX_CANCELLATION_WINDOW = 2880; // 2days

    public Show(String id, String title, SeatManager seatManager, int cancellationWindow) {
        this.id = id;
        this.title = title;
        this.seatManager = seatManager;
        this.bookingManager = new BookingManager();

        if (cancellationWindow < 0 || cancellationWindow > MAX_CANCELLATION_WINDOW) {
            throw new IllegalArgumentException("Cancellation window must be from 0 to " + MAX_CANCELLATION_WINDOW);
        }
        this.cancellationWindow = cancellationWindow;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public BookingManager getBookingManager() {
        return this.bookingManager;
    }

    public SeatManager getSeatManager() {
        return this.seatManager;
    }

    public int getCancellationWindow() {
        return this.cancellationWindow;
    }

    public Map<String, BookingInfo> getAllBookings() {
        return bookingManager.getAllBookingsForShow(id);
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
            bookingManager.addBooking(this.id, username, seatIdentifier,
                    UtilityHelper.getDateXminutesFromNow(cancellationWindow));
        } else {
            System.out.println("One or more selected seats are not available.");
        }
        return allBookedSeatsIsAvailable;
    }

    private String generateSeatIdentifier(int row, int column) {
        char rowLetter = (char) ('A' + row);
        return rowLetter + String.valueOf(column + 1);
    }

    public void cancelBookedSeats(String username) {
        BookingInfo info = bookingManager.getBookingInfoForUser(this.getId(), username);
        String bookedSeats = info.getSeatIdentifier();
        Date effectivity = info.getEffectivity();
        if (effectivity.after(new Date())) {
            int[][] bookedSeatsArray = UtilityHelper.parseSeatNumbers(bookedSeats.split(","));
            for (int[] bookedSeat : bookedSeatsArray) {
                seatManager.clearSeat(bookedSeat[0], bookedSeat[1]);
            }
            bookingManager.cancelBooking(this.getId(), username);
            System.out.println("Booking cancelled successfully.");
        } else {
            System.out.println("Cannot cancel booking, cancellation period reached.");
        }
    }

    public void printShowInfo() {
        System.out.println("Show Number         : " + this.getId());
        System.out.println("Show Title          : " + this.getTitle());
        System.out.println("Seat Size           : " + this.seatManager.getSeatsSize());
        System.out.println("Cancellation Window : " + this.getCancellationWindow() + " minutes");
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
        System.out.println("Available Seat: " + this.seatManager.getAvailableSeatCount());
    }

    public boolean canBookShow(String username) {
        if (seatManager.getAvailableSeatCount() <= 0) {
            return false;
        }
        return !getAllBookings().containsKey(username);
    }
}
