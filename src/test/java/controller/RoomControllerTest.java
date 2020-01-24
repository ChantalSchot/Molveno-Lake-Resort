package controller;

import controller.EntityNotFoundException;
import controller.RoomController;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        try {
            Room room;
            room = roomController.getRoom("101");
            assertEquals(room.getRoomNumber(), "101");
            System.out.println("De kamer die is opgehaald heeft kamernummer " + room.getRoomNumber() + ".\nAvailable: " + room.isAvailable() + "\n" + room.getFacilities());
        } catch(EntityNotFoundException e) {
            System.out.println(e);
            assertEquals(e.toString(), "");
        }
    }

    @Test
    public void getRoomList() {
        List<Room> list= roomController.getRoomList();
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
        List<Room> list= roomController.getAvailableRooms();
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
        try {
            Room room = roomController.getRoom("101");
            Room newRoom = new Room("105", 1, 1, 0, false);
            System.out.println("Kamer met nummer " + room.getRoomNumber() + " wordt aangepast naar kamer 105");
            roomController.putRoom(newRoom, room.getRoomID());

            List<Room> list= roomController.getRoomList();
            assertEquals(4, list.size());
            if(!list.isEmpty()) {
                System.out.println("Lijst van kamers:");
                for(Room roomIt : list) {
                    System.out.println("Kamer " + roomIt.getRoomNumber() + " is " + (roomIt.isAvailable() ? "Available" : "Not available."));
                }
            }
        } catch(EntityNotFoundException e) {
            System.out.println(e);
            assertEquals(e.toString(), "");
        }
    }

    @Test
    public void postRoom() {
        System.out.println("Nieuwe kamer toevoegen:");
        Room room = new Room();
        room.setNumberOfSingleBeds(1);
        room.setRoomNumber("201");
        room.setAvailable(false);
        roomController.postRoom(room);

        List<Room> list= roomController.getRoomList();
        assertEquals(5, list.size());
        if(!list.isEmpty()) {
            System.out.println("Lijst van kamers:");
            for(Room roomIt : list) {
                System.out.println("Kamer " + roomIt.getRoomNumber() + " is " + (roomIt.isAvailable() ? "Available" : "Not available."));
            }
        }
    }

    @Test
    public void deleteRoom() {
        System.out.println("Kamer 101 wordt verwijderd...");

        try {
            Room room = roomController.getRoom("101");
            roomController.deleteRoom(room.getRoomID());
        } catch(EntityNotFoundException e) {
            System.out.println(e);
            assertEquals(e.toString(), "");
        }

        List<Room> list= roomController.getRoomList();
        assertEquals(3, list.size());
        if(!list.isEmpty()) {
            System.out.println("Lijst van kamers:");
            for(Room roomIt : list) {
                System.out.println("Kamer " + roomIt.getRoomNumber() + " is " + (roomIt.isAvailable() ? "Available" : "Not available."));
            }
        }
    }
}