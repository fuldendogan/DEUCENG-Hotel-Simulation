import java.util.HashMap;
import java.util.Map;

public class Hotel {
    private String name;
    private String foundationDate;
    private Address address;
    private PhoneNumber phoneNumber;
    private Double starts;
    private Room[] rooms = new Room[0];
    private Staff[] staffs = new Staff[0];
    private Customer[] customers = new Customer[0];
    private Reservation[] reservations = new Reservation[0];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(String foundationDate) {
        this.foundationDate = foundationDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getStarts() {
        return starts;
    }

    public void setStarts(Double starts) {
        this.starts = starts;
    }


    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public Staff[] getStaffs() {
        return staffs;
    }

    public void setStaffs(Staff[] staffs) {
        this.staffs = staffs;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    public Reservation[] getReservations() {
        return reservations;
    }

    public void setReservations(Reservation[] reservations) {
        this.reservations = reservations;
    }

    public Customer getCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId))
                return customer;
        }
        return null;
    }

    public Room getRoomById(String roomId) {
        for (Room room : rooms) {
            if (room.getRoomId().equals(roomId))
                return room;
        }
        return null;
    }

    public void searchCustomer(String key) {
        boolean found = false;
        if (key.contains("*")) {
            for (Customer customer : customers) {
                if (customer.getName().startsWith(key.replace("*", ""))) {
                    customer.print();
                    found = true;
                }
            }
        } else if (key.contains("?")) {
            for (Customer customer : customers) {
                if (customer.getName().length() == key.length() && customer.getName().startsWith(key.replace("?", ""))) {
                    customer.print();
                    found = true;
                }
            }
        } else {
            for (Customer customer : customers) {
                if (customer.getName().equals(key)) {
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
            Reservation reservation = Reservation.getReservationByRoomId(reservations, room.getRoomId());
            if (reservation == null || !room.isAvailableBetweenDates(reservation, startDate, endDate)) {
                room.print();
                found = true;
            }
        }
        if (!found)
            System.out.println("No room found for the given dates.");
    }

    public void printMostReservedRoom() {
        int[] roomReservationDayCount = new int[rooms.length + 1];
        for (Reservation reservation : reservations) {
            roomReservationDayCount[Integer.parseInt(reservation.getRoomId())] += reservation.getGapBetweenDates();
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
        int[] customerReservationDayCount = new int[customers.length + 1];
        for (Reservation reservation : reservations) {
            customerReservationDayCount[Integer.parseInt(reservation.getRoomId())] += reservation.getGapBetweenDates();
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
        System.out.println("\t 2.The best customer = " + customer.getName() + " " + customer.getSurname() + " " + max + " days");
    }

    public void printProfit() {
        int income = 0;
        System.out.print("\t 3.Income = ");
        for (int i = 0; i < reservations.length; i++) {
            int eachIncome = reservations[i].getGapBetweenDates() * getRoomById(reservations[i].getRoomId()).getPrice();
            if (i == (reservations.length - 1))
                System.out.printf("%,d", eachIncome);
            else
                System.out.printf("%,d + ", eachIncome);
            income += eachIncome;
        }
        System.out.printf(" = %,d", income);
        int salary = 0;
        for (Staff staff : staffs) {
            salary += staff.getSalary();
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
            int startMonth = reservation.getDateOfArrival().getMonth();
            int endMonth = reservation.getDateOfDeparture().getMonth();
            for (int i = startMonth; i <= endMonth; i++) {
                String deuDate = i + ";" + reservation.getDateOfArrival().getYear();
                int dayCountOfTheMonth = DeuDate.getNumberOfDaysOfMonth(i, reservation.getDateOfArrival().getYear());

                int reservedDayCount;
                if (startMonth == endMonth)
                    reservedDayCount = reservation.getDateOfDeparture().getDay() - reservation.getDateOfArrival().getDay();
                else {
                    if (i == startMonth)
                        reservedDayCount = dayCountOfTheMonth - reservation.getDateOfArrival().getDay() + 1;
                    else if (i == endMonth)
                        reservedDayCount = reservation.getDateOfDeparture().getDay() - 1;
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
            double dayCountOfTheMonth = monthlyOccupancyRate.getOrDefault(i + ";" + reservations[0].getDateOfArrival().getYear(), 0);
            double numberOfDaysOfMonth = DeuDate.getNumberOfDaysOfMonth(i, reservations[0].getDateOfArrival().getYear());
            double percentage = dayCountOfTheMonth / (numberOfDaysOfMonth * rooms.length) * 100;
            System.out.printf("%d%%\t", Math.round(percentage * 100.0 / 100.0));//round to 2 decimal places
        }
        System.out.println();
    }

    public void simulation(String date1S, String date2S) {
        DeuDate date1 = DeuDate.convertStringDateToDeuDate(date1S);
        DeuDate date2 = DeuDate.convertStringDateToDeuDate(date2S);
        System.out.println();
        String space = "%-12s", space2 = "%8s";
        StringBuilder daysString = new StringBuilder(), customerString = new StringBuilder(), satisfactionString = new StringBuilder();
        double avgSatisfaction = 0;
        if (date1.getMonth() == date2.getMonth()) {
            for (int dayNumber = date1.getDay(); dayNumber <= date2.getDay(); dayNumber++) {
                daysString.append(String.format(space2, dayNumber));
                double customerCount = 0;
                for (Reservation reservation : reservations) {
                    if (reservation.containsThisDay(date1)) {
                        customerCount++;
                    }
                }
                customerString.append(String.format(space2, customerCount));
                double houseKeeperCount = 0;
                for (Staff staff : staffs) {
                    if (staff.getJob().equals(StaffType.HOUSEKEEPER)) {
                        houseKeeperCount++;
                    }
                }
                int satisfaction = 100;
                if (customerCount != 0)
                    satisfaction = (int) (100 * (houseKeeperCount * 3 / customerCount));
                if (satisfaction > 100)
                    satisfaction = 100;
                avgSatisfaction += satisfaction;
                satisfactionString.append(String.format(space2, satisfaction + "%"));
            }
            avgSatisfaction /= (date2.getDay() - date1.getDay() + 1);
        } else {
            DeuDate[] dates = DeuDate.getDaysBetweenDates(date1, date2);
            for (DeuDate day : dates) {
                daysString.append(String.format(space2, day.getDay()));
                double customerCount = 0;
                for (Reservation reservation : reservations) {
                    if (reservation.containsThisDay(day)) {
                        customerCount++;
                    }
                }
                customerString.append(String.format(space2, customerCount));
                double houseKeeperCount = 0;
                for (Staff staff : staffs) {
                    if (staff.getJob().equals(StaffType.HOUSEKEEPER)) {
                        houseKeeperCount++;
                    }
                }
                int satisfaction = 100;
                if (customerCount != 0)
                    satisfaction = (int) (100 * (houseKeeperCount * 3 / customerCount));
                if (satisfaction > 100)
                    satisfaction = 100;
                avgSatisfaction += satisfaction;
                satisfactionString.append(String.format(space2, satisfaction + "%"));
            }
            avgSatisfaction /= dates.length;
        }
        System.out.print(String.format(space, "Day") + ":");
        System.out.println(daysString);
        System.out.print(String.format(space, "Customer") + ":");
        System.out.println(customerString);
        System.out.print(String.format(space, "Satisfaction") + ":");
        System.out.println(satisfactionString);
        System.out.println("Average Satisfaction = " + avgSatisfaction + "%");
    }

    public void processCommand(String[] command) {
        String operation = command[0];
        switch (operation.trim()) {
            case "addRoom":
                for (int i = 0; i < Integer.parseInt(command[1]); i++) {
                    Room room = new Room(String.valueOf(rooms.length + 1),
                            RoomType.valueOf(command[2].toUpperCase()),
                            Boolean.parseBoolean(command[3]),
                            Boolean.parseBoolean(command[4]),
                            Integer.valueOf(command[5]));
                    addToArray(rooms, room);
                }
                break;
            case "listRooms":
                for (Room room : rooms)
                    room.print();
                break;
            case "addEmployee":
                Staff staff = new Staff();
                staff.setStaffId(String.valueOf(staffs.length + 1));
                staff.setName(command[1]);
                staff.setSurname(command[2]);
                staff.setGender(command[3]);
                staff.setBirthDate(DeuDate.convertStringDateToDeuDate(command[4]));
                Address staffAddress = new Address(command[5], command[6], command[7]);
                staff.setAddress(staffAddress);
                staff.setPhoneNumber(PhoneNumber.convertStringPhoneNumberToPhoneNumber(command[8]));
                staff.setJob(StaffType.valueOf(command[9].toUpperCase()));
                staff.setSalary(Double.valueOf(command[10]));
                addToArray(staffs, staff);
                break;
            case "listEmployees":
                for (Staff eachStaff : staffs)
                    eachStaff.print();
                break;
            case "addCustomer":
                Customer customer = new Customer();
                customer.setCustomerId(String.valueOf(customers.length + 1));
                customer.setName(command[1]);
                customer.setSurname(command[2]);
                customer.setGender(command[3]);
                customer.setBirthDate(DeuDate.convertStringDateToDeuDate(command[4]));
                Address customerAddress = new Address(command[5], command[6], command[7]);
                customer.setContactAddress(customerAddress);
                customer.setPhoneNumber(PhoneNumber.convertStringPhoneNumberToPhoneNumber(command[8]));
                addToArray(customers, customer);
                break;
            case "listCustomers":
                for (Customer eachCustomer : customers)
                    eachCustomer.print();
                break;
            case "searchCustomer":
                searchCustomer(command[1]);
                break;
            case "searchRoom":
                searchRoom(command[1], command[2]);
                break;
            case "addReservation":
                Reservation reservation = new Reservation(command[1], command[2],
                        DeuDate.convertStringDateToDeuDate(command[3]),
                        DeuDate.convertStringDateToDeuDate(command[4]));
                addToArray(reservations, reservation);
                break;
            case "listReservations":
                for (Reservation eachReservation : reservations)
                    eachReservation.print(getCustomerById(eachReservation.getCustomerId()));
                break;
            case "statistics":
                printMostReservedRoom();
                printMOstStayingCustomer();
                printProfit();
                printOccupancyRate();
                break;
            case "simulation":
                simulation(command[1], command[2]);
                break;
            case "":
                break;
            default:
                System.out.println("Invalid command, command: \"" + operation + "\" is not supported");
        }
    }

    private <T> void addToArray(T[] array, T newElement) {
        if (array instanceof Room[]) {
            if (array.length == Constants.MAX_NUMBER_OF_ROOMS) {
                System.out.println("Room count is full, cannot add more room");
                return;
            }
            Room[] newArray = new Room[array.length + 1];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = (Room) array[i];
            }
            newArray[array.length] = (Room) newElement;
            rooms = newArray;
        } else if (array instanceof Staff[]) {
            if (array.length == Constants.MAX_NUMBER_OF_STAFF) {
                System.out.println("Staff count is full, cannot add more staff");
                return;
            }
            Staff[] newArray = new Staff[array.length + 1];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = (Staff) array[i];
            }
            newArray[array.length] = (Staff) newElement;
            staffs = newArray;
        } else if (array instanceof Customer[]) {
            Customer[] newArray = new Customer[array.length + 1];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = (Customer) array[i];
            }
            newArray[array.length] = (Customer) newElement;
            customers = newArray;
        } else if (array instanceof Reservation[]) {
            if (!isRoomAvailable((Reservation) newElement)) {
                System.out.println("Room #"+((Reservation) newElement).getRoomId()+" is not available");
                return;
            }
            Reservation[] newArray = new Reservation[array.length + 1];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = (Reservation) array[i];
            }
            newArray[array.length] = (Reservation) newElement;
            reservations = newArray;
        }
    }

    private boolean isRoomAvailable(Reservation reservation) {
        Room room = getRoomById(reservation.getRoomId());
        if (room == null)
            return false;
        Reservation existReservation = Reservation.getReservationByRoomId(reservations, room.getRoomId());
        if (existReservation == null || room.isAvailableBetweenDates(existReservation, reservation.getDateOfArrival(), reservation.getDateOfDeparture()))
            return true;
        return false;
    }
}