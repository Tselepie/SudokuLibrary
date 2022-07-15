package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends Controller  {

    @FXML
    private Button availabilityButton;

    @FXML
    private Button back;

    @FXML
    private Button continueToMenu;

    @FXML
    private TextField txtUsername2;

    @FXML
    private PasswordField txtPassword2;

    @FXML
    private PasswordField txtConfirmPass2;

    @FXML
    private Label infoLabel;

    @FXML
    private Label namesPassRulesLabel;

    @FXML
    private Label rule_1;

    @FXML
    private Label rule_2;

    @FXML
    private Label rule_3;

    @FXML
    private Label lblStatus2;

    @FXML
    private Label lblStatus3;

    @FXML
    private Separator separator1;

    @FXML
    private Separator separator2;

    private ResourceBundle bundle;
    private Locale locale;

    private void loadLanguage(String language) {
        locale = new Locale(language);
        bundle = ResourceBundle.getBundle("sample.Languages", locale);

        infoLabel.setText(bundle.getString("infoLabel"));
        namesPassRulesLabel.setText(bundle.getString("namesPassRulesLabel"));
        rule_1.setText(bundle.getString("rule_1"));
        rule_2.setText(bundle.getString("rule_2"));
        rule_3.setText(bundle.getString("rule_3"));
        txtUsername2.setPromptText(bundle.getString("txtUsername2"));
        txtPassword2.setPromptText(bundle.getString("txtPassword2"));
        txtConfirmPass2.setPromptText(bundle.getString("txtConfirmPass2"));
        availabilityButton.setText(bundle.getString("availabilityButton"));
        continueToMenu.setText(bundle.getString("continueToMenu"));
        back.setText(bundle.getString("back"));
    }

    private boolean createRules(String string) {
        if (string.isEmpty())
            return false;
        if (string.length() < 3 || string.length() > 20)
            return false;
        Pattern pattern = Pattern.compile("[a-zA-Z0-9\\\\s]*");
        Matcher matcher = pattern.matcher(string);
        if (!matcher.matches())
            return false;
        return true;
    }

    private boolean searchNames() {
        Path path = Paths.get("Users").toAbsolutePath();
        File dir = new File(path.toString());

        for (File f : Objects.requireNonNull(dir.listFiles())) {
            if (f.getName().equals(txtUsername2.getText() + ".txt"))
                return false;
        }
        return true;
    }

    private void createAndContinue() {
        FileHandler fh = new FileHandler();

        if (!(txtPassword2.getText().equals(txtConfirmPass2.getText()))) {
            txtConfirmPass2.clear();
            txtConfirmPass2.setPromptText("Passwords don't match");
        }
        else if (createRules(txtUsername2.getText()) && createRules(txtPassword2.getText())) {
            fh.createFile(txtUsername2.getText(), txtPassword2.getText());
            createStage("Menu.fxml", continueToMenu, false, 600, 700);
        }
    }

    public void language_EN_2() { loadLanguage(""); }

    public void language_EL_2() { loadLanguage("el"); }

    public void keyPressedPass() {
        txtPassword2.addEventFilter(KeyEvent.ANY, event -> {
            if(!txtPassword2.getText().equals(txtConfirmPass2.getText())) {
                separator2.setVisible(false);
                separator1.setVisible(true);
            }
            else {
                separator1.setVisible(false);
                separator2.setVisible(true);
            }
            switch (event.getCode()) {
                case ENTER:
                    createAndContinue();
                    break;
            }
        });
    }

    public void keyPressedConf() {
        txtConfirmPass2.addEventFilter(KeyEvent.ANY, event -> {
            if(!txtPassword2.getText().equals(txtConfirmPass2.getText())) {
                separator2.setVisible(false);
                separator1.setVisible(true);
            }
            else {
                separator1.setVisible(false);
                separator2.setVisible(true);
            }
            switch (event.getCode()) {
                case ENTER:
                    createAndContinue();
                    break;
            }
        });
    }

    public void goBack() {
        createStage( "sample.fxml", back, false, 500, 500);
    }

    public void checkNames() {
        if (!searchNames() && !txtUsername2.getText().isEmpty()) {
            lblStatus3.setText("");
            lblStatus2.setText("Already in use");
        }
        else {
            lblStatus2.setText("");
            lblStatus3.setText("Available");
        }
    }

    public void checksAll() {
        lblStatus2.setText("");
        lblStatus3.setText("");

        createAndContinue();
    }
}
