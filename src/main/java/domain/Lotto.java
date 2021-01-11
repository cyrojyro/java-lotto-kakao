package domain;

import java.util.List;

public class Lotto {
    public static final int LOTTO_PRICE = 1_000;
    protected static final int LOTTO_COUNT = 6;
    protected static final int LOTTO_MIN = 1;
    protected static final int LOTTO_MAX = 45;

    protected final LottoBalls lottoBalls;

    public Lotto() {
        List<LottoBall> randomNumbers = LottoBall.generateRandomLottoNumbers();
        lottoBalls = new LottoBalls(randomNumbers.subList(0, LOTTO_COUNT));
    }

    public Lotto(List<LottoBall> balls) {
        this(new LottoBalls(balls));
    }

    public Lotto(LottoBalls lottoBalls) {
        this.lottoBalls = lottoBalls;
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

    public int calculateSameBall(Lotto lotto) {
        return (int) lottoBalls.getLottoNumbers().stream()
                .filter(lotto::hasBall)
                .count();
    }

    public boolean hasBonusBall(LottoBall lottoBall) {
        return hasBall(lottoBall);
    }

    @Override
    public String toString() {
        return lottoBalls.toString();
    }
}
