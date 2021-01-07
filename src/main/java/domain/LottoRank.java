package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum LottoRank {
    // 선언 순서 중요
    FIRST(Arrays.asList(6),false),
    SECOND(Arrays.asList(5),true),
    THIRD(Arrays.asList(5),false),
    FOURTH(Arrays.asList(4),false),
    FIFTH(Arrays.asList(3),false),
    NONE(Arrays.asList(0,1,2),false);

    private final List<Integer> counts;
    private final boolean rightBounsBall;

    LottoRank(List<Integer> counts, boolean rightBounsBall){
        this.counts = counts;
        this.rightBounsBall = rightBounsBall;
    }

    public boolean checkRank(int count, boolean rightBonusBall){
        return this.getCounts().contains(count) && (!this.isRightBounsBall() | rightBonusBall);
    }

    public static LottoRank calculateLottoRank(int count, boolean rightBounsBall) {
        Optional<LottoRank> result = Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank.checkRank(count, rightBounsBall))
                .findFirst();

        if(result.isPresent()) {
            return result.get();
        }

        throw new IllegalStateException();
    }

    private List<Integer> getCounts() {
        return counts;
    }

    public boolean isRightBounsBall() {
        return rightBounsBall;
    }
}
