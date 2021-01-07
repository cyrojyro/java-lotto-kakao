package domain;

import utils.RandomGenerator;

import java.util.List;

public class Lotto {
    private final List<Integer> lottoBalls;
    private final int bonusBall;

    public static final int LOTTO_COUNT = 6;
    public static final int LOTTO_MIN = 1;
    public static final int LOTTO_MAX = 45;

    Lotto() {
        List<Integer> randomNumbers = RandomGenerator.generateNumbers(
                LOTTO_MIN, LOTTO_MAX, LOTTO_COUNT + 1);
        lottoBalls = randomNumbers.subList(0, LOTTO_COUNT);
        bonusBall = randomNumbers.get(LOTTO_COUNT);
    }

    Lotto(List<Integer> balls, int bonusBall){
        lottoBalls = balls;
        this.bonusBall = bonusBall;
    }

    public List<Integer> getLottoBalls() {
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
}
