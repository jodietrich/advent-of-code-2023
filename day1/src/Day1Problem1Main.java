import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day1Problem1Main {
    public static void main(String[] args) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of("day1/resources/day1.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int calibrationSum = 0;
        for (String line: lines) {
            String digits = line.replaceAll("[^0-9]", "");
            calibrationSum += Character.getNumericValue(digits.charAt(0))*10 + Character.getNumericValue(digits.charAt(digits.length() - 1));
        }
        System.out.println(calibrationSum);
    }
}
