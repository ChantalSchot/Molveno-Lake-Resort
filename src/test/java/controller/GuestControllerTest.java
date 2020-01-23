package controller;

import model.Guest;
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
			System.out.println("Informatie wordt opgehaald van guest met ID number 1...\n");
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
			System.out.println("Registreer nieuwe gast: Jane Appleseed, geboren in 2002, afkomstig uit New York. \n");
			guestController.postGuest("Jane Appleseed", "January 24th, 2002", "Jane@email.com", "0316 - 23454321", "AB1234DE", "Chicago Street", "New York");

			System.out.print("Nieuwe gast geregistreerd. ");
			Guest guest = guestController.getGuest(4);
			System.out.println("Gastinformatie: \nNaam: " + guest.getName() + "\nGeboortedatum: " + guest.getBirthDate() + "\nStad: " + guest.getCity() + "\nBoekingen: " + guest.getBookings().length);
			System.out.println(guestController.getGuest(4).toString());

			System.out.println("\nNieuwe gastenlijst:");
			for (Guest guestIterator : guestController.getGuestList()) {
				System.out.println(guestIterator.getGuestID() + ". " + guestIterator.getName() + ", " + guestIterator.getCity() + ", " + guestIterator.getBookings().length + " boekingen.");
			}
			assertEquals("Jane Appleseed", guestController.getGuestList().get(3).getName());
		} catch (EntityNotFoundException e) {
			assertEquals(e.toString(),"");
		}
	}

	@Test
	void putGuest() {
		try {
			System.out.println("Current guest (ID no. 2):");
			System.out.println(guestController.getGuest(2).toString());
			System.out.println("\nUpdating guest and requesting new information...\n");
			
			guestController.putGuest(2,"Alice Smith", "February 20th, 1990", "asmith@email.com", "031-12345678", "", "Veemarkt 1, 5678 CD", "");
			System.out.println("\n" + guestController.getGuest(2).toString());
			
			assertEquals(guestController.getGuest(2).getName(), "Alice Smith");
			
		} catch (EntityNotFoundException e) {
			assertEquals(e.toString(), "");
		}
	}

	@Test
	void deleteGuest() {
		try {
			System.out.println("Guest to be deleted (ID no. 3):");
			System.out.println(guestController.getGuest(3).toString());
			
			System.out.println("\nDeleting guest... Requesting guest information again: ");
			guestController.deleteGuest(3);
			System.out.println(guestController.getGuest(3).toString());
			
		} catch (EntityNotFoundException e) {
			System.out.println(e);
			assertEquals(e,e);
		}
		
	}
}