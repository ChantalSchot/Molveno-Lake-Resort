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
import javax.xml.ws.Response;
import java.awt.print.Book;
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
            System.out.println("Booking guest: " + booking.getGuest());
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

    @GetMapping("{id}/guest")
    public ResponseEntity<Guest> getBookingGuest(@PathVariable long id) throws EntityNotFoundException {
        Optional<Booking> optionalBooking =  bookingRepository.findById(id);

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            if (booking.getGuest().getName() == null) {
                Guest foundGuest = this.guestRepository.findById(booking.getGuest().getId()).get();
                booking.setGuest(foundGuest);
                this.bookingRepository.save(booking);
            }

            return ResponseEntity.ok(booking.getGuest());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PutMapping
//    public ResponseEntity<Booking> putBooking(@RequestBody Booking booking) {
//        Guest foundGuest = this.guestRepository.findById(booking.getGuest().getId()).get();
//        booking.setGuest(foundGuest);
//        Booking savedBooking = this.bookingRepository.save(booking);
//        return ResponseEntity.ok(savedBooking);
//    }

    @PostMapping
    public ResponseEntity<Booking> postBooking(@RequestBody Booking booking){
        Optional<Guest> optionalGuest = this.guestRepository.findById(booking.getGuest().getId());

        if (optionalGuest.isPresent()) {
            Guest foundGuest = optionalGuest.get();
            booking.setGuest(foundGuest);
            Booking savedBooking = this.bookingRepository.save(booking);
            return ResponseEntity.ok(savedBooking);
        } else {
            this.guestRepository.save(booking.getGuest());
            this.bookingRepository.save(booking);
            return ResponseEntity.ok(booking);
        }
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
