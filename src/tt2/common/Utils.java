package tt2.common;

public class Utils {
    public static int getRandomInRange(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
