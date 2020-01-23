package Controller;

import Model.Booking;
import Model.Guest;

import java.util.ArrayList;
import java.util.List;

public class BookingController {
    private List<Booking> bookingList;

    public BookingController(){
        bookingList = new ArrayList<>();

    }

    public Booking getBooking(int id) throws EntityNotFoundException {
        for (Booking booking : bookingList){
            if(booking.getBookingNumber() == id){
                return booking;
            }
        }
        throw new EntityNotFoundException("Booking was not found for ID: " + id);
    }

    public List<Booking> GetBookings(){
        return bookingList;
    }

    public void putBooking(Booking booking) throws EntityNotFoundException {
        for (Booking bookingIterator : bookingList) {
            if (bookingIterator.getBookingNumber() == booking.getBookingNumber()) {
                bookingList.remove(booking);
                bookingList.add(booking);
                break;
            }
        }
        throw new EntityNotFoundException("Booking was not found for ID: " + booking.getBookingNumber());
    }

    public void postBooking(Booking booking){
        bookingList.add(booking);
    }

    public void deleteBooking(int id) throws EntityNotFoundException {
        for(Booking g : bookingList){
            if(id == g.getBookingNumber()){
                bookingList.remove(g);
            }
        }
        throw new EntityNotFoundException("Booking was not found for ID: " + id);
    }

}
