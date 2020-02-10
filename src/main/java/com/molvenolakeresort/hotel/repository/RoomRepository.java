package com.molvenolakeresort.hotel.repository;

import com.molvenolakeresort.hotel.model.Room;
import com.molvenolakeresort.hotel.model.RoomType;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
	List<Room> findByAvailable(boolean available);
	
	List<Room> findByRoomType(RoomType roomType);

	
//	QUERY EXAMPLE to add date into method (to find available rooms by check in / check out date):
//	@Query("SELECT b FROM Booking b WHERE Date(checkIn) <= 2020-02-20")
//	public List<Room> find(@Param("checkIn") Date checkIn, @Param("checkOut") Date checkOut);
	
}
