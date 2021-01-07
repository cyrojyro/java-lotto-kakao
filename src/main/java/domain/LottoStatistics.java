package domain;

import text.Phrase;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    private static String entryToString(Map.Entry<LottoRank, BigInteger> entry) {
        LottoRank lottoRank = entry.getKey();
        BigInteger value = entry.getValue();
        if (lottoRank.getDescription().isEmpty()) {
            return "";
        }
        return Phrase.getLottoRewardStatement(lottoRank.getDescription(),
                lottoRank.getReward(), value);
    }

    public BigInteger calculateEarningsRate(BigInteger buyAmount) {
        return calculateTotalReward()
                .multiply(new BigInteger("100"))
                .divide(buyAmount);
    }

    @Override
    public String toString() {
        String base = Phrase.WINNING_STATISTICS_PHRASE;
        base += rankNumbers.entrySet().stream()
                .map(LottoStatistics::entryToString)
                .sorted()
                .collect(Collectors.joining());
        return base;
    }
}
