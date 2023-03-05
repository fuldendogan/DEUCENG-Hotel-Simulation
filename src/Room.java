public class Room {
    private String roomId;
    private RoomType roomType; //regular , deluxe, suite
    private Boolean hasAirConditioner;
    private Boolean hasBalcony;
    private Integer price;

    public Room(String roomId, RoomType roomType, Boolean hasAirConditioner, Boolean hasBalcony, Integer price) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.hasAirConditioner = hasAirConditioner;
        this.hasBalcony = hasBalcony;
        this.price = price;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Boolean getHasAirConditioner() {
        return hasAirConditioner;
    }

    public void setHasAirConditioner(Boolean hasAirConditioner) {
        this.hasAirConditioner = hasAirConditioner;
    }

    public Boolean getHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(Boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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
        return reservation.getDateOfArrival().isBetweenDates(startDate, endDate) && reservation.getDateOfDeparture().isBetweenDates(startDate, endDate);
    }
}
