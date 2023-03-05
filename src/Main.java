import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Main {
    private static final Hotel deuCengHotel = Hotel.getHotel();

    public static void main(String[] args) {
        try {
            Scanner myReader = new Scanner(new File("commands.txt"));
            while (myReader.hasNextLine()) {
                String commandLine = myReader.nextLine();
                System.out.println(commandLine);
                String[] command = commandLine.split(";");
                processCommand(command);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("***An error occurred while reading the file, error: " + e.getMessage());
        }
    }

    private static void processCommand(String[] command) {
        String operation = command[0];
        switch (operation.trim()) {
            case "addRoom":
                for (int i = 0; i < Integer.valueOf(command[1]); i++) {
                    Room room = new Room(String.valueOf(deuCengHotel.rooms.size() + 1),
                            RoomType.valueOf(command[2].toUpperCase()),
                            Boolean.parseBoolean(command[3]),
                            Boolean.parseBoolean(command[4]),
                            Integer.valueOf(command[5]));
                    deuCengHotel.rooms.add(room);
                }
                break;
            case "listRooms":
                for (Room room : deuCengHotel.rooms)
                    room.print();
                break;
            case "addEmployee":
                Staff staff = new Staff();
                staff.staffId = String.valueOf(deuCengHotel.staffs.size() + 1);
                staff.name = command[1];
                staff.surname = command[2];
                staff.gender = command[3];
                staff.birthDate = DeuDate.convertStringDateToDeuDate(command[4]);
                Address staffAddress = new Address(command[5], command[6], command[7]);
                staff.address = staffAddress;
                staff.phoneNumber = PhoneNumber.convertStringPhoneNumberToPhoneNumber(command[8]);
                staff.job = StaffType.valueOf(command[9].toUpperCase());
                staff.salary = Double.valueOf(command[10]);
                deuCengHotel.staffs.add(staff);
                break;
            case "listEmployees":
                for (Staff eachStaff : deuCengHotel.staffs)
                    eachStaff.print();
                break;
            case "addCustomer":
                Customer customer = new Customer();
                customer.customerId = String.valueOf(deuCengHotel.customers.size() + 1);
                customer.name = command[1];
                customer.surname = command[2];
                customer.gender = command[3];
                customer.birthDate = DeuDate.convertStringDateToDeuDate(command[4]);
                Address customerAddress = new Address(command[5], command[6], command[7]);
                customer.contactAddress = customerAddress;
                customer.phoneNumber = PhoneNumber.convertStringPhoneNumberToPhoneNumber(command[8]);
                deuCengHotel.customers.add(customer);
                break;
            case "listCustomers":
                for (Customer eachCustomer : deuCengHotel.customers)
                    eachCustomer.print();
                break;
            case "searchCustomer":
                deuCengHotel.searchCustomer(command[1]);
                break;
            case "searchRoom":
                deuCengHotel.searchRoom(command[1], command[2]);
                break;
            case "addReservation":
                Reservation reservation = new Reservation(command[1], command[2],
                        DeuDate.convertStringDateToDeuDate(command[3]),
                        DeuDate.convertStringDateToDeuDate(command[4]));
                deuCengHotel.reservations.add(reservation);
                break;
            case "listReservations":
                for (Reservation eachReservation : deuCengHotel.reservations)
                    eachReservation.print();
                break;
            case "statistics":
                deuCengHotel.printMostReservedRoom();
                deuCengHotel.printMOstStayingCustomer();
                deuCengHotel.printProfit();
                deuCengHotel.printOccupancyRate();
                break;
            case "simulation":
                deuCengHotel.simulation(command[1], command[2]);
                break;
            case "":
                break;
            default:
                System.out.println("Invalid command, command: \"" + operation + "\" is not supported");
        }
    }
}

