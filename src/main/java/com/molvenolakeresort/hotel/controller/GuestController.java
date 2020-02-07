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

	// Create new guest
	@PostMapping (consumes = "application/json", produces = "application/json")
	public ResponseEntity<Guest> postGuest(@RequestBody Guest guest) {
		return ResponseEntity.ok(this.guestRepository.save(guest));
	}

	// Update  by ID
	@PutMapping (consumes = "application/json", produces = "application/json")
	public ResponseEntity<Guest> putGuest(@RequestBody Guest newGuest) {
		Guest guest = this.guestRepository.save(newGuest);

		return ResponseEntity.ok(guest);
	}

	@DeleteMapping (consumes = "application/json")
	public void deleteGuest(@RequestBody Guest guest) {
		this.guestRepository.delete(guest);
	}

	// Add some guests to the list when application starts
	@PostConstruct
	public void init() throws ParseException {
		Guest one = new Guest("Jane Doe", "31-01-1992", "janedoe@email.com", "+31 6 1234 5678",
				"AB9381B39", "Main Street 99", "New York");
		this.guestRepository.save(one);
		Guest two = new Guest("Jan Janssen", "02-10-1990", "jjanssen@email.com", "+31 72 5712345",
				"KH9274027", "Dorpsstraat 83", "Dordrecht");
		this.guestRepository.save(two);
		Guest three = new Guest("Nicholas Wiley", "09-01-1959", "nicwiley@email.com", "042 2934813",
				"IT392K382", "Ellsworth Summit", "Howemouth");
		this.guestRepository.save(three);
		Guest four = new Guest("Ervin Howell", "19-05-1978", "ervinh@email.com", "010 9320 592",
				"ABE382915", "Victor Plains 391", "Gwenborough");
		this.guestRepository.save(four);
		Guest five = new Guest("Patricia Lebsack", "29-11-1993", "patleb@email.com", "063 298 492143",
				"IW938G913", "Hager Mall", "Corkshire");
		this.guestRepository.save(five);
	}

}