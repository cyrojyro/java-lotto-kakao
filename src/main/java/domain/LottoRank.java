package domain;

import text.Phrase;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum LottoRank {
    FIRST(Arrays.asList(6), Arrays.asList(true, false), new BigInteger("2000000000")),
    SECOND(Arrays.asList(5), Arrays.asList(true), new BigInteger("30000000")),
    THIRD(Arrays.asList(5), Arrays.asList(false), new BigInteger("1500000")),
    FOURTH(Arrays.asList(4), Arrays.asList(true, false), new BigInteger("50000")),
    FIFTH(Arrays.asList(3), Arrays.asList(true, false), new BigInteger("5000")),
    NONE(Arrays.asList(0, 1, 2), Arrays.asList(true, false), new BigInteger("0"));

    private final List<Integer> validSameBalls;
    private final List<Boolean> validBonusBall;
    private final BigInteger reward;

    LottoRank(List<Integer> validSameBalls, List<Boolean> validBonusBall, BigInteger reward) {
        this.validSameBalls = validSameBalls;
        this.validBonusBall = validBonusBall;
        this.reward = reward;
    }

    public static LottoRank calculateLottoRank(int count, boolean validBonusBall) {
        Optional<LottoRank> result = Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank.checkRank(count, validBonusBall))
                .findFirst();

        if (result.isPresent()) {
            return result.get();
        }

        throw new IllegalStateException(Phrase.ILLEGAL_LOTTO_RANKING_ARGUMENT);
    }

    public boolean checkRank(int count, boolean validBonusBall) {
        return this.getValidSameBalls().contains(count) &&
                this.getValidBonusBall().contains(validBonusBall);
    }

    public boolean checkRank(Lotto lotto, Lotto winningLotto) {
        return checkRank(lotto.calculateSameBall(winningLotto),
                lotto.hasSameBonusBall(winningLotto));
    }

    private List<Integer> getValidSameBalls() {
        return validSameBalls;
    }

    public List<Boolean> getValidBonusBall() {
        return validBonusBall;
    }

    public BigInteger getReward() {
        return reward;
    }
}
