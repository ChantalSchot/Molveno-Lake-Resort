package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
    static int guestIDgenerator = 1;
    private int bookingNumber;
    private Guest guest;
    private int totalGuests;
    private Room[] bookedRooms; //ToDo : Is this needed?
    //ToDo private Invoice invoice;
    private Status status;
    private Date checkInDate;
    private Date checkOutDate;
    private RoomBooking roomBooking;

    @Override
    public String toString(){
        String rooms = printRooms(bookedRooms);
        return  " Guest: " + guest.getName() + " Total Guests: " + totalGuests + " Check in date : " + checkInDate
                + " Check out date: " + checkOutDate + " Status: " + status + "rooms: " +  rooms;
    }

    private String printRooms(Room[] bookedRooms) {
        String rooms = "";
        for(Room r :bookedRooms ){
            rooms = rooms.concat(r.getRoomNumber() + ", ");
        }
        return rooms;
    }

    public Booking(Guest guest, Room[] bookedRoom ) throws ParseException {
        this.bookingNumber = guestIDgenerator++;
        this.guest = guest;
        this.totalGuests = 3;
        this.bookedRooms = bookedRoom;
        this.status = Status.booked;
        this.checkInDate = new SimpleDateFormat("dd/MM/yyyy").parse("12/05/2020");
        this.checkOutDate = new SimpleDateFormat("dd/MM/yyyy").parse("19/05/2020");
        //this.roomBooking = ;
    }

    public Booking(Guest guest, int totalGuests,  Room[] bookedRooms, Status status,  Date checkInDate,Date checkOutDate) {
        this.bookingNumber = guestIDgenerator++;
        this.guest = guest;
        this.totalGuests = totalGuests;
        this.bookedRooms = bookedRooms;
        this.status = status;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;

    }

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
