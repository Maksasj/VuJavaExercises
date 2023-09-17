package tt2.scene.button;

import tt2.Tartar2;
import tt2.scene.Scene;

public class ButtonActionQuitGame extends ButtonActionClick {
    @Override
    public void doAction(Object obj) {
        Tartar2.quitGame();
    }
}
