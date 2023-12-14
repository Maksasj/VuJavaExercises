/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.common;

public enum IsometricRotation {
    LEFT_UP,
    RIGHT_UP,
    RIGHT_DOWN,
    LEFT_DOWN;

    @Override
    public String toString() {
        return super.toString();
    }

    public static IsometricRotation fromString(String str) {
        if(str.contentEquals("LEFT_UP")) {
            return IsometricRotation.LEFT_UP;
        } else if(str.contentEquals("RIGHT_UP")) {
            return IsometricRotation.RIGHT_UP;
        } else if(str.contentEquals("RIGHT_DOWN")) {
            return IsometricRotation.RIGHT_DOWN;
        } else if(str.contentEquals("LEFT_DOWN")) {
            return IsometricRotation.LEFT_DOWN;
        }

        // Default value
        return IsometricRotation.LEFT_UP;
    }
}
