package domain.helper;

import domain.LottoBall;
import domain.LottoBalls;

import java.util.ArrayList;
import java.util.List;

public class LottoBallsUtil {
    public static LottoBalls asList(Integer... args) {
        List<LottoBall> lottoBalls = new ArrayList<>();
        for (int i : args) {
            lottoBalls.add(LottoBall.valueOf(i));
        }
        return new LottoBalls(lottoBalls);
    }
}
