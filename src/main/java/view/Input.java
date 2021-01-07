package view;

import domain.Lotto;
import text.Phrase;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String REGEX = ",";

    public static int getLottoBuyAmount() {
        int amount;
        try {
            amount = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            throw new InputMismatchException(Phrase.ILLEGAL_INPUT);
        }
        validateAmount(amount);
        return getLottoSpent(amount);
    }

    private static int getLottoSpent(int amount) {
        return amount - amount % Lotto.LOTTO_PRICE;
    }

    private static void validateAmount(int amount) {
        if (amount < Lotto.LOTTO_PRICE) {
            throw new InputMismatchException(Phrase.ILLEGAL_INPUT);
        }
    }

    public static int getPreviousBonus() {
        int previousBonus;
        try {
            previousBonus = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            throw new InputMismatchException(Phrase.ILLEGAL_INPUT);
        }
        return previousBonus;
    }

    public static List<Integer> getPreviousWinBalls() {
        List<Integer> lottoBalls;
        try {
            lottoBalls = splitToInt(scanner.nextLine());
        } catch (Exception e) {
            throw new InputMismatchException(Phrase.ILLEGAL_INPUT);
        }
        return lottoBalls;
    }

    public static List<Integer> splitToInt(String userInput) {
        userInput = userInput.replace(" ", "");
        return Arrays.stream(
                userInput.split(REGEX))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
}
