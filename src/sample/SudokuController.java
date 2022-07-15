package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class SudokuController extends Controller implements Initializable {

    @FXML
    private Canvas canvas;

    @FXML
    public Label stageNumber;

    @FXML
    private Label stageCompleted;

    @FXML
    private Button clickToStartButton;

    @FXML
    private Button backToStageSelect;

    @FXML
    private Menu menuOptions;

    @FXML
    private MenuItem menuItemExit;

    @FXML
    private Button number_one;

    @FXML
    private Button number_two;

    @FXML
    private Button number_three;

    @FXML
    private Button number_four;

    @FXML
    private Button number_five;

    @FXML
    private Button number_six;

    @FXML
    private Button number_seven;

    @FXML
    private Button number_eight;

    @FXML
    private Button number_nine;

    private ResourceBundle bundle;
    private Locale locale;
    private boolean help = false;
    private boolean letters = false;

    public int width = 77;
    public int playerSelectCol = -1;
    public int playerSelectRow;
    public int number;

    SudokuBoard sudokuBoard;

    private void loadLanguage(String language) {
        locale = new Locale(language);
        bundle = ResourceBundle.getBundle("sample.Languages", locale);

        clickToStartButton.setText(bundle.getString("clickToStartButton"));
        menuOptions.setText(bundle.getString("menuOptions"));
        menuItemExit.setText(bundle.getString("menuItemExit"));
        stageCompleted.setText(bundle.getString("stageCompleted"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sudokuBoard = new SudokuBoard();
        clickToStartButton.setOnAction(event -> {
            clickToStartButton.setVisible(false);
            clickToStartButton.setDisable(true);

            drawOnCanvas(canvas.getGraphicsContext2D());
        });
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                int mouse_x = (int) mouseEvent.getX();
                int mouse_y = (int) mouseEvent.getY();
                clickToStartButton.setVisible(false);
                clickToStartButton.setDisable(true);
                playerSelectCol = mouse_x / 79;
                playerSelectRow = mouse_y / 79;

                drawOnCanvas(canvas.getGraphicsContext2D());
            }
        });
    }

    public void language_EN_3() { loadLanguage(""); }

    public void language_EL_3() { loadLanguage("el"); }

    public void checkText(String text) {
        stageNumber.setText(text);
    }

    public void wordokuPressed(){
        letters = !letters;
        if (letters) {
            number_one.setText("A");
            number_two.setText("B");
            number_three.setText("C");
            number_four.setText("D");
            number_five.setText("E");
            number_six.setText("F");
            number_seven.setText("G");
            number_eight.setText("H");
            number_nine.setText("I");
        }else{
            number_one.setText("1");
            number_two.setText("2");
            number_three.setText("3");
            number_four.setText("4");
            number_five.setText("5");
            number_six.setText("6");
            number_seven.setText("7");
            number_eight.setText("8");
            number_nine.setText("9");
        }
        drawOnCanvas(canvas.getGraphicsContext2D());

    }

    public void drawOnCanvas(GraphicsContext context) {
        context.clearRect(0, 0, 720, 720);
        int[][] initial = sudokuBoard.getInitial();
        int[][] player = sudokuBoard.getPlayer();
        int[][] mistakes = sudokuBoard.getMistakes();
        char[][] wordokuInitial = sudokuBoard.getWordokuStage();
        char[][] wordoku = sudokuBoard.getWordokuPlayer();
        char[][] wordokuMistakes = sudokuBoard.getWordokuMistakes();
        int position_y;
        int position_x;

        for(int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                position_x = col * 79 + 2;
                position_y = row * 79 + 2;
                context.setFill(Color.WHITE);
                context.fillRect(position_x, position_y, width, width);
            }
        }

        if(playerSelectCol != -1) {
            position_y = playerSelectRow * 79 + 2;
            for (int col = 0; col < 9; col++) {
                position_x = col * 79 + 2;
                context.setFill(Color.rgb(224, 224, 224));
                context.fillRect(position_x, position_y, width, width);
            }

            position_x = playerSelectCol * 79 + 2;
            for (int row = 0; row < 9; row++) {
                position_y = row * 79 + 2;
                context.setFill(Color.rgb(224, 224, 224));
                context.fillRect(position_x, position_y, width, width);
            }

            int selectedRow = playerSelectRow - playerSelectRow % 3;
            int selectedCol = playerSelectCol - playerSelectCol % 3;
            for (int row = selectedRow; row < selectedRow + 3; row++) {
                for (int col = selectedCol; col < selectedCol + 3; col++) {
                    position_x = col * 79 + 2;
                    position_y = row * 79 + 2;
                    context.setFill(Color.rgb(190, 190, 190));
                    context.fillRect(position_x, position_y, width, width);
                }
            }

            context.setFill(Color.rgb(160, 160, 160));
            context.fillRect(playerSelectCol * 79 + 2, playerSelectRow * 79 + 2, width, width);
        }
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                position_x = col * 79 + 30;
                position_y = row * 79 + 50;
                context.setFill(Color.rgb(40, 40, 40));
                context.setFont(Font.font("Comic Sans MS", 35));
                if (initial[row][col] != 0 && !letters)
                    context.fillText(initial[row][col] + "", position_x, position_y);
                if(initial[row][col] != 0 && letters) {
                    context.fillText(wordokuInitial[row][col] + "", position_x, position_y);
                }
            }
        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                position_x = col * 79 + 30;
                position_y = row * 79 + 50;
                context.setFont(Font.font("Comic Sans MS", 35));
                if(!letters) {
                    if (player[row][col] != 0) {
                        context.setFill(Color.LIGHTSKYBLUE);
                        context.fillText(player[row][col] + "", position_x, position_y);
                    }
                    if (mistakes[row][col] != 0) {
                        context.setFill(Color.RED);
                        context.fillText(mistakes[row][col] + "", position_x, position_y);
                    }
                }
                else {
                    if (player[row][col] != 0) {
                        context.setFill(Color.LIGHTSKYBLUE);
                        context.fillText(wordoku[row][col] + "", position_x, position_y);
                    }
                    if (mistakes[row][col] != 0) {
                        context.setFill(Color.RED);
                        context.fillText(wordokuMistakes[row][col] + "", position_x, position_y);
                    }
                }
            }
        }

        if(help) {
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    context.setFont(Font.font("Comic Sans MS", 15));
                    if (initial[row][col] == 0 && player[row][col] == 0 && mistakes[row][col] == 0) {
                        for(int value = 0; value < 9; value++) {
                            position_x = col * 79 + 5*(2 + (5 * (value % 3)));
                            position_y = row * 79 + 5*(4 + (5 * (value / 3)));
                            if(sudokuBoard.validMove(value + 1, row, col)) {
                                context.setFill(Color.rgb(204,102,0));
                                context.fillText(value + 1 + "", position_x, position_y);
                            }
                        }
                    }
                }
            }
        }

        if(sudokuBoard.gameEnd()) {
            stageCompleted.setVisible(true);
        }
    }

    public void helpPressed() {
        help = !help;

        clickToStartButton.setVisible(false);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonOne() {
        if(playerSelectCol != -1) {
            sudokuBoard.modifyPlayer(1, playerSelectRow, playerSelectCol);
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonTwo() {
        if(playerSelectCol != -1) {
            sudokuBoard.modifyPlayer(2, playerSelectRow, playerSelectCol);
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonThree() {
        if(playerSelectCol != -1) {
            sudokuBoard.modifyPlayer(3, playerSelectRow, playerSelectCol);
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonFour() {
        if(playerSelectCol != -1) {
            sudokuBoard.modifyPlayer(4, playerSelectRow, playerSelectCol);
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonFive() {
        if(playerSelectCol != -1) {
            sudokuBoard.modifyPlayer(5, playerSelectRow, playerSelectCol);
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonSix() {
        if(playerSelectCol != -1) {
            sudokuBoard.modifyPlayer(6, playerSelectRow, playerSelectCol);
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonSeven() {
        if(playerSelectCol != -1) {
            sudokuBoard.modifyPlayer(7, playerSelectRow, playerSelectCol);
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonEight() {
        if(playerSelectCol != -1) {
            sudokuBoard.modifyPlayer(8, playerSelectRow, playerSelectCol);
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonNine() {
        if(playerSelectCol != -1) {
            sudokuBoard.modifyPlayer(9, playerSelectRow, playerSelectCol);
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void nextStage() {
        String s = stageNumber.getText();

        s = s.replaceAll("[^\\d]", " ");
        s = s.trim();
        s = s.replaceAll(" +", " ");
        number = Integer.parseInt(s, 10);
        if(s.equals("10")) {
            backToStageSelect();
            return;
        }
        number++;
        sudokuBoard.newGame();
        stageNumber.setText("stage_" + number);
        sudokuBoard.getInitialStage("stage_" + number);
        clickToStartButton.setVisible(false);
        clickToStartButton.setDisable(true);

        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void previousStage() {
        String s = stageNumber.getText();

        s = s.replaceAll("[^\\d]", " ");
        s = s.trim();
        s = s.replaceAll(" +", " ");
        number = Integer.parseInt(s, 10);
        if(s.equals("1")) {
            backToStageSelect();
            return;
        }
        number--;
        sudokuBoard.newGame();
        stageNumber.setText("stage_" + number);
        sudokuBoard.getInitialStage("stage_" + number);
        clickToStartButton.setVisible(false);
        clickToStartButton.setDisable(true);

        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void backToStageSelect() { createStage("StageSelect.fxml", backToStageSelect, false, 720, 720); }
}
