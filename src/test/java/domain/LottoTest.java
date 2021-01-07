package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTest {
    private Lotto lotto;
    private Lotto testLotto;

    @BeforeEach
    public void setUpLottoTest() {
        lotto = new Lotto(Arrays.asList(1, 7, 8, 9, 10, 11), 12);
        testLotto = new Lotto(Arrays.asList(2, 1, 3, 4, 5, 6), 12);
    }

    @RepeatedTest(100)
    public void generateLottoTest() {
        Lotto randomLotto = new Lotto();
        LottoBalls lottoNumbers = randomLotto.getLottoBalls();
        int bonusNumber = randomLotto.getBonusBall();
        assertThat(lottoNumbers.size()).isEqualTo(Lotto.LOTTO_COUNT);
        for (int lottoNumber : lottoNumbers.getLottoNumbers()) {
            assertThat(Collections.frequency(lottoNumbers.getLottoNumbers(),
                    lottoNumber)).isEqualTo(1);
            assertThat(lottoNumber).isBetween(Lotto.LOTTO_MIN, Lotto.LOTTO_MAX);
        }
        assertThat(Collections.frequency(lottoNumbers.getLottoNumbers(),
                bonusNumber)).isEqualTo(0);
    }

    @Test
    public void createLottoTest_duplicate() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Lotto(Arrays.asList(1, 1, 2, 3, 4, 5), 6));
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6), 6));
    }

    @Test
    public void createLottoTest_wrongSize() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Lotto(Arrays.asList(1, 2, 3, 4, 5), 6));
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 8));
    }

    @Test
    public void createLottoTest_wrongNumber() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 46), 8));
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 0), 8));
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6), 46));
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6), 0));
    }

    @Test
    public void hasNumberBallTest_sameBall() {
        int testNumber = testLotto.getOneBall(1);
        assertThat(lotto.hasBall(testNumber)).isTrue();
    }

    @Test
    public void hasNumberBallTest_differentBall() {
        int testNumber = testLotto.getOneBall(0);
        assertThat(lotto.hasBall(testNumber)).isFalse();
    }

    @Test
    public void calculateSameBallTest_success() {
        assertThat(lotto.calculateSameBall(testLotto)).isEqualTo(1);
    }

    @Test
    public void calculateSameBallTest_fail() {
        assertThat(lotto.calculateSameBall(testLotto)).isNotEqualTo(4);
    }

    @Test
    public void hasSameBonusBallTest() {
        assertThat(lotto.hasSameBonusBall(testLotto)).isTrue();
    }

    @Test
    public void findLottoRankTest_rankNone() {
        assertThat(lotto.findLottoRank(testLotto)).isEqualTo(LottoRank.NONE);
    }

    @Test
    public void findLottoRankTest_rankSecond() {
        Lotto rankSecondLotto = new Lotto(Arrays.asList(1, 7, 8, 9, 10, 15), 12);
        assertThat(lotto.findLottoRank(rankSecondLotto)).isEqualTo(LottoRank.SECOND);
    }

    @Test
    public void findLottoRankTest_rankThird() {
        Lotto rankThirdLotto = new Lotto(Arrays.asList(7, 1, 8, 9, 15, 10), 19);
        assertThat(lotto.findLottoRank(rankThirdLotto)).isEqualTo(LottoRank.THIRD);
    }
}
