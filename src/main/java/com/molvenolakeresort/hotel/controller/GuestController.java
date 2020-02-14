package com.molvenolakeresort.hotel.controller;

import com.molvenolakeresort.hotel.model.Booking;
import com.molvenolakeresort.hotel.model.Guest;
import com.molvenolakeresort.hotel.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.swing.text.html.Option;
import javax.xml.ws.Response;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/guests")
public class GuestController {

	@Autowired
	private GuestRepository guestRepository;
	//	private List<Guest> guestList = new ArrayList<>();
	public String exceptionError = "Guest was not found for ID: ";

//	public GuestController() {
//		guestList = new ArrayList<>();

	//TEST GUEST LIST :
//		guestList.add(new Guest("Jan Janssen"));
//		guestList.add(new Guest("Alice"));
//		guestList.add(new Guest("Bob"));
//	}


	// Get list of guests
	@GetMapping
	public Iterable<Guest> getGuestList() {
		return this.guestRepository.findAll();
	}


	// Get by ID
	@GetMapping(value = "{id}", produces = "application/json")
	public ResponseEntity<Guest> getGuest(@PathVariable long id) {
		Optional<Guest> optionalGuest = this.guestRepository.findById(id);

		if (optionalGuest.isPresent()) {
			Guest guest = optionalGuest.get();
			return ResponseEntity.ok(guest);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

//	 Get guest bookings
	@GetMapping(value="{id}/bookings", produces = "application/json")
	public ResponseEntity<List> getGuestBookings(@PathVariable long id) {
		Optional<Guest> optionalGuest = this.guestRepository.findById(id);

		if (optionalGuest.isPresent()) {
			Guest guest = optionalGuest.get();
			return ResponseEntity.ok(guest.getBookings());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Create new guest
	@PostMapping (consumes = "application/json", produces = "application/json")
	public ResponseEntity<Guest> postGuest(@RequestBody Guest guest) {
		Optional<Guest> optionalGuest = this.guestRepository.findById(guest.getId());

		if (optionalGuest.isPresent()) {
			Guest postedGuest = optionalGuest.get();
			return ResponseEntity.ok(this.guestRepository.save(guest));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	

	@DeleteMapping (consumes = "application/json")
	public void deleteGuest(@RequestBody Guest guest) {
		this.guestRepository.delete(guest);
	}

//	 Add some guests to the list when application starts
//	@PostConstruct
//	public void init() throws ParseException {

//	}

}