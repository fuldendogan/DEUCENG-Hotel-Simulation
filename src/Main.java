import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName = "commands.txt";

        try {
            Scanner myReader = new Scanner(new File(fileName));
            while (myReader.hasNextLine()) {
                String commandLine = myReader.nextLine();
                Utils.logCommand(commandLine);
                String[] command = commandLine.split(";");
                Hotel.processCommand(command);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("***An error occurred while reading the file, error: " + e.getMessage() + ", file name: " + fileName + "***");
        }
    }
}

