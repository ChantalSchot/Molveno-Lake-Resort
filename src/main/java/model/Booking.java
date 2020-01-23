package model;

import java.util.Date;

public class Booking {
    private int bookingNumber;
    private Guest guest;
    private int totalGuests;
    private Room[] bookedRooms; //ToDo : Is this needed?
    //ToDo private Invoice invoice;
    private Status status;
    private Date checkInDate;
    private Date checkOutDate;
    private RoomBooking roomBooking;


    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public int getTotalGuests() {
        return totalGuests;
    }

    public void setTotalGuests(int totalGuests) {
        this.totalGuests = totalGuests;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public Room[] getBookedRooms() {
        return bookedRooms;
    }

    public void setBookedRooms(Room[] bookedRooms) {
        this.bookedRooms = bookedRooms;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public RoomBooking getRoombooking() {
        return roomBooking;
    }

    public void setRoombooking(RoomBooking roomBooking) {
        this.roomBooking = roomBooking;
    }
}
