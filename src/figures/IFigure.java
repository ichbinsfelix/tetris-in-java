package figures;

import java.util.Arrays;

public class IFigure extends Figure {
    public static final int COLOR = 1;

    public IFigure(int x, int y) {
        super(x, y, COLOR, Arrays.asList(
                new Offset(1, 0),
                new Offset(0, 0),
                new Offset(0, -1),
                new Offset(1, -1)));
    }
}


