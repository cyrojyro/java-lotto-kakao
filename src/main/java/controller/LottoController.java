package controller;

import domain.*;
import view.Input;
import view.Output;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private Lottos totalBoughtLottos;
    private Lottos manualBoughtLottos;
    private Lottos autoBoughtLottos;

    private WinningLotto winningLotto;

    private int totalBuyNumber;
    private int manualBuyNumber;
    private int autoBuyNumber;

    public void simulateLotto() {
        setTotalBuyNumber();
        buyManualLotto();
        buyAutoLotto();
        setTotalLottoStatus();
        setWinningLotto();
        showStatistics();
    }

    private void setTotalBuyNumber() {
        Output.askAmount();
        totalBuyNumber = Input.getLottoBuyAmount() / Lotto.LOTTO_PRICE;

    }

    private void buyManualLotto() {
        Output.askManualNumber();
        manualBuyNumber = Input.getLottoManualBuyNumber(totalBuyNumber);

        if (manualBuyNumber <= 0) {
            List<Lotto> lottos = new ArrayList<>();
            manualBoughtLottos = new Lottos(lottos);
            return;
        }

        setManualLotto();
    }

    private void setManualLotto() {
        Output.askManualLotto();
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < manualBuyNumber; ++i) {
            lottos.add(new Lotto(Input.getLottoBalls()));
        }

        manualBoughtLottos = new Lottos(lottos);
    }

    private void buyAutoLotto() {
        autoBuyNumber = totalBuyNumber - manualBuyNumber;
        autoBoughtLottos = new Lottos(autoBuyNumber);
    }

    private void setTotalLottoStatus() {
        totalBoughtLottos = new Lottos(manualBoughtLottos, autoBoughtLottos);
        Output.printBuy(manualBuyNumber, autoBuyNumber);
        Output.printLottos(totalBoughtLottos);
    }

    private void setWinningLotto() {
        Output.askPreviousWinNumber();
        LottoBalls winningNumbers = Input.getLottoBalls();
        Output.askPreviousWinBonus();
        LottoBall winningBonus = Input.getPreviousBonus();
        winningLotto = new WinningLotto(winningNumbers, winningBonus);
    }

    private void showStatistics() {
        LottoStatistics lottoStatistics = totalBoughtLottos.getLottoStatistics(winningLotto);
        Output.printLottoStatistics(lottoStatistics);
        Output.printLottoRevenue(lottoStatistics, totalBuyNumber * Lotto.LOTTO_PRICE);
    }
}
