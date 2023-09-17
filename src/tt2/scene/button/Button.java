package tt2.scene.button;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;
import tt2.Tartar2;
import tt2.common.*;
import tt2.common.camera.CameraController;

import static com.raylib.java.core.input.Mouse.MouseButton.*;
import static tt2.common.Settings.DEFAULT_SPRITE_SCALE;

public class Button extends CommonRenderingMaster implements IPositioned, IRenderable, ITickable {
    private Vector3 position;
    private Rectangle buttonRect;
    private Texture2D buttonTexture;
    private ButtonAction buttonAction;
    private boolean btnAction = false;

    public Button(Vector3 position, Rectangle buttonRect, Texture2D buttonTexture, ButtonAction buttonAction) {
        this.position = position;
        this.buttonRect = new Rectangle(
            buttonRect.x * DEFAULT_SPRITE_SCALE,
            buttonRect.y * DEFAULT_SPRITE_SCALE,
            buttonRect.width * DEFAULT_SPRITE_SCALE,
            buttonRect.height * DEFAULT_SPRITE_SCALE
        );
        this.buttonTexture = buttonTexture;
        this.buttonAction = buttonAction;
    }

    @Override
    public void render() {
        Tartar2.raylib.textures.DrawTextureEx(buttonTexture, new Vector2(position.x, position.y),0,DEFAULT_SPRITE_SCALE, Color.WHITE);
    }

    @Override
    public void tick() {
        Vector2 mousePos = CameraController.getMousePosition();

        rCore core = Tartar2.raylib.core;

        if (Tartar2.raylib.shapes.CheckCollisionPointRec(mousePos, buttonRect)) {
            if(core.IsMouseButtonDown(MOUSE_BUTTON_LEFT))
                btnAction = false;

            if(core.IsMouseButtonReleased(MOUSE_BUTTON_LEFT))
                btnAction = true;
        }

        if(btnAction) {
            buttonAction.doAction();

            btnAction = false;
        }
    }

    @Override
    public Vector3 getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector3 newPos) {
        position = newPos;
    }

    @Override
    public Vector2 getIsometricPosition() {
        return null;
    }
}
