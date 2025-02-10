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
 * A GUI for TaskMaster using FXML.
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
            stage.setScene(scene);
            stage.setTitle("TaskMaster - Your Personal Task Manager");

            // ✅ Force the initial window size
            stage.setWidth(700);
            stage.setHeight(500);

            // ✅ Prevent shrinking below the min size
            stage.setMinWidth(700);
            stage.setMinHeight(500);
            stage.setMaxWidth(1000);
            stage.setMaxHeight(700);
            stage.setResizable(true);

            fxmlLoader.<MainWindow>getController().setTaskMaster(taskMaster);
            stage.getIcons().add(icon);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
