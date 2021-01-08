package domain;

import text.Text;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoStatistics {
    private final HashMap<LottoRank, BigInteger> rankNumbers;

    public LottoStatistics(HashMap<LottoRank, BigInteger> rankNumbers) {
        this.rankNumbers = rankNumbers;
    }

    private static String entryToString(Map.Entry<LottoRank, BigInteger> entry) {
        LottoRank lottoRank = entry.getKey();
        BigInteger value = entry.getValue();
        if (lottoRank.getDescription().isEmpty()) {
            return "";
        }
        return Text.getLottoRewardStatement(lottoRank.getDescription(),
                lottoRank.getReward(), value);
    }

    public HashMap<LottoRank, BigInteger> getRankNumbers() {
        return rankNumbers;
    }

    public BigInteger calculateTotalReward() {
        return rankNumbers.keySet().stream()
                .map(reward -> reward.getReward().multiply(rankNumbers.get(reward)))
                .reduce(BigInteger.valueOf(0), BigInteger::add);
    }

    public BigInteger calculateEarningsRate(BigInteger buyAmount) {
        return calculateTotalReward()
                .multiply(new BigInteger("100"))
                .divide(buyAmount);
    }

    @Override
    public String toString() {
        String base = Text.WINNING_STATISTICS_PHRASE;
        base += rankNumbers.entrySet().stream()
                .map(LottoStatistics::entryToString)
                .sorted()
                .collect(Collectors.joining());
        return base;
    }
}
