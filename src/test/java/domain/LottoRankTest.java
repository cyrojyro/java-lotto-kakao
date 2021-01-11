package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoRankTest {
    @Test
    public void checkRankTest() {
        assertThat(LottoRank.SECOND.checkRank(5, true)).isTrue();
        assertThat(LottoRank.SECOND.checkRank(5, false)).isFalse();
        assertThat(LottoRank.THIRD.checkRank(5, false)).isTrue();
        assertThat(LottoRank.THIRD.checkRank(5, true)).isFalse();
    }
}
