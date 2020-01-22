import java.util.ArrayList;
import java.util.List;

public class RoomController {
    List<Room> roomList = new ArrayList<Room>();

    //Get one Room object with the RoomID of the Room
    Room getRoom(int ID) {
        for (int i = 0; i < roomList.length; i++)
        {
            if ( roomList[i].roomID == ID ) {
                return roomList[i];  //We found it, return the Room object
            }
        }
        return null; //Room is not available
    }

    RoomController() {

    }

    Room[] getRoomList() {
        return roomList;
    }

    //Modify room with roomID with all the new info of newRoomInfo
    int putRoom(Room newRoomInfo, int roomID) {
        for (int i = 0; i < roomList.length; i++)
        {
            if ( roomList[i].roomID == roomID ) {
                roomList[i].roomNumber = newRoomInfo.roomNumber;
                roomList[i].available = newRoomInfo.available;
                roomList[i].numberOfSingleBeds = newRoomInfo.numberOfSingleBeds;
                roomList[i].numberOfDoubleBeds = newRoomInfo.numberOfDoubleBeds;
                roomList[i].numberOfBabyBeds = newRoomInfo.numberOfBabyBeds;
                roomList[i].disabledRoom = newRoomInfo.disabledRoom;
                return 0; //We found it, return 0 because everything went alright
            }
        }
        return -1; //Room is not available
    }

    //Create room
    void postRoom() {

    }

    //Delete room
    void deleteRoom(int ID) {

    }
}
