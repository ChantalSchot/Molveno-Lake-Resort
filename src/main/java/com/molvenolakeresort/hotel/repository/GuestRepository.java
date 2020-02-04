package com.molvenolakeresort.hotel.repository;

import com.molvenolakeresort.hotel.model.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends CrudRepository<Guest,Long> {

}