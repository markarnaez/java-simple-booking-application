package com.example;

import java.util.Map;

public class AdminViewShowAction extends ViewShowAction {

    public AdminViewShowAction(ShowManager showManager) {
        super(showManager);
    }

    @Override
    protected void displayMoreDetails(Show selectedShow) {
        Map<String, Map<String, BookingInfo>> allBookings = selectedShow.getBookingManager().getBookingsByShow();
        if (allBookings != null) {
            Map<String, BookingInfo> bookings = allBookings.get(selectedShow.getId());
            if (bookings != null) {
                for (BookingInfo booking : bookings.values()) {
                    System.out.println(booking.toString());
                }
            }
        }
    }
}
