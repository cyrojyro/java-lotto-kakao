package domain;

import text.Text;
import utils.RandomGenerator;

import java.util.List;

public class Lotto {
    public static final int LOTTO_PRICE = 1_000;

    protected static final int LOTTO_COUNT = 6;
    protected static final int LOTTO_MIN = 1;
    protected static final int LOTTO_MAX = 45;

    private final int bonusBall;
    private final LottoBalls lottoBalls;

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
            throw new IllegalArgumentException(Text.ILLEGAL_LOTTO_ARGUMENT);
        }
    }

    public LottoBalls getLottoBalls() {
        return lottoBalls;
    }

    public boolean hasBall(int ball) {
        return lottoBalls.contains(ball);
    }

    public int calculateSameBall(Lotto winningLotto) {
        return (int) this.lottoBalls.stream()
                .filter(winningLotto::hasBall)
                .count();
    }

    public boolean hasBonusBall(Lotto winningLotto) {
        return this.hasBall(winningLotto.getBonusBall());
    }

    public int getBonusBall() {
        return bonusBall;
    }

    public LottoRank findLottoRank(Lotto winningLotto) {
        return LottoRank.calculateLottoRank(calculateSameBall(winningLotto),
                hasBonusBall(winningLotto));
    }

    @Override
    public String toString() {
        return lottoBalls.toString();
    }
}
