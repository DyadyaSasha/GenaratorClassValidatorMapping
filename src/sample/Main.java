package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image("file:files/sberbank.png"));
        primaryStage.setTitle("Class validator/mapper");
        primaryStage.setResizable(false);

        initRootLayout();


    }


    //Инициализирует корневой макет.
    public void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            //root = FXMLLoader.load(getClass().getResource("main.fxml"));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("main.fxml"));
            root = (Parent) loader.load();
            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            //даём доступ контроллеру главному приложению
            Controller controller = loader.getController();
            controller.setMainApp(this);
            //даём контроллеру ссылку на базовый холст окна
            controller.setAnchorPane((AnchorPane)this.root);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Возвращает главную сцену.
    public Stage getPrimaryStage() {
        return primaryStage;
    }




    public static void main(String[] args) {
        launch(args);

    }
}
