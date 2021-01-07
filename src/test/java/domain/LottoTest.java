package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTest {

    private Lotto lotto;
    private Lotto testLotto;

    @BeforeEach
    public void setUpLotto() {
        lotto = new Lotto(Arrays.asList(1,7,8,9,10,11));
        testLotto = new Lotto(Arrays.asList(1,2,3,4,5,6));
    }

    @RepeatedTest(100)
    public void generateLotto() {
        Lotto randomLotto = new Lotto();
        List<Integer> lottoNumbers = randomLotto.getLottoNumbers();
        assertThat(lottoNumbers.size()).isEqualTo(Lotto.LOTTO_COUNT);
        for (int lottoNumber : lottoNumbers) {
            assertThat(Collections.frequency(lottoNumbers, lottoNumber)).isEqualTo(1);
            assertThat(lottoNumber).isBetween(Lotto.LOTTO_MIN, Lotto.LOTTO_MAX);
        }
    }

    @Test
    public void hasNumberTest_sameBall() {
        int testNumber = testLotto.getOneNumber(0);
        assertThat(lotto.hasNumber(testNumber)).isTrue();
    }

    @Test
    public void hasNumberTest_differentBall() {
        int testNumber = testLotto.getOneNumber(1);
        assertThat(lotto.hasNumber(testNumber)).isFalse();
    }

    @Test
    public void calculateSameNumberTest_success(){
        assertThat(lotto.calculateSameNumber(testLotto)).isEqualTo(1);
    }

    @Test
    public void calculateSameNumberTest_fail(){
        assertThat(lotto.calculateSameNumber(testLotto)).isNotEqualTo(4);
    }


}
