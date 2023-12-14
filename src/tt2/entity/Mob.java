/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.entity;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector3;
import tt2.common.IRenderable;
import tt2.common.IStatable;
import tt2.common.VisibilityLevel;

public abstract class Mob extends Entity implements IStatable {
    public Statblock statblock;
    int health;

    public Mob(Vector3 pos, Statblock statblock) {
        super(pos);

        this.statblock = statblock;

        health = getMaxHealth();
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damageValue) {
        health -= damageValue;
    }

    public boolean isDead() {
        return (health <= 0.0f) || isDeleted();
    }

    @Override
    public int getMaxHealth() {
        return statblock.staminaStat * 2;
    }

    @Override
    public int getMeleeDamage() {
        return statblock.strengthStat * 2 + statblock.dexterityStat;
    }

    @Override
    public int getRangedDamage() {
        return statblock.dexterityStat * 2 + statblock.strengthStat;
    }
}
