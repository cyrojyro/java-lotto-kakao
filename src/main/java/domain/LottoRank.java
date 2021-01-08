package domain;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Optional;

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
    private final BonusState bonusStateBallState;
    private final int reward;
    private final String description;

    LottoRank(int sameBallNumber, BonusState bonusStateBallState,
              int reward, String description) {
        this.sameBallNumber = sameBallNumber;
        this.bonusStateBallState = bonusStateBallState;
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
        if (this.getBonusBallState() == BonusState.IRRELEVANT) {
            return this.getSameBallNumber() == count;
        }
        if (this.getBonusBallState() == BonusState.NEED_TRUE) {
            return this.getSameBallNumber() == count && validBonusBall;
        }
        return this.getSameBallNumber() == count && !validBonusBall;
    }

    private int getSameBallNumber() {
        return sameBallNumber;
    }

    public BonusState getBonusBallState() {
        return bonusStateBallState;
    }

    enum BonusState {
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
