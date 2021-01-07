package domain;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum LottoRank {
    // 선언 순서 중요
    FIRST(Arrays.asList(6), Arrays.asList(true, false), new BigInteger("2000000000")),
    SECOND(Arrays.asList(5), Arrays.asList(true), new BigInteger("30000000")),
    THIRD(Arrays.asList(5), Arrays.asList(false), new BigInteger("1500000")),
    FOURTH(Arrays.asList(4), Arrays.asList(true, false), new BigInteger("50000")),
    FIFTH(Arrays.asList(3), Arrays.asList(true, false), new BigInteger("5000")),
    NONE(Arrays.asList(0, 1, 2), Arrays.asList(true, false), new BigInteger("0"));

    private final List<Integer> rightCounts;
    private final List<Boolean> rightBonusBalls;

    LottoRank(List<Integer> rightCounts, List<Boolean> rightBonusBalls, BigInteger reward) {
        this.rightCounts = rightCounts;
        this.rightBonusBalls = rightBonusBalls;
        this.reward = reward;
    }

    public boolean checkRank(int count, boolean rightBonusBall){
        return this.getRightCounts().contains(count) &&
                this.getRightBonusBalls().contains(rightBonusBall);
    }

    public boolean checkRank(Lotto lotto, Lotto winningLotto){
        return checkRank(lotto.calculateSameBall(winningLotto),
                lotto.hasSameBonusBall(winningLotto));
    }

    public static LottoRank calculateLottoRank(int count, boolean rightBonusBall) {
        Optional<LottoRank> result = Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank.checkRank(count, rightBonusBall))
                .findFirst();

        if(result.isPresent()) {
            return result.get();
        }

        throw new IllegalStateException();
    }

    private List<Integer> getRightCounts() {
        return rightCounts;
    }

    public List<Boolean> getRightBonusBalls() {
        return rightBonusBalls;
    }
}
