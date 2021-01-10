package domain;

import domain.helper.LottoBallsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoStatisticsTest {

    private LottoStatistics lottoStatistics;

    @BeforeEach
    public void createLottosTest() {
        WinningLotto winningLotto = new WinningLotto(
                LottoBallsUtil.asList(1, 7, 8, 9, 10, 11), LottoBall.valueOf(12));
        Lottos lottos = new Lottos(
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
        lottoStatistics = lottos.getLottoStatistics(winningLotto);
    }

    @Test
    public void calculateTotalRewardTest() {
        assertThat(lottoStatistics.calculateTotalReward()).isEqualTo(
                new BigInteger("6033000000"));
    }

    @Test
    public void calculateEarningsRateTest() {
        assertThat(lottoStatistics.calculateEarningsRate(
                new BigInteger("6000")))
                .isEqualTo(new BigInteger("100550000"));
        assertThat(lottoStatistics.calculateEarningsRate(
                new BigInteger("6033000000")))
                .isEqualTo(new BigInteger("100"));
        assertThat(lottoStatistics.calculateEarningsRate(
                new BigInteger("12066000000")))
                .isEqualTo(new BigInteger("50"));
    }
}
