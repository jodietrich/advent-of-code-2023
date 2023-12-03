import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.max;

public class Main2 {
    public static void main(String[] args) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of("day2/resources/input1.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int lineSum = 0;
        System.out.println("Power:");
        for (String line: lines) {
            int power = linePower(line);
            lineSum += power;
            System.out.println(power);
        }
        System.out.println("Power sum:");
        System.out.println(lineSum);
    }

    private static int linePower(String line) {
        HashMap<String, Integer> maxCounts = new HashMap<>();
        maxCounts.put("red", 0);
        maxCounts.put("green", 0);
        maxCounts.put("blue", 0);
        Pattern numColor = Pattern.compile("\\d+ (" + String.join("|", maxCounts.keySet()) + ")");
        Matcher matcher = numColor.matcher(line);
        while (matcher.find()) {
            String currentMatch = matcher.group();
            String[] split = currentMatch.split(" ");
            int number = Integer.parseInt(split[0]);
            String color = split[1];
            maxCounts.put(color, max(maxCounts.get(color), number));
        }
        int power = 1;
        for (int count: maxCounts.values()) {
            power *= count;
        }
        return power;
    }
}