package tt2.scene;

import tt2.Tartar2;
import tt2.common.camera.Camera;

public class SplashScene extends Scene {
    public SplashScene() {
        super();
    }

    @Override
    public void render() {

    }

    @Override
    public void step() {

    }

    @Override
    public void tick() {
        super.tick();

        System.out.println("Splash scene");

        if(getSceneTimer() >= 2.0f) {
            Tartar2.setActiveScene(Tartar2.mainMenuScene);
        }
    }

    @Override
    public Camera getActiveCamera() {
        return null;
    }
}
