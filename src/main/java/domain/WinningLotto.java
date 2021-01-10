package domain;

import text.Text;

import java.util.List;

public class WinningLotto extends Lotto {
    private final LottoBall bonusBall;

    public WinningLotto(List<LottoBall> balls, LottoBall bonusBall) {
        this(new LottoBalls(balls), bonusBall);
    }

    public WinningLotto(LottoBalls lottoBalls, LottoBall bonusBall) {
        super(lottoBalls);
        this.bonusBall = bonusBall;
        validateWinningLotto();
    }

    private void validateWinningLotto() {
        if (lottoBalls.contains(bonusBall)) {
            throw new IllegalArgumentException(Text.ILLEGAL_LOTTO_ARGUMENT);
        }
    }

    public LottoBall getBonusBall() {
        return bonusBall;
    }
}
