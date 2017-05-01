package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import utils.СlassCompiler;

public class Controller {

    // Ссылка на главное приложение, чтобы была возможноть у контроллера
    // пользоваться методоми главного приложения
    private Main mainApp;

    @FXML
    private AnchorPane anchorPane;

    //здесь собираются все выбранные папки и файлы(только их имена)
    private ObservableList<String> items = FXCollections.observableArrayList();
    //здесь собираются все выбранные папки и файлы(их имена
    // и ссылки типа File, откуда можно взять путь до файла/папки)
    private Map<String, File> mapOfItems = new HashMap();

    //Вызывается главным приложением в методе initRootLayout(), чтобы оставить ссылку на самого себя.
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    @FXML
    private Button additem, deleteitem, addfolder; //имя переменной должно совпадать с соответствующим элементом в fxml файле

    //здесь собираются все выбранные папки и файлы типа, которые отображаются непосредственно в окне
    @FXML
    private ListView<String> listOfItems = new ListView();

    //initialize()  какой-то метод "внутри" javafx, который вызывается при запуске приложения
    public void initialize(){
        anchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                listOfItems.getSelectionModel().clearSelection();
            }
        });
    }

    @FXML
    public void addItem(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберете java-класс или jar-файл");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("jar-Files", "*.jar"),
                new FileChooser.ExtensionFilter("java-class Files", "*.java"));
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if (file != null){
            mapOfItems.put(file.getName(), file);
            items.add(file.getName());
            СlassCompiler compiler = new СlassCompiler(file);
            compiler.classCompile();
        }
        listOfItems.setItems(items);
        listOfItems.getSelectionModel().clearSelection();
    }

    @FXML
    public void addFolder(){
        DirectoryChooser folderChooser = new DirectoryChooser();
        folderChooser.setTitle("Выберете папку с классами");
        File folder = folderChooser.showDialog(mainApp.getPrimaryStage());
        if (folder != null){
            mapOfItems.put(folder.getName(), folder);
            items.add(folder.getName());
        }
        listOfItems.setItems(items);
        listOfItems.getSelectionModel().clearSelection();
    }

    @FXML
    public void deleteItem(){
        int index = listOfItems.getSelectionModel().getSelectedIndex();
        if (index != -1){
            mapOfItems.remove(listOfItems.getSelectionModel().getSelectedItem());
            listOfItems.getItems().remove(index);
        }
        listOfItems.getSelectionModel().clearSelection();
    }

}
