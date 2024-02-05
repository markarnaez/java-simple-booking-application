# Simple Booking Application

## Use Case

Build a simple Java application for the use case to book a show. The program must take input from the command line.
The program would set up available seats per show, allow buyers to select 1 or more available seats, and buy/cancel tickets. The application shall cater to the below 2 types of users & their requirements – (1) `Admin` and (2) `Buyer`

### Admin

The users should be able to set up and view the list of shows and seat allocations.

Commands to be implemented for Admin :

1. **Setup**  `<Show Number> <Number of Rows> <Number of seats per row> <Cancellation window in minutes> `
<br> To set the number of seats per show

2. **View** `<Show Number>`
<br> To display Show Number, Ticket#, Buyer Phone#, Seat Numbers allocated to the buyer

### Buyer

The users should be able to retrieve the list of available seats for a show, select 1 or more seats, buy and cancel tickets.

Commands to be implemented for Buyer :

1. **Availability** `<Show Number>`
<br> To list all available seat numbers for a show. E,g A1, F4 etc

2. **Book** `<Show Number> <Phone#> <Comma separated list of seats>`
<br> To book a ticket. This must generate a unique ticket # and display

3. **Cancel** `<Ticket#> <Phone#>`
<br> To cancel a ticket. See constraints in the section below

*Constraints:*

- Assume the max seats per row are 10 and the max rows are 26.
- Example seat number A1,H5 etc.
- The “Add” command for admin must ensure rows cannot be added beyond the upper limit of 26.
- After booking, the User can cancel the seats within a time window of 2 minutes (configurable). Cancellation after that is not allowed.
- Only one booking per phone# is allowed per show.

## Additional Assumptions

1. All number inputs are positive whole numbers.
2. Admin users cannot book a show as well as cancel an existing booking.
3. Since the data is saved in the memory, running a new instance of the application will have a different set of data.
4. Admin username and password are fixed (admin/123) and cannot be changed by a user.
5. There is no limit on how many seats a Buyer can book, however, if one of the seats is not available, it will not book all the provided seats.
6. Seat input is comma-separated, with no spaces in the format of row[A-Z]col[1-10], (e.g. A1,C3,Z10).
7. Admin cannot update an existing show however can remove it without constraints.
8. Maximum cancellation window value is 2880 mins (2 days).
9. Buyer username is a 5-digit input (serves as phone#).
