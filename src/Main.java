import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel deuCengHotel = new Hotel();
        String fileName = "commands.txt";

        try {
            Scanner myReader = new Scanner(new File(fileName));
            while (myReader.hasNextLine()) {
                String commandLine = myReader.nextLine();
                System.out.println(commandLine);
                String[] command = commandLine.split(";");
                deuCengHotel.processCommand(command);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("***An error occurred while reading the file, error: " + e.getMessage() + ", file name: " + fileName + "***");
        }
    }
}

