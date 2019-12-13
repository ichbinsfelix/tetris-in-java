package tetris;

import figures.Figure;
import figures.RandomFigureFactory;
import tetris.gui.ActionEvent;
import tetris.gui.GUI;

public class Game {
    private GUI gui;
    private FigureController figureController;
    private Figure activeFigure;
    private Field field;
    private Scoring scoring;

    public Game(int width, int height, GUI gui) {
        this.gui = gui;
        figureController = new FigureController(this);
        //this.gui.setActionHandler(figureController);
        field = new Field(width, height);
        scoring = new Scoring();
    }

    public void onFigureMoved() {
        updateGUI();
    }

    public void figureLanded() {
        field.addBlocks(activeFigure.getBlocks());
        int numRemovedRows = field.removeFullRows();
        createFigure();
        scoring.updateScore(numRemovedRows);
        scoring.updateHighScore();
    }

    public Field getField() {
        return field;
    }

    public Scoring getScoring() {
        return scoring;
    }

    public Figure getActiveFigure() {
        return activeFigure;
    }

    private void start() {
        createFigure();
        figureController.start();
    }

    public void stop() {
        gui.setActionHandler(null);
        activeFigure = null;
        field.removeAllBlocks();
        scoring.reset();
        figureController.stopControllerThread();
    }

    private void createFigure() {
        activeFigure = RandomFigureFactory.getInstance().generateRandomFigureOnTop(field);
        try
        {
            field.detectCollision(activeFigure);
        }
        catch(CollisionException ex) {
            stop();
        }
    }

    public void run() {
        start();

        while (true) {
            updateGUI();
            ActionEvent e = gui.waitEvent();
            if(e == ActionEvent.MOVE_DOWN) {
                figureController.moveDown();
            }
            else if(e == ActionEvent.MOVE_LEFT) {
                figureController.moveLeft();
            }
            else if(e == ActionEvent.MOVE_RIGHT) {
                figureController.moveRight();
            }
            else if(e == ActionEvent.ROTATE_LEFT) {
                figureController.rotateLeft();
            }
            else if(e == ActionEvent.ROTATE_RIGHT) {
                figureController.rotateRight();
            }
            else if(e == ActionEvent.DROP) {
                figureController.drop();
            }
        }
    }

    private void updateGUI() {
        gui.clear();
        if(activeFigure != null) {
            activeFigure.draw(gui);
        }

        gui.drawBlocks(field.getBlocks());
        gui.setLevel(scoring.getLevel());
        gui.setScore(scoring.getScore());
        gui.setHighScore(scoring.getHighScore());
    }
}
