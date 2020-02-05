package com.molvenolakeresort.hotel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Guest implements Serializable {
//    static int guestIDgenerator = 1;

    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    
    private String name;
    private String birthDate;
    private String mail;
    private String phone;
    private String passportNr;
    private String address;
    private String city;
    
    @OneToMany
    private List<Booking> bookings;


    @Override
    public String toString(){
        return  "Name: " + name + ". Birth date: " + birthDate + ". Mail: " + mail + ". Phone: " + phone + ".\nPassportnumber: " + passportNr + ". Address: " + address + ". City: " + city + ".";
    }


    // Guest constructors
    public Guest() {
    }

    public Guest(String name) {
//        this.id = guestIDgenerator++;
        this.name = name;
        this.birthDate = "January 1st, 1980";
        this.mail = "address@email.com";
        this.phone = "06-12345678";
        this.passportNr = "AB12C34D5";
        this.address = "com.molvenolakeresort.hotel.Main Street 1, 1234 AB";
        this.city = "Utrecht";
    }

    public Guest(String name, String birthDate, String mail, String phone, String passportNr, String address, String city) {
//        this.id = guestIDgenerator++;
        this.birthDate = birthDate;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.passportNr = passportNr;
        this.address = address;
        this.city = city;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassportNr() {
        return passportNr;
    }

    public void setPassportNr(String passportNr) {
        this.passportNr = passportNr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Booking> getBookings() {
        return bookings;
        }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

}
