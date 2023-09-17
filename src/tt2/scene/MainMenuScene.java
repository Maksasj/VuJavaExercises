package tt2.scene;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector3;
import com.raylib.java.shapes.Rectangle;
import tt2.Tartar2;
import tt2.common.camera.Camera;
import tt2.scene.button.Button;
import tt2.scene.button.ButtonActionSwitchScene;
import tt2.textures.TextureAssetManager;

public class MainMenuScene extends Scene {
    private Button beginGameButton;

    public MainMenuScene() {
        super();

        beginGameButton = new Button(
                new Vector3(0.0f, 0.0f, 0.0f),
                new Rectangle(0, 0, 32, 32),
                TextureAssetManager.PLAYER_TEXTURE,
                new ButtonActionSwitchScene(Tartar2.gameScene)
        );
    }

    @Override
    public void render() {
        Tartar2.raylib.core.ClearBackground(Color.DARKGRAY);

        beginGameButton.render();
    }

    @Override
    public void step() {

    }

    @Override
    public void tick() {
        super.tick();

        beginGameButton.tick();
    }

    @Override
    public Camera getActiveCamera() {
        return null;
    }
}
