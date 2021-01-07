package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> randomPool;
    private List<Integer> lottoNumbers;

    public static final int LOTTO_COUNT = 6;
    public static final int LOTTO_MIN = 1;
    public static final int LOTTO_MAX = 45;

    Lotto(){
        lottoNumbers = new ArrayList<>();
        randomPool = new ArrayList<>();
        for(int lottoNum = LOTTO_MIN; lottoNum <= LOTTO_MAX; ++lottoNum){
            randomPool.add(lottoNum);
        }
    }

    public void generateLotto() {
        Collections.shuffle(randomPool);
        lottoNumbers = randomPool.subList(0, LOTTO_COUNT);
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
