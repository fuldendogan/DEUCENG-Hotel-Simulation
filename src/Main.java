import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Main {
    static Hotel deuCengHotel = new Hotel();

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
        if (command[0].equals("addRoom")) {
            for (int i = 0; i < Integer.valueOf(command[1]); i++) {
                Room room = new Room();
                room.roomId = deuCengHotel.rooms.size() + 1;
                room.roomType = RoomType.valueOf(command[2].toUpperCase());
                room.hasAirConditioner = Boolean.parseBoolean(command[3]);
                room.hasBalcony = Boolean.parseBoolean(command[4]);
                room.price = Integer.valueOf(command[5]);
                deuCengHotel.rooms.add(room);
            }
        } else if (command[0].equals("listRooms")) {
            for (Room room : deuCengHotel.rooms)
                room.print();
        }
        else if (command[0].trim().equals(""))
            return;
        else
            System.out.println("Invalid command, command: " + command[0] + " is not supported");
    }
}

