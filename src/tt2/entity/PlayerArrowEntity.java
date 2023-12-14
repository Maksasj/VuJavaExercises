/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.entity;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.Tartar2;
import tt2.common.camera.Camera;
import tt2.textures.TextureAssetManager;

public class PlayerArrowEntity extends ArrowEntity {
    public PlayerArrowEntity(Vector3 pos, Vector3 direction, float arrowSpeed, float travelDistance, Mob shooter) {
        super(pos, direction, arrowSpeed, travelDistance, shooter);
    }

    @Override
    public DamageDealerType getDealerType() {
        return DamageDealerType.PLAYER;
    }
}
