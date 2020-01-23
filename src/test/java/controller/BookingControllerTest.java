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
            System.out.println("De boeking die is opgehaald heeft kamernummer " + booking.getBookedRooms()[0].getRoomNumber()
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
    void putBooking() throws EntityNotFoundException {
        Booking booking = bookingController.getBooking(3);
        Guest guest = new Guest("Niet Bob");
        System.out.println(booking.toString());

        Room[] list = booking.getBookedRooms();
        Room[] NList = {list[0], list[1]};
        booking.setBookedRooms(NList);
        booking.setGuest(guest);

        bookingController.putBooking(booking);
        Booking NBooking = bookingController.getBooking(3);

        assertEquals("Niet Bob", NBooking.getGuest().getName() );
        assertEquals(2, NBooking.getBookedRooms().length );
        System.out.println(NBooking.toString());

    }

    @Test
    void postBooking() throws ParseException {
        Guest guest = new Guest("Willem Alexander");
        Room[] rooms = roomController.getRoomList().toArray(new Room[0]);


        Booking nBooking = new Booking(guest, rooms);
        bookingController.postBooking(nBooking);


        List<Booking> bookingList= bookingController.GetBookings();
        assertEquals(4, bookingList.size());
        if(!bookingList.isEmpty()) {
            System.out.println("Lijst van Boekingen:");
            for(Booking booking : bookingList) {
                System.out.println(booking.toString());
            }
        }
    }

    @Test
    void deleteBooking() {
        System.out.println("Boeking 1 word verwijderd");

        try {
            Booking booking = bookingController.getBooking(1);
            bookingController.deleteBooking(1);
        } catch(EntityNotFoundException e) {
            System.out.println(e);
            assertEquals(e.toString(), "");
        }

        List<Booking> list= bookingController.GetBookings();
        assertEquals(2, list.size());
        if(!list.isEmpty()) {
            System.out.println("Lijst van boekingen:");
            for(Booking booking : list) {
                System.out.println("Boeking " + booking.getBookingNumber()
                        + ", geboekt door de klant " + booking.getGuest().getName());
            }
        }
    }
}