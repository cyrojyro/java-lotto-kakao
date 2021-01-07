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

    @Test
    public void checkRankTest(){
        assertThat(LottoRank.SECOND.checkRank(5, true)).isTrue();
        assertThat(LottoRank.SECOND.checkRank(5, false)).isFalse();
        assertThat(LottoRank.THIRD.checkRank(5, false)).isTrue();
        assertThat(LottoRank.THIRD.checkRank(5, true)).isFalse();
    }
}
