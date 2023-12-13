package wavegame;

import java.util.function.Function;

public interface IClickable extends IDrawable {
    void setOnClick(Function<Object[], Void> onClick, Object[] args);
    boolean isEnabled();
    void click();
}
