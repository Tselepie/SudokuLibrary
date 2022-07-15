package sample;

import java.util.ArrayList;

public class KillerSudokuBoard extends SudokuBoard {
    private int[][] colouredBoard;

    ArrayList<AreasKillerSudoku> areas;

    public KillerSudokuBoard() {
        super();
        colouredBoard = new int[9][9];
        areas = new ArrayList<>();
    }

    /**This method creates the initial board and returns it.*/
    public int[][] getInitialStage(String stage) {
        loadKillerBoard(stage);
        return setInitialStage();
    }

    /**This method creates a board that contains the summaries of the areas.*/
    public int[][] setInitialStage() {
        for (AreasKillerSudoku temp : areas) {
            MyPoint temp2 = temp.GetIndexes().get(0);
            initial[temp2.getX()][temp2.getY()] = temp.GetSum();
        }
        return initial;
    }

    /**This method checks if the answer is correct according to the classic sudoku rules.*/
    public boolean validMove(int value, int rowSelected, int colSelected) {
        for (int row = 0; row < 9; row++) {
            if (value == player[row][colSelected]) return false;
        }
        for (int col = 0; col < 9; col++) {
            if (value == player[rowSelected][col]) return false;
        }
        rowSelected = rowSelected - rowSelected % 3;
        colSelected = colSelected - colSelected % 3;
        for (int row = rowSelected; row < rowSelected + 3; row++) {
            for (int col = colSelected; col < colSelected + 3; col++) {
                if (value == player[row][col]) return false;
            }
        }
        return true;
    }

    /**This method places the player's answer in the players board if the answer is correct*/
    public void modifyPlayer(int value, int row, int col) {
        if (player[row][col] != value) {

            if (this.validMove(value, row, col) && ValidForKiller(row, col, value)) {
                player[row][col] = value;
                mistakes[row][col] = 0;
            } else {
                mistakes[row][col] = value;
                player[row][col] = 0;
            }
        }
    }

    public int[][] getInitial() {
        return initial;
    }

    public int[][] getPlayersBoard() {
        return player;
    }

    public int[][] getColouredBoard() {
        return colouredBoard;
    }


    /**This method creates a board that contains the colours.*/
    public void loadKillerBoard(String filename) {

        FileHandler file = new FileHandler();
        file.KillerSudokuFile(filename);
        areas = file.getAreas();
        for (AreasKillerSudoku temp : areas) {
            for (MyPoint temp2 : temp.GetIndexes()) {
                colouredBoard[temp2.getX()][temp2.getY()] = temp.GetColour();
            }
        }
    }


    /**returns the area that contains the specific box*/
    public AreasKillerSudoku getArea(int row, int column) {
        for (AreasKillerSudoku temp : areas) {
            for (MyPoint temp1 : temp.GetIndexes()) {
                if (temp1.getX() == row && temp1.getY() == column) {
                    return temp;
                }
            }
        }
        return null;
    }

    /**
     * we check if the rest cells of the area are full and then
     * returns true if the sum of the whole area is correct
     **/
    public boolean checkForSumInArea(int col, int row, int value) {
        AreasKillerSudoku area = getArea(row, col);
        if (checkRestCells(area,row,col)) {
            if (playersSum(area)+value == area.GetSum()) return true;
        }
        return false;
    }

    /**
     * returns the summary of the numbers the player has inputted
     **/
    private int playersSum(AreasKillerSudoku area) {
        int sum = 0;
        for (MyPoint temp : area.GetIndexes()) {
            sum += player[temp.getX()][temp.getY()];

        }
        return sum;
    }

    /**This method returns true if the cells of an area are full and their sum is correct*/
    private boolean ValidForKiller(int row, int col, int value) {
        int old = player[row][col]; // in case the cell was already fill with another number
        AreasKillerSudoku area1 = this.getArea(row, col);
        checkRestCells(area1, row, col);

        if (CellsAreMoreThanOne(area1)) {
            if (playersSum(area1) - old + value > area1.GetSum() ) {
                return false;
            }
            if (checkRestCells(area1,row,col) && !checkForSumInArea(col, row, value)) return false;
            return true;
        } else {
            if (playersSum(area1) - old + value == area1.GetSum()) {
                return true;
            }
            return false;
        }
    }

    /**
     * This method returns true if there is more than one cell in an area
     **/
    private boolean CellsAreMoreThanOne(AreasKillerSudoku area) {
        int cells = 0;
        for (MyPoint temp : area.GetIndexes()) {
            cells += 1;
        }
        if (cells > 1) return true;
        return false;
    }

    /**
     * This method takes the cells of the area the player wants to input his answer and
     * checks the rest of the cells are full
     * returns true if the rest of the cells are full
     **/
    private boolean checkRestCells(AreasKillerSudoku area, int row, int col) {

        for (MyPoint temp : area.GetIndexes()) {
            if (!(temp.getX()==row && temp.getY()==col)) {
                if (player[temp.getX()][temp.getY()] == 0) return false;
            }
        }
        return true;
    }

    /**This method returns true if an area is full
     */
    private boolean checkIfAreaIsFull(AreasKillerSudoku area) {
        for (MyPoint temp : area.GetIndexes()) {
            if (player[temp.getX()][temp.getY()] == 0) return false;
        }
        return true;
    }

    public boolean gameEnd() {
        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                if(player[row][col] == 0)
                    return false;
            }
        }
        return true;
    }
}

