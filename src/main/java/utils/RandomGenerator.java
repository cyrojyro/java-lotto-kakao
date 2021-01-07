package utils;

import text.Phrase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomGenerator {
    public static List<Integer> generateNumbers(int min, int max, int count) {
        validateArguments(min, max, count);
        List<Integer> randomPool = initRandomPoll(min, max);
        Collections.shuffle(randomPool);
        return randomPool.subList(0, count);
    }

    private static void validateArguments(int min, int max, int count) {
        if (min > max || count > (max - min + 1) || count <= 0) {
            throw new IllegalArgumentException(
                    Phrase.ILLEGAL_RANDOM_GENERATOR_ARGUMENT);
        }
    }

    private static List<Integer> initRandomPoll(int min, int max) {
        List<Integer> randomPool = new ArrayList<>();
        for (int lottoNum = min; lottoNum <= max; ++lottoNum) {
            randomPool.add(lottoNum);
        }
        return randomPool;
    }
}
