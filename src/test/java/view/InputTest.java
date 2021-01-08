package view;

import domain.LottoBalls;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class InputTest {
    @Test
    public void splitToIntTest() {
        assertThat(Input.splitToLottoBalls("1,2,3"))
                .isEqualTo(LottoBalls.asList(1, 2, 3));
        assertThat(Input.splitToLottoBalls(" 1, 2  ,3,4,5  ,6"))
                .isEqualTo(LottoBalls.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    public void splitToIntTest_invalid() {
        assertThatThrownBy(() -> Input.splitToLottoBalls("asd"))
                .isInstanceOf(Exception.class);
        assertThatThrownBy(() -> Input.splitToLottoBalls("1,,2,3,4,5,6"))
                .isInstanceOf(Exception.class);
        assertThatThrownBy(() -> Input.splitToLottoBalls("1,a,2,3,4,5"))
                .isInstanceOf(Exception.class);
        assertThatThrownBy(() -> Input.splitToLottoBalls(""))
                .isInstanceOf(Exception.class);
    }
}
