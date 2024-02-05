package com.example.manager;

import java.util.HashMap;
import java.util.Map;

import com.example.model.BookingInfo;
import com.example.model.Show;
import com.example.model.User;
import com.example.util.UtilityHelper;

public class ShowManager {
    private Map<String, Show> showsLibrary;
    private User currentUser;

    public ShowManager() {
        this.showsLibrary = new HashMap<>();
        this.currentUser = null;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Map<String, Show> getShowsLibrary() {
        return showsLibrary;
    }

    public void addShow(Show show) {
        showsLibrary.put(show.getId(), show);
        show.printShowInfo();
        System.out.println("Show successfully added.");
    }

    public void updateShow(String showNumber, Show updatedShow) {
        showsLibrary.put(showNumber, updatedShow);
        updatedShow.printShowInfo();
        System.out.println("Show successfully updated.");
    }

    public void removeShow(String showNumber) {
        showsLibrary.remove(showNumber);
    }

    public Show getShow(String showNumber) {
        return showsLibrary.get(showNumber);
    }

    public boolean showExists(String showNumber) {
        return showsLibrary.containsKey(showNumber);
    }

    public void displayShowList() {
        if (showsLibrary != null && showsLibrary.size() > 0) {
            for (String item : showsLibrary.keySet()) {
                System.out.println(item.concat(" - ").concat(showsLibrary.get(item).getTitle()));
            }
        } else {
            System.out.println("No existing shows.");
        }
    }

    public boolean bookShow(Show selectedShow, String[] selectedSeatsArray) {
        return selectedShow.bookSeat(UtilityHelper.parseSeatNumbers(selectedSeatsArray), currentUser.getUsername());
    }

    public void cancelShowBooking(String ticketNumber) {
        Show selectedShow = showsLibrary.get(ticketNumber.split("-")[0]);
        String username = getCurrentUser().getUsername();

        if (selectedShow != null && username != null) {
            BookingInfo bookingInfo = selectedShow.getAllBookings().get(username);
            if (bookingInfo != null && bookingInfo.getTicketNumber().equals(ticketNumber)){
                selectedShow.cancelBookedSeats(username);
            } else {
                System.out.println("Booking not found for this ticket " +  ticketNumber);
            }
        } else {
            System.out.println("Show not found for this ticket " + ticketNumber);
        }
    }

}
