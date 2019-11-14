package figures;

import java.util.Arrays;

public class OFigure extends Figure {
    public static final int COLOR = 4;

    public OFigure(int x, int y) {
        super(x, y, COLOR, Arrays.asList(
                new Offset(-1, 0),
                new Offset(0, 0),
                new Offset(0, -1),
                new Offset(1, -1)));
    }
}
