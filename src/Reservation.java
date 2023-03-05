import java.util.List;

public class Reservation {
    String customerId;
    String roomId;
    DeuDate dateOfArrival;
    //It is expected to make a reservation between 01.01.2024 and 31.12.2024.
    DeuDate dateOfDeparture;

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

    public int getGapBetweenDates() {
        int dateOfArrivalDayCount = DeuDate.convertDeuDateToDayCount(dateOfArrival);
        int dateOfDepartureDayCount = DeuDate.convertDeuDateToDayCount(dateOfDeparture);
        return dateOfDepartureDayCount - dateOfArrivalDayCount;
    }

    public void print() {
        String space = "%-10s";
        Customer customer = Hotel.getHotel().getCustomerById(customerId);
        System.out.println("\tRoom #" + roomId + " " +
                String.format(space, customer.name) + " " +
                String.format(space, customer.surname) + " " +
                String.format(space, dateOfArrival) + " " +
                String.format(space, dateOfDeparture));
    }
}
