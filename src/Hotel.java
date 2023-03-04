import java.util.ArrayList;
import java.util.List;

public class Hotel {

    static Hotel hotel;//singleton pattern
    public static Hotel getHotel() {
        if (hotel == null)
            hotel = new Hotel();
        return hotel;
    }

    String name;
    String foundationDate;
    Address address;
    PhoneNumber phoneNumber;
    Double starts;
    Integer maxNumberOfRooms = 30;
    Integer maxNumberOfStaff = 50;
    List<Room> rooms = new ArrayList<>();
    static List<Staff> staffs = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();
    static List<Reservation> reservations = new ArrayList<>();

    public static Customer getCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.customerId.equals(customerId))
                return customer;
        }
        return null;
    }


    public void searchCustomer(String key) {
        boolean found = false;
        if (key.contains("*")) {
            for (Customer customer : customers) {
                if (customer.name.startsWith(key.replace("*", ""))) {
                    customer.print();
                    found = true;
                }
            }
        } else if (key.contains("?")) {
            for (Customer customer : customers) {
                if (customer.name.length() == key.length() && customer.name.startsWith(key.replace("?", ""))) {
                    customer.print();
                    found = true;
                }
            }
        } else {
            for (Customer customer : customers) {
                if (customer.name.equals(key)) {
                    customer.print();
                    found = true;
                }
            }
        }
        if (!found)
            System.out.println("No customer found with name \"" + key + "\".");
    }

    public void searchRoom(String key1, String key2) {
        //key=25.4.2024;30.4.2024
        DeuDate startDate = DeuDate.convertStringDateToDeuDate(key1);
        DeuDate endDate = DeuDate.convertStringDateToDeuDate(key2);

        boolean found = false;
        for (Room room : rooms) {
            Reservation reservation = Reservation.getReservationByRoomId(reservations, room.roomId);
            if (reservation == null || !room.isAvailableBetweenDates(reservation, startDate, endDate)){
                room.print();
                found = true;
            }
        }
        if (!found)
            System.out.println("No room found for the given dates.");
    }


}
