package tt2.common;

public enum IsometricDirection {
    NONE,
    LEFT_UP,
    RIGHT_UP,
    RIGHT_DOWN,
    LEFT_DOWN,
    UP,
    DOWN;

    public IsometricRotation toIsometricRotation() {
        return switch (this) {
            case LEFT_UP -> IsometricRotation.LEFT_UP;
            case RIGHT_UP -> IsometricRotation.RIGHT_UP;
            case RIGHT_DOWN -> IsometricRotation.RIGHT_DOWN;
            case LEFT_DOWN -> IsometricRotation.LEFT_DOWN;
            default -> IsometricRotation.LEFT_UP;
        };
    }
}
