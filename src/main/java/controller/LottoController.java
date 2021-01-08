package controller;

import domain.Lotto;
import domain.LottoBall;
import domain.LottoStatistics;
import domain.Lottos;
import view.Input;
import view.Output;

import java.util.List;

public class LottoController {
    Lottos lottosBought;
    Lotto winningLotto;
    private int buyAmount;
    private int buyNumber;

    public void simulateLotto() {
        buyLotto();
        initializeLottos();
        setWinningLotto();
        printStatistics();
    }

    private void buyLotto() {
        Output.askAmount();
        buyAmount = Input.getLottoBuyAmount();
        buyNumber = buyAmount / Lotto.LOTTO_PRICE;
        Output.printBuy(buyNumber);
    }

    private void initializeLottos() {
        lottosBought = new Lottos(buyNumber);
        Output.printLottos(lottosBought);
    }

    private void setWinningLotto() {
        Output.askPreviousWinNumber();
        List<LottoBall> winningNumbers = Input.getPreviousWinBalls();
        Output.askPreviousWinBonus();
        LottoBall winningBonus = Input.getPreviousBonus();
        winningLotto = new Lotto(winningNumbers, winningBonus);
    }

    private void printStatistics() {
        LottoStatistics lottoStatistics = lottosBought.getLottoStatistics(winningLotto);
        Output.printLottoStatistics(lottoStatistics);
        Output.printLottoRevenue(lottoStatistics, buyAmount);
    }
}
