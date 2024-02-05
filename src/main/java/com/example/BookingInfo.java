package com.example;

import java.util.Date;

public class BookingInfo {
    private String seatIdentifier;
    private String ticketNumber;
    private String userId;
    private Date effectivity;

    public BookingInfo(String seatIdentifier, String ticketNumber, String userId, Date effectivity) {
        this.seatIdentifier = seatIdentifier;
        this.ticketNumber = ticketNumber;
        this.userId = userId;
        this.effectivity = effectivity;
    }

    public String getSeatIdentifier() {
        return seatIdentifier;
    }

    public Date getEffectivity() {
        return effectivity;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Booking Info - " +
                "User Id : '" + this.userId + '\'' +
                ", Ticker Number : '" + this.ticketNumber + '\'' +
                ", Booked Seats : '" + this.seatIdentifier + '\'' +
                ", Effective Date : '" + this.effectivity + '\''
                ;
    }
}
