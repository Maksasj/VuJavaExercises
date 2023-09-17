package tt2.scene;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import com.raylib.java.shapes.Rectangle;
import tt2.Tartar2;
import tt2.common.Settings;
import tt2.common.camera.Camera;
import tt2.scene.button.*;
import tt2.textures.TextureAssetManager;

import java.util.Set;

public class MainMenuScene extends Scene {
    private Button playButton;
    private Button quitButton;

    public MainMenuScene() {
        super();

        playButton = new Button(
                new Vector3(290.0f, 225.0f, 0.0f),
                new Vector2(48, 16),
                TextureAssetManager.PLAY_BUTTON,
                new ButtonActionSwitchScene(Tartar2.gameScene),
                new ButtonActionHoveringScale(3.1f)
        );

        quitButton = new Button(
                new Vector3(290.0f, 300.0f, 0.0f),
                new Vector2(48, 16),
                TextureAssetManager.QUIT_BUTTON,
                new ButtonActionQuitGame(),
                new ButtonActionHoveringScale(3.1f)
        );
    }

    @Override
    public void render() {
        Tartar2.raylib.core.ClearBackground(Color.DARKGRAY);

        playButton.render();
        quitButton.render();

        Tartar2.raylib.textures.DrawTextureEx(TextureAssetManager.TARTAR2_LOGO, new Vector2(45, 45), 0, 2, Color.WHITE);
    }

    @Override
    public void step() {

    }

    @Override
    public void tick() {
        super.tick();

        playButton.tick();
        quitButton.tick();
    }

    @Override
    public Camera getActiveCamera() {
        return null;
    }
}
