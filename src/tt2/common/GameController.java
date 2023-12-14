/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.common;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import com.raylib.java.textures.Texture2D;
import org.lwjgl.system.CallbackI;
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
        // player = new Player(new Vector3(8, 3, 8));

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

            ITexture icon = ability.getIconTexture();
            if(icon == null)
                continue;

            float iconScale = 2.0f;

            Vector2 iconPosition = new Vector2(
                16.0f,
                16.0f + (i * (32.0f * iconScale + + 6.0f)) // 4.0f is just y offset
            );

            icon.render(iconPosition, iconScale, Color.WHITE);

            // if ability is selected lets draw selection frame
            if(i == selectedAbility) {
                Vector2 frameIconPosition = iconPosition;

                float rate = 1.1f;

                frameIconPosition.x -= 16.0f * (iconScale * rate - iconScale);
                frameIconPosition.y -= 16.0f * (iconScale * rate - iconScale);

                TextureAssetManager.uiTexture.getSubTexture(3).render(frameIconPosition, iconScale * rate, Color.WHITE);
            }
        }

        renderHealthUI();
    }

    private void renderHealthUI() {
        int maxHealth = player.getMaxHealth();
        int health = player.getHealth();

        for(int i = 0; i < maxHealth; ++i) {
            float heartSpriteScale = 5.0f;

            Vector2 heartPosition = new Vector2(
                    Settings.WINDOW_WIDTH - 8.0f * heartSpriteScale,
                    Settings.WINDOW_HEIGHT - 8.0f * heartSpriteScale
            );

            heartPosition.y -= i * 8.0f * heartSpriteScale + 4.0f; // 4.0f is just an offset

            if(i <= health) {
                TextureAssetManager.uiTexture.getSubTexture(9).render(heartPosition, heartSpriteScale, Color.WHITE);
            } else {
                TextureAssetManager.uiTexture.getSubTexture(8).render(heartPosition, heartSpriteScale, Color.WHITE);
            }
        }
    }

    private boolean isAnyAbilityIsBlocked() {
        for(Ability ability : abilities)
            if(ability.isBlocked())
                return true;

        return false;
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

        if(!isAnyAbilityIsBlocked())
            selected.tick();
    }

    public Player getPlayer() {
        return player;
    }

    public static World getWorld() {
        return ((GameScene) Tartar2.activeScene).getWorld();
    }
}
