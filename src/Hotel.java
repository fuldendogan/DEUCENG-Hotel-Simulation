import java.util.ArrayList;
import java.util.List;

public class Hotel {
    String name;
    String foundationDate;
    Address address;
    PhoneNumber phoneNumber;
    Double starts;
    Integer maxNumberOfRooms = 30;
    Integer maxNumberOfStaff = 50;
    List<Room> rooms = new ArrayList<>();
    List<Staff> staffs = new ArrayList<>();
}
