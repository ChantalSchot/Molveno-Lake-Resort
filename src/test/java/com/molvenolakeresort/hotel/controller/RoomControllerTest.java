package com.molvenolakeresort.hotel.controller;

import com.molvenolakeresort.hotel.model.Room;
import com.molvenolakeresort.hotel.model.RoomType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomControllerTest {
    RoomController roomController;

    @BeforeEach
    public void init() {
        roomController = new RoomController();
    }

    @Test
    public void getRoom() {
        Room room;
        room = roomController.getRoom(1).getBody();
        assertEquals("101", room.getRoomNumber());
        System.out.println("De kamer die is opgehaald heeft kamernummer " + room.getRoomNumber() + ".\nAvailable: " + room.isAvailable() + "\n" + room.getFacilities());
    }

    @Test
    public void getRoomList() {
        List<Room> list = new ArrayList<>();

        //Copy whole roomController list to temporary list to test length
        for(Room room : roomController.getRoomList()) {
            list.add(room);
        }

        assertEquals(4, list.size());
        if(!list.isEmpty()) {
            System.out.println("Lijst van kamers:");
            for(Room room : list) {
                System.out.println("Kamer " + room.getRoomNumber() + " is " + (room.isAvailable() ? "Available" : "Not available."));
            }
        }
    }

    @Test
    void getAvailableRooms() {
        List<Room> list = new ArrayList<>();

        //Copy whole roomController list to temporary list to test length
        for(Room room : roomController.getAvailableRooms().getBody()) {
            list.add(room);
        }

        assertEquals(3, list.size());
        if(!list.isEmpty()) {
            System.out.println("Lijst van beschikbare kamers:");
            for(Room room : list) {
                System.out.println("Kamer " + room.getRoomNumber() + " is " + (room.isAvailable() ? "Available" : "Not available."));
            }
        }
    }

    @Test
    public void putRoom() {
        //Modify a room
        Room room = roomController.getRoom(1).getBody();
        System.out.println("Kamer met nummer " + room.getRoomNumber() + " wordt aangepast naar kamer 105");
        room.setRoomNumber("105");
        roomController.putRoom(room);
        assertEquals("105", roomController.getRoom(1).getBody().getRoomNumber());

        List<Room> list = new ArrayList<>();

        //Copy whole roomController list to temporary list to test length
        for(Room roomIt : roomController.getRoomList()) {
            list.add(roomIt);
        }

        if(!list.isEmpty()) {
            System.out.println("Lijst van kamers:");
            for(Room roomIt : list) {
                System.out.println("Kamer " + roomIt.getRoomNumber() + " is " + (roomIt.isAvailable() ? "Available" : "Not available."));
            }
        }
    }

//    @Test
//    public void postRoom() {
//        System.out.println("Nieuwe kamer toevoegen:");
//        Room room = new Room();
//        room.setNumberOfSingleBeds(1);
//        room.setRoomNumber("201");
//        room.setAvailable(false);
//        roomController.postRoom(room);
//
//        List<Room> list= roomController.getRoomList();
//        assertEquals(5, list.size());
//        if(!list.isEmpty()) {
//            System.out.println("Lijst van kamers:");
//            for(Room roomIt : list) {
//                System.out.println("Kamer " + roomIt.getRoomNumber() + " is " + (roomIt.isAvailable() ? "Available" : "Not available."));
//            }
//        }
//    }
//
//    @Test
//    public void deleteRoom() {
//        System.out.println("Kamer 101 wordt verwijderd...");
//
//        try {
//            Room room = roomController.getRoom("101");
//            roomController.deleteRoom(room.getId());
//        } catch(EntityNotFoundException e) {
//            System.out.println(e);
//            assertEquals(e.toString(), "");
//        }
//
//        List<Room> list= roomController.getRoomList();
//        assertEquals(3, list.size());
//        if(!list.isEmpty()) {
//            System.out.println("Lijst van kamers:");
//            for(Room roomIt : list) {
//                System.out.println("Kamer " + roomIt.getRoomNumber() + " is " + (roomIt.isAvailable() ? "Available" : "Not available."));
//            }
//        }
//    }

 
}
