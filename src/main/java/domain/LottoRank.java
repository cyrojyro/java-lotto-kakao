package domain;

import text.Text;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum LottoRank {
    FIRST(Arrays.asList(6), Arrays.asList(true, false),
            new BigInteger("2000000000"), "6개 일치"),
    SECOND(Arrays.asList(5), Arrays.asList(true),
            new BigInteger("30000000"), "5개 일치, 보너스 볼 일치"),
    THIRD(Arrays.asList(5), Arrays.asList(false),
            new BigInteger("1500000"), "5개 일치"),
    FOURTH(Arrays.asList(4), Arrays.asList(true, false),
            new BigInteger("50000"), "4개 일치"),
    FIFTH(Arrays.asList(3), Arrays.asList(true, false),
            new BigInteger("5000"), "3개 일치"),
    NONE(Arrays.asList(0, 1, 2), Arrays.asList(true, false),
            new BigInteger("0"), "");

    private final List<Integer> validSameBalls;
    private final List<Boolean> validBonusBall;
    private final BigInteger reward;
    private final String description;

    LottoRank(List<Integer> validSameBalls, List<Boolean> validBonusBall,
              BigInteger reward, String description) {
        this.validSameBalls = validSameBalls;
        this.validBonusBall = validBonusBall;
        this.reward = reward;
        this.description = description;
    }

    public static LottoRank calculateLottoRank(int count, boolean validBonusBall) {
        Optional<LottoRank> result = Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank.checkRank(count, validBonusBall))
                .findFirst();

        if (result.isPresent()) {
            return result.get();
        }

        throw new IllegalStateException(Text.ILLEGAL_LOTTO_RANKING_ARGUMENT);
    }

    public boolean checkRank(int count, boolean validBonusBall) {
        return this.getValidSameBalls().contains(count) &&
                this.getValidBonusBall().contains(validBonusBall);
    }

    public boolean checkRank(Lotto lotto, Lotto winningLotto) {
        return checkRank(lotto.calculateSameBall(winningLotto),
                lotto.hasBonusBall(winningLotto));
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

    public String getDescription() {
        return description;
    }
}
