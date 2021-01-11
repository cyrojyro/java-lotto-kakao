package domain;

import text.Text;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoStatistics {
    private final HashMap<LottoRank, Integer> rankNumbers;

    public LottoStatistics(HashMap<LottoRank, Integer> rankNumbers) {
        this.rankNumbers = rankNumbers;
    }

    private static String entryToString(Map.Entry<LottoRank, Integer> entry) {
        LottoRank lottoRank = entry.getKey();
        Integer value = entry.getValue();
        if (lottoRank.getDescription().isEmpty()) {
            return "";
        }
        return Text.getLottoRewardStatement(lottoRank.getDescription(),
                lottoRank.getReward(), value);
    }

    public HashMap<LottoRank, Integer> getRankNumbers() {
        return rankNumbers;
    }

    public BigInteger calculateTotalReward() {
        return rankNumbers.keySet().stream()
                .map(reward -> reward.getReward().
                        multiply(BigInteger.valueOf(rankNumbers.get(reward))))
                .reduce(BigInteger.valueOf(0), BigInteger::add);
    }

    public BigInteger calculateEarningsRate(BigInteger buyAmount) {
        return calculateTotalReward()
                // % 단위로 변환하기 위해 100곱
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
