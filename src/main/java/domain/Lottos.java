package domain;

import java.util.HashMap;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public int calculateNumberOfRank(LottoRank lottoRank, Lotto winningLotto) {
        return (int)lottos.stream()
                .filter(lotto -> lottoRank.checkRank(lotto, winningLotto))
                .count();
    }

    public LottoStatistics getLottoStatistics(Lotto winningLotto){
        HashMap<LottoRank, Integer> rankNumbers = new HashMap<>();

        for(LottoRank lottoRank: LottoRank.values()){
            rankNumbers.put(lottoRank, calculateNumberOfRank(lottoRank, winningLotto));
        }

        return new LottoStatistics(rankNumbers);
    }
}
