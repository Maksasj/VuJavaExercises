package tt2.scene;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import tt2.Tartar2;
import tt2.common.Settings;
import tt2.common.Utils;
import tt2.common.camera.Camera;
import tt2.textures.TextureAssetManager;

public class SplashScene extends Scene {
    private float ufoEnjoersLogoTransparency;

    public SplashScene() {
        super();

        ufoEnjoersLogoTransparency = 0.0f;
    }

    @Override
    public void render() {
        Tartar2.raylib.core.ClearBackground(Color.BLACK);

        float ufoEnjoersLogoScale = 4.0f;

        Vector2 screenCenter = new Vector2(
            Settings.WINDOW_WIDTH / 2.0f,
            Settings.WINDOW_HEIGHT / 2.0f
        );

        screenCenter.x -= 96.0f * ufoEnjoersLogoScale * 0.5f;
        screenCenter.y -= 96.0f * ufoEnjoersLogoScale * 0.5f;

        Tartar2.raylib.textures.DrawTextureEx(
            TextureAssetManager.UFO_ENJOYERS_LOGO,
            screenCenter,
            0,
            ufoEnjoersLogoScale,
            new Color(255, 255, 255, (int) (255 * ufoEnjoersLogoTransparency))
        );
    }

    @Override
    public void step() {

    }

    @Override
    public void tick() {
        super.tick();

        float time = getSceneTimer();

        if(time <= 1.0f) {
            ufoEnjoersLogoTransparency += ((1.0f - time) / 10.0f);
        }

        if(time >= 2.0f) {
            ufoEnjoersLogoTransparency += ((2.0f - time) / 10.0f);
        }

        ufoEnjoersLogoTransparency = Utils.clamp(ufoEnjoersLogoTransparency, 0, 1.0f);

        if(getSceneTimer() >= 3.5f) {
            Tartar2.setActiveScene(Tartar2.mainMenuScene);
        }
    }

    @Override
    public Camera getActiveCamera() {
        return null;
    }
}
