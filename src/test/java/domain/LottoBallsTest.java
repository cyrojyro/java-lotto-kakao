package domain;

import domain.helper.LottoBallsUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoBallsTest {
    @Test
    public void hasDuplicateTest_isTrue() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new LottoBalls(LottoBallsUtil.asList(1, 2, 3, 4, 5, 5)));
    }

    @Test
    public void hasDuplicateTest_isFalse() {
        assertThat(new LottoBalls(LottoBallsUtil.asList(1, 2, 3, 4, 5, 6))).isNotNull();
    }
}
