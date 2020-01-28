package com.molvenolakeresort.hotel.controller;

import com.molvenolakeresort.hotel.model.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingController {
    private List<Booking> bookingList;

    public BookingController(){
        bookingList = new ArrayList<>();

    }

    public Booking getBooking(int bookingId) throws EntityNotFoundException {
        for (Booking booking : bookingList){
            if(booking.getBookingNumber() == bookingId){
                return booking;
            }
        }
        throw new EntityNotFoundException("Booking was not found for ID: " + bookingId);
    }

    public List<Booking> GetBookings(){
        return bookingList;
    }

    public void putBooking(Booking booking) throws EntityNotFoundException {
        for (Booking bookingIterator : bookingList) {
            if (bookingIterator.getBookingNumber() == booking.getBookingNumber()) {
                bookingList.remove(booking);
                bookingList.add(booking);
                return;
            }
        }
        throw new EntityNotFoundException("Booking was not found for ID: " + booking.getBookingNumber());
    }

    public void postBooking(Booking booking){
        bookingList.add(booking);
    }

    public void deleteBooking(int bookingId) throws EntityNotFoundException {
        for(Booking g : bookingList){
            if(bookingId == g.getBookingNumber()){
                bookingList.remove(g);
                return;
            }
        }
        throw new EntityNotFoundException("Booking was not found for ID: " + bookingId);
    }

}
