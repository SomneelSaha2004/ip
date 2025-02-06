package taskmaster;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import taskmaster.ui.MainWindow;
/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final TaskMaster taskMaster = new TaskMaster();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            Image icon = new Image(Main.class.getResourceAsStream("/images/icon.png"));
            stage.setTitle("TaskMaster");
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setMaxWidth(417);
            fxmlLoader.<MainWindow>getController().setTaskMaster(taskMaster);  // inject the Duke instance
            stage.getIcons().add(icon);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
