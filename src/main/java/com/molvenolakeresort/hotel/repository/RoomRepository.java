package com.molvenolakeresort.hotel.repository;

import com.molvenolakeresort.hotel.model.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {
	Iterable<Room> findByAvailableIsTrue();
}
