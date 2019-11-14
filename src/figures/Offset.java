package figures;

public class Offset {
    private int x;
    private int y;

    public Offset(int x, int y) {
            this.x = x;
            this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void rotateCounterClockwise() {
        int oldX = x;
        int oldY = y;
        x = -oldY;
        y = oldX;
    }

    public void rotateClockwise() {
        int oldX = x;
        int oldY = y;
        x = oldY;
        y = -oldX;
    }
}
