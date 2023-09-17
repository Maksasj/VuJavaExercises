package tt2.scene.button;

import tt2.Tartar2;
import tt2.common.IActionable;
import tt2.scene.Scene;

public class ButtonActionSwitchScene extends ButtonAction {
    private final Scene targetScene;

    public ButtonActionSwitchScene(Scene targetScene) {
        this.targetScene = targetScene;
    }

    @Override
    public void doAction() {
        Tartar2.setActiveScene(targetScene);
    }
}
