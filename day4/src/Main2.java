import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of("day4/resources/input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Integer> copies = new ArrayList<>(Collections.nCopies(lines.size(), 1));
        for (int i = 0; i < lines.size(); ++i) {
            int value = cardValue(lines.get(i));
            for (int j = i+1; j < i+1+value; ++j) {
                copies.set(j, copies.get(j) + copies.get(i));
            }
            copies.forEach(System.out::println);
            System.out.println();
        }
        System.out.println("Copies:");
        copies.forEach(System.out::println);
        int copySum = copies.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Total: " + copySum);
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
        return winnerSet.size();
    }
}