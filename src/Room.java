public class Room {
    String roomId;
    RoomType roomType; //regular , deluxe, suite
    Boolean hasAirConditioner;
    Boolean hasBalcony;
    Integer price;

    public Room() {
    }

    public Room(String roomId, RoomType roomType, Boolean hasAirConditioner, Boolean hasBalcony, Integer price) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.hasAirConditioner = hasAirConditioner;
        this.hasBalcony = hasBalcony;
        this.price = price;
    }

    public void print() {
        String space = "%-10s";
        System.out.println("\tRoom #" + roomId + " " +
                String.format(space, roomType) + " " +
                String.format(space, hasAirConditioner()) + " " +
                String.format(space, hasBalcony()) + " " +
                String.format(space, price + "TL"));
    }

    private String hasAirConditioner() {
        return hasAirConditioner ? "aircondition" : "no-aircondition";
    }

    public String hasBalcony() {
        return hasBalcony ? "balcony" : "no-balcony";
    }



    public boolean isAvailableBetweenDates(Reservation reservation, DeuDate startDate, DeuDate endDate) {
        if (reservation.dateOfArrival.isBetweenDates(startDate, endDate) || reservation.dateOfDeparture.isBetweenDates(startDate, endDate))
            return false;
        return true;
    }
}
