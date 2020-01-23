package Model;

public class Room {
    static int roomIDGenerator = 1;
    private int roomID;
    private String roomNumber;
    private boolean available;
    private int numberOfSingleBeds;
    private int numberOfDoubleBeds;
    private int numberOfBabyBeds;
    private boolean disabledRoom;
    private Facilities facilities;

    public Room() {
        roomID = roomIDGenerator++;
        available = true;
        facilities = new Facilities();
    }

    public Room(String roomNumber, int singleBeds, int doubleBeds, int babyBeds, boolean disabled) {
        this.roomID = roomIDGenerator++;
        this.available = true;
        this.facilities = new Facilities();
        this.roomNumber = roomNumber;
        this.numberOfSingleBeds = singleBeds;
        this.numberOfDoubleBeds = doubleBeds;
        this.numberOfBabyBeds = babyBeds;
        this.disabledRoom = disabled;

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

    public Facilities getFacilities() {
        return facilities;
    }
}
