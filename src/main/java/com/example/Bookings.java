package com.example;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Bookings {
    private Map<String, Map<String, BookingInfo>> bookingsByShow;
    private Map<String, Integer> ticketCounters;

    public Bookings() {
        this.bookingsByShow = new HashMap<>();
        this.ticketCounters = new HashMap<>();
    }

    public Map<String, Map<String, BookingInfo>> getBookingsByShow() {
        return this.bookingsByShow;
    }

    public void addBooking(String showId, String userId, String seatIdentifier, Date effectivity) {
        if (!bookingsByShow.containsKey(showId)) {
            bookingsByShow.put(showId, new HashMap<>());
        }
        String ticketNumber = generateTicketNumber(showId);
        Map<String, BookingInfo> bookings = bookingsByShow.get(showId);
        bookings.put(userId, new BookingInfo(seatIdentifier, ticketNumber, userId, effectivity));
    }

    public void cancelBooking(String showId, String userId) {
        Map<String, BookingInfo> bookings = bookingsByShow.get(showId);
        bookings.remove(userId);
    }

    public BookingInfo getBookingInfoForUser(String showId, String userId) {
        if (bookingsByShow.containsKey(showId)) {
            Map<String, BookingInfo> bookings = bookingsByShow.get(showId);
            if (bookings.containsKey(userId)) {
                return bookings.get(userId);
            }
        }
        return null;
    }

    private String generateTicketNumber(String showId) {
        ticketCounters.putIfAbsent(showId, 1);
        int ticketNumber = ticketCounters.get(showId);
        ticketCounters.put(showId, ticketNumber + 1);
        return showId.concat("-").concat(String.format("%03d", ticketNumber));
    }

}
