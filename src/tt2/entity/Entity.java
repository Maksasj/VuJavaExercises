/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.entity;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.common.IRenderable;
import tt2.common.IStatable;
import tt2.common.VisibilityLevel;

import javax.swing.plaf.synth.SynthTextAreaUI;

public abstract class Entity extends GameObject implements IRenderable {
    private final Vector3 intermediatePosition;

    private VisibilityLevel visibilityFlag;
    private boolean deletedFlag;

    public Entity(Vector3 pos) {
        super(pos);
        visibilityFlag = VisibilityLevel.VISIBLE;
        deletedFlag = false;
        intermediatePosition = new Vector3(pos.x, pos.y, pos.z);
    }

    private void handleSmoothMovement() {
        Vector3 newPos = getPosition();

        newPos.x += (intermediatePosition.x - newPos.x) / 10.0f;
        newPos.y += (intermediatePosition.y - newPos.y) / 10.0f;
        newPos.z += (intermediatePosition.z - newPos.z) / 10.0f;
    }

    @Override
    public void tick() {
        handleSmoothMovement();
    }

    public void movePosition(Vector3 movePos) {
        intermediatePosition.x += movePos.x;
        intermediatePosition.y += movePos.y;
        intermediatePosition.z += movePos.z;
    }

    public boolean isDeleted() {
        return deletedFlag;
    }

    public void markAsDeleted() {
        deletedFlag = true;
    }

    public Vector3 getIntermediatePosition() {
        return intermediatePosition;
    }

    @Override
    public VisibilityLevel getVisibilityLevel() {
        return visibilityFlag;
    }

    @Override
    public void setApplyTint(boolean applyTint) {

    }

    @Override
    public void resetRenderingFlags() {
        visibilityFlag = VisibilityLevel.VISIBLE;
    }

    @Override
    public void setVisibilityLevel(VisibilityLevel visibilityLevel) {
        visibilityFlag = visibilityLevel;
    }

    @Override
    public void setTintColor(Color color) {

    }

    @Override
    public Color getTintColor() {
        return null;
    }

    @Override
    public void doRenderingPreProcessing() {

    }
}
