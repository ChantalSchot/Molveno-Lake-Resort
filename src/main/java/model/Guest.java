package model;

public class Guest {
    static int guestIDgenerator = 1;
    private int guestID;
    private String name;
    private String mail;
    private String phone;
    private String passportNr;
    private String address;
    private String city;
    private Booking[] bookings;

    // Guest constructors
    public Guest() {
        this("Default name");

    }

    public Guest(String name) {
        this.guestID = guestIDgenerator++;
        this.name = name;
        this.mail = "address@email.com";
        this.phone = "06-12345678";
        this.passportNr = "AB12C34D5";
        this.address = "Main Street 1, AAAA 11";
        this.city = "Utrecht";
    }

    public Guest(String name, String mail, String phone, String passportNr, String address, String city) {
        this.guestID = guestIDgenerator++;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.passportNr = passportNr;
        this.address = address;
        this.city = city;
    }

    public static void setGuestIDgenerator(int guestIDgenerator) {
        Guest.guestIDgenerator = guestIDgenerator;
    }

    public int getGuestID() {
        return guestID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Booking[] getBookings() {
        return bookings;
    }

    public void setBookings(Booking[] bookings) {
        this.bookings = bookings;
    }
}
