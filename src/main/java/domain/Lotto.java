package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> randomPool;
    private List<Integer> lottoBalls;
    private int bonusBall;

    public static final int LOTTO_COUNT = 6;
    public static final int LOTTO_MIN = 1;
    public static final int LOTTO_MAX = 45;

    Lotto(){
        lottoBalls = new ArrayList<>();
        randomPool = new ArrayList<>();
        for(int lottoNum = LOTTO_MIN; lottoNum <= LOTTO_MAX; ++lottoNum){
            randomPool.add(lottoNum);
        }
        generateLotto();
    }

    Lotto(List<Integer> balls, int bonusBall){
        lottoBalls = balls;
        randomPool = new ArrayList<>();
        this.bonusBall = bonusBall;
    }

    public void generateLotto() {
        Collections.shuffle(randomPool);
        lottoBalls = randomPool.subList(0, LOTTO_COUNT);
        bonusBall = randomPool.get(LOTTO_COUNT + 1);
    }

    public List<Integer> getLottoBalls() {
        return lottoBalls;
    }

    public int getOneBall(int index) {
        return lottoBalls.get(index);
    }

    public boolean hasBall(int ball) {
        return lottoBalls.contains(ball);
    }

    public int calculateSameBall(Lotto testLotto) {
        return (int) this.lottoBalls.stream()
                .filter(testLotto::hasBall)
                .count();
    }

    public boolean hasSameBonusBall(Lotto testLotto) {
        return testLotto.bonusBall == this.bonusBall;
    }

    public int getBonusBall() {
        return bonusBall;
    }

    public LottoRank findLottoRank(Lotto testLotto) {
        return LottoRank.calculateLottoRank(calculateSameBall(testLotto),hasSameBonusBall(testLotto));
    }
}
