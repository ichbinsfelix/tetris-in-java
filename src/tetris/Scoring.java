package tetris;

import java.io.*;

public class Scoring {

    private static String HIGH_SCORE_FILE = "highscore.txt";
    private static final int LINES_PER_LEVEL = 10;
    private static final int[] SCORE_REWARDS = new int[]{40, 100, 300, 1200};

    private int highScore = 0;
    private int score = 0;
    private int removedRows = 0;

    public Scoring() {
        loadHighScore();
    }

    public void updateScore(int nrows) {
        if(nrows == 0) {
            return;
        }

        int index = nrows-1;
        score += SCORE_REWARDS[index];
        removedRows += nrows;
    }

    public void updateHighScore() {
        if (score > highScore) {
            highScore = score;
            saveHighScore();
        }
    }

    public void reset() {
        score = 0;
        removedRows = 0;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return 1 + removedRows / LINES_PER_LEVEL;
    }

    private void loadHighScore () {
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(HIGH_SCORE_FILE));
            String line = reader.readLine();
            highScore = Integer.parseInt(line);
        } catch (FileNotFoundException e) {
            System.out.println("WARNING: highscore file doesn't exist yet!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            System.out.println("ERROR: highscore could not be parsed from file!");
        }
        finally {
            try {
                if(reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveHighScore () {
        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter(new FileWriter(HIGH_SCORE_FILE));
            writer.write(Integer.toString(highScore));
            writer.newLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
