package me.kire.re.challenge1;

import lombok.Builder;
import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ChallengeOne {

    public static void main(String[] args) {
        Path path = Paths.get("src/main/java/me/kire/re/challenge1/message_01.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            List<Word> words = new LinkedList<>();
            lines.forEach(line -> {
                String[] tokens = line.split(" ");
                for (String token : tokens) {
                    if (words.isEmpty()) {
                        words.add(Word.builder().name(token).count(1).build());
                    } else {
                        boolean hasName = words.stream()
                                .anyMatch(word -> equalsName(token, word));
                        if (hasName) {
                            Optional<Word> optionalWord = words.stream()
                                    .filter(w -> equalsName(token, w))
                                    .findFirst();
                            if (optionalWord.isPresent()) {
                                Word word = optionalWord.get();
                                word.setCount(word.getCount() + 1);
                            }

                        } else {
                            words.add(Word.builder().name(token).count(1).build());
                        }
                    }
                }
            });

            words.forEach(word -> System.out.printf("%s%d", word.getName(), word.getCount()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean equalsName(String token, Word word) {
        return word.getName().equalsIgnoreCase(token);
    }

}

@Data
@Builder
class Word {
    private String name;
    private Integer count;
}
