/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.moody_blues.common;

public enum RoomType {
    PUBLIC,
    PRIVATE;

    @Override
    public String toString() {
        return switch (this) {
            case PUBLIC -> "Public";
            case PRIVATE -> "Private";
        };
    }
}
