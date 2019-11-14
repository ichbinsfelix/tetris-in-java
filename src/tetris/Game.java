package tetris;

import figures.*;
import tetris.gui.ActionEvent;
import tetris.gui.GUI;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private GUI gui;
    private FigureController figureController;
    private Figure activeFigure;
    private Field field;

    public Game(int width, int height, GUI gui) {
        this.gui = gui;
        figureController = new FigureController(this);
        //this.gui.setActionHandler(figureController);
        field = new Field(width, height);
    }

    public void figureLanded() {
        field.addBlocks(activeFigure.getBlocks());
        field.removeFullRows();
        createFigure();
    }

    public Field getField() {
        return field;
    }

    public Figure getActiveFigure() {
        return activeFigure;
    }

    private void start() {
        createFigure();
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
            updateGUI();
        }
    }

    private void updateGUI() {
        gui.clear();
        if(activeFigure != null) {
            activeFigure.draw(gui);
        }
        gui.drawBlocks(field.getBlocks());
    }

    public void stop() {
        gui.setActionHandler(null);
        activeFigure = null;
        field.removeAllBlocks();
    }
}
