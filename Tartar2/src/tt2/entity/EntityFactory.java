/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.entity;

import com.google.gson.JsonObject;
import com.raylib.java.raymath.Vector3;
import tt2.common.CommonFactory;
import tt2.common.IsometricRotation;
import tt2.world.tile.DefaultTile;
import tt2.world.tile.PortalTile;
import tt2.world.tile.StairsTile;
import tt2.world.tile.Tile;

public class EntityFactory extends CommonFactory {
    static public Entity createEntity(JsonObject obj) {
        Entity entity = null;

        String type = obj.get("type").getAsString();
        Vector3 position = getVector3(obj.get("pos").getAsJsonObject());

        if(type.contentEquals("Goblin")) {
            entity = new Goblin(position);
        }

        return entity;
    }
}
