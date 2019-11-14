package tetris;

import figures.Figure;
import tetris.gui.Block;

import java.util.ArrayList;
import java.util.List;

public class Field extends Object {
    private int height;
    private int width;
    private final List<Block> blocks = new ArrayList<>();

    public Field(int width, int height) {
        this.height = height;
        this.width = width;
/*
        for(int row = 3; row >= 0; row--) {
            for(int col = 0; col < width-1; col++) {
                blocks.add(new Block(col, row, 1));
            }
        }
        */
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public void detectCollision (Figure figure) throws CollisionException {
        for(Block block : figure.getBlocks()) {
            if(block.x < 0) {
                throw new CollisionException("Went too left!");
            }

            if (block.x >= width) {
                throw new CollisionException("Went too right!");
            }

            if (block.y < 0) {
                throw new CollisionException("Went below field!");
            }

            for(Block fallenBlock : blocks) {
                if(fallenBlock.x == block.x && fallenBlock.y == block.y) {
                    throw new CollisionException("Collision with already fallen block!");
                }
            }
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void addBlocks(List<Block> blocks) {
        for (Block b: blocks) {
            this.blocks.add(b);
        }
    }

    public void removeAllBlocks() {
        blocks.clear();
    }

    public int removeFullRows() {
        int removedRowsCount = 0;
        for(int row = height-1; row >= 0; row--) {
            if(isRowFull(row)) {
                removeRow(row);
                removedRowsCount++;
            }
        }

        return removedRowsCount;
    }

    private boolean isRowFull(int y) {
        List<Boolean> isFieldOccupied = new ArrayList<>();
        for(int i = 0; i < width; i++) {
            isFieldOccupied.add(false);
        }

        for(Block fallenBlock : blocks) {
            if(fallenBlock.y != y) {
                continue;
            }

            isFieldOccupied.set(fallenBlock.x, true);
        }

        for(Boolean occupied : isFieldOccupied) {
            if(!occupied) {
                return false;
            }
        }

        return true;

        // ffffffff
        // 2 4
        // 3 4
        // 3 5
        // 4 4

        // |x | 2
        // |xx| 1
        // |x | 0

        // fffftttttfftttffffttttffff
        //

        // isRowFull(1) ? true
        // isRowFull(2) ? false
    }

    private void removeRow(int y) {
        List<Block> wantToDelete = new ArrayList<>();
        for(Block fallenBlock : blocks) {
            if(fallenBlock.y != y) {
                continue;
            }

            wantToDelete.add(fallenBlock);
        }

        for(Block toDeleteBlock : wantToDelete) {
            blocks.remove(toDeleteBlock);
        }

        for(Block fallenBlock : blocks) {
            if(fallenBlock.y < y) {
                continue;
            }

            fallenBlock.y--;
        }
    }
}
