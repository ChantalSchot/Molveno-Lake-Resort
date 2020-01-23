package Controller;

import Model.Guest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GuestControllerTest {

	@Test
	void getGuest() throws EntityNotFoundException {
		GuestController guestController = new GuestController();
		// Get guest name from 3rd guest in dummy GuestController guestList
		assertEquals("Bob", guestController.getGuest(3).getName());
	}

	@Test
	void getGuestList() {
		GuestController guestController = new GuestController();



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