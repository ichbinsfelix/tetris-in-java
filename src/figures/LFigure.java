package figures;

import java.util.Arrays;

public class LFigure extends Figure {
    public static final int COLOR = 3;

    public LFigure(int x, int y) {
        super(x, y, COLOR, Arrays.asList(
                new Offset(0, 1),
                new Offset(0, 0),
                new Offset(0, -1),
                new Offset(1, -1)));
    }
}


