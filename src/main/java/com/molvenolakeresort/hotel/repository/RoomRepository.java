package com.molvenolakeresort.hotel.repository;

import com.molvenolakeresort.hotel.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
	List<Room> findByAvailable(boolean available);
}
