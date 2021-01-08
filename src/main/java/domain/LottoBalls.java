package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class LottoBalls {
    private final List<LottoBall> LottoBalls;

    public LottoBalls(List<LottoBall> LottoBalls) {
        Collections.sort(LottoBalls);
        this.LottoBalls = LottoBalls;
    }

    public static List<LottoBall> asList(Integer... args) {
        List<LottoBall> LottoBalls = new ArrayList<>();
        for (int i : args) {
            LottoBalls.add(LottoBall.valueOf(i));
        }
        return LottoBalls;
    }

    public boolean contains(LottoBall bonusBall) {
        return LottoBalls.contains(bonusBall);
    }

    public boolean hasDuplicate() {
        int count = (int) LottoBalls.stream().distinct().count();
        return count != LottoBalls.size();
    }

    public Stream<LottoBall> stream() {
        return LottoBalls.stream();
    }

    public int size() {
        return LottoBalls.size();
    }

    public List<LottoBall> getLottoNumbers() {
        return new ArrayList<>(LottoBalls);
    }

    public boolean isIllegal(LottoBall bonusBall) {
        return this.contains(bonusBall) || this.hasDuplicate() ||
                this.size() != Lotto.LOTTO_COUNT || this.hasIllegalNumber();
    }

    public boolean hasIllegalNumber() {
        return LottoBalls.stream().anyMatch(LottoBall::isIllegalNumber);
    }

    @Override
    public String toString() {
        return LottoBalls.toString();
    }
}
