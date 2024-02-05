package com.example;

public class Seat {
    private int row;
    private int column;
    private boolean booked;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.booked = false;
    }

    public int getRow() {
		return row;
	}

    public int getColumn() {
		return column;
	}

	public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "row=" + row +
                ", column=" + column +
                ", booked=" + booked +
                '}';
    //TODO conver this to A1,A2 format
    }

}
