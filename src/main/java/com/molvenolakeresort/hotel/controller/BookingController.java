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
        Optional<Booking> optionalBooking =  bookingRepository.findById(id);

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            return ResponseEntity.ok(booking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Iterable<Booking> GetBookings(){
        Iterable<Booking> booking = bookingRepository.findAll();
        return booking;
    }

    @PutMapping
    public ResponseEntity<Booking> putBooking(@RequestBody Booking newBooking) throws EntityNotFoundException {


        Booking booking = this.bookingRepository.save(newBooking);

        return ResponseEntity.ok(booking);

    }

    @PostMapping
    public ResponseEntity<Booking> postBooking(@RequestBody Booking booking){
        return ResponseEntity.ok(this.bookingRepository.save(booking));
    }

    @DeleteMapping("{id}")
    public void deleteBooking(long id) throws EntityNotFoundException {
        bookingRepository.deleteById(id);

    }

}
