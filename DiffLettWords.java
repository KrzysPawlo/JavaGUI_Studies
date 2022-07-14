package ZadPD3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;




public class DiffLettWords {

    public static boolean FunUniq(String str) {
        final int size = str.length();

        for (int i = 0; i < size - 1; i++) {
            char Char = str.charAt(i);

            for (int j = i + 1; j < size; j++) {
                if (Char == str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {


        String book = "Tekst.txt";
        int minLen = 5;
        try (Stream<String> lines = Files.lines(Paths.get(book))) {
            Map<Integer,List<String>> map =
                    lines.filter(Predicate.not(String::isEmpty)).map(s -> s.split("\\P{L}+")).flatMap(Arrays::stream)
                            .filter(DiffLettWords::FunUniq).filter(s -> s.length() > minLen).distinct()
                                .map(String::toLowerCase).sorted().collect(Collectors.groupingBy(String::length));



            List<Integer> lastTwo =
                    map.keySet().stream().sorted()
                            .collect(Collectors.toList());
            System.out.println("Two lists of the longest " +
                    "words with all letters different:");
            int len = lastTwo.get(lastTwo.size()-2);
            System.out.println("length " + len + ": " +
                    map.get(len));
            len = lastTwo.get(lastTwo.size()-1);
            System.out.println("length " + len + ": " +
                    map.get(len));
        } catch(IOException e) {
            System.out.println("Something wrong...");
            e.printStackTrace();
            System.exit(1);
        }
    }
}