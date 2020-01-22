package Controller;

import Model.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestController {
    private List<Guest> guests;

    public GuestController(){
        guests = new ArrayList<>();
        Guest one =new Guest(){};
        one.setId(1); one.setName("Hans"); one.setMail("Hans@capgemini.com"); one.setAddress("Rejkavikplein 1"); one.setCity("Utrecht"); one.setPassportNr("987654321"); one.setPhone("0987654321");
        Guest two =new Guest(){};
        two.setId(2); two.setName("Klaas"); two.setMail("Klaas@capgemini.com"); two.setAddress("Rejkavikplein 2"); two.setCity("Utrecht"); two.setPassportNr("0192837465"); two.setPhone("0192837465");
        Guest three =new Guest(){};
        three.setId(3); three.setName("Jan"); three.setMail("Jan@capgemini.com"); three.setAddress("Rejkavikplein 3"); three.setCity("Utrecht"); three.setPassportNr("5647382910"); three.setPhone("5647382910");

        guests.add(one);
        guests.add(two);
        guests.add(three);
    }


    public Guest getGuest(int id){
        for (Guest g : guests){
            if(g.getId() == id){
                return g;
            }
        }
        System.out.println("There is no guest with that id.");
        return null;
    }

    public List<Guest> getGuests(){
        return guests;
    }

    public void postGuest(Guest guest){
        guests.add(guest);
        System.out.println(guest.getName() + "is added as a guest.");
    }

    public void putGuest(Guest guest){
        for(Guest g : guests){
            if(guest.getId() == g.getId()){
                guests.remove(g);
                guests.add(guest);
                break;
            }
        }
        System.out.println("The info of" + guest.getName() + "is updated.");
    }

    public  void deleteGuest(int id){
        for(Guest g : guests){
            if(id == g.getId()){
                guests.remove(g);
                System.out.println("The guest" + g.getName() + "is succefully deleted." );
                break;
            }
        }

    }
}
