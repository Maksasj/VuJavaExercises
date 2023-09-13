package tt2.world.tile;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector3;
import tt2.common.IRenderable;
import tt2.entity.GameObject;

public abstract class Tile extends GameObject implements IRenderable {
    private float yOffset;
    private Color tintColor;
    private boolean applyTintColor;

    public Tile(Vector3 pos) {
        super(pos);
    }

    public void setTintColor(Color color) {
        tintColor = color;
    }

    public Color getTintColor() {
        return tintColor;
    }

    public void submitApplyTintColorFlag(boolean applyTint) {
        applyTintColor = applyTint;
    }

    public boolean isTintColorApplied() {
        return applyTintColor;
    }

    public void submitYOffset(float yOffset) {
        this.yOffset = yOffset;
    }

    public void resetYOffset() {
        yOffset = 0.0f;
    }

    public float getYOffset() {
        return yOffset;
    }

    @Override
    public void resetRenderingFlags() {
        submitApplyTintColorFlag(false);
        resetYOffset();
    }
}
