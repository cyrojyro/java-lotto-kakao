package domain;

import text.Phrase;
import utils.RandomGenerator;

import java.util.List;

public class Lotto {
    public static final int LOTTO_PRICE = 1000;
    private final int bonusBall;
    private final LottoBalls lottoBalls;

    public static final int LOTTO_COUNT = 6;
    public static final int LOTTO_MIN = 1;
    public static final int LOTTO_MAX = 45;

    public Lotto() {
        List<Integer> randomNumbers = RandomGenerator.generateNumbers(
                LOTTO_MIN, LOTTO_MAX, LOTTO_COUNT + 1);
        lottoBalls = new LottoBalls(randomNumbers.subList(0, LOTTO_COUNT));
        bonusBall = randomNumbers.get(LOTTO_COUNT);
    }

    public Lotto(List<Integer> balls, int bonusBall) {
        this(new LottoBalls(balls), bonusBall);
    }

    public Lotto(LottoBalls lottoBalls, int bonusBall) {
        this.lottoBalls = lottoBalls;
        validateLotto(lottoBalls, bonusBall);
        this.bonusBall = bonusBall;
    }

    private static void validateLotto(LottoBalls balls, int bonusBall) {
        if (balls.isIllegal(bonusBall) || bonusBall < LOTTO_MIN || bonusBall > LOTTO_MAX) {
            throw new IllegalArgumentException(Phrase.ILLEGAL_LOTTO_ARGUMENT);
        }
    }

    public LottoBalls getLottoBalls() {
        return lottoBalls;
    }

    public int getOneBall(int index) {
        return lottoBalls.get(index);
    }

    public boolean hasBall(int ball) {
        return lottoBalls.contains(ball);
    }

    public int calculateSameBall(Lotto testLotto) {
        return (int) this.lottoBalls.stream()
                .filter(testLotto::hasBall)
                .count();
    }

    public boolean hasSameBonusBall(Lotto testLotto) {
        return testLotto.bonusBall == this.bonusBall;
    }

    public int getBonusBall() {
        return bonusBall;
    }

    public LottoRank findLottoRank(Lotto testLotto) {
        return LottoRank.calculateLottoRank(calculateSameBall(testLotto),
                hasSameBonusBall(testLotto));
    }

    @Override
    public String toString() {
        return lottoBalls.toString();
    }
}
