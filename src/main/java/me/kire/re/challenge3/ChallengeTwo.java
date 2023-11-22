package me.kire.re.challenge3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ChallengeTwo {

    public static void main(String[] args) {
        Path path = Paths.get("src/main/java/me/kire/re/challenge3/message_03.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            KeyValidator keyValidator = new KeyValidator();
            lines.forEach(keyValidator::process);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

class KeyValidator {

    protected String key;
    protected char character;
    protected long numberLeft;
    protected long numberRight;

    private int result;

    public void process(String line) {
        String[] tokens = line.split(":");
        String[] tokenLeftSplit = tokens[0].split(" ");
        String[] tokenNumbers = tokenLeftSplit[0].split("-");

        this.key = tokens[1].trim();
        this.character = tokenLeftSplit[1].charAt(0);
        this.numberLeft = Long.parseLong(tokenNumbers[0]);
        this.numberRight = Long.parseLong(tokenNumbers[1]);

        this.validatePolicyKey();
        this.findInvalidKeyFourtyTwo();
        this.findPassword();
    }

    public void validatePolicyKey() {
        long count = key
                .chars()
                .filter(k -> k == character)
                .count();
        boolean isInRange = count >= this.numberLeft && count <= this.numberRight;
        if (!isInRange) {
            this.result++;
        }
    }

    public void findInvalidKeyFourtyTwo() {
        if (this.result == 42) {
            System.out.println("Invalid Key 42: " + key);
        }
    }

    public void findPassword() {
        if (this.result == 13) {
            System.out.println("Password: " + key);
        }
    }

}
