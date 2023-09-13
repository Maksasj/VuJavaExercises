package tt2.common;

import com.raylib.java.raymath.Vector3;

public class Utils {
    public static int getRandomInRange(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static float pointDistance2(Vector3 firstPoint, Vector3 secondPoint) {
        float x = firstPoint.x - secondPoint.x;
        float y = firstPoint.y - secondPoint.y;
        float z = firstPoint.z - secondPoint.z;

        return x * x + y * y + z * z;
    }
}
