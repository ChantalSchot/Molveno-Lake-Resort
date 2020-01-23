package controller;

import model.Booking;
import model.Guest;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingControllerTest {
    private BookingController bookingController;
    private RoomController roomController;
    private GuestController guestcontroller;

    @BeforeEach
    void init() throws EntityNotFoundException, ParseException {
        bookingController = new BookingController();
        roomController = new RoomController();
        guestcontroller = new GuestController();
        Room[] rooms = roomController.getRoomList().toArray(new Room[0]);
        List<Guest> guests = guestcontroller.getGuestList();

        bookingController.postBooking(new Booking( guests.get(0), rooms));
        bookingController.postBooking(new Booking( guests.get(1), rooms));
        bookingController.postBooking(new Booking( guests.get(2), rooms));
}

    @Test
    void getBooking() throws EntityNotFoundException {
        try {
            Booking booking = bookingController.getBooking(1);

            assertEquals("101", booking.getBookedRooms()[0].getRoomNumber());
            assertEquals(1, booking.getBookingNumber());
            assertEquals(1, booking.getGuest().getGuestID());
            System.out.println("De booking die is opgehaald heeft kamernummer " + booking.getBookedRooms()[0].getRoomNumber()
                    + ", geboekt door de klant " + booking.getGuest().getName());

        }
     catch(EntityNotFoundException e) {
        System.out.println(e);
        assertEquals(e.toString(), "");
    }
    }


    @Test
    void getBookings() {
        List<Booking> bookingList = bookingController.GetBookings();
        assertEquals(3, bookingList.size());
        if(!bookingList.isEmpty()) {
            System.out.println("Lijst van Boekingen:");
            for(Booking booking : bookingList) {
                System.out.println(booking.toString());
            }
        }
    }

    @Test
    void putBooking() {
    }

    @Test
    void postBooking() {
    }

    @Test
    void deleteBooking() {

    }
}