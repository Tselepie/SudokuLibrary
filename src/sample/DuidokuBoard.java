package sample;

import java.util.concurrent.ThreadLocalRandom;

public class DuidokuBoard {
    private int[][] mistakes;
    private int[][] computer;
    private int[][] player;

    public int playerCounter;
    public int computerCounter;


    public DuidokuBoard() {
        computer = new int[9][9];

        mistakes = new int[9][9];

        player = new int[9][9];
    }

    public int[][] getMistakes() {
        return mistakes;
    }

    public int[][] getComputer() {
        return computer;
    }

    public int[][] getPlayer() {
        return player;
    }

    public boolean modifyPlayer(int value, int row, int col) {
        if(computer[row][col] == 0 && player[row][col] == 0) {
            if (validMove(value, row, col)) {
                player[row][col] = value;
                mistakes[row][col] = 0;
                playerCounter++;
                return true;
            }
            else {
                mistakes[row][col] = value;
                player[row][col] = 0;
            }
        }
        return false;
    }

    public void modifyComputerLevel_1(int row, int col) {
        int r;
        int c;
        int randomValue;

        do {

            //Generate random Integers in range 0 to 3
            int randomRow = ThreadLocalRandom.current().nextInt(0, 4);
            int randomCol = ThreadLocalRandom.current().nextInt(0, 4);
            r = randomRow;
            c = randomCol;

            //Generate random Integers in range 1 to 4
            randomValue = ThreadLocalRandom.current().nextInt(1, 5);

        }while (computer[r][c] > 0 || player[r][c] > 0 || mistakes[r][c] == -1 || !validMove(randomValue, r, c));

        computer[r][c] = randomValue;
        computerCounter++;
    }

    public void checkEveryBox() {
        int counter = 0;

        for(int row = 0; row < 4; row++) {
            for(int col = 0; col < 4; col++) {
                for(int val = 1; val < 5; val++) {
                    if(computer[row][col] == 0 && player[row][col] == 0) {
                        if (!validMove(val, row, col))
                            counter++;
                    }
                }
                if(counter == 4) {
                    computer[row][col] = -1;
                    mistakes[row][col] = -1;
                    player[row][col] = -1;
                }
                counter = 0;
            }
        }
    }

    public boolean validMove(int value, int rowSelected, int colSelected) {
        for(int row = 0; row < 4; row++) {
            if(value == computer[row][colSelected] || value == player[row][colSelected])
                return false;
        }

        for(int col = 0; col < 4; col++) {
            if(value == computer[rowSelected][col] || value == player[rowSelected][col])
                return false;
        }

        rowSelected = rowSelected - rowSelected % 2;
        colSelected = colSelected - colSelected % 2;
        for(int row = rowSelected; row < rowSelected + 2; row++) {
            for(int col = colSelected; col < colSelected + 2; col++) {
                if(value == computer[row][col] || value == player[row][col])
                    return false;
            }
        }
        return true;
    }

    public void newGame() {
        computer = new int[9][9];

        mistakes = new int[9][9];

        player = new int[9][9];
    }

    public boolean gameEnd() {
        for(int row = 0; row < 4; row++) {
            for(int col = 0; col < 4; col++) {
                if(player[row][col] == 0 && computer[row][col] == 0)
                    return false;
            }
        }
        return true;
    }
}
