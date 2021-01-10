package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoBallTest {
    @Test
    public void generateRandomLottoNumbersTest() {
        List<LottoBall> lottoBalls = LottoBall.generateRandomLottoNumbers();
        int distinctCount = (int) lottoBalls.stream()
                .distinct()
                .count();
        assertThat(distinctCount).isEqualTo(lottoBalls.size());
    }

    @Test
    public void isIllegalNumberTest_isIllegal() {
        assertThatIllegalArgumentException().isThrownBy(() -> LottoBall.valueOf(0));
        assertThatIllegalArgumentException().isThrownBy(() -> LottoBall.valueOf(46));
    }

    @Test
    public void isIllegalNumberTest_isLegal() {
        assertThat(LottoBall.valueOf(1).getNumber()).isEqualTo(1);
        assertThat(LottoBall.valueOf(45).getNumber()).isEqualTo(45);
    }
}
