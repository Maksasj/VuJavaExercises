/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.entity;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.Tartar2;
import tt2.common.GameController;
import tt2.common.Utils;
import tt2.common.camera.Camera;
import tt2.textures.TextureAssetManager;
import tt2.world.World;

public class TextPopupEntity extends Entity {
    private String textToRender;
    private int fontSize;
    private Color textColor;
    private float timer;
    private float visibilityTime;
    private float fadeOutTime;

    public TextPopupEntity(Vector3 pos, String textToRender, Color textColor, float visibilityTime, float fadeOutTime) {
        super(pos);

        this.fontSize = 50;

        this.textColor = textColor;
        this.textToRender = textToRender;

        textColor.a = 0;

        this.timer = 0;
        this.visibilityTime = visibilityTime;
        this.fadeOutTime = fadeOutTime;
    }

    public void setTextToRender(String text) {
        textToRender = text;
    }

    @Override
    public void step() {
        super.step();
    }

    @Override
    public void tick() {
        super.tick();

        movePosition(new Vector3(0.0f, 0.01f, 0.0f));

        timer += Tartar2.activeScene.getDeltaTime();
        float existTime = visibilityTime + fadeOutTime;

        if(timer > visibilityTime) {
            float delta = timer - visibilityTime;

            float rate = 1.0f - Utils.clamp(delta / fadeOutTime, 0.0f, 1.0f);
            textColor.a = Math.round(255.0f * rate);
        } else {
            textColor.a = 255;
        }

        if(timer >= existTime) {
            markAsDeleted();
        }
    }

    @Override
    public void render() {
        if(isDeleted())
            return;

        Camera activeCamera = Tartar2.activeScene.getActiveCamera();

        Vector2 pos = getIsometricPosition();
        Vector3 cameraPosition = Tartar2.activeScene.getActiveCamera().getPosition();
        float cameraZoom = activeCamera.getZoom();

        Tartar2.raylib.text.DrawText(
                textToRender,
                (int) (pos.x * cameraZoom * 32.0f - cameraZoom * 16 + cameraPosition.x + fontSize * 0.5f),
                (int) (pos.y * cameraZoom * 32.0f + cameraPosition.z + fontSize * 0.5f),
                50,
                textColor
        );
    }
}
