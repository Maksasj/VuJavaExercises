package tt2.world;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import tt2.common.IAssetManager;
import tt2.entity.Entity;
import tt2.entity.EntityFactory;
import tt2.entity.Player;
import tt2.world.tile.Tile;
import tt2.world.tile.TileFactory;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class LevelAssetManager implements IAssetManager {
    private Gson gson;

    public JsonObject LEVELS[];

    public LevelAssetManager() {
        gson = new Gson();

        LEVELS = new JsonObject[4];
        Arrays.fill(LEVELS, null);
    }

    @Override
    public void load() {
        try {
            LEVELS[0] = gson.fromJson(new FileReader("assets/levels/level0.json"), JsonObject.class);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public World loadLevel(Player player, JsonObject levelJson) {
        World world = new World(player);

        String name = levelJson.get("name").getAsString();

        JsonArray tiles = levelJson.get("tiles").getAsJsonArray();
        JsonArray entities = levelJson.get("entities").getAsJsonArray();

        for(JsonElement tileElement : tiles) {
            JsonObject tileJson = tileElement.getAsJsonObject();

            Tile tile = TileFactory.createTile(tileJson);

            if(tile != null)
                world.addTile(tile);
        }

        for(JsonElement entityElement : entities) {
            JsonObject entityJson = entityElement.getAsJsonObject();

            Entity entity = EntityFactory.createEntity(entityJson);

            if(entity != null)
                world.addEntity(entity);
        }

        return world;
    }

    @Override
    public void unload() {

    }
}
