package Controller;

import Model.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestController {
	private List<Guest> guestList;

	public GuestController() {
		guestList = new ArrayList<>();

		//TEST GUEST LIST (ID
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
		throw new EntityNotFoundException("Guest was not found for ID: " + guestID);
	}

	public List<Guest> getGuestList() {
		return guestList;
	}

	public void postGuest(String name) {
		Guest newGuest = new Guest(name);
		guestList.add(newGuest);
	}

	public void postGuest(String name, String mail, String phone, String passportNr, String address, String city) {
		Guest newGuest = new Guest(name, mail, phone, passportNr, address, city);
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
		throw new EntityNotFoundException("Guest was not found for ID: " + guest);
	}

	public void deleteGuest(int guestID) throws EntityNotFoundException {
		for (Guest guest : guestList) {
			if (guestID == guest.getGuestID()) {
				guestList.remove(guest);
				return;
			}
		}
        throw new EntityNotFoundException("Guest was not found for ID: " + guestID);
	}
}
