package tt2.common;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import com.raylib.java.textures.Texture2D;
import tt2.Tartar2;
import tt2.entity.GameObject;
import tt2.entity.GameObjectSorterByPerspective;
import tt2.entity.Player;
import tt2.items.Ability;
import tt2.items.BowAttackAbility;
import tt2.items.MoveAbility;
import tt2.items.SwordAttackAbility;
import tt2.scene.GameScene;
import tt2.textures.TextureAssetManager;
import tt2.world.World;

import java.util.ArrayList;
import java.util.List;

public class GameController extends CommonRenderingMaster implements ITickable, IStepable {
    private final List<Ability> abilities;
    private int selectedAbility;
    private final Player player;

    public GameController() {
        player = new Player(new Vector3(8, 1, 8));

        selectedAbility = 0;
        abilities = new ArrayList<Ability>();

        abilities.add(new MoveAbility(player));
        abilities.add(new SwordAttackAbility(player));
        abilities.add(new BowAttackAbility(player));
    }

    @Override
    public void render() {
        abilities.get(selectedAbility).render();

        int abilityCount = abilities.size();

        for(int i = 0; i < abilityCount; ++i) {
            Ability ability = abilities.get(i);

            Texture2D icon = ability.getIconTexture();
            if(icon == null)
                continue;

            float iconScale = 2.0f;

            Vector2 iconPosition = new Vector2(
                16.0f,
                16.0f + (i * (32.0f * iconScale + + 6.0f)) // 4.0f is just y offset
            );

            Tartar2.raylib.textures.DrawTextureEx(icon, iconPosition,0, iconScale, Color.WHITE);

            // if ability is selected lets draw selection frame
            if(i == selectedAbility) {
                Vector2 frameIconPosition = iconPosition;

                frameIconPosition.x -= 16.0f * (iconScale * 1.1f - iconScale);
                frameIconPosition.y -= 16.0f * (iconScale * 1.1f - iconScale);

                Tartar2.raylib.textures.DrawTextureEx(TextureAssetManager.SELECTION_FRAME_ICON, iconPosition,0, iconScale * 1.1f, Color.WHITE);
            }
        }
    }

    @Override
    public void doRenderingPreProcessing() {
        abilities.get(selectedAbility).doRenderingPreProcessing();
    }

    @Override
    public void step() {

    }

    @Override
    public void tick() {
        float mouseWheelDelta = rCore.GetMouseWheelMove();

        if(mouseWheelDelta > 0.0f)
            selectedAbility = Math.max(selectedAbility - 1, 0);

        if(mouseWheelDelta < 0.0f)
            selectedAbility = Math.min(selectedAbility + 1, abilities.size() - 1);

        Ability selected = abilities.get(selectedAbility);

        if(!selected.isBlocked())
            selected.tick();
    }

    public Player getPlayer() {
        return player;
    }

    public static World getWorld() {
        return ((GameScene) Tartar2.activeScene).getWorld();
    }
}
