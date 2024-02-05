package com.example.action;

import java.util.Map;
import java.util.Scanner;

import com.example.manager.ShowManager;
import com.example.model.BookingInfo;
import com.example.model.Show;
import com.example.model.User;

public class ListAllBuyerBookings implements UserAction {
    private ShowManager showManager;
    private User user;

    public ListAllBuyerBookings(ShowManager showManager, User user) {
        this.showManager = showManager;
        this.user = user;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println("List of Bookings");
        System.out.println("--------------------------------");
        String username = user.getUsername();
        Map<String, Show> showLibrary = showManager.getShowsLibrary();
        for (String showId : showLibrary.keySet()) {
            BookingInfo info = showManager.getShow(showId).getBookingManager().getBookingInfoForUser(showId, username);
            if (info != null) {
                System.out.println(info.toString());
            }
        }
    }

}
