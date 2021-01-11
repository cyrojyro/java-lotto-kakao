package domain;

import domain.helper.LottoBallsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottosTest {
    private Lottos lottos;
    private WinningLotto winningLotto;

    @BeforeEach
    public void createLottos() {
        winningLotto = new WinningLotto(LottoBallsUtil.asList(1, 7, 8, 9, 10, 11), LottoBall.valueOf(12));
        lottos = new Lottos(
                Arrays.asList(
                        new Lotto(LottoBallsUtil.asList(1, 7, 8, 9, 10, 11)), // 1
                        new Lotto(LottoBallsUtil.asList(1, 7, 8, 9, 10, 11)), // 1
                        new Lotto(LottoBallsUtil.asList(1, 7, 8, 9, 10, 11)), // 1
                        new Lotto(LottoBallsUtil.asList(1, 7, 8, 9, 10, 12)), // 2
                        new Lotto(LottoBallsUtil.asList(1, 7, 8, 9, 10, 13)), // 3
                        new Lotto(LottoBallsUtil.asList(1, 7, 8, 9, 10, 13)), // 3
                        new Lotto(LottoBallsUtil.asList(21, 27, 28, 29, 30, 33)) // 6
                )
        );
    }

    @Test
    public void calculateNumberOfRank() {
        assertThat(lottos.calculateNumberOfRank(LottoRank.FIRST, winningLotto)).isEqualTo(3);
        assertThat(lottos.calculateNumberOfRank(LottoRank.SECOND, winningLotto)).isEqualTo(1);
        assertThat(lottos.calculateNumberOfRank(LottoRank.THIRD, winningLotto)).isEqualTo(2);
        assertThat(lottos.calculateNumberOfRank(LottoRank.FOURTH, winningLotto)).isEqualTo(0);
        assertThat(lottos.calculateNumberOfRank(LottoRank.FIFTH, winningLotto)).isEqualTo(0);
        assertThat(lottos.calculateNumberOfRank(LottoRank.NONE, winningLotto)).isEqualTo(1);
    }

    @Test
    public void getLottoStatisticsTest() {
        LottoStatistics lottoStatistics = lottos.getLottoStatistics(winningLotto);
        HashMap<LottoRank, Integer> rankNumbers = lottoStatistics.getRankNumbers();
        HashMap<LottoRank, Integer> trueRankNumbers = new HashMap<>();
        trueRankNumbers.put(LottoRank.FIRST, 3);
        trueRankNumbers.put(LottoRank.SECOND, 1);
        trueRankNumbers.put(LottoRank.THIRD, 2);
        trueRankNumbers.put(LottoRank.FOURTH, 0);
        trueRankNumbers.put(LottoRank.FIFTH, 0);
        trueRankNumbers.put(LottoRank.NONE, 1);
        assertThat(trueRankNumbers).isEqualTo(rankNumbers);
    }
}
