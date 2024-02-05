package com.example.manager;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Seat;

public class SeatManager {
    private List<Seat> seats;
    private int rowSize, colSize;

    private static final int MAX_ROWS = 26;
    private static final int MAX_COLS = 10;

    public SeatManager(int rows, int columns) {
        if (rows > MAX_ROWS || columns > MAX_COLS) {
            throw new IllegalArgumentException("Maximum rows or columns exceeded");
        }
        this.rowSize = rows;
        this.colSize = columns;
        this.seats = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.seats.add(new Seat(i, j));
            }
        }
    }

    public int getColSize() {
        return colSize;
    }

    public int getRowSize() {
        return rowSize;
    }

    public List<Seat> getSeats() {
        return this.seats;
    }

    public int getSeatsSize() {
        return this.seats.size();
    }

    public boolean bookSeat(int row, int column) {
        Seat seat = getSeat(row, column);
        if (seat != null && !seat.isBooked()) {
            seat.setBooked(true);
            return true;
        }
        return false;
    }

    public boolean isSeatAvailable(int row, int column) {
        Seat seat = getSeat(row, column);
        return seat != null && !seat.isBooked();
    }

    public boolean isSeatAvailable(int[][] selectedSeats) {
        boolean result = true;
        for (int[] selectedSeat : selectedSeats) {
            int row = selectedSeat[0];
            int column = selectedSeat[1];
            if (!isSeatAvailable(row, column)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public Seat getSeat(int row, int column) {
        for (Seat seat : this.seats) {
            if (seat.getRow() == row && seat.getColumn() == column) {
                return seat;
            }
        }
        return null;
    }

    public void clearSeat(int row, int column){
        Seat seat = getSeat(row, column);
        seat.setBooked(false);
    }

    public int getAvailableSeatCount() {
        int availableSeats = 0;
        for (Seat seat : this.seats) {
            if (!seat.isBooked()) {
                availableSeats++;
            }
        }
        return availableSeats;
    }

}
