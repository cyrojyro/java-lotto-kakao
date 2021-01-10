package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoBalls {
    private final List<LottoBall> lottoBalls;

    public LottoBalls(List<LottoBall> LottoBalls) {
        Collections.sort(LottoBalls);
        this.lottoBalls = LottoBalls;
    }

    public boolean contains(LottoBall bonusBall) {
        return lottoBalls.contains(bonusBall);
    }

    public boolean hasDuplicate() {
        int count = (int) lottoBalls.stream().distinct().count();
        return count != lottoBalls.size();
    }

    public List<LottoBall> getLottoNumbers() {
        return new ArrayList<>(lottoBalls);
    }

    public boolean isIllegal(LottoBall bonusBall) {
        return this.contains(bonusBall) || this.hasDuplicate() ||
                lottoBalls.size() != Lotto.LOTTO_COUNT || this.hasIllegalNumber();
    }

    public boolean hasIllegalNumber() {
        return lottoBalls.stream().anyMatch(LottoBall::isIllegalNumber);
    }

    @Override
    public String toString() {
        return lottoBalls.toString();
    }
}
