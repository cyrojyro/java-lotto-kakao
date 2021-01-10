package domain;

import domain.helper.LottoBallsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTest {
    private Lotto lotto;
    private Lotto winningLotto;

    @BeforeEach
    public void setUpLottoTest() {
        lotto = new Lotto(LottoBallsUtil.asList(1, 7, 8, 9, 10, 12), LottoBall.valueOf(13));
        winningLotto = new Lotto(LottoBallsUtil.asList(2, 1, 3, 4, 5, 6), LottoBall.valueOf(12));
    }

    @RepeatedTest(100)
    public void generateLottoTest() {
        Lotto randomLotto = new Lotto();
        LottoBalls lottoNumbers = randomLotto.getLottoBalls();
        LottoBall bonusNumber = randomLotto.getBonusBall();
        assertThat(lottoNumbers.getLottoNumbers().size()).isEqualTo(Lotto.LOTTO_COUNT);
        for (LottoBall lottoBall : lottoNumbers.getLottoNumbers()) {
            assertThat(Collections.frequency(lottoNumbers.getLottoNumbers(),
                    lottoBall)).isEqualTo(1);
        }
        assertThat(Collections.frequency(lottoNumbers.getLottoNumbers(),
                bonusNumber)).isEqualTo(0);
    }

    @Test
    public void createLottoTest_duplicate() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Lotto(LottoBallsUtil.asList(1, 1, 2, 3, 4, 5), LottoBall.valueOf(6)));
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Lotto(LottoBallsUtil.asList(1, 2, 3, 4, 5, 6), LottoBall.valueOf(6)));
    }

    @Test
    public void createLottoTest_wrongSize() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Lotto(LottoBallsUtil.asList(1, 2, 3, 4, 5), LottoBall.valueOf(6)));
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Lotto(LottoBallsUtil.asList(1, 2, 3, 4, 5, 6, 7), LottoBall.valueOf(8)));
    }

    @Test
    public void createLottoTest_wrongNumber() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Lotto(LottoBallsUtil.asList(1, 2, 3, 4, 5, 46), LottoBall.valueOf(8)));
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Lotto(LottoBallsUtil.asList(1, 2, 3, 4, 5, 0), LottoBall.valueOf(8)));
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Lotto(LottoBallsUtil.asList(1, 2, 3, 4, 5, 6), LottoBall.valueOf(46)));
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Lotto(LottoBallsUtil.asList(1, 2, 3, 4, 5, 6), LottoBall.valueOf(0)));
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
        assertThat(lotto.hasBonusBall(winningLotto)).isTrue();
    }

    @Test
    public void findLottoRankTest_rankNone() {
        assertThat(lotto.findLottoRank(winningLotto)).isEqualTo(LottoRank.NONE);
    }

    @Test
    public void findLottoRankTest_rankSecond() {
        Lotto rankSecondLotto = new Lotto(LottoBallsUtil.asList(1, 7, 8, 9, 10, 15), LottoBall.valueOf(12));
        assertThat(lotto.findLottoRank(rankSecondLotto)).isEqualTo(LottoRank.SECOND);
    }

    @Test
    public void findLottoRankTest_rankThird() {
        Lotto rankThirdLotto = new Lotto(LottoBallsUtil.asList(7, 1, 8, 9, 15, 10), LottoBall.valueOf(19));
        assertThat(lotto.findLottoRank(rankThirdLotto)).isEqualTo(LottoRank.THIRD);
    }
}
