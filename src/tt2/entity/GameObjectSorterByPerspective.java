package tt2.entity;

import com.raylib.java.raymath.Vector3;
import tt2.common.Utils;

import java.util.Comparator;

public class GameObjectSorterByPerspective implements Comparator<GameObject> {
    private static final Vector3 worldPerspectivePoint = new Vector3(16.0f, 16.0f, 16.0f);

    @Override
    public int compare(GameObject a, GameObject b) {
        return Float.compare(Utils.pointDistance2(worldPerspectivePoint, b.getPosition()), Utils.pointDistance2(worldPerspectivePoint, a.getPosition()));
    }
}
