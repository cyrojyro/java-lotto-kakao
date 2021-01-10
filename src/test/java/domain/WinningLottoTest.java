package domain;

import domain.helper.LottoBallsUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class WinningLottoTest {
    @Test
    public void createLottoTest_duplicate() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new WinningLotto(LottoBallsUtil.asList(1, 1, 2, 3, 4, 5), LottoBall.valueOf(6)));
        assertThatIllegalArgumentException().isThrownBy(() ->
                new WinningLotto(LottoBallsUtil.asList(1, 2, 3, 4, 5, 6), LottoBall.valueOf(6)));
    }

    @Test
    public void createLottoTest_wrongSize() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new WinningLotto(LottoBallsUtil.asList(1, 2, 3, 4, 5), LottoBall.valueOf(6)));
        assertThatIllegalArgumentException().isThrownBy(() ->
                new WinningLotto(LottoBallsUtil.asList(1, 2, 3, 4, 5, 6, 7), LottoBall.valueOf(8)));
    }

    @Test
    public void createLottoTest_wrongNumber() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new WinningLotto(LottoBallsUtil.asList(1, 2, 3, 4, 5, 46), LottoBall.valueOf(8)));
        assertThatIllegalArgumentException().isThrownBy(() ->
                new WinningLotto(LottoBallsUtil.asList(1, 2, 3, 4, 5, 0), LottoBall.valueOf(8)));
        assertThatIllegalArgumentException().isThrownBy(() ->
                new WinningLotto(LottoBallsUtil.asList(1, 2, 3, 4, 5, 6), LottoBall.valueOf(46)));
        assertThatIllegalArgumentException().isThrownBy(() ->
                new WinningLotto(LottoBallsUtil.asList(1, 2, 3, 4, 5, 6), LottoBall.valueOf(0)));
    }
}
