package controller;

import domain.*;
import view.Input;
import view.Output;

import java.util.List;

public class LottoController {
    Lottos autoBoughtLottos;
    WinningLotto winningLotto;
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
        autoBoughtLottos = new Lottos(buyNumber);
        Output.printLottos(autoBoughtLottos);
    }

    private void setWinningLotto() {
        Output.askPreviousWinNumber();
        LottoBalls winningNumbers = Input.getLottoBalls();
        Output.askPreviousWinBonus();
        LottoBall winningBonus = Input.getPreviousBonus();
        winningLotto = new WinningLotto(winningNumbers, winningBonus);
    }

    private void printStatistics() {
        LottoStatistics lottoStatistics = autoBoughtLottos.getLottoStatistics(winningLotto);
        Output.printLottoStatistics(lottoStatistics);
        Output.printLottoRevenue(lottoStatistics, buyAmount);
    }
}
