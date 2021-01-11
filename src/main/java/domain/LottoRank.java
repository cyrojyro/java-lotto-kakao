package domain;

import java.math.BigInteger;

public enum LottoRank {
    FIRST(6, BonusState.IRRELEVANT,
            2_000_000_000, "6개 일치"),
    SECOND(5, BonusState.NEED_TRUE,
            30_000_000, "5개 일치, 보너스 볼 일치"),
    THIRD(5, BonusState.NEED_FALSE,
            1_500_000, "5개 일치"),
    FOURTH(4, BonusState.IRRELEVANT,
            50_000, "4개 일치"),
    FIFTH(3, BonusState.IRRELEVANT,
            5_000, "3개 일치"),
    NONE(0, BonusState.IRRELEVANT,
            0, "");

    private final int sameBallNumber;
    private final BonusState bonusState;
    private final int reward;
    private final String description;

    LottoRank(int sameBallNumber, BonusState bonusState,
              int reward, String description) {
        this.sameBallNumber = sameBallNumber;
        this.bonusState = bonusState;
        this.reward = reward;
        this.description = description;
    }

    public boolean checkRank(int count, boolean hasBonusBall) {
        if (this.bonusState == BonusState.IRRELEVANT) {
            return this.getSameBallNumber() == count;
        }
        if (this.bonusState == BonusState.NEED_TRUE) {
            return this.getSameBallNumber() == count && hasBonusBall;
        }
        return this.getSameBallNumber() == count && !hasBonusBall;
    }

    private int getSameBallNumber() {
        return sameBallNumber;
    }

    enum BonusState {
        NEED_TRUE, NEED_FALSE, IRRELEVANT
    }

    public BigInteger getReward() {
        return BigInteger.valueOf(reward);
    }

    public String getDescription() {
        return description;
    }
}
