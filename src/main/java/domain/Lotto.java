package domain;

import text.Text;

import java.util.List;

public class Lotto {
    public static final int LOTTO_PRICE = 1_000;
    protected static final int LOTTO_COUNT = 6;
    protected static final int LOTTO_MIN = 1;
    protected static final int LOTTO_MAX = 45;

    private final LottoBall bonusBall;
    private final LottoBalls lottoBalls;

    public Lotto() {
        List<LottoBall> randomNumbers = LottoBall.generateRandomLottoNumbers();
        lottoBalls = new LottoBalls(randomNumbers.subList(0, LOTTO_COUNT));
        bonusBall = randomNumbers.get(LOTTO_COUNT);
    }

    public Lotto(List<LottoBall> balls, LottoBall bonusBall) {
        this(new LottoBalls(balls), bonusBall);
    }

    public Lotto(LottoBalls lottoBalls, LottoBall bonusBall) {
        this.lottoBalls = lottoBalls;
        validateLotto(lottoBalls, bonusBall);
        this.bonusBall = bonusBall;
    }

    private static void validateLotto(LottoBalls balls, LottoBall bonusBall) {
        if (balls.isIllegal(bonusBall) || LottoBall.isIllegalNumber(bonusBall)) {
            throw new IllegalArgumentException(Text.ILLEGAL_LOTTO_ARGUMENT);
        }
    }

    public LottoBalls getLottoBalls() {
        return lottoBalls;
    }

    public boolean hasBall(LottoBall ball) {
        return lottoBalls.contains(ball);
    }

    public boolean hasBall(int ball) {
        return hasBall(LottoBall.valueOf(ball));
    }

    public int calculateSameBall(Lotto winningLotto) {
        return (int) lottoBalls.getLottoNumbers().stream()
                .filter(winningLotto::hasBall)
                .count();
    }

    public boolean hasBonusBall(Lotto winningLotto) {
        return hasBall(winningLotto.getBonusBall());
    }

    public LottoBall getBonusBall() {
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
