package domain;

import org.junit.jupiter.api.RepeatedTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTest {
    @RepeatedTest(100)
    public void generateLotto(){
        Lotto lotto = new Lotto();
        lotto.generateLotto();
        List<Integer> lottoNumbers = lotto.getLottoNumbers();
        assertThat(lottoNumbers.size()).isEqualTo(Lotto.LOTTO_COUNT);
        for(int lottoNumber: lottoNumbers){
            assertThat(Collections.frequency(lottoNumbers, lottoNumber)).isEqualTo(1);
            assertThat(lottoNumber).isBetween(Lotto.LOTTO_MIN, Lotto.LOTTO_MAX);
        }
    }
}
