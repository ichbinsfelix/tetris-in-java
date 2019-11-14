import tetris.Game;
import tetris.gui.GUI;

public class Tetris {
    public static final int GAME_WIDTH = 20;
    public static final int GAME_HEIGHT = 20;

    public static void main(String[] args) {

        Game game = new Game(GAME_WIDTH, GAME_HEIGHT, new GUI(GAME_WIDTH, GAME_HEIGHT));
        game.run();

    }
}
