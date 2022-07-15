package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;


public class Main extends Application {

    /*
    Creates the login stage opening the sample.fxml file
     */
    @Override
    public void start(Stage loginScreen) throws IOException {
        URL location = getClass().getResource("sample.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = FXMLLoader.load(location);

        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        loginScreen.setScene(new Scene(root, 500, 500));
        loginScreen.show();
        loginScreen.setResizable(false);

        //Gets the bounds of the screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();

        //Sets the window in the center of the screen
        loginScreen.setX((primScreenBounds.getWidth() - loginScreen.getWidth()) / 2);
        loginScreen.setY((primScreenBounds.getHeight() - loginScreen.getHeight()) / 2);
    }

    public static void main(String[] args) { launch(args); }
}
