package figures;

import java.util.Arrays;

public class TFigure extends Figure {
    public static final int COLOR = 6;

    public TFigure(int x, int y) {
        super(x, y, COLOR, Arrays.asList(
                new Offset(-1, 0),
                new Offset(0, 0),
                new Offset(1, 0),
                new Offset(0, -1)));
    }
}
