package domain;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public BigInteger calculateNumberOfRank(LottoRank lottoRank, Lotto winningLotto) {
        return new BigInteger(
                String.valueOf(
                        lottos.stream()
                                .filter(lotto -> lottoRank.checkRank(lotto, winningLotto))
                                .count()));
    }

    public LottoStatistics getLottoStatistics(Lotto winningLotto) {
        HashMap<LottoRank, BigInteger> rankNumbers = new HashMap<>();

        for(LottoRank lottoRank: LottoRank.values()){
            rankNumbers.put(lottoRank, calculateNumberOfRank(lottoRank, winningLotto));
        }

        return new LottoStatistics(rankNumbers);
    }
}
