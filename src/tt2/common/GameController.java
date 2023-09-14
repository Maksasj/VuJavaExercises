package tt2.common;

import com.raylib.java.raymath.Vector3;
import tt2.Tartar2;
import tt2.entity.Player;
import tt2.items.Ability;
import tt2.items.MoveAbility;
import tt2.scene.GameScene;
import tt2.world.World;

import java.util.ArrayList;
import java.util.List;

public class GameController extends CommonRenderingMaster implements ITickable, IStepable {
    private final List<Ability> abilities;
    private final int selectedAbility;
    private final Player player;

    public GameController() {
        player = new Player(new Vector3(8, 1, 8));

        selectedAbility = 0;
        abilities = new ArrayList<Ability>();

        abilities.add(new MoveAbility(player));
    }

    @Override
    public void render() {
        abilities.get(selectedAbility).render();
    }

    @Override
    public void resetRenderingFlags() {

    }

    @Override
    public VisibilityLevel getVisibilityLevel() {
        return null;
    }

    @Override
    public void step() {

    }

    @Override
    public void tick() {
        abilities.get(selectedAbility).tick();
    }

    public Player getPlayer() {
        return player;
    }

    public static World getWorld() {
        return ((GameScene) Tartar2.activeScene).getWorld();
    }
}
