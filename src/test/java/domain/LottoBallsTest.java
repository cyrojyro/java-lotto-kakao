package domain;

import domain.helper.LottoBallsUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoBallsTest {
    @Test
    public void hasDuplicateTest_isTrue() {
        LottoBalls lottoBalls = new LottoBalls(LottoBallsUtil.asList(1, 2, 3, 4, 5, 5));
        assertThat(lottoBalls.hasDuplicate()).isTrue();
    }

    @Test
    public void hasDuplicateTest_isFalse() {
        LottoBalls lottoBalls = new LottoBalls(LottoBallsUtil.asList(1, 2, 3, 4, 5, 6));
        assertThat(lottoBalls.hasDuplicate()).isFalse();
    }
}
