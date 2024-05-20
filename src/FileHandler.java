import java.io.*;
import java.util.Scanner;

public class FileHandler {
    private static final String FILE_NAME = "inferno_run_tracker.txt";

    public static void saveToFile(String username, int day, int wave, String notes, int totalAttempts, int highestWave) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write("Username: " + username);
            writer.newLine();
            writer.write("Current day of attempts: " + day);
            writer.newLine();
            writer.write("Current wave: " + wave);
            writer.newLine();
            if (!notes.isEmpty()) {
                writer.write("Notes: " + notes);
                writer.newLine();
            }
            writer.write("Total attempts: " + totalAttempts);
            writer.newLine();
            writer.write("Highest wave all time: " + highestWave);
            writer.newLine();
            writer.write("----------");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int readTotalAttempts() {
        int totalAttempts = 0;
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("Total attempts: ")) {
                    totalAttempts = Integer.parseInt(line.substring(16).trim());
                }
            }
        } catch (FileNotFoundException e) {
            // File not found, start with 0 attempts
        }
        return totalAttempts;
    }

    public static int readHighestWave() {
        int highestWave = 0;
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("Highest wave all time: ")) {
                    highestWave = Integer.parseInt(line.substring(22).trim());
                }
            }
        } catch (FileNotFoundException e) {
            // File not found, start with 0 highest wave
        }
        return highestWave;
    }
}
