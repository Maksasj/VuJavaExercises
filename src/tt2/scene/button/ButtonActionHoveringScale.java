/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.scene.button;

import tt2.common.IActionable;

public class ButtonActionHoveringScale extends ButtonActionHovering {
    private float scale;

    public ButtonActionHoveringScale(float scale) {
        this.scale = scale;
    }

    @Override
    public void doAction(Object obj) {
        if(obj != null) {
            Button button = (Button) obj;
            button.setButtonScale(scale);
        }
    }
}
