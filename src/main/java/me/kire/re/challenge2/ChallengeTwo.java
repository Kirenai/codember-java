package me.kire.re.challenge2;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ChallengeTwo {

    public static void main(String[] args) {
        Path path = Paths.get("src/main/java/me/kire/re/challenge2/message_02.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            lines.forEach(line -> {
                Storage storage = new Storage();
                String[] tokens = line.split("");
                for (String token : tokens) {
                    switch (Symbol.getBySymbol(token)) {
                        case INCREASE -> storage.increase();
                        case DECREASE -> storage.decrease();
                        case MULTIPLIES_ITSELF -> storage.multipliesItself();
                        case PRINT -> storage.print();
                    }
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

@AllArgsConstructor
enum Symbol {
    INCREASE("#"),
    DECREASE("@"),
    MULTIPLIES_ITSELF("*"),
    PRINT("&");

    private final String symbol;

    public static Symbol getBySymbol(String symbol) {
        for (Symbol s : values()) {
            if (s.symbol.equals(symbol)) {
                return s;
            }
        }
        throw new IllegalArgumentException("No enum constant with symbol " + symbol);
    }
}

@Data
class Storage {
    private int count;

    public void increase() {
        this.count += 1;
    }

    public void decrease() {
        this.count -= 1;
    }

    public void multipliesItself() {
        this.count *= this.count;
    }

    public void print() {
        System.out.print(count);
    }
}
