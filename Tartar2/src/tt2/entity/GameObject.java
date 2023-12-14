/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.entity;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.common.IPositioned;
import tt2.common.IStepable;
import tt2.common.ITickable;
import tt2.common.Utils;

import java.util.Comparator;

public class GameObject implements ITickable, IStepable, IPositioned {
    private Vector3 position;

    public GameObject(Vector3 pos) {
        position = pos;
    }

    @Override
    public void step() {

    }

    @Override
    public void tick() {

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
        float x = position.x;
        float y = position.y;
        float z = position.z;

        return new Vector2(0.5f * x + z * -0.5f, 0.25f * x + z * 0.25f - y * 0.5f);
    }
}
