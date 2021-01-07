import controller.LottoController;
import view.Output;

public class Main {
    public static void main(String[] args) {
        try {
            new LottoController().simulateLotto();
        } catch (Exception e) {
            Output.printException(e);
        }
    }
}
