package com.molvenolakeresort.hotel.controller;

import com.molvenolakeresort.hotel.model.Room;
import com.molvenolakeresort.hotel.model.RoomType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/rooms")
public class RoomController {
	private List<Room> roomList = new ArrayList<Room>();

	public RoomController() {
		//TESTROOMS!!!!!!
		//String roomNumber, RoomType roomType, int noOfAdults, int noOfChildren, int singleBeds, int doubleBeds, int babyBeds, boolean disabled
		roomList.add(new Room("101", RoomType.doubleRoom,2,1,0,2,1,false));
		roomList.add(new Room("102", RoomType.singleRoom, 1, 1, 0, 1, 0,  false));
		roomList.add(new Room("103", RoomType.doubleRoom, 2, 0,2,0,0,false));

		Room newRoom = new Room("104", RoomType.familyRoom, 4, 2, 1, 2, 1, false);
		newRoom.setAvailable(false);
		roomList.add(newRoom);
	}

	//Get one Room object with the Room number of the Room
	@GetMapping("{number}")
	public Room getRoom(@PathVariable String number) throws EntityNotFoundException {
		for (Room room : roomList) {
			if (room.getRoomNumber() == number) {
				return room;  //We found it, return the Room object
			}
		}
		throw new EntityNotFoundException("RoomController::getRoom() Room not found in the list for roomNumber: " + number);
	}

	@GetMapping("{id}")
	public Room getRoom(@PathVariable int id) throws EntityNotFoundException {
		for (Room room : roomList) {
			if (room.getRoomID() == id) {
				return room;  //We found it, return the Room object
			}
		}
		throw new EntityNotFoundException("RoomController::getRoom() Room not found in the list for roomNumber: " + id);
	}

	@GetMapping
	public List<Room> getRoomList() {
		return roomList;
	}

	@GetMapping(value = "/availableRoomList")
	public List<Room> getAvailableRooms() {
		List<Room> availableRooms = new ArrayList<Room>();
		for (Room room : roomList) {
			if (room.isAvailable()) {
				availableRooms.add(room);
			}
		}
		return availableRooms;
	}

	//Modify room with roomID with all the new info of newRoomInfo
	@PutMapping("{id}")
	public void putRoom(@RequestBody Room newRoomInfo,@PathVariable int id) throws EntityNotFoundException {
		for (Room room : roomList) {
			if (room.getRoomID() == id) {
				room.setRoomNumber(newRoomInfo.getRoomNumber());
				room.setAvailable(newRoomInfo.isAvailable());
				room.setRoomType(newRoomInfo.getRoomType());
				room.setNoOfAdults(newRoomInfo.getNoOfAdults());
				room.setNoOfChildren(newRoomInfo.getNoOfChildren());
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
	@PostMapping
	public void postRoom(@RequestBody Room room) {
		roomList.add(room);
	}

	//Delete room
	@DeleteMapping("{id}")
	public void deleteRoom(@PathVariable int id) throws EntityNotFoundException {
		for (Room room : roomList) {
			if (room.getRoomID() == id) {
				roomList.remove(room);  //We found it, delete the Room object
				return;
			}
		}
		throw new EntityNotFoundException("RoomController::deleteRoom() Room not found in the list for ID: " + id);
	}
}
