package domain;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Optional;

public enum LottoRank {
    FIRST(6, Bonus.IRRELEVANT,
            2_000_000_000, "6개 일치"),
    SECOND(5, Bonus.NEED_TRUE,
            30_000_000, "5개 일치, 보너스 볼 일치"),
    THIRD(5, Bonus.NEED_FALSE,
            1_500_000, "5개 일치"),
    FOURTH(4, Bonus.IRRELEVANT,
            50_000, "4개 일치"),
    FIFTH(3, Bonus.IRRELEVANT,
            5_000, "3개 일치"),
    NONE(0, Bonus.IRRELEVANT,
            0, "");

    private final int sameBallNumber;
    private final Bonus bonusBallState;
    private final int reward;
    private final String description;

    LottoRank(int sameBallNumber, Bonus bonusBallState,
              int reward, String description) {
        this.sameBallNumber = sameBallNumber;
        this.bonusBallState = bonusBallState;
        this.reward = reward;
        this.description = description;
    }

    public static LottoRank calculateLottoRank(int count, boolean validBonusBall) {
        Optional<LottoRank> result = Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank.checkRank(count, validBonusBall))
                .findFirst();

        return result.orElse(LottoRank.NONE);
    }

    public boolean checkRank(int count, boolean validBonusBall) {
        if (this.getBonusBallState() == Bonus.IRRELEVANT) {
            return this.getSameBallNumber() == count;
        }
        if (this.getBonusBallState() == Bonus.NEED_TRUE) {
            return this.getSameBallNumber() == count && validBonusBall;
        }
        return this.getSameBallNumber() == count && !validBonusBall;
    }

    private int getSameBallNumber() {
        return sameBallNumber;
    }

    public Bonus getBonusBallState() {
        return bonusBallState;
    }

    enum Bonus {
        NEED_TRUE, NEED_FALSE, IRRELEVANT
    }

    public boolean checkRank(Lotto lotto, Lotto winningLotto) {
        return checkRank(lotto.calculateSameBall(winningLotto),
                lotto.hasBonusBall(winningLotto));
    }

    public BigInteger getReward() {
        return BigInteger.valueOf(reward);
    }

    public String getDescription() {
        return description;
    }
}
