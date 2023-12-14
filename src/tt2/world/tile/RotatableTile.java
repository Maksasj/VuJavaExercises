/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.world.tile;

import com.raylib.java.raymath.Vector3;
import tt2.common.IRotatable;
import tt2.common.IsometricRotation;

public abstract class RotatableTile extends Tile implements IRotatable {
    private IsometricRotation tileRotation;

    public RotatableTile(Vector3 pos, TileDensity tileDensity, IsometricRotation tileRotation) {
        super(pos, tileDensity);

        this.tileRotation = tileRotation;
    }

    public IsometricRotation getIsometricRotation() {
        return tileRotation;
    }
}
