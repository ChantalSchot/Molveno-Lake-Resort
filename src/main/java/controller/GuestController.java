package controller;

import model.Booking;
import model.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestController {
	private List<Guest> guestList;
	public String exceptionError = "Guest was not found for ID: ";

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

	public void putGuest(int guestID, String name, String birthDate, String mail, String phone, String passportNr, String address, String city) throws EntityNotFoundException {
		for (Guest guest : guestList) {
			if (guest.getGuestID() == guestID) {
				// Change if fields are changed and if new field is not empty
				System.out.print("Updated: ");
				if (!(guest.getName() == name) && !(name.isEmpty())) {
					guest.setName(name);
					System.out.print("name ");
				} if (!(guest.getBirthDate() == birthDate) && !(birthDate.isEmpty())) {
					guest.setBirthDate(birthDate);
					System.out.print("birth date ");
				} if (!(guest.getMail() == mail) && !(mail.isEmpty())) {
					guest.setMail(mail);
					System.out.print("mail ");
				} if (!(guest.getPhone() == phone) && !(phone.isEmpty())) {
					guest.setPhone(phone);
					System.out.print("phone ");
				} if (!(guest.getPassportNr() == passportNr) && !(passportNr.isEmpty())) {
					guest.setPassportNr(passportNr);
					System.out.print("passport number ");
				} if (!(guest.getAddress() == address) && !(address.isEmpty())) {
					guest.setAddress(address);
					System.out.print("address ");
				} if (!(guest.getCity() == city) && !(city.isEmpty())) {
					guest.setCity(city);
					System.out.print("city ");
				}
				
				return;
			}
		}
		throw new EntityNotFoundException(exceptionError + guestID);
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
