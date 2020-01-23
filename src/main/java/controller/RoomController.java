package controller;

import model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomController {
	private List<Room> roomList = new ArrayList<Room>();

	public RoomController() {
		//TESTROOMS!!!!!!
		roomList.add(new Room("101", 1, 0, 0, false));
		roomList.add(new Room("102", 0, 1, 0, false));
		roomList.add(new Room("103", 1, 0, 1, false));
	}

	//Get one Room object with the RoomID of the Room
	public Room getRoom(String number) throws EntityNotFoundException {
		for (Room room : roomList) {
			if (room.getRoomNumber() == number) {
				return room;  //We found it, return the Room object
			}
		}
		throw new EntityNotFoundException("RoomController::getRoom() Room not found in the list for roomNumber: " + number);
	}

	public List<Room> getRoomList() {
		return roomList;
	}

	//Modify room with roomID with all the new info of newRoomInfo
	public void putRoom(Room newRoomInfo, int id) throws EntityNotFoundException {
		for (Room room : roomList) {
			if (room.getRoomID() == id) {
				room.setRoomNumber(newRoomInfo.getRoomNumber());
				room.setAvailable(newRoomInfo.isAvailable());
				room.setNumberOfSingleBeds(newRoomInfo.getNumberOfSingleBeds());
				room.setNumberOfDoubleBeds(newRoomInfo.getNumberOfDoubleBeds());
				room.setNumberOfBabyBeds(newRoomInfo.getNumberOfBabyBeds());
				room.setDisabledRoom(newRoomInfo.isDisabledRoom());
				return;
			}
		}
		throw new EntityNotFoundException("RoomController::putRoom() Room not found in the list for ID: " + id);
	}

	//Create room
	public void postRoom(Room room) {
		roomList.add(room);
	}

	//Delete room
	public void deleteRoom(int id) throws EntityNotFoundException {
		for (Room room : roomList) {
			if (room.getRoomID() == id) {
				roomList.remove(room.getRoomID());  //We found it, delete the Room object
				return;
			}
		}
		throw new EntityNotFoundException("RoomController::deleteRoom() Room not found in the list for ID: " + id);
	}
}
