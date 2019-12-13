package tetris;

import figures.Figure;
import tetris.gui.ActionHandler;

public class FigureController extends Thread implements ActionHandler  {
    public static final int START_WAIT_TIME_MS = 500;
    public static final int PER_LEVEL_SPEEDUP_MS = 50;
    public static final int MIN_WAIT_TIME_NS = 100;

    private Game game;
    private Object waitTillEndLock = new Object();
    private boolean isRunning = false;

    public FigureController(Game game) {
        this.game = game;
    }

    public void stopControllerThread() {
        synchronized(waitTillEndLock) {
            isRunning = false;
            waitTillEndLock.notify();
        }
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
    public synchronized void rotateLeft() {
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
    public synchronized void rotateRight() {
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

    private synchronized boolean move(int byX, int byY) {
        Figure activeFigure = game.getActiveFigure();
        if(activeFigure == null) {
            return false;
        }

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

    @Override
    public void run() {
        isRunning = true;
        System.out.println("FigureController thread has started!");
        while(isRunning) {
            try {
                moveDown();
                game.onFigureMoved();
                synchronized(waitTillEndLock) {
                    int waitTime = START_WAIT_TIME_MS
                            - (game.getScoring().getLevel() - 1) * PER_LEVEL_SPEEDUP_MS;
                    if(waitTime < MIN_WAIT_TIME_NS) {
                        waitTime = MIN_WAIT_TIME_NS;
                    }
                    waitTillEndLock.wait(waitTime);
                }
            } catch (InterruptedException e) {
                System.out.println("FigureController thread has been interrupted");
                break;
            }
        }

        System.out.println("FigureController thread has ended!");
    }
}

