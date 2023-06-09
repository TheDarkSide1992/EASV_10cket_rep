import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        Parent root;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/view/util/DeleteEventsView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Not active events");
        stage.setScene(new Scene(root));
        stage.show();
*/
        Parent root = FXMLLoader.load(getClass().getResource("gui/view/Index.fxml"));

        primaryStage.setTitle("10cket_System");

        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(true);
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();

    }
}