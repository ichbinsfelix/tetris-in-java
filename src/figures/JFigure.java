package figures;

import java.util.Arrays;

public class JFigure extends Figure {
    public static final int COLOR = 2;

    public JFigure(int x, int y) {
        super(x, y, COLOR, Arrays.asList(
                new Offset(1, 0),
                new Offset(2, 0),
                new Offset(3, 0),
                new Offset(4, 0)));
    }
}



