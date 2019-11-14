package figures;

import tetris.Field;

import java.util.Random;

public class RandomFigureFactory {

    public static RandomFigureFactory getInstance() {
        return instance;
    } //Get the only object available

    public Figure generateRandomFigureOnTop(Field field) {
        Random random = new Random();
        int figureChoice = random.nextInt(6);
        int positionX = field.getWidth() / 2;
        if(figureChoice == 0) {
            return new LFigure(positionX, field.getHeight() - 2);
        }
        else if (figureChoice == 1) {
            return new TFigure(positionX, field.getHeight()-1);
        }
        else if (figureChoice == 2) {
            return new IFigure(positionX, field.getHeight()-2);
        }
        else if (figureChoice == 3) {
            return new JFigure(positionX, field.getHeight()-2);
        }

        return new ZFigure(positionX, field.getHeight() - 1);
    }

    private RandomFigureFactory() {}    //make the constructor private so that this class cannot be instantiated

    private static RandomFigureFactory instance = new RandomFigureFactory(); // singleton design pattern
}
