package domain;

import domain.helper.LottoBallsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottosTest {
    private Lottos lottos;
    private Lotto winningLotto;

    @BeforeEach
    public void createLottos() {
        winningLotto = new Lotto(LottoBallsUtil.asList(1, 7, 8, 9, 10, 11), LottoBall.valueOf(12));
        lottos = new Lottos(
                Arrays.asList(
                        new Lotto(LottoBallsUtil.asList(1, 7, 8, 9, 10, 11), LottoBall.valueOf(45)), // 1
                        new Lotto(LottoBallsUtil.asList(1, 7, 8, 9, 10, 11), LottoBall.valueOf(45)), // 1
                        new Lotto(LottoBallsUtil.asList(1, 7, 8, 9, 10, 11), LottoBall.valueOf(45)), // 1
                        new Lotto(LottoBallsUtil.asList(1, 7, 8, 9, 10, 12), LottoBall.valueOf(45)), // 2
                        new Lotto(LottoBallsUtil.asList(1, 7, 8, 9, 10, 13), LottoBall.valueOf(45)), // 3
                        new Lotto(LottoBallsUtil.asList(1, 7, 8, 9, 10, 13), LottoBall.valueOf(45)), // 3
                        new Lotto(LottoBallsUtil.asList(21, 27, 28, 29, 30, 33), LottoBall.valueOf(45)) // 6
                )
        );
    }

    @Test
    public void calculateNumberOfRank() {
        assertThat(lottos.calculateNumberOfRank(LottoRank.FIRST, winningLotto))
                .isEqualTo(new BigInteger("3"));
        assertThat(lottos.calculateNumberOfRank(LottoRank.SECOND, winningLotto))
                .isEqualTo(new BigInteger("1"));
        assertThat(lottos.calculateNumberOfRank(LottoRank.THIRD, winningLotto))
                .isEqualTo(new BigInteger("2"));
        assertThat(lottos.calculateNumberOfRank(LottoRank.FOURTH, winningLotto))
                .isEqualTo(new BigInteger("0"));
        assertThat(lottos.calculateNumberOfRank(LottoRank.FIFTH, winningLotto))
                .isEqualTo(new BigInteger("0"));
        assertThat(lottos.calculateNumberOfRank(LottoRank.NONE, winningLotto))
                .isEqualTo(new BigInteger("1"));
    }

    @Test
    public void getLottoStatisticsTest() {
        LottoStatistics lottoStatistics = lottos.getLottoStatistics(winningLotto);
        HashMap<LottoRank, BigInteger> rankNumbers = lottoStatistics.getRankNumbers();
        HashMap<LottoRank, BigInteger> trueRankNumbers = new HashMap<>();
        trueRankNumbers.put(LottoRank.FIRST, new BigInteger("3"));
        trueRankNumbers.put(LottoRank.SECOND, new BigInteger("1"));
        trueRankNumbers.put(LottoRank.THIRD, new BigInteger("2"));
        trueRankNumbers.put(LottoRank.FOURTH, new BigInteger("0"));
        trueRankNumbers.put(LottoRank.FIFTH, new BigInteger("0"));
        trueRankNumbers.put(LottoRank.NONE, new BigInteger("1"));
        assertThat(trueRankNumbers).isEqualTo(rankNumbers);
    }
}
