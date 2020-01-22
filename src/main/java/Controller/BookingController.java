package Controller;

import Model.Booking;
import Model.Guest;

import java.util.ArrayList;
import java.util.List;

public class BookingController {
    private List<Booking> bookings;

    public BookingController(){
        bookings = new ArrayList<>();
    }

    public Booking getBooking(int id){
        for (Booking g : bookings){
            if(g.getBookingNumber() == id){
                return g;
            }
        }
        System.out.println("There is no booking with that number.");
        return null;
    }

    public List<Booking> GetBookings(){
        return bookings;
    }

    public void putBooking(Booking booking){

    }

    public void postBooking(Booking booking){

    }

    public void deleteBooking(int id){
        for(Booking g : bookings){
            if(id == g.getBookingNumber()){
                bookings.remove(g);
                System.out.println("The the booking" + g.getBookingNumber() + "is succefully deleted." );
                break;
            }
        }
    }


}
