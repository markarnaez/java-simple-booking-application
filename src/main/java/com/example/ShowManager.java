package com.example;

import java.util.HashMap;
import java.util.Map;

public class ShowManager {
     private Map<String, Show> showsLibrary;
     
     public ShowManager() {
         this.showsLibrary = new HashMap<>();
        }
        
    public Map<String, Show> getShowsLibrary() {
            return showsLibrary;
        }

    public void addShow(String showNumber, Show show) {
        showsLibrary.put(showNumber, show);
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

    public boolean bookShow(Show selectedShow, String[] selectedSeatsArray, String username){
        return selectedShow.bookSeat(UtilityHelper.parseSeatNumbers(selectedSeatsArray), username);
    }

    public void cancelShowBooking(String ticketNumber, String username){
        Show selectedShow = showsLibrary.get(ticketNumber.split("-")[0]);
        selectedShow.cancelBookedSeats(username);
        // for (Show show : showsLibrary.values()) {
        //     for ( Map<String, BookingInfo> item: show.getBookings().getBookingsByShow().values()) {
        //         if(item.containsKey(username) && (item.get(username).getTicketNumber().equals(ticketNumber))){
        //                 show.cancelBookedSeats(username);
        //                 return;
        //         }
        //     }
        // }
    }



}
