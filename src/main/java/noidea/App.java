package noidea;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CourseSystem;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static CourseSystem system = CourseSystem.getInstance();

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 1280, 720);
        stage.setScene(scene);

        system.login("bwest@email.sc.edu", "password");
        setRoot("degreeProgress");
        system.getStudent().printAllRequirements();

        stage.show();
    }

    public void stop() {
        system.exit();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}