package domain.helper;

import domain.LottoBall;

import java.util.ArrayList;
import java.util.List;

public class LottoBallsUtil {
    public static List<LottoBall> asList(Integer... args) {
        List<LottoBall> LottoBalls = new ArrayList<>();
        for (int i : args) {
            LottoBalls.add(LottoBall.valueOf(i));
        }
        return LottoBalls;
    }
}
