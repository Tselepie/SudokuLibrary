package sample;

public class SudokuBoard extends ChooseGame {

    private char[][] wordoku;
    private char[][] wordokuInitial;
    private char[][] wordokuMistakes;

    protected int[][] mistakes;
    protected int[][] player;
    protected int[][] initial;

    FileHandler inputFile = new FileHandler();

    public SudokuBoard() {
        initial = new int[9][9];
        mistakes = new int[9][9];
        player = new int[9][9];
        wordoku = new char[9][9];
        wordokuInitial = new char[9][9];
        wordokuMistakes = new char[9][9];
    }

    public int[][] getInitialStage(String stage) {
        loadBoard(stage);
        return initial;
    }

    public char[][] getWordokuStage() {
        return changeIntToLetters(initial);
    }

    public char[][] getWordokuMistakes() {
        return changeIntToLetters(mistakes);
    }

    public char[][] getWordokuPlayer() {
        return changeIntToLetters(player);
    }

    public int[][] getMistakes() {
        return mistakes;
    }

    public int[][] getInitial() {
        return initial;
    }

    public int[][] getPlayer() {
        return player;
    }

    public void modifyPlayer(int value, int row, int col) {
        if(initial[row][col] == 0) {
            if (validMove(value, row, col)) {
                player[row][col] = value;
                mistakes[row][col] = 0;
                wordoku = changeIntToLetters(player);
                wordokuMistakes[row][col]=' ';
            }
            else if (mistakes[row][col] == value|| player[row][col] == value) {
                mistakes[row][col] = 0;
                player[row][col] = 0;
                wordoku[row][col]=' ';
                wordokuMistakes[row][col]=' ';
            }
            else {
                mistakes[row][col] = value;
                player[row][col] = 0;
                wordoku[row][col]=' ';
                wordokuMistakes=changeIntToLetters(mistakes);
            }
        }
    }

    public char[][] changeIntToLetters(int[][] numbers){
        char[][] letters =new char[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (numbers[row][col] == 1)
                    letters[row][col] = 'A';
                if (numbers[row][col] == 2)
                    letters[row][col] = 'B';
                if (numbers[row][col] == 3)
                    letters[row][col] = 'C';
                if (numbers[row][col] == 4)
                    letters[row][col] = 'D';
                if (numbers[row][col] == 5)
                    letters[row][col] = 'E';
                if (numbers[row][col] == 6)
                    letters[row][col] = 'F';
                if (numbers[row][col] == 7)
                    letters[row][col] = 'G';
                if (numbers[row][col] == 8)
                    letters[row][col] = 'H';
                if (numbers[row][col] == 9)
                    letters[row][col] = 'I';
            }
        }
        return letters;
    }

    public boolean validMove(int value, int rowSelected, int colSelected) {
        for(int row = 0; row < 9; row++) {
            if(value == initial[row][colSelected] || value == player[row][colSelected])
                return false;
        }

        for(int col = 0; col < 9; col++) {
            if(value == initial[rowSelected][col] || value == player[rowSelected][col])
                return false;
        }

        rowSelected = rowSelected - rowSelected % 3;
        colSelected = colSelected - colSelected % 3;
        for(int row = rowSelected; row < rowSelected + 3; row++) {
            for(int col = colSelected; col < colSelected + 3; col++) {
                if(value == initial[row][col] || value == player[row][col])
                    return false;
            }
        }
        return true;
    }

    public void newGame() {
        initial = new int[9][9];

        mistakes = new int[9][9];

        player = new int[9][9];
    }

    public boolean gameEnd() {
        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                if(player[row][col] == 0 && initial[row][col] == 0)
                    return false;
            }
        }
        return true;
    }

    //places numbers in board
    public void loadBoard(String file){
        inputFile.classicSudokuFile(file);
        int trace = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                initial[i][j] = inputFile.getNumbers(trace);
                trace++;
            }
        }

    }

    public int getNumbers(int i){
        return inputFile.getNumbers(i);
    }
}
