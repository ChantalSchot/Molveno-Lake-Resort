package controller;

import model.Booking;
import model.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestController {
	private List<Guest> guestList;
	private String exceptionError = "Guest was not found for ID: ";

	public GuestController() {
		guestList = new ArrayList<>();

		//TEST GUEST LIST :
		guestList.add(new Guest("Jan Janssen"));
		guestList.add(new Guest("Alice"));
		guestList.add(new Guest("Bob"));
	}

	public Guest getGuest(int guestID) throws EntityNotFoundException {
		for (Guest guest : guestList) {
			if (guest.getGuestID() == guestID) {
				return guest;
			}
		}
		throw new EntityNotFoundException(exceptionError + guestID);
	}

	public List<Guest> getGuestList() {
		return guestList;
	}

	public void postGuest(String name) {
		Guest newGuest = new Guest(name);
		guestList.add(newGuest);
	}

	public void postGuest(String name, String birthDate, String mail, String phone, String passportNr, String address, String city) {
		Guest newGuest = new Guest(name, birthDate, mail, phone, passportNr, address, city);
		guestList.add(newGuest);
	}

	public void putGuest(Guest guest) throws EntityNotFoundException {
		for (Guest guestIterator : guestList) {
			if (guestIterator.getGuestID() == guest.getGuestID()) {
				guestList.remove(guest);
				guestList.add(guest);
				return;
			}
		}
		throw new EntityNotFoundException(exceptionError + guest.getGuestID());
	}

	public void deleteGuest(int guestID) throws EntityNotFoundException {
		for (Guest guest : guestList) {
			if (guestID == guest.getGuestID()) {
				guestList.remove(guest);
				return;
			}
		}
		throw new EntityNotFoundException(exceptionError + guestID);
	}

	public Booking[] getGuestBookings(int guestID) throws EntityNotFoundException{
		if (getGuest(guestID).getBookings().length != 0) {
			return getGuest(guestID).getBookings();
		}
		else {
			throw new EntityNotFoundException(getGuest(guestID).getName() + " currently has no bookings.");
		}

	}

}
