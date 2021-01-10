package view;

import domain.Lotto;
import domain.LottoStatistics;
import domain.Lottos;
import text.Text;

import java.math.BigInteger;

public class Output {
    public static void askAmount() {
        System.out.println(Text.BUY_AMOUNT_PHRASE);
    }

    public static void askManualNumber() {
        System.out.println(Text.MANUAL_BUY_PHRASE);
    }

    public static void askManualLotto() {
        System.out.println(Text.MANUAL_LOTTO_PHRASE);
    }

    public static void printBuy(int manualBuyNumber, int autoBuyNumber) {
        System.out.println(Text.getBuyStatement(manualBuyNumber, autoBuyNumber));
    }

    public static void askPreviousWinNumber() {
        System.out.println(Text.LAST_WEEK_BALLS_PHRASE);
    }

    public static void askPreviousWinBonus() {
        System.out.println(Text.BONUS_BALL_INPUT_PHRASE);
    }

    public static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto);
        }
    }

    public static void printLottoStatistics(LottoStatistics lottoStatistics) {
        System.out.print(lottoStatistics);
    }

    public static void printLottoRevenue(LottoStatistics lottoStatistics, int buyAmount) {
        System.out.println(Text.getRevenueStatement(
                lottoStatistics.calculateEarningsRate(
                        BigInteger.valueOf(buyAmount))));
    }

    public static void printException(Exception e) {
        System.out.println(e.getMessage());
    }
}
