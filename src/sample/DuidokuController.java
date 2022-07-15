package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class DuidokuController extends Controller implements Initializable {

    @FXML
    private Canvas canvas;

    @FXML
    private Button clickToStartButton;

    @FXML
    private Button backToChooseGame;

    @FXML
    private Button playAgainButton;

    @FXML
    private Label victoryLabel;

    @FXML
    private Label defeatLabel;

    private ResourceBundle bundle;
    private Locale locale;

    public int width = 80;
    public int playerSelectCol = -1;
    public int playerSelectRow;

    DuidokuBoard duidokuBoard;

    private void loadLanguage(String language) {
        locale = new Locale(language);
        bundle = ResourceBundle.getBundle("sample.Languages", locale);

        clickToStartButton.setText(bundle.getString("clickToStartButton"));
        victoryLabel.setText(bundle.getString("victoryLabel"));
        defeatLabel.setText(bundle.getString("defeatLabel"));
        playAgainButton.setText(bundle.getString("playAgainButton"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        duidokuBoard = new DuidokuBoard();
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
                playerSelectCol = mouse_x / 85;
                playerSelectRow = mouse_y / 85;

                drawOnCanvas(canvas.getGraphicsContext2D());
            }
        });
    }

    public void language_EN_4() { loadLanguage(""); }

    public void language_EL_4() { loadLanguage("el"); }

    public void drawOnCanvas(GraphicsContext context) {
        context.clearRect(0, 0, 360, 360);
        int[][] computer = duidokuBoard.getComputer();
        int[][] player = duidokuBoard.getPlayer();
        int[][] mistakes = duidokuBoard.getMistakes();
        int position_y;
        int position_x;

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                position_x = col * 85 + 15;
                position_y = row * 85 + 20;
                context.setFill(Color.WHITE);
                context.fillRect(position_x, position_y, width, width);
            }
        }

        if(playerSelectCol != -1) {

            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    position_x = col * 85 + 15;
                    position_y = row * 85 + 20;
                    if (mistakes[row][col] == -1) {
                        context.setFill(Color.BLACK);
                        context.fillRect(position_x, position_y, width, width);
                    }
                }
            }

            context.setLineWidth(5);
            context.setStroke(Color.BLACK);
            context.strokeRect(playerSelectCol * 85 + 15, playerSelectRow * 85 + 20, width, width);
        }

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                position_x = col * 85 + 42;
                position_y = row * 85 + 80;
                context.setFont(Font.font("Comic Sans MS", 50));
                if (player[row][col] > 0) {
                    context.setFill(Color.LIGHTSKYBLUE);
                    context.fillText(player[row][col] + "", position_x, position_y);
                }
                if (computer[row][col] > 0) {
                    context.setFill(Color.BLACK);
                    context.fillText(computer[row][col] + "", position_x, position_y);
                }
                if (mistakes[row][col] > 0) {
                    context.setFill(Color.RED);
                    context.fillText(mistakes[row][col] + "", position_x, position_y);
                }
            }
        }

        if(duidokuBoard.gameEnd()) {
            if(duidokuBoard.computerCounter >= duidokuBoard.playerCounter)
                defeatLabel.setVisible(true);
            else
                victoryLabel.setVisible(true);
            playAgainButton.setVisible(true);
            playAgainButton.setOnAction(event -> {
                defeatLabel.setVisible(false);
                victoryLabel.setVisible(false);
                playAgainButton.setVisible(false);
                duidokuBoard.newGame();

                drawOnCanvas(canvas.getGraphicsContext2D());
            });
        }
    }

    public void buttonOne() {
        if(playerSelectCol != -1) {
            duidokuBoard.checkEveryBox();
            if(duidokuBoard.modifyPlayer(1, playerSelectRow, playerSelectCol)) {
                duidokuBoard.modifyComputerLevel_1(playerSelectRow, playerSelectCol);
                duidokuBoard.checkEveryBox();
            }
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonTwo() {
        if(playerSelectCol != -1) {
            duidokuBoard.checkEveryBox();
            if(duidokuBoard.modifyPlayer(2, playerSelectRow, playerSelectCol)) {
                duidokuBoard.modifyComputerLevel_1(playerSelectRow, playerSelectCol);
                duidokuBoard.checkEveryBox();
            }
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonThree() {
        if(playerSelectCol != -1) {
            duidokuBoard.checkEveryBox();
            if(duidokuBoard.modifyPlayer(3, playerSelectRow, playerSelectCol)) {
                duidokuBoard.modifyComputerLevel_1(playerSelectRow, playerSelectCol);
                duidokuBoard.checkEveryBox();
            }
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void buttonFour() {
        if(playerSelectCol != -1) {
            duidokuBoard.checkEveryBox();
            if(duidokuBoard.modifyPlayer(4, playerSelectRow, playerSelectCol)) {
                duidokuBoard.modifyComputerLevel_1(playerSelectRow, playerSelectCol);
                duidokuBoard.checkEveryBox();
            }
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void backToGameSelect() { createStage("ChooseGame.fxml", backToChooseGame, false, 600, 700); }
}
