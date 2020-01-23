import controller.EntityNotFoundException;
import controller.GuestController;

public class Main {
	public static void main(String[] args) throws EntityNotFoundException {

		GuestController guestList = new GuestController();

		try {
			System.out.println(guestList.getGuest(1).getName());
//			guestList.postGuest("Pietje");
//			System.out.println(guestList.getGuest(4).getName());
		} catch (EntityNotFoundException e) {
			System.out.println(e);
		}


	}
}
