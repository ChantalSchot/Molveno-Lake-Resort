package com.molvenolakeresort.hotel.model;

import java.io.Serializable;

public class Room implements Serializable {
    static int roomIDGenerator = 1;
    private int roomID;
    private String roomNumber;
    private boolean available;
    private int numberOfSingleBeds;
    private int numberOfDoubleBeds;
    private int numberOfBabyBeds;
    private boolean disabledRoom;
    private Facilities facilities;
    private int noOfAdults;
    private int noOfChildren;
    private RoomType roomType;
    private RoomStatus roomStatus;

    public Room() {
        roomID = roomIDGenerator++;
        available = true;
        facilities = new Facilities();
    }

    public Room(String roomNumber, RoomType roomType, int noOfAdults, int noOfChildren, int singleBeds, int doubleBeds, int babyBeds, boolean disabled) {
        this.roomID = roomIDGenerator++;
        this.available = true;
        this.facilities = new Facilities();
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.noOfAdults = noOfAdults;
        this.noOfChildren = noOfChildren;
        this.numberOfSingleBeds = singleBeds;
        this.numberOfDoubleBeds = doubleBeds;
        this.numberOfBabyBeds = babyBeds;
        this.disabledRoom = disabled;
    }

    public void setFacilities(Facilities facilities) {
        this.facilities = facilities;
    }

    public int getRoomID() {
        return roomID;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getNumberOfSingleBeds() {
        return numberOfSingleBeds;
    }

    public void setNumberOfSingleBeds(int numberOfSingleBeds) {
        this.numberOfSingleBeds = numberOfSingleBeds;
    }

    public int getNumberOfDoubleBeds() {
        return numberOfDoubleBeds;
    }

    public void setNumberOfDoubleBeds(int numberOfDoubleBeds) {
        this.numberOfDoubleBeds = numberOfDoubleBeds;
    }

    public int getNumberOfBabyBeds() {
        return numberOfBabyBeds;
    }

    public void setNumberOfBabyBeds(int numberOfBabyBeds) {
        this.numberOfBabyBeds = numberOfBabyBeds;
    }

    public boolean isDisabledRoom() {
        return disabledRoom;
    }

    public void setDisabledRoom(boolean disabledRoom) {
        this.disabledRoom = disabledRoom;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public Facilities getFacilities() {
        return facilities;
    }

    public int getNoOfAdults() {
        return noOfAdults;
    }

    public void setNoOfAdults(int noOfAdults) {
        this.noOfAdults = noOfAdults;
    }

    public int getNoOfChildren() {
        return noOfChildren;
    }
    public void setNoOfChildren(int noOfChildren) {
        this.noOfChildren = noOfChildren;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
