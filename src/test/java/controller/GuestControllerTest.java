package controller;

import model.Guest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GuestControllerTest {


	@Test
	void getGuest() throws EntityNotFoundException {
		GuestController guestController = new GuestController();
		// Get guest name from 3rd guest in dummy GuestController guestList
		assertEquals("Alice", guestController.getGuest(2).getName());
	}

	@Test
	void getGuestList() {
		GuestController guestController = new GuestController();
		guestController.getGuestList().listIterator();
		for (Guest guest : guestController.getGuestList()) {
			System.out.println(guest.getName() + ", " + guest.getCity() + ", ID: " + guest.getGuestID());
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