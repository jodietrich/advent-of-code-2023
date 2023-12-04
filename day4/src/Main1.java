import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of("day4/resources/input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int lineSum = 0;
        System.out.println("Values:");
        for (String line: lines) {
            int value = cardValue(line);
            lineSum += value;
            System.out.println(value);
        }
        System.out.println("Total: " + lineSum);
    }

    private static int cardValue(String line) {
        line = line.split(": +")[1];
        String[] splits = line.split(" +\\| +");
        List<String> winners = List.of(splits[0].split(" +"));
        List<String> have = List.of(splits[1].split(" +"));
//        System.out.println("Winners");
//        winners.forEach(System.out::println);
//        System.out.println("Have");
//        have.forEach(System.out::println);
        HashSet<String> winnerSet = new HashSet<>(winners.size());
        winnerSet.addAll(winners);
        winnerSet.retainAll(have);
//        System.out.println("Matching numbers");
//        winnerSet.forEach(System.out::println);
        if (winnerSet.isEmpty()) {
            return 0;
        } else {
            return 1 << (winnerSet.size() - 1);
        }
    }
}