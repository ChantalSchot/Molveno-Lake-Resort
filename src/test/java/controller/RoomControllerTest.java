package Controller;

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
    void init() {
        roomController = new RoomController();
    }

    @Test
    void getRoom() {
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
    void getRoomList() {
        List<Room> list= roomController.getRoomList();
        assertEquals(3, list.size());
        if(!list.isEmpty()) {
            System.out.println("Lijst van kamers:");
            for(Room room : list) {
                System.out.println("Kamer " + room.getRoomNumber() + " is " + (room.isAvailable() ? "Available" : "Not available."));
            }
        }
    }

    @Test
    void putRoom() {
    }

    @Test
    void postRoom() {
    }

    @Test
    void deleteRoom() {
    }
}