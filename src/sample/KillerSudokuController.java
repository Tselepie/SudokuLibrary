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

public class KillerSudokuController extends Controller implements Initializable {

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

    private ResourceBundle bundle;
    private Locale locale;

    public int width = 77;
    public int playerSelectCol = -1;
    public int playerSelectRow;
    public int number;

    KillerSudokuBoard killerSudokuBoard;

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
        killerSudokuBoard = new KillerSudokuBoard();
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

    public void language_EN_4() { loadLanguage(""); }

    public void language_EL_4() { loadLanguage("el"); }

    public void checkText(String text) {
        stageNumber.setText(text);
    }

    public void drawOnCanvas(GraphicsContext context) {
        context.clearRect(0, 0, 720, 720);
        int[][] initial = killerSudokuBoard.getInitial();
        int[][] player = killerSudokuBoard.getPlayersBoard();
        int[][] mistakes = killerSudokuBoard.getMistakes();
        int[][] coloures=killerSudokuBoard.getColouredBoard();
        int position_y;
        int position_x;


        for(int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                position_x = col * 79 + 2;
                position_y = row * 79 + 2;
                if (coloures[row][col] == 1) {
                    context.setFill(Color.rgb(255,255,204));
                    context.fillRect(position_x, position_y, width, width);
                } else if (coloures[row][col] == 2) {
                    context.setFill(Color.LIGHTGREEN);
                    context.fillRect(position_x, position_y, width, width);
                }else if (coloures[row][col] == 3) {
                    context.setFill(Color.LIGHTBLUE);
                    context.fillRect(position_x, position_y, width, width);
                } else if (coloures[row][col] == 4) {
                    context.setFill(Color.LIGHTPINK);
                    context.fillRect(position_x, position_y, width, width);
                } else if (coloures[row][col] == 5) {
                    context.setFill(Color.rgb(221,160,221));
                    context.fillRect(position_x, position_y, width, width);
                } else if (coloures[row][col] == 6) {
                    context.setFill(Color.rgb(255, 220, 79));
                    context.fillRect(position_x, position_y, width, width);
                }
            }
        }

        if(playerSelectCol != -1) {
            position_y = playerSelectRow * 79 + 2;
            for (int col = 0; col < 9; col++) {
                position_x = col * 79 + 2;
                context.setLineWidth(3);
                context.setStroke(Color.DARKGREY);
                context.strokeRect(position_x,position_y,width,width);
            }

            position_x = playerSelectCol * 79 + 2;
            for (int row = 0; row < 9; row++) {
                position_y = row * 79 + 2;
                context.setLineWidth(3);
                context.setStroke(Color.DARKGREY);
                context.strokeRect(position_x,position_y,width,width);
            }

            int selectedRow = playerSelectRow - playerSelectRow % 3;
            int selectedCol = playerSelectCol - playerSelectCol % 3;
            for (int row = selectedRow; row < selectedRow + 3; row++) {
                for (int col = selectedCol; col < selectedCol + 3; col++) {
                    position_x = col * 79 + 2;
                    position_y = row * 79 + 2;
                    context.setLineWidth(3);
                    context.setStroke(Color.GREY);
                    context.strokeRect(position_x,position_y,width,width);
                }
            }
            position_x=playerSelectCol * 79 + 2;
            position_y=playerSelectRow * 79 + 2;

            if (coloures[playerSelectRow][playerSelectCol] == 1) {
                context.setFill(Color.rgb(255, 255, 77));
                context.fillRect(position_x, position_y, width, width);
            } else if (coloures[playerSelectRow][playerSelectCol] == 2) {
                context.setFill(Color.rgb(50, 205, 50));
                context.fillRect(position_x, position_y, width, width);
            } else if (coloures[playerSelectRow][playerSelectCol] == 3) {
                context.setFill(Color.rgb(30,144,255));
                context.fillRect(position_x, position_y, width, width);
            } else if (coloures[playerSelectRow][playerSelectCol] == 4) {
                context.setFill(Color.rgb(255, 105, 180));
                context.fillRect(position_x, position_y, width, width);
            } else if (coloures[playerSelectRow][playerSelectCol] == 5) {
                context.setFill(Color.MEDIUMPURPLE);
                context.fillRect(position_x, position_y, width, width);
            } else if (coloures[playerSelectRow][playerSelectCol] == 6) {
                context.setFill(Color.rgb(255,165,0));
                context.fillRect(position_x, position_y, width, width);
            }
        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                position_x = col * 79 + 30;
                position_y = row * 79 + 50;
                context.setFill(Color.BLACK);
                context.setFont(Font.font("Comic Sans MS", 20));
                if (initial[row][col] != 0)
                    context.fillText(initial[row][col] + "", position_x-20, position_y-20);
            }
        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                position_x = col * 79 + 30;
                position_y = row * 79 + 50;
                context.setFont(Font.font("Comic Sans MS", 35));
                if (player[row][col] != 0) {
                    context.setFill(Color.BLACK);
                    context.fillText(player[row][col] + "", position_x, position_y);
                }
                if(mistakes[row][col] != 0) {
                    context.setFill(Color.RED);
                    context.fillText(mistakes[row][col] + "", position_x, position_y);
                }
            }
        }

        if(killerSudokuBoard.gameEnd()) {
            stageCompleted.setVisible(true);
        }
    }

    public void buttonOne() {
        if(playerSelectCol != -1) {
            killerSudokuBoard.modifyPlayer(1, playerSelectRow, playerSelectCol);
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonTwo() {
        if(playerSelectCol != -1) {
            killerSudokuBoard.modifyPlayer(2, playerSelectRow, playerSelectCol);
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonThree() {
        if(playerSelectCol != -1) {
            killerSudokuBoard.modifyPlayer(3, playerSelectRow, playerSelectCol);
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonFour() {
        if(playerSelectCol != -1) {
            killerSudokuBoard.modifyPlayer(4, playerSelectRow, playerSelectCol);
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonFive() {
        if(playerSelectCol != -1) {
            killerSudokuBoard.modifyPlayer(5, playerSelectRow, playerSelectCol);
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonSix() {
        if(playerSelectCol != -1) {
            killerSudokuBoard.modifyPlayer(6, playerSelectRow, playerSelectCol);
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonSeven() {
        if(playerSelectCol != -1) {
            killerSudokuBoard.modifyPlayer(7, playerSelectRow, playerSelectCol);
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonEight() {
        if(playerSelectCol != -1) {
            killerSudokuBoard.modifyPlayer(8, playerSelectRow, playerSelectCol);
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonNine() {
        if(playerSelectCol != -1) {
            killerSudokuBoard.modifyPlayer(9, playerSelectRow, playerSelectCol);
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
        killerSudokuBoard.newGame();
        stageNumber.setText("kstage_" + number);
        killerSudokuBoard.getInitialStage("kstage_" + number);
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
        killerSudokuBoard.newGame();
        stageNumber.setText("kstage_" + number);
        killerSudokuBoard.getInitialStage("kstage_" + number);
        clickToStartButton.setVisible(false);
        clickToStartButton.setDisable(true);

        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void backToStageSelect() { createStage("KillerStageSelect.fxml", backToStageSelect, false, 720, 720); }
}
