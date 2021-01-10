package view;

import domain.Lotto;
import domain.LottoBall;
import domain.LottoBalls;
import text.Text;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Input {
    public static final String REGEX = ",";
    private static final Scanner scanner = new Scanner(System.in);

    public static int getLottoBuyAmount() {
        int amount;
        try {
            amount = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            throw new InputMismatchException(Text.ILLEGAL_INPUT);
        }
        validateAmount(amount);
        return getLottoSpent(amount);
    }

    private static int getLottoSpent(int amount) {
        return amount - amount % Lotto.LOTTO_PRICE;
    }

    private static void validateAmount(int amount) {
        if (amount < Lotto.LOTTO_PRICE) {
            throw new InputMismatchException(Text.ILLEGAL_INPUT);
        }
    }

    public static LottoBall getPreviousBonus() {
        LottoBall previousBonus;
        try {
            previousBonus = LottoBall.valueOf(scanner.nextInt());
            scanner.nextLine();
        } catch (Exception e) {
            throw new InputMismatchException(Text.ILLEGAL_INPUT);
        }
        return previousBonus;
    }

    public static LottoBalls getLottoBalls() {
        List<LottoBall> lottoBalls;
        try {
            lottoBalls = splitToLottoBalls(scanner.nextLine());
        } catch (Exception e) {
            throw new InputMismatchException(Text.ILLEGAL_INPUT);
        }
        return new LottoBalls(lottoBalls);
    }

    public static List<LottoBall> splitToLottoBalls(String userInput) {
        userInput = userInput.replace(" ", "");
        return Arrays.stream(
                userInput.split(REGEX))
                .map(Integer::valueOf)
                .map(LottoBall::valueOf)
                .collect(Collectors.toList());
    }
}
