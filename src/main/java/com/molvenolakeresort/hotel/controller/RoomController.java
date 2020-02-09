package com.molvenolakeresort.hotel.controller;

import com.molvenolakeresort.hotel.model.Room;
import com.molvenolakeresort.hotel.model.RoomType;
import com.molvenolakeresort.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/rooms")
public class RoomController {
	@Autowired
	RoomRepository roomRepository;

	public RoomController() {

	}

	//Get one Room object with the id of the Room
	@GetMapping("{id}")
	public ResponseEntity<Room> getRoom(@PathVariable long id) {
		Optional<Room> foundRoom = roomRepository.findById(id);
		if(foundRoom.isPresent()) {
			Room room = foundRoom.get();
			return ResponseEntity.ok(room);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public Iterable<Room> getRoomList() {
		return roomRepository.findAll();
	}

	@GetMapping(value = "/availableRoomList")
	public ResponseEntity<List<Room>> getAvailableRooms() {
		List<Room> availableRooms = roomRepository.findByAvailable(true);
		return ResponseEntity.ok(availableRooms);
	}
	
	@GetMapping("/roomtype/{roomType}")
	public ResponseEntity<?> getRoomTypeRooms(@PathVariable String roomType) {
		
		if (roomType.equals("single")) {
			List<Room> roomList = roomRepository.findByRoomType(RoomType.singleRoom);
			return ResponseEntity.ok(roomList);
		} else if (roomType.equals("double")) {
			List<Room> roomList = roomRepository.findByRoomType(RoomType.doubleRoom);
			return ResponseEntity.ok(roomList);
		} else if (roomType.equals("family")) {
			List<Room> roomList = roomRepository.findByRoomType(RoomType.familyRoom);
			return ResponseEntity.ok(roomList);
		} else if (roomType.equals("penthouse")) {
			List<Room> roomList = roomRepository.findByRoomType(RoomType.penthouse);
			return ResponseEntity.ok(roomList);
		} else {
			String error = "Room type not recognised, roomtype entered: " + roomType;
			return ResponseEntity.badRequest().body(error);
		}
	}

	//Modify existing room with id with all the new info of newRoomInfo
	@PutMapping
	public ResponseEntity<?> putRoom(@RequestBody Room newRoomInfo) {
		List<Room> rooms = (List<Room>) roomRepository.findAll();
		
		for (Room room : rooms) {
			// check if room number is equal to an existing room number
			if (room.getRoomNumber().equals(newRoomInfo.getRoomNumber())) {
				// check if the equal room numbers are from different rooms (different ID)
				if (room.getId() != newRoomInfo.getId()) {
					String error = "There is already a room with that name.";
					return ResponseEntity.badRequest().body(error);
				}
			}
		}
		;
		return ResponseEntity.ok(roomRepository.save(newRoomInfo));
	}

	//Create room
	@PostMapping
	public void postRoom(@RequestBody Room room) {
		roomRepository.save(room);
	}

	//Delete room
	@DeleteMapping("{id}")
	public void deleteRoom(@PathVariable long id) {
		roomRepository.deleteById(id);
	}

	@PostConstruct
	public void init() {
		///TESTROOMS! roomNumber, RoomType roomType, int noOfAdults, int noOfChildren, int singleBeds, int doubleBeds, int babyBeds, boolean disabled
		roomRepository.save(new Room("101", RoomType.singleRoom,1,1,1,0,1,false,849));
		roomRepository.save(new Room("102", RoomType.doubleRoom, 2, 1, 2, 0, 1,  false,1199));
		roomRepository.save(new Room("103", RoomType.familyRoom, 4, 1,2,1,1,false,1649));
		Room newRoom = new Room("104", RoomType.penthouse, 8, 2, 4, 2, 2, false,2399);
		newRoom.setAvailable(false);
		roomRepository.save(newRoom);
		roomRepository.save(new Room("105", RoomType.singleRoom, 1, 1,1,0,1,false,1199));
		roomRepository.save(new Room("106", RoomType.singleRoom,1,1,1,0,1,false,849));
		roomRepository.save(new Room("107", RoomType.doubleRoom, 2, 1, 2, 0, 1,  false,1199));
		roomRepository.save(new Room("108", RoomType.familyRoom, 4, 1,2,1,1,false,1649));
		roomRepository.save(new Room("109", RoomType.singleRoom, 1, 1,1,0,1,false,1199));
		roomRepository.save(new Room("110", RoomType.penthouse, 8, 2, 4, 2, 2, false,2399));
	}
}

