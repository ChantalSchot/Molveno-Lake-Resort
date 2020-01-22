package Controller;

import Model.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestController {
    private List<Guest> guests;

    public GuestController(){
        guests = new ArrayList<>();
    }


    public Guest getGuest(int id) throws EntityNotFoundException {
        for (Guest g : guests){
            if (g.getId() == id) {
                return g;
            }
        }
        throw new EntityNotFoundException("Guest was not found for ID: " + id);
    }

    public List<Guest> getGuests(){
        return guests;
    }

    public void postGuest(Guest guest){
        guests.add(guest);
    }

    public void putGuest(Guest guest){
        for(Guest g : guests){
            if(guest.getId() == g.getId()){
                guests.remove(g);
                guests.add(guest);
            }

        }
    }

    public  void deleteGuest(int id){
        for(Guest g : guests){
            if(id == g.getId()){
                guests.remove(g);
            }

        }
    }
}
