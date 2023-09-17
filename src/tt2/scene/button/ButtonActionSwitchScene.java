package tt2.scene.button;

import tt2.Tartar2;
import tt2.scene.Scene;

public class ButtonActionSwitchScene extends ButtonActionClick {
    private final Scene targetScene;

    public ButtonActionSwitchScene(Scene targetScene) {
        this.targetScene = targetScene;
    }

    @Override
    public void doAction(Object obj) {
        Tartar2.setActiveScene(targetScene);
    }
}
