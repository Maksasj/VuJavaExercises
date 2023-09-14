package tt2.world.tile;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector3;
import tt2.common.IRenderable;
import tt2.common.VisibilityLevel;
import tt2.entity.GameObject;

public abstract class Tile extends GameObject implements IRenderable {
    private float yOffset;
    private Color tintColor;
    private boolean applyTintColor;
    private VisibilityLevel visibilityFlag;

    public Tile(Vector3 pos) {
        super(pos);

        visibilityFlag = VisibilityLevel.VISIBLE;
    }

    @Override
    public void setApplyTint(boolean applyTint) {
        applyTintColor = applyTint;
    }

    @Override
    public void setTintColor(Color color) {
        tintColor = color;
    }

    @Override
    public Color getTintColor() {
        return tintColor;
    }

    @Override
    public VisibilityLevel getVisibilityLevel() {
        return visibilityFlag;
    }

    @Override
    public void setVisibilityLevel(VisibilityLevel visibilityLevel) {
        visibilityFlag = visibilityLevel;
    }

    public boolean isTintColorApplied() {
        return applyTintColor;
    }

    public void submitYOffset(float yOffset) {
        this.yOffset = yOffset;
    }

    public float getYOffset() {
        return yOffset;
    }

    @Override
    public void resetRenderingFlags() {
        visibilityFlag = VisibilityLevel.VISIBLE;
        applyTintColor = false;

        yOffset = 0.0f;
    }

    @Override
    public void doVisibilityPostProcessing() {

    }
}
