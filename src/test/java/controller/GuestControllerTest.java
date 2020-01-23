package controller;

import model.Guest;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GuestControllerTest {
	GuestController guestController;

	@BeforeEach
	void init() {
		guestController = new GuestController();
	}

	@Test
	void getGuest() throws EntityNotFoundException {

		System.out.println("Informatie wordt opgehaald van guest ID 1...\n");
		Guest guest;
		guest = guestController.getGuest(1);
		System.out.println("Gastinformatie: \nNaam: " + guestController.getGuest(1).getName() + "\nGeboortedatum: " + guestController.getGuest(1).getBirthDate() + "\nStad: " + guestController.getGuest(1).getCity() + "\nBoekingen: " + guestController.getGuest(1).getBookings().length);

		// Guest information can be requested through the Guest Controller:
		// Get guest name from 3rd guest in dummy GuestController guestList
		assertEquals("Bob", guestController.getGuest(3).getName());
	}


	@Test
	void getGuestList() {
		System.out.println("Gastenlijst wordt opgehaald...");
		for (Guest guest : guestController.getGuestList()) {
			System.out.println(guest.getGuestID() + ". " + guest.getName() + ", " + guest.getCity() + ", " + guest.getBookings().length + " boekingen.");
		}
		assertEquals("Jan Janssen", guestController.getGuestList().get(0).getName());
	}

	@Test
	void postGuest() {
	}

	@Test
	void putGuest() {
	}

	@Test
	void deleteGuest() {
	}
}