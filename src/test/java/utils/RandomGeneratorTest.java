package utils;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RandomGeneratorTest {
    @RepeatedTest(10)
    public void randomGeneratorTest() {
        assertThat(RandomGenerator.generateNumbers(0, 5, 5))
                .asList().isSubsetOf(0, 1, 2, 3, 4, 5).size().isEqualTo(5);
    }

    @Test
    public void randomGeneratorTest_sameMinMax() {
        assertThat(RandomGenerator.generateNumbers(0, 0, 1))
                .isEqualTo(Arrays.asList(0));
    }

    @Test
    public void randomGeneratorTest_invalidMinMax() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                RandomGenerator.generateNumbers(0, -1, 1));
    }

    @Test
    public void randomGeneratorTest_invalidCount() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                RandomGenerator.generateNumbers(0, 0, 2));
    }
}
