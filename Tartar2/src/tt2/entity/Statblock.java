/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.entity;

public class Statblock {
    public int staminaStat;
    public int strengthStat;
    public int intelligenceStat;
    public int dexterityStat;

    public Statblock(int staminaStat, int strengthStat, int intelligenceStat, int dexterityStat) {
        this.staminaStat = staminaStat;
        this.strengthStat = strengthStat;
        this.intelligenceStat = intelligenceStat;
        this.dexterityStat = dexterityStat;
    }
}
