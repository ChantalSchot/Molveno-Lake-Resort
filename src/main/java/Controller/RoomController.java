package Controller;

import java.util.ArrayList;
import java.util.List;
import Model.Room;

public class RoomController {
    private List<Room> roomList = new ArrayList<Room>();

    public RoomController() {
        roomList.add(new Room("101", 1, 0, 0, false));
        roomList.add(new Room("102", 0, 1, 0, false));
        roomList.add(new Room("103", 1, 0, 1, false));
    }

    //Get one Room object with the RoomID of the Room
    public Room getRoom(int ID) {
        for (int i = 0; i < roomList.size(); i++)
        {
            if ( roomList.get(i).getRoomID() == ID ) {
                return roomList.get(i);  //We found it, return the Room object
            }
        }
        return null; //Room is not available
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    //Modify room with roomID with all the new info of newRoomInfo
    public int putRoom(Room newRoomInfo, int roomID) {
        for (int i = 0; i < roomList.size(); i++)
        {
            if (roomList.get(i).getRoomID() == roomID) {
                roomList.get(i).setRoomNumber(newRoomInfo.getRoomNumber());
                roomList.get(i).setAvailable(newRoomInfo.isAvailable());
                roomList.get(i).setNumberOfSingleBeds(newRoomInfo.getNumberOfSingleBeds());
                roomList.get(i).setNumberOfDoubleBeds(newRoomInfo.getNumberOfDoubleBeds());
                roomList.get(i).setNumberOfBabyBeds(newRoomInfo.getNumberOfBabyBeds());
                roomList.get(i).setDisabledRoom(newRoomInfo.isDisabledRoom());
                return 0; //We found it, return 0 because everything went alright
            }
        }
        return -1; //Room is not available
    }

    //Create room
    public void postRoom(String roomNumber, int singleBeds, int doubleBeds, int babyBeds, boolean disabled) {
        roomList.add(new Room(roomNumber, singleBeds, doubleBeds, babyBeds, disabled));
    }

    //Delete room
    public int deleteRoom(int ID) {
        for (int i = 0; i < roomList.size(); i++)
        {
            if ( roomList.get(i).getRoomID() == ID ) {
                roomList.remove(roomList.get(i));  //We found it, delete the Room object
                return 0; // Delete went well
            }
        }
        return -1; // Didn't delete the object, object not available
    }
}
