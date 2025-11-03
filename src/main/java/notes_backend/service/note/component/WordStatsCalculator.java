package notes_backend.service.note.component;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class WordStatsCalculator {
    public Map<String, Long> calculate(String text) {
        return Arrays.stream(text.toLowerCase()
                        .replaceAll("[^a-zA-Zа-яА-Я0-9\\s]", "")
                        .split("\\s+"))
                .filter(word -> !word.isBlank())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}
