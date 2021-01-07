package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottosTest {
    private Lottos lottos;
    private Lotto winningLotto;

    @BeforeEach
    public void createLottos(){
        winningLotto = new Lotto(Arrays.asList(1,7,8,9,10,11), 12);
        lottos = new Lottos(
            Arrays.asList(
                    new Lotto(Arrays.asList(1,7,8,9,10,11), 12),
                    new Lotto(Arrays.asList(1,7,8,9,10,11), 12),
                    new Lotto(Arrays.asList(1,7,8,9,10,11), 12),
                    new Lotto(Arrays.asList(1,7,8,9,10,13), 12),
                    new Lotto(Arrays.asList(1,7,8,9,10,13), 12),
                    new Lotto(Arrays.asList(1,7,8,9,10,13), 14)
            )
        );
    }

    @Test
    public void calculateNumberOfRank(){
        assertThat(lottos.calculateNumberOfRank(LottoRank.FIRST, winningLotto)).isEqualTo(3);
        assertThat(lottos.calculateNumberOfRank(LottoRank.SECOND, winningLotto)).isEqualTo(2);
        assertThat(lottos.calculateNumberOfRank(LottoRank.THIRD, winningLotto)).isEqualTo(1);
        assertThat(lottos.calculateNumberOfRank(LottoRank.FOURTH, winningLotto)).isEqualTo(0);
        assertThat(lottos.calculateNumberOfRank(LottoRank.FIFTH, winningLotto)).isEqualTo(0);
        assertThat(lottos.calculateNumberOfRank(LottoRank.NONE, winningLotto)).isEqualTo(0);
    }

    @Test
    public void getLottoStatisticsTest(){
        LottoStatistics lottoStatistics = lottos.getLottoStatistics(winningLotto);
        HashMap<LottoRank, Integer> rankNumbers = lottoStatistics.getRankNumbers();
        HashMap<LottoRank, Integer> trueRankNumbers = new HashMap<>();
        trueRankNumbers.put(LottoRank.FIRST, 3);
        trueRankNumbers.put(LottoRank.SECOND, 2);
        trueRankNumbers.put(LottoRank.THIRD, 1);
        trueRankNumbers.put(LottoRank.FOURTH, 0);
        trueRankNumbers.put(LottoRank.FIFTH, 0);
        trueRankNumbers.put(LottoRank.NONE, 0);
        assertThat(trueRankNumbers).isEqualTo(rankNumbers);
    }
}
