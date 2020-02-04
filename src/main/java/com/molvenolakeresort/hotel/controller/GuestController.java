package com.molvenolakeresort.hotel.controller;

import com.molvenolakeresort.hotel.model.Booking;
import com.molvenolakeresort.hotel.model.Guest;
import com.molvenolakeresort.hotel.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
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
	public Iterable<Guest> findAll() {
		return this.guestRepository.findAll();
	}


	// Get by ID
	@GetMapping("{id}")
	public ResponseEntity<Guest> findById(@PathVariable long id) {
		Optional<Guest> optionalGuest = this.guestRepository.findById(id);

		if (optionalGuest.isPresent()) {
			Guest guest = optionalGuest.get();
			return ResponseEntity.ok(guest);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Create new guest
	@PostMapping
	public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
		return ResponseEntity.ok(this.guestRepository.save(guest));
	}

	// Get new updated printer by ID
//	public Optional<Guest> updateById(long id, Guest newGuest) {
//		Optional<Guest> optionalGuest = findById(id);
//
//		if (optionalGuest.isPresent()) {
//			Guest guest = optionalGuest.get();
//			guest.setName(newGuest.getName());
//			guest.setBirthDate(newGuest.getBirthDate());
//			guest.setMail(newGuest.getMail());
//			guest.setPhone(newGuest.getPhone());
//			guest.setAddress(newGuest.getAddress());
//			guest.setCity(newGuest.getCity());
//			return Optional.of(guest);
//		} else {
//			return Optional.empty();
//		}
//	}

	// Update  by ID
	@PutMapping
	public ResponseEntity<Guest> updateById(@RequestBody Guest newGuest) {
		Guest guest = this.guestRepository.save(newGuest);

		return ResponseEntity.ok(guest);
	}


	@DeleteMapping
	public void deleteGuestById(@RequestBody Guest guest) {
		this.guestRepository.delete(guest);
	}

	// Add some guests to the list when application starts
	@PostConstruct
	public void init() {
		Guest one = new Guest("Jane Doe", "31-01-1990", "janedoe@email.com", "+31 6 1234 5678",
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
	

//	public void postGuest(Guest guest) {
//		Guest newGuest = new Guest(name, birthDate, mail, phone, passportNr, address, city);
//		guestList.add(guest);
//	}

//	public void putGuest(int guestID, String name, String birthDate, String mail, String phone, String passportNr, String address, String city) throws EntityNotFoundException {
//		for (Guest guest : this.guestRepository.findAll()) {
////			if (guest.getId() == guestID) {
//				// Change if fields are changed and if new field is not empty
//				System.out.print("Updated: ");
//				if (!(guest.getName() == name) && !(name.isEmpty())) {
//					guest.setName(name);
//					System.out.print("name ");
//				} if (!(guest.getBirthDate() == birthDate) && !(birthDate.isEmpty())) {
//					guest.setBirthDate(birthDate);
//					System.out.print("birth date ");
//				} if (!(guest.getMail() == mail) && !(mail.isEmpty())) {
//					guest.setMail(mail);
//					System.out.print("mail ");
//				} if (!(guest.getPhone() == phone) && !(phone.isEmpty())) {
//					guest.setPhone(phone);
//					System.out.print("phone ");
//				} if (!(guest.getPassportNr() == passportNr) && !(passportNr.isEmpty())) {
//					guest.setPassportNr(passportNr);
//					System.out.print("passport number ");
//				} if (!(guest.getAddress() == address) && !(address.isEmpty())) {
//					guest.setAddress(address);
//					System.out.print("address ");
//				} if (!(guest.getCity() == city) && !(city.isEmpty())) {
//					guest.setCity(city);
//					System.out.print("city ");
//				}
//
//				return;
//			}
//		}
//		throw new EntityNotFoundException(exceptionError + guestID);
//	}
//
//	public void deleteGuest(int guestID) throws EntityNotFoundException {
//		for (Guest guest : this.guestRepository.findAll()) {
//			if (guestID == guest.getId()) {
//				this.guestRepository.delete(guest);
//				return;
//			}
//		}
//		throw new EntityNotFoundException(exceptionError + guestID);
//	}
//
//	public List<Booking> getGuestBookings(long guestID) throws EntityNotFoundException{
//		Guest guest = this.guestRepository.findById(guestID).get();
//		if (guest.getBookings().toArray().length != 0) {
//			return guest.getBookings();
//		}
//		else {
//			throw new EntityNotFoundException(guest.getName() + " currently has no bookings.");
//		}
//
//	}
//
//}
