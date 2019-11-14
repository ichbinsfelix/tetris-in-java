package tetris;

import figures.Figure;
import tetris.gui.ActionHandler;

public class FigureController implements ActionHandler {
    private Game game;

    public FigureController(Game game) {
        this.game = game;
    }

    @Override
    public void moveDown() {
        if(!move(0, -1)) {
            game.figureLanded();
        }
    }

    @Override
    public void moveLeft() {
        move(-1, 0);
    }

    @Override
    public void moveRight() {
        move(1, 0);
    }

    @Override
    public void rotateLeft() {
        Figure activeFigure = game.getActiveFigure();
        activeFigure.rotateCounterClockwise();
        try
        {
            game.getField().detectCollision(activeFigure);
        } catch (CollisionException e) {
            activeFigure.rotateClockwise();
        }
    }

    @Override
    public void rotateRight() {
        Figure activeFigure = game.getActiveFigure();
        activeFigure.rotateClockwise();
        try
        {
            game.getField().detectCollision(activeFigure);
        } catch (CollisionException e) {
            activeFigure.rotateCounterClockwise();
        }
    }

    @Override
    public void drop()  {
        while(move(0, -1)) {}
        game.figureLanded();
    }

    private boolean move(int byX, int byY) {
        Figure activeFigure = game.getActiveFigure();
        activeFigure.moveBy(byX, byY);
        try
        {
            game.getField().detectCollision(activeFigure);
            return true;
        } catch (CollisionException e) {
            activeFigure.moveBy(-byX, -byY);
        }

        return false;
    }
}