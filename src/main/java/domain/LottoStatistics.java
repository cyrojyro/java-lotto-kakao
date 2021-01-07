package domain;

import java.util.HashMap;

public class LottoStatistics {
    private HashMap<LottoRank, Integer> rankNumbers;

    public LottoStatistics(HashMap<LottoRank, Integer> rankNumbers) {
        this.rankNumbers = rankNumbers;
    }

    public HashMap<LottoRank, Integer> getRankNumbers(){
        return rankNumbers;
    }
}
