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
    public Room getRoom(int ID) throws EntityNotFoundException {
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getRoomID() == ID) {
                return roomList.get(i);  //We found it, return the Room object
            }
        }
        throw new EntityNotFoundException("RoomController::getRoom() Room not found in the list for ID: " + ID);
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    //Modify room with roomID with all the new info of newRoomInfo
    public void putRoom(Room newRoomInfo, int ID) throws EntityNotFoundException {
        for (Room room : roomList) {
            if (room.getRoomID() == ID) {
                room.setRoomNumber(newRoomInfo.getRoomNumber());
                room.setAvailable(newRoomInfo.isAvailable());
                room.setNumberOfSingleBeds(newRoomInfo.getNumberOfSingleBeds());
                room.setNumberOfDoubleBeds(newRoomInfo.getNumberOfDoubleBeds());
                room.setNumberOfBabyBeds(newRoomInfo.getNumberOfBabyBeds());
                room.setDisabledRoom(newRoomInfo.isDisabledRoom());
                return;
            }
        }
        throw new EntityNotFoundException("RoomController::putRoom() Room not found in the list for ID: " + ID);
    }

    //Create room
    public void postRoom(Room room) {
        roomList.add(room);
    }

    //Delete room
    public void deleteRoom(int ID) throws EntityNotFoundException {
        for (Room room : roomList) {
            if (room.getRoomID() == ID) {
                roomList.remove(room.getRoomID());  //We found it, delete the Room object
                return;
            }
        }
        throw new EntityNotFoundException("RoomController::deleteRoom() Room not found in the list for ID: " + ID);
    }
}
