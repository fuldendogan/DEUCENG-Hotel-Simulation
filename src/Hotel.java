import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel {
    static private Hotel hotel;//singleton pattern

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
    static List<Room> rooms = new ArrayList<>();
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

    public static Room getRoomById(String roomId) {
        for (Room room : rooms) {
            if (room.roomId.equals(roomId))
                return room;
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
            if (reservation == null || !room.isAvailableBetweenDates(reservation, startDate, endDate)) {
                room.print();
                found = true;
            }
        }
        if (!found)
            System.out.println("No room found for the given dates.");
    }

    public void printMostReservedRoom() {
        int[] roomReservationDayCount = new int[rooms.size() + 1];
        for (Reservation reservation : reservations) {
            roomReservationDayCount[Integer.parseInt(reservation.roomId)] += reservation.getGapBetweenDates();
        }
        int max = -1;
        int roomId = -1;
        for (int i = 0; i < roomReservationDayCount.length; i++) {
            if (roomReservationDayCount[i] >= max) {
                max = roomReservationDayCount[i];
                roomId = i;
            }
        }
        System.out.println("\t 1.The most reserved room = Room #" + roomId + " with " + max + " days reserved.");

    }

    public void printMOstStayingCustomer() {
        int[] customerReservationDayCount = new int[customers.size() + 1];
        for (Reservation reservation : reservations) {
            customerReservationDayCount[Integer.parseInt(reservation.roomId)] += reservation.getGapBetweenDates();
        }
        int max = -1;
        int customerId = -1;
        for (int i = 0; i < customerReservationDayCount.length; i++) {
            if (customerReservationDayCount[i] >= max) {
                max = customerReservationDayCount[i];
                customerId = i;
            }
        }
        Customer customer = getCustomerById(String.valueOf(customerId));
        System.out.println("\t 2.The best customer = " + customer.name + " " + customer.surname + " " + max + " days");
    }

    public void printProfit() {
        int income = 0;
        System.out.print("\t 3.Income = ");
        for (int i = 0; i < reservations.size(); i++) {
            int eachIncome = reservations.get(i).getGapBetweenDates() * getRoomById(reservations.get(i).roomId).price;
            if (i == (reservations.size() - 1))
                System.out.printf("%,d", eachIncome);
            else
                System.out.printf("%,d + ", eachIncome);
            income += eachIncome;
        }
        System.out.printf(" = %,d", income);
        int salary = 0;
        for (Staff staff : staffs) {
            salary += staff.salary;
        }
        salary *= 12;
        int constantExpenses = 120000;
        int profit = income - salary - constantExpenses;

        System.out.printf("\n\t   Salary = %,d", salary);
        System.out.printf("\n\t   Constant expenses = " + "%,d", constantExpenses);
        System.out.printf("\n\t   Profit = %,d  - %,d - %,d = %,d", income, salary, constantExpenses, profit);
    }

    public void printOccupancyRate() {
        System.out.println("\n\t 4.Monthly occupancy rate");
        System.out.print("\t\t");
        for (int i = 1; i <= 12; i++) {
            System.out.printf("%d\t", i);
        }
        Map<String, Integer> monthlyOccupancyRate = new HashMap<>();
        for (Reservation reservation : reservations) {
            int startMonth = reservation.dateOfArrival.month;
            int endMonth = reservation.dateOfDeparture.month;
            for (int i = startMonth; i <= endMonth; i++) {
                String deuDate = i + ";" + reservation.dateOfArrival.year;
                int dayCountOfTheMonth = DeuDate.getNumberOfDaysOfMonth(i, reservation.dateOfArrival.year);

                int reservedDayCount;
                if (startMonth == endMonth)
                    reservedDayCount = reservation.dateOfDeparture.day - reservation.dateOfArrival.day;
                else {
                    if (i == startMonth)
                        reservedDayCount = dayCountOfTheMonth - reservation.dateOfArrival.day + 1;
                    else if (i == endMonth)
                        reservedDayCount = reservation.dateOfDeparture.day - 1;
                    else
                        reservedDayCount = dayCountOfTheMonth;
                }

                if (monthlyOccupancyRate.containsKey(deuDate)) {
                    monthlyOccupancyRate.put(deuDate, monthlyOccupancyRate.get(deuDate) + reservedDayCount);
                } else {
                    monthlyOccupancyRate.put(deuDate, reservedDayCount);
                }
            }
        }
        System.out.print("\n\t\t");
        for (int i = 1; i <= 12; i++) {
            double dayCountOfTheMonth = monthlyOccupancyRate.getOrDefault(i + ";" + reservations.get(0).dateOfArrival.year, 0);
            double numberOfDaysOfMonth = DeuDate.getNumberOfDaysOfMonth(i, reservations.get(0).dateOfArrival.year);
            double percentage = dayCountOfTheMonth / (numberOfDaysOfMonth * rooms.size()) * 100;
            System.out.printf("%d%%\t", Math.round(percentage * 100.0 / 100.0));//round to 2 decimal places
        }
        System.out.println();
    }
}