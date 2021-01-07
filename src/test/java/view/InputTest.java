package view;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class InputTest {
    @Test
    public void splitToIntTest() {
        assertThat(Input.splitToInt("1,2,3"))
                .isEqualTo(Arrays.asList(1, 2, 3));
        assertThat(Input.splitToInt(" 1, 2  ,3,4,5  ,6"))
                .isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    public void splitToIntTest_invalid() {
        assertThatThrownBy(() -> Input.splitToInt("asd"))
                .isInstanceOf(Exception.class);
        assertThatThrownBy(() -> Input.splitToInt("1,,2,3,4,5,6"))
                .isInstanceOf(Exception.class);
        assertThatThrownBy(() -> Input.splitToInt("1,a,2,3,4,5"))
                .isInstanceOf(Exception.class);
        assertThatThrownBy(() -> Input.splitToInt(""))
                .isInstanceOf(Exception.class);
    }
}
