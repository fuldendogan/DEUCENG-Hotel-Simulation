public class Room {
    Integer roomId;
    RoomType roomType; //regular , deluxe, suite
    Boolean hasAirConditioner;
    Boolean hasBalcony;
    Integer price;

    public void print() {
        System.out.println("Room #" + roomId + " " + roomType + " " +
                hasAirConditioner() + " " +
                hasBalcony() + " " +
                price+"TL");
    }

    private String hasAirConditioner() {
        return hasAirConditioner ? "aircondition" : "no-aircondition";
    }

    public String hasBalcony() {
        return hasBalcony ? "balcony" : "no-balcony";
    }
}
