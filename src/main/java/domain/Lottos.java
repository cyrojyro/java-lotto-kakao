package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(int buyNumber) {
        lottos = new ArrayList<>();
        for (int i = 0; i < buyNumber; ++i) {
            lottos.add(new Lotto());
        }
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Lottos(Lottos lottos1, Lottos lottos2) {
        lottos = Stream.of(lottos1.getLottos(), lottos2.getLottos())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    public int calculateNumberOfRank(LottoRank lottoRank, WinningLotto winningLotto) {
        return (int) lottos.stream()
                .filter(lotto -> lottoRank.checkRank(
                        lotto.calculateSameBall(winningLotto),
                        lotto.hasBonusBall(winningLotto.getBonusBall())))
                .count();
    }

    public LottoStatistics getLottoStatistics(WinningLotto winningLotto) {
        HashMap<LottoRank, Integer> rankNumbers = new HashMap<>();

        for (LottoRank lottoRank : LottoRank.values()) {
            rankNumbers.put(lottoRank, calculateNumberOfRank(lottoRank, winningLotto));
        }

        return new LottoStatistics(rankNumbers);
    }
}
