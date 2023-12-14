/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

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

import java.awt.image.ColorModel;

import static com.raylib.java.core.input.Mouse.MouseButton.*;
import static tt2.common.Settings.DEFAULT_SPRITE_SCALE;

public class Button extends CommonRenderingMaster implements IPositioned, IRenderable, ITickable {
    private Vector3 position;
    private Rectangle buttonRect;
    private ITexture buttonTexture;
    private ButtonActionClick buttonAction;
    private ButtonActionHovering buttonHoveringAction;
    private boolean btnAction;
    private boolean mouseHovering;
    private float buttonScale;

    public Button(Vector3 position, Vector2 buttonRect, ITexture buttonTexture, ButtonActionClick buttonAction, ButtonActionHovering buttonHoveringAction) {
        btnAction = false;
        mouseHovering = false;

        buttonScale = DEFAULT_SPRITE_SCALE;

        this.position = position;
        this.buttonRect = new Rectangle(
                position.x, position.y,
            buttonRect.x,
            buttonRect.y
        );
        this.buttonTexture = buttonTexture;

        this.buttonAction = buttonAction;
        this.buttonHoveringAction = buttonHoveringAction;
    }

    @Override
    public void render() {
        Vector2 texturePosition = new Vector2(
                position.x - ((buttonScale * buttonRect.width) / 2.0f),
                position.y - ((buttonScale * buttonRect.height) / 2.0f)
        );

        buttonTexture.render(texturePosition, buttonScale, Color.WHITE);
    }

    public void setButtonScale(float scale) {
        buttonScale = scale;
    }

    @Override
    public void tick() {
        Vector2 mousePos = CameraController.getMousePosition();

        rCore core = Tartar2.raylib.core;

        Rectangle rectToCheck = new Rectangle(
            position.x - ((buttonScale * buttonRect.width) / 2.0f),
            position.y - ((buttonScale * buttonRect.height) / 2.0f),
            buttonScale * buttonRect.width,
            buttonScale * buttonRect.height
        );

        mouseHovering = Tartar2.raylib.shapes.CheckCollisionPointRec(mousePos, rectToCheck);

        if(mouseHovering) {
            if(core.IsMouseButtonDown(MOUSE_BUTTON_LEFT))
                btnAction = false;

            if(core.IsMouseButtonReleased(MOUSE_BUTTON_LEFT))
                btnAction = true;

            buttonHoveringAction.doAction(this);
        } else {
            setButtonScale(DEFAULT_SPRITE_SCALE);
        }

        if(btnAction) {
            buttonAction.doAction(null);

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
