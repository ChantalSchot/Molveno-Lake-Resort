package model;

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
        return  " Guest: " + guest.getName() + " Total Guests: " + totalGuests + " Check in date : " + checkInDate
                + " Check out date: " + checkOutDate + " Status: " + status + "rooms: " + printRooms(bookedRooms);
    }

    private String printRooms(Room[] bookedRooms) {
        String rooms = "";
        for(Room r :bookedRooms ){
            rooms.concat(r.getRoomNumber() + ", ");
        }
        return rooms;
    }

    public Booking(Guest guest, Room[] bookedRoom ) {
        this.bookingNumber = guestIDgenerator++;
        this.guest = guest;
        this.totalGuests = 3;
        this.bookedRooms = bookedRoom;
        this.status = Status.booked;
        this.checkInDate = new Date(2020, 03, 05);
        this.checkOutDate = new Date(2020, 03, 15);
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
