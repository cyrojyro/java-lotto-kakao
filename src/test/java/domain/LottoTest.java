package domain;

import domain.helper.LottoBallsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTest {
    private Lotto lotto;
    private WinningLotto winningLotto;

    @BeforeEach
    public void setUpLottoTest() {
        lotto = new Lotto(LottoBallsUtil.asList(1, 7, 8, 9, 10, 12));
        winningLotto = new WinningLotto(
                LottoBallsUtil.asList(2, 1, 3, 4, 5, 6), LottoBall.valueOf(12));
    }

    @RepeatedTest(100)
    public void generateLottoTest() {
        Lotto randomLotto = new Lotto();
        LottoBalls lottoNumbers = randomLotto.getLottoBalls();
        assertThat(lottoNumbers.getLottoNumbers().size()).isEqualTo(Lotto.LOTTO_COUNT);
        for (LottoBall lottoBall : lottoNumbers.getLottoNumbers()) {
            assertThat(Collections.frequency(lottoNumbers.getLottoNumbers(),
                    lottoBall)).isEqualTo(1);
        }
    }

    @Test
    public void hasNumberBallTest_sameBall() {
        assertThat(lotto.hasBall(1)).isTrue();
    }

    @Test
    public void hasNumberBallTest_differentBall() {
        assertThat(lotto.hasBall(2)).isFalse();
    }

    @Test
    public void calculateSameBallTest_success() {
        assertThat(lotto.calculateSameBall(winningLotto)).isEqualTo(1);
    }

    @Test
    public void calculateSameBallTest_fail() {
        assertThat(lotto.calculateSameBall(winningLotto)).isNotEqualTo(4);
    }

    @Test
    public void hasBonusBallTest() {
        assertThat(lotto.hasBonusBall(winningLotto.getBonusBall())).isTrue();
    }

    @Test
    public void findLottoRankTest_rankNone() {
        assertThat(lotto.findLottoRank(winningLotto)).isEqualTo(LottoRank.NONE);
    }

    @Test
    public void findLottoRankTest_rankSecond() {
        WinningLotto rankSecondLotto = new WinningLotto(
                LottoBallsUtil.asList(1, 7, 8, 9, 10, 15), LottoBall.valueOf(12));
        assertThat(lotto.findLottoRank(rankSecondLotto)).isEqualTo(LottoRank.SECOND);
    }

    @Test
    public void findLottoRankTest_rankThird() {
        WinningLotto rankThirdLotto = new WinningLotto(
                LottoBallsUtil.asList(7, 1, 8, 9, 15, 10), LottoBall.valueOf(19));
        assertThat(lotto.findLottoRank(rankThirdLotto)).isEqualTo(LottoRank.THIRD);
    }
}
