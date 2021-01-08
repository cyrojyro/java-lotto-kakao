package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoBallTest {
    @Test
    public void generateRandomLottoNumbersTest() {
        List<LottoBall> lottoBalls = LottoBall.generateRandomLottoNumbers();
        int distinctCount = (int) lottoBalls.stream()
                .distinct()
                .count();
        assertThat(distinctCount).isEqualTo(lottoBalls.size());
        lottoBalls.forEach(lottoBall ->
            assertThat(LottoBall.isIllegalNumber(lottoBall)).isFalse()
        );
    }

    @Test
    public void isIllegalNumberTest_isIllegal() {
        assertThat(LottoBall.isIllegalNumber(0)).isTrue();
        assertThat(LottoBall.isIllegalNumber(46)).isTrue();
    }

    @Test
    public void isIllegalNumberTest_isLegal() {
        assertThat(LottoBall.isIllegalNumber(1)).isFalse();
        assertThat(LottoBall.isIllegalNumber(45)).isFalse();
    }
}
