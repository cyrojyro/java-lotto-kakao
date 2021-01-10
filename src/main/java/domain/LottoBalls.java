package domain;

import text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoBalls {
    private final List<LottoBall> lottoBalls;

    public LottoBalls(List<LottoBall> lottoBalls) {
        Collections.sort(lottoBalls);
        this.lottoBalls = lottoBalls;
        validateLottoBalls();
    }

    private void validateLottoBalls() {
        if (isIllegal()) {
            throw new IllegalArgumentException(Text.ILLEGAL_LOTTO_ARGUMENT);
        }
    }

    public boolean contains(LottoBall bonusBall) {
        return lottoBalls.contains(bonusBall);
    }

    private boolean hasDuplicate() {
        int count = (int) lottoBalls.stream().distinct().count();
        return count != lottoBalls.size();
    }

    public List<LottoBall> getLottoNumbers() {
        return new ArrayList<>(lottoBalls);
    }

    private boolean isIllegal() {
        return this.hasDuplicate() || lottoBalls.size() != Lotto.LOTTO_COUNT;
    }

    @Override
    public String toString() {
        return lottoBalls.toString();
    }
}
