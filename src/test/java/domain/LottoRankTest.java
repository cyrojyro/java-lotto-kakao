package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoRankTest {

    @Test
    public void calculateLottoRankTest_rankNone() {
        assertThat(LottoRank.calculateLottoRank(3,false)).isEqualTo(LottoRank.FIFTH);
    }

    @Test
    public void calculateLottoRankTest_rankSecond() {
        assertThat(LottoRank.calculateLottoRank(5,true)).isEqualTo(LottoRank.SECOND);
    }

    @Test
    public void calculateLottoRankTest_rankThird() {
        assertThat(LottoRank.calculateLottoRank(5,false)).isEqualTo(LottoRank.THIRD);
    }
}
