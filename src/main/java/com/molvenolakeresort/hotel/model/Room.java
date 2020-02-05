package com.molvenolakeresort.hotel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String roomNumber;
    private boolean available;
    private int numberOfSingleBeds;
    private int numberOfDoubleBeds;
    private int numberOfBabyBeds;
    private boolean disabledRoom;
    private int noOfAdults;
    private int noOfChildren;
    private RoomType roomType;
    private RoomStatus roomStatus;

    //@OneToOne
    private Facilities facilities;

    @ManyToMany
    List<Booking> bookings = new ArrayList<>();

    public Room() {
        available = true;
        facilities = new Facilities();
    }

    public Room(String roomNumber, RoomType roomType, int noOfAdults, int noOfChildren, int singleBeds, int doubleBeds, int babyBeds, boolean disabled) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

//    public void setId(int id) {
//        this.id = id;
//    }

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
