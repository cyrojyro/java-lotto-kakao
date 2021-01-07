package view;

import domain.Lotto;
import domain.LottoStatistics;
import domain.Lottos;
import text.Phrase;

import java.math.BigInteger;

public class Output {
    public static void askAmount() {
        System.out.println(Phrase.BUY_AMOUNT_PHRASE);
    }

    public static void printBuy(int buyNumber) {
        System.out.println(buyNumber + Phrase.LOTTOS_BOUGHT_PHRASE);
    }

    public static void askPreviousWinNumber() {
        System.out.println(Phrase.LAST_WEEK_BALLS_PHRASE);
    }

    public static void askPreviousWinBonus() {
        System.out.println(Phrase.BONUS_BALL_INPUT_PHRASE);
    }

    public static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto);
        }
        System.out.println();
    }

    public static void printLottoStatistics(LottoStatistics lottoStatistics) {
        System.out.print(lottoStatistics);
    }

    public static void printLottoRevenue(LottoStatistics lottoStatistics, int buyAmount) {
        System.out.println(Phrase.getRevenueStatement(
                lottoStatistics.calculateEarningsRate(
                        BigInteger.valueOf(buyAmount))));
    }

    public static void printException(Exception e) {
        System.out.println(e.getMessage());
    }
}
