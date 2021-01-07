package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class LottoBalls {
    private final List<Integer> lottoBalls;

    public LottoBalls(List<Integer> lottoBalls) {
        Collections.sort(lottoBalls);
        this.lottoBalls = lottoBalls;
    }

    public boolean contains(int bonusBall) {
        return lottoBalls.contains(bonusBall);
    }

    public boolean hasDuplicate() {
        int count = (int) lottoBalls.stream().distinct().count();
        return count != lottoBalls.size();
    }

    public Stream<Integer> stream() {
        return lottoBalls.stream();
    }

    public int size() {
        return lottoBalls.size();
    }

    public List<Integer> getLottoNumbers() {
        return new ArrayList<>(lottoBalls);
    }

    public boolean isIllegal(int bonusBall) {
        return this.contains(bonusBall) || this.hasDuplicate() ||
                this.size() != Lotto.LOTTO_COUNT || this.hasIllegalNumber();
    }

    public boolean hasIllegalNumber() {
        return lottoBalls.stream().anyMatch(i ->
                i > Lotto.LOTTO_MAX || i < Lotto.LOTTO_MIN);
    }

    @Override
    public String toString() {
        return lottoBalls.toString();
    }
}
