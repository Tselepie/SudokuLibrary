package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;


public class Controller {

    @FXML
    private Label lblStatus;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button continueAsGuest;

    @FXML
    private Button createUser;

    @FXML
    private Button login;

    @FXML
    private Button chooseGameButton;

    @FXML
    private Button playerStatsButton;

    @FXML
    private Button rulesButton;

    @FXML
    private Button goBackMenu;

    @FXML
    private Button goBackPlayerStats;

    @FXML
    private Button goBackRules;

    @FXML
    private Button exit;

    @FXML
    private URL location;

    private ResourceBundle bundle;
    private Locale locale;

    private void loadLanguage(String language) {
        locale = new Locale(language);
        bundle = ResourceBundle.getBundle("sample.Languages", locale);

        if(location.toString().contains("sample.fxml")) {
            txtUsername.setPromptText(bundle.getString("txtUsername"));
            txtPassword.setPromptText(bundle.getString("txtPassword"));
            login.setText(bundle.getString("login"));
            lblStatus.setText(bundle.getString("lblStatus"));
            createUser.setText(bundle.getString("createUser"));
            continueAsGuest.setText(bundle.getString("continueAsGuest"));
        }

        if(location.toString().contains("Menu.fxml")) {
            chooseGameButton.setText(bundle.getString("chooseGameButton"));
            playerStatsButton.setText(bundle.getString("playerStatsButton"));
            rulesButton.setText(bundle.getString("rulesButton"));
            goBackMenu.setText(bundle.getString("goBackMenu"));
            exit.setText(bundle.getString("exit"));
        }
    }

    /*
    When Login is pressed this method checks every txt file
    in the Users folder
     */
    private void login() {
        FileHandler fh = new FileHandler();
        lblStatus.setVisible(false);

        if (fh.searchInFile(txtUsername.getText(), txtPassword.getText()))
            createStage("Menu.fxml", login, false, 600, 700);
        else
            lblStatus.setVisible(true);
    }

    public void loginAction() { login(); }

    public void language_EN() { loadLanguage(""); }

    public void language_EL() { loadLanguage("el"); }

    //Creates the Create user stage opening the SignUp.fxml file
    public void create_user() { createStage("SignUp.fxml", createUser, false, 600, 700); }

    //Creates the menu stage opening the Menu.fxml file
    public void continueAsGuest() { createStage("Menu.fxml", continueAsGuest, false, 600, 700); }

    //When typing username if enter key is pressed login method is called
    public void nameKeyPressed() {
        txtUsername.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    login();
                    break;
            }
        });
    }

    //When typing password if enter key is pressed login method is called
    public void passKeyPressed() {
        txtPassword.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    login();
                    break;
            }
        });
    }

    //This method is called whenever a new stage is going to be created
    public void createStage(String resource, Button button, boolean resizable, int width, int height) {
        try {
            URL location = getClass().getResource(resource);
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = FXMLLoader.load(location);

            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Stage stage = (Stage) button.getScene().getWindow();
            Scene scene = new Scene(root, width, height);
            stage.setScene(scene);
            stage.setResizable(resizable);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
            stage.show();
        }
        catch (Exception e3) {
            System.out.println(e3);
        }
    }

    /*
    These methods below represent the menu options
     */

    //Creates the Choose Game stage opening the ChooseGame.fxml file
    public void chooseGame() { createStage("ChooseGame.fxml", chooseGameButton, false, 600, 700); }

    //Creates the Statistics stage opening the PlayerStats.fxml file
    public void statsButton() { createStage("PlayerStats.fxml", playerStatsButton, false, 500, 600); }

    ////Creates the Rules stage opening the Rules.fxml file
    public void gameRules() { createStage("Rules.fxml", rulesButton, false, 600, 700); }

    //Creates again the Login stage opening the sample.fxml file
    public void backToLogin() { createStage("sample.fxml", goBackMenu, false, 500, 500); }

    //When exit is pressed this method is called and closes the application
    public void exitGame() { Platform.exit(); }

    /*
    These methods below represent the BACK button in each of the menu options except the Choose Game option
    They create the Menu stage opening the Menu.fxml file
     */
    public void backToMenuPS() { createStage("Menu.fxml", goBackPlayerStats, false, 600, 700); }

    public void backToMenuR() { createStage("Menu.fxml", goBackRules, false, 600, 700); }
}
