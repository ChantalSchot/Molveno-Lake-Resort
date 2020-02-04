package com.molvenolakeresort.hotel.controller;

import com.molvenolakeresort.hotel.model.Booking;
import com.molvenolakeresort.hotel.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/bookings")
public class BookingController {
    private List<Booking> bookingList;

    @Autowired
    private BookingRepository bookingRepository;

    public BookingController(){
        bookingList = new ArrayList<>();

    }

    @GetMapping("{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable long id) throws EntityNotFoundException {
        Booking booking =  bookingRepository.findById(id);
        return ResponseEntity.ok(booking);

        /*
        for (Booking booking : bookingList){
            if(booking.getId() == id){
                return booking;
            }
        }
        throw new EntityNotFoundException("Booking was not found for ID: " + id);
        */
    }

    public Iterable<Booking> GetBookings(){
        Iterable<Booking> booking = bookingRepository.findAll();
        return booking;
    }

    @PutMapping
    public void putBooking(@RequestBody Booking booking) throws EntityNotFoundException {
        bookingRepository.save(booking);

        /*
        for (Booking bookingIterator : bookingList) {
            if (bookingIterator.getId() == booking.getId()) {
                bookingList.remove(booking);
                bookingList.add(booking);
                return;
            }
        }
        throw new EntityNotFoundException("Booking was not found for ID: " + booking.getId());*/
    }

    @PostMapping
    public void postBooking(@RequestBody Booking booking){
        bookingRepository.save(booking);
        //bookingList.add(booking);
    }

    @DeleteMapping("{id}")
    public void deleteBooking(long id) throws EntityNotFoundException {
        bookingRepository.deleteById(id);

        /*
        for(Booking g : bookingList){
            if(id == g.getId()){
                bookingList.remove(g);
                return;
            }
        }
        throw new EntityNotFoundException("Booking was not found for ID: " + id);
         */
    }

}
