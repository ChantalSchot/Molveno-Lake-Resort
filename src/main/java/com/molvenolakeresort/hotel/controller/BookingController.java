package com.molvenolakeresort.hotel.controller;

import com.molvenolakeresort.hotel.model.Booking;
import com.molvenolakeresort.hotel.model.Guest;
import com.molvenolakeresort.hotel.model.Room;
import com.molvenolakeresort.hotel.model.RoomType;
import com.molvenolakeresort.hotel.repository.BookingRepository;
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
    public ResponseEntity<Booking> putBooking(@RequestBody Booking newBooking) {
        Booking booking = this.bookingRepository.save(newBooking);
        return ResponseEntity.ok(booking);
    }

    @PostMapping
    public ResponseEntity<Booking> postBooking(@RequestBody Booking booking){
        return ResponseEntity.ok(this.bookingRepository.save(booking));
    }

    @DeleteMapping("{id}")
    public void deleteBooking(long id) {
        bookingRepository.deleteById(id);
    }

    @PostConstruct
    public void init() {
        try {
            Guest guest1 = new Guest("Berard Broodje");
            Guest guest2 = new Guest("Gerard Grootje");
            Guest guest3 = new Guest("Piet Plezier");
            List<Room> roomList = new ArrayList<>();
            List<Room> roomList2 = new ArrayList<>();
            roomList.add(new Room("201", RoomType.doubleRoom,2,1,0,2,1,false));
            roomList.add(new Room("202", RoomType.doubleRoom,2,1,0,2,1,false));
            roomList2.add(new Room("203", RoomType.doubleRoom,2,1,0,2,1,false));

            bookingList.add(new Booking(guest1, 5, roomList, "12/05/2020", "19/05/2020"));
            bookingList.add(new Booking(guest2, 5, roomList2, "12/05/2020", "19/05/2020"));
            bookingList.add(new Booking(guest3, 5, roomList, "19/05/2020", "21/05/2020"));

        } catch (ParseException e) {
            System.out.println("Cannot make booking list because: " + e);
        }
    }

}
