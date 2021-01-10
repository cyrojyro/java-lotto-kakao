package domain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lottos {
    private List<Lotto> lottos = new ArrayList<>();

    public Lottos(int buyNumber) {
        for (int i = 0; i < buyNumber; ++i) {
            lottos.add(new Lotto());
        }
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    public BigInteger calculateNumberOfRank(LottoRank lottoRank, WinningLotto winningLotto) {
        return new BigInteger(
                String.valueOf(
                        lottos.stream()
                                .filter(lotto -> lottoRank.checkRank(lotto, winningLotto))
                                .count()));
    }

    public LottoStatistics getLottoStatistics(WinningLotto winningLotto) {
        HashMap<LottoRank, BigInteger> rankNumbers = new HashMap<>();

        for (LottoRank lottoRank : LottoRank.values()) {
            rankNumbers.put(lottoRank, calculateNumberOfRank(lottoRank, winningLotto));
        }

        return new LottoStatistics(rankNumbers);
    }
}
