package domain;

import text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoBall implements Comparable<LottoBall> {
    private final int number;

    private static final List<LottoBall> numbers = new ArrayList<>();
    private static final List<LottoBall> randomPool;

    static {
        for (int i = Lotto.LOTTO_MIN; i <= Lotto.LOTTO_MAX; ++i) {
            numbers.add(new LottoBall(i));
        }
        randomPool = new ArrayList<>(numbers);
    }

    private LottoBall(int number) {
        this.number = number;
    }

    public static List<LottoBall> generateRandomLottoNumbers() {
        Collections.shuffle(randomPool);
        return new ArrayList<>(randomPool.subList(0, Lotto.LOTTO_COUNT + 1));
    }

    private static boolean isIllegalNumber(int number) {
        return number > Lotto.LOTTO_MAX || number < Lotto.LOTTO_MIN;
    }

    public static LottoBall valueOf(int number) {
        if (isIllegalNumber(number)) {
            throw new IllegalArgumentException(Text.ILLEGAL_LOTTO_ARGUMENT);
        }
        return numbers.get(number - 1);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LottoBall lottoBall) {
        return Integer.compare(this.getNumber(), lottoBall.getNumber());
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
