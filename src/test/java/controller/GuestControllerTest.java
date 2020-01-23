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
		// Gastinformatie ophalen:
		try {
			System.out.println("Informatie wordt opgehaald van guest ID 1...\n");
			Guest guest;
			guest = guestController.getGuest(1);
			System.out.println("Gastinformatie: \nNaam: " + guest.getName() + "\nGeboortedatum: " + guest.getBirthDate() + "\nStad: " + guest.getCity() + "\nBoekingen: " + guest.getBookings().length);

			assertEquals("Bob", guestController.getGuest(3).getName());
		} catch (EntityNotFoundException e) {
			assertEquals(e.toString(),"");
		}
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
		try {
			System.out.println("Registreer een nieuwe gast: Jane Appleseed, geboren in 2002, afkomstig uit New York. \n");
			guestController.postGuest("Jane Appleseed", "January 24th, 2002", "Jane@email.com", "0316 - 23454321", "AB1234DE", "Chicago Street", "New York");

			System.out.print("Nieuwe gast geregistreerd. ");
			Guest guest = guestController.getGuest(4);
			System.out.println("Gastinformatie: \nNaam: " + guest.getName() + "\nGeboortedatum: " + guest.getBirthDate() + "\nStad: " + guest.getCity() + "\nBoekingen: " + guest.getBookings().length);

			System.out.println("\nNieuwe gastenlijst:");
			for (Guest guestIterator : guestController.getGuestList()) {
				System.out.println(guestIterator.getGuestID() + ". " + guestIterator.getName() + ", " + guestIterator.getCity() + ", " + guestIterator.getBookings().length + " boekingen.");
			}
			assertEquals("Jane Appleseed", guestController.getGuestList().get(3).getName());
		}
		catch (EntityNotFoundException e) {
			assertEquals(e.toString(),"");
		}

	}

	@Test
	void putGuest() {
	}

	@Test
	void deleteGuest() {
	}
}