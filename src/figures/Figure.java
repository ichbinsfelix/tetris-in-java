package figures;

import tetris.Game;
import tetris.gui.Block;
import tetris.gui.GUI;

import java.util.ArrayList;
import java.util.List;

public class Figure {
    private int x;
    private int y;
    private int color;
    private List<Offset> offsets;

    public Figure(int x, int y, int color, List<Offset> offsets) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.offsets = offsets;
    }

    public void draw(GUI gui) {
        gui.drawBlocks(getBlocks());
    }

    public List<Block> getBlocks() {
        List<Block> allBlocks = new ArrayList<>();
        for(Offset offset : offsets) {
            Block block = new Block(x+offset.getX(), y+offset.getY(), color);
            allBlocks.add(block);
        }

        return allBlocks;
    }

    public boolean moveBy(int byX, int byY) {
        x = x + byX;
        y = y + byY;
        return true;
    }

    public void rotateCounterClockwise() {
        for(Offset offset : offsets) {
            offset.rotateCounterClockwise();
        }
    }

    public void rotateClockwise() {
        for(Offset offset : offsets) {
            offset.rotateClockwise();
        }
    }


}
