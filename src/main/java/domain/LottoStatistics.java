package domain;

import java.math.BigInteger;
import java.util.HashMap;

public class LottoStatistics {
    private final HashMap<LottoRank, BigInteger> rankNumbers;

    public LottoStatistics(HashMap<LottoRank, BigInteger> rankNumbers) {
        this.rankNumbers = rankNumbers;
    }

    public HashMap<LottoRank, BigInteger> getRankNumbers() {
        return rankNumbers;
    }

    public BigInteger calculateTotalReward() {
        BigInteger totalReward = new BigInteger("0");

        for (LottoRank lottoRank : rankNumbers.keySet()) {
            totalReward = totalReward.add(
                    lottoRank.getReward()
                            .multiply(rankNumbers.get(lottoRank)));
        }
        return totalReward;
    }

    public BigInteger calculateEarningsRate(BigInteger buyAmount) {
        BigInteger ratio = calculateTotalReward().divide(buyAmount);
        return ratio.multiply(new BigInteger("100"));
    }
}
