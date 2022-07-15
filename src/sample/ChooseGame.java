package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ChooseGame extends Controller{

    @FXML
    public Label stageSelected;

    @FXML
    private Button stageSelectButton;

    @FXML
    private Button sudokuOption;

    @FXML
    private Button killerSudokuOption;

    @FXML
    private Button doidokuOption;

    @FXML
    private Button goBackM;


    /*
    These methods below represent the available choices in the CHOOSE GAME stage
     */

    //Creates the Stage Select stage for the classical sudoku game opening the StageSelect.fxml file
    public void sudokuStageSelect() { createStage("StageSelect.fxml", sudokuOption, false, 720, 720); }

    public void killerSudokuStageSelect() { createStage("KillerStageSelect.fxml", killerSudokuOption, false, 720, 720); }

    //
    public void doidokuStageSelect() { createStage("DuidokuGameView.fxml", doidokuOption, false, 600, 600); }

    //The BACK button in the STAGE SELECT stage
    public void backToMenu() { createStage("Menu.fxml", goBackM, false, 600, 700); }

    //The BACK button in the CHOOSE GAME stage
    public void backToChooseGame() { createStage("ChooseGame.fxml", stageSelectButton, false, 600, 700); }

    //Creates the SUDOKU stage of the selected stage
    public void stageSetUp(String stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SudokuGameView.fxml"));
        try {
            Parent root = fxmlLoader.load();

            SudokuController sudokuController = fxmlLoader.getController();
            sudokuController.checkText(stage);
            sudokuController.sudokuBoard.getInitialStage(stage);//Sends the name of the selected stage so that the correct stage txt file will be opened
            Stage sudokuStage = (Stage) stageSelectButton.getScene().getWindow();
            Scene sudokuScene = new Scene(root, 1100, 720);
            sudokuStage.setScene(sudokuScene);
            sudokuStage.show();
        }
        catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public void killerStageSetUp(String stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KillerSudokuGameView.fxml"));
        try {
            Parent root = fxmlLoader.load();

            KillerSudokuController killerSudokuController = fxmlLoader.getController();
            killerSudokuController.checkText(stage);
            killerSudokuController.killerSudokuBoard.getInitialStage(stage);
            //Sends the name of the selected stage so that the correct stage txt file will be opened
            Stage killerSudokuStage = (Stage) stageSelectButton.getScene().getWindow();
            Scene killerSudokuScene = new Scene(root, 1100, 720);
            killerSudokuStage.setScene(killerSudokuScene);
            killerSudokuStage.show();
        }
        catch (Exception e2) {
            System.out.println(e2);
        }
    }

    /*
    All the methods below are used when a stage image is pressed and create the SUDOKU stage opening the
    txt file in the SudokuMaps folder with the name of the selected stage
     */
    @FXML
    public void stage_oneClicked() {
        stageSelected.setText("stage_1");
        stageSetUp(stageSelected.getText());
    }

    @FXML
    public void stage_twoClicked() {
        stageSelected.setText("stage_2");
        stageSetUp(stageSelected.getText());
    }

    @FXML
    public void stage_threeClicked() {
        stageSelected.setText("stage_3");
        stageSetUp(stageSelected.getText());
    }

    @FXML
    public void stage_fourClicked() {
        stageSelected.setText("stage_4");
        stageSetUp(stageSelected.getText());
    }

    @FXML
    public void stage_fiveClicked() {
        stageSelected.setText("stage_5");
        stageSetUp(stageSelected.getText());
    }

    @FXML
    public void stage_sixClicked() {
        stageSelected.setText("stage_6");
        stageSetUp(stageSelected.getText());
    }

    @FXML
    public void stage_sevenClicked() {
        stageSelected.setText("stage_7");
        stageSetUp(stageSelected.getText());
    }

    @FXML
    public void stage_eightClicked() {
        stageSelected.setText("stage_8");
        stageSetUp(stageSelected.getText());
    }

    @FXML
    public void stage_nineClicked() {
        stageSelected.setText("stage_9");
        stageSetUp(stageSelected.getText());
    }

    @FXML
    public void stage_tenClicked() {
        stageSelected.setText("stage_10");
        stageSetUp(stageSelected.getText());
    }

    @FXML
    public void kStage_oneClicked() {
        stageSelected.setText("kstage_1");
        killerStageSetUp(stageSelected.getText());
    }

    @FXML
    public void kStage_twoClicked() {
        stageSelected.setText("kstage_2");
        killerStageSetUp(stageSelected.getText());
    }

    @FXML
    public void kStage_threeClicked() {
        stageSelected.setText("kstage_3");
        killerStageSetUp(stageSelected.getText());
    }

    @FXML
    public void kStage_fourClicked() {
        stageSelected.setText("kstage_4");
        killerStageSetUp(stageSelected.getText());
    }

    @FXML
    public void kStage_fiveClicked() {
        stageSelected.setText("kstage_5");
        killerStageSetUp(stageSelected.getText());
    }

    @FXML
    public void kStage_sixClicked() {
        stageSelected.setText("kstage_6");
        killerStageSetUp(stageSelected.getText());
    }

    @FXML
    public void kStage_sevenClicked() {
        stageSelected.setText("kstage_7");
        killerStageSetUp(stageSelected.getText());
    }

    @FXML
    public void kStage_eightClicked() {
        stageSelected.setText("kstage_8");
        killerStageSetUp(stageSelected.getText());
    }

    @FXML
    public void kStage_nineClicked() {
        stageSelected.setText("kstage_9");
        killerStageSetUp(stageSelected.getText());
    }

    @FXML
    public void kStage_tenClicked() {
        stageSelected.setText("kstage_10");
        killerStageSetUp(stageSelected.getText());
    }
}
