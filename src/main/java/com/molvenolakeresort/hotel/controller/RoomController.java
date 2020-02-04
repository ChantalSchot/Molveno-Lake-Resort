package com.molvenolakeresort.hotel.controller;

import com.molvenolakeresort.hotel.model.Room;
import com.molvenolakeresort.hotel.model.RoomType;
import com.molvenolakeresort.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
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
	public ResponseEntity<Iterable<Room>> getAvailableRooms() {
		Iterable<Room> availableRooms = roomRepository.findByAvailableIsTrue();
		return ResponseEntity.ok(availableRooms);
	}

	//Modify existing room with id with all the new info of newRoomInfo
	@PutMapping
	public void putRoom(@RequestBody Room newRoomInfo) {
		roomRepository.save(newRoomInfo);
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
		roomRepository.save(new Room("101", RoomType.doubleRoom,2,1,0,2,1,false));
		roomRepository.save(new Room("102", RoomType.singleRoom, 1, 1, 0, 1, 0,  false));
		roomRepository.save(new Room("103", RoomType.doubleRoom, 2, 0,2,0,0,false));
		Room newRoom = new Room("104", RoomType.familyRoom, 4, 2, 1, 2, 1, false);
		newRoom.setAvailable(false);
		roomRepository.save(newRoom);
	}
	}

