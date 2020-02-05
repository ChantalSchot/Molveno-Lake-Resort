package com.molvenolakeresort.hotel.controller;

import com.molvenolakeresort.hotel.model.Booking;
import com.molvenolakeresort.hotel.model.Guest;
import com.molvenolakeresort.hotel.model.Room;
import com.molvenolakeresort.hotel.model.RoomType;
import com.molvenolakeresort.hotel.repository.BookingRepository;
import com.molvenolakeresort.hotel.repository.GuestRepository;
import com.molvenolakeresort.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/bookings")
public class BookingController {
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingRepository bookingRepository;

    public BookingController(){
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

    @GetMapping
    public Iterable<Booking> getBookings(){
        Iterable<Booking> booking = bookingRepository.findAll();
        return booking;
    }

    @PutMapping
    public ResponseEntity<Booking> putBooking(@RequestBody Booking booking) {
        Booking newBooking = this.bookingRepository.save(booking);
        return ResponseEntity.ok(newBooking);
    }

    @PostMapping
    public ResponseEntity<Booking> postBooking(@RequestBody Booking booking){
        return ResponseEntity.ok(this.bookingRepository.save(booking));
    }

    @DeleteMapping()
    public void deleteBooking(@RequestBody Booking booking) {
        bookingRepository.delete(booking);
    }

    @PostConstruct
    public void init() {

        bookingRepository.save(new Booking());
        bookingRepository.save(new Booking());
        bookingRepository.save(new Booking());
        bookingRepository.save(new Booking());
    }

}
