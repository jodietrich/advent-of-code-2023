import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1Problem2Main {
    private final static Map<String, Character> wordToDigit = Map.of(
            "one", '1',
            "two", '2',
            "three", '3',
            "four", '4',
            "five", '5',
            "six", '6',
            "seven", '7',
            "eight", '8',
            "nine", '9'
    );

    public static void main(String[] args) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of("day1/resources/day1.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int calibrationSum = 0;
        for (String line: lines) {
            System.out.println(line);
            char firstDigit = findFirstDigit(line, wordToDigit);
            char lastDigit = findLastDigit(line);
            int rowNumber = Character.getNumericValue(firstDigit)*10 + Character.getNumericValue(lastDigit);
            System.out.println(rowNumber);
            calibrationSum += rowNumber;
        }
        System.out.println(calibrationSum);
    }

    private static char findLastDigit(String line) {
        String reverseLine = new StringBuilder(line).reverse().toString();
        Map<String, Character> reverseWordToDigit = new HashMap<>(wordToDigit.size());
        for (Map.Entry<String, Character> entry: wordToDigit.entrySet()) {
            String reverseWord = new StringBuilder(entry.getKey()).reverse().toString();
            reverseWordToDigit.put(reverseWord, entry.getValue());
        }
        return findFirstDigit(reverseLine, reverseWordToDigit);
    }

    private static char findFirstDigit(String line, Map<String, Character> wordToDigitMap) {
        Matcher digitMatcher = Pattern.compile("\\d").matcher(line);
        int digitStart = line.length();
        if (digitMatcher.find()) {
            digitStart = digitMatcher.start();
        }
        Pattern numberWords = Pattern.compile(String.join("|", wordToDigitMap.keySet()));
        Matcher wordMatcher = numberWords.matcher(line);
        int wordStart = line.length();
        if (wordMatcher.find()) {
            wordStart = wordMatcher.start();
        }
        if (digitStart < wordStart) {
            return line.charAt(digitStart);
        } else {
            return wordToDigitMap.get(wordMatcher.group(0));
        }
    }
}
