package text;

import java.math.BigInteger;

public class Phrase {
    // 오류 메시지
    public static final String ILLEGAL_LOTTO_RANKING_ARGUMENT = "로또 등수를 산정할 수 없습니다.";
    public static final String ILLEGAL_RANDOM_GENERATOR_ARGUMENT = "랜덤 생성 인자가 잘못되었습니다.";
    public static final String ILLEGAL_LOTTO_ARGUMENT = "로또 번호가 잘못되었습니다.";
    public static final String ILLEGAL_INPUT = "잘못된 입력입니다.";

    // UI 관련 메시지
    public static final String BUY_AMOUNT_PHRASE = "구입금액을 입력해 주세요.";
    public static final String LOTTOS_BOUGHT_PHRASE = "개를 구매했습니다.";
    public static final String LAST_WEEK_BALLS_PHRASE = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String BONUS_BALL_INPUT_PHRASE = "보너스 볼을 입력해 주세요.";
    public static final String WINNING_STATISTICS_PHRASE = "\n당첨 통계\n---------\n";

    // 템플릿
    public static String getRevenueStatement(BigInteger revenue) {
        return "총 수익률은 " + revenue + "% 입니다.";
    }

    public static String getLottoRewardStatement(String description,
                                                 BigInteger reward, BigInteger value) {
        return description + " (" + reward + "원) - " + value + "개\n";
    }
}
