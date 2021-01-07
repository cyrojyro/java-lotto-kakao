package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoStatisticsTest {

    private LottoStatistics lottoStatistics;

    @BeforeEach
    public void createLottos() {
        Lotto winningLotto = new Lotto(Arrays.asList(1, 7, 8, 9, 10, 11), 12);
        Lottos lottos = new Lottos(
                Arrays.asList(
                        new Lotto(Arrays.asList(1, 7, 8, 9, 10, 11), 12),
                        new Lotto(Arrays.asList(1, 7, 8, 9, 10, 11), 12),
                        new Lotto(Arrays.asList(1, 7, 8, 9, 10, 11), 12),
                        new Lotto(Arrays.asList(1, 7, 8, 9, 10, 13), 12),
                        new Lotto(Arrays.asList(1, 7, 8, 9, 10, 13), 12),
                        new Lotto(Arrays.asList(1, 7, 8, 9, 10, 13), 14)
                )
        );
        lottoStatistics = lottos.getLottoStatistics(winningLotto);
    }

    @Test
    public void calculateTotalRewardTest() {
        assertThat(lottoStatistics.calculateTotalReward()).isEqualTo(new BigInteger("6061500000"));
    }

    @Test
    public void calculateEarningsRateTest() {
        assertThat(lottoStatistics.calculateEarningsRate(new BigInteger("6000"))).isEqualTo(new BigInteger("101025000"));
        assertThat(lottoStatistics.calculateEarningsRate(new BigInteger("6061500000"))).isEqualTo(new BigInteger("100"));
    }
}
