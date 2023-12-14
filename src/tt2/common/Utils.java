/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.common;

import com.raylib.java.raymath.Vector3;

public class Utils {
    public static int getRandomInRange(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static int clamp(int value, int min, int max) {
        if(value < min)
            return min;

        return Math.min(value, max);
    }

    public static float clamp(float value, float min, float max) {
        if(value < min)
            return min;

        return Math.min(value, max);
    }

    public static float pointDistance2(Vector3 firstPoint, Vector3 secondPoint) {
        float x = firstPoint.x - secondPoint.x;
        float y = firstPoint.y - secondPoint.y;
        float z = firstPoint.z - secondPoint.z;

        return x * x + y * y + z * z;
    }
}
