package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum LottoRank {
    // 선언 순서 중요
    FIRST(Arrays.asList(6), Arrays.asList(true, false)),
    SECOND(Arrays.asList(5),Arrays.asList(true)),
    THIRD(Arrays.asList(5),Arrays.asList(false)),
    FOURTH(Arrays.asList(4),Arrays.asList(true, false)),
    FIFTH(Arrays.asList(3),Arrays.asList(true, false)),
    NONE(Arrays.asList(0,1,2),Arrays.asList(true, false));

    private final List<Integer> rightCounts;
    private final List<Boolean> rightBonusBalls;

    LottoRank(List<Integer> rightCounts, List<Boolean> rightBonusBalls){
        this.rightCounts = rightCounts;
        this.rightBonusBalls = rightBonusBalls;
    }

    public boolean checkRank(int count, boolean rightBonusBall){
        return this.getRightCounts().contains(count) &&
                this.getRightBonusBalls().contains(rightBonusBall);
    }

    public boolean checkRank(Lotto lotto, Lotto winningLotto){
        return checkRank(lotto.calculateSameBall(winningLotto),
                lotto.hasSameBonusBall(winningLotto));
    }

    public static LottoRank calculateLottoRank(int count, boolean rightBonusBall) {
        Optional<LottoRank> result = Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank.checkRank(count, rightBonusBall))
                .findFirst();

        if(result.isPresent()) {
            return result.get();
        }

        throw new IllegalStateException();
    }

    private List<Integer> getRightCounts() {
        return rightCounts;
    }

    public List<Boolean> getRightBonusBalls() {
        return rightBonusBalls;
    }
}
