import java.util.List;

public class Reservation {
    private String customerId;
    private String roomId;
    private DeuDate dateOfArrival;
    //It is expected to make a reservation between 01.01.2024 and 31.12.2024.
    private DeuDate dateOfDeparture;

    public Reservation(String customerId, String roomId, DeuDate dateOfArrival, DeuDate dateOfDeparture) {
        this.customerId = customerId;
        this.roomId = roomId;
        this.dateOfArrival = dateOfArrival;
        this.dateOfDeparture = dateOfDeparture;
    }

    public static Reservation getReservationByRoomId(List<Reservation> reservations, String roomId) {
        for (Reservation reservation : reservations) {
            if (reservation.roomId.equals(roomId))
                return reservation;
        }
        return null;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public DeuDate getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(DeuDate dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public DeuDate getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(DeuDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public int getGapBetweenDates() {
        int dateOfArrivalDayCount = DeuDate.convertDeuDateToDayCount(dateOfArrival);
        int dateOfDepartureDayCount = DeuDate.convertDeuDateToDayCount(dateOfDeparture);
        return dateOfDepartureDayCount - dateOfArrivalDayCount;
    }

    public void print(Customer customer) {
        String space = "%-10s";
        if (customer == null)
            return;
        System.out.println("\tRoom #" + roomId + " " +
                String.format(space, customer.getName()) + " " +
                String.format(space, customer.getSurname()) + " " +
                String.format(space, dateOfArrival) + " " +
                String.format(space, dateOfDeparture));
    }

    public boolean containsThisDay(DeuDate targetDate) {
        boolean afterArrivalDate = dateOfArrival.compareTo(targetDate) <= 0;
        boolean beforeDepartureDate = dateOfDeparture.compareTo(targetDate) > 0;
        return afterArrivalDate && beforeDepartureDate;
    }
}
