package net.goldfoxyt.atdl.app;

import javafx.scene.image.Image;
import net.goldfoxyt.atdl.io.TaskManager;
import net.goldfoxyt.atdl.manager.FileManager;
import net.goldfoxyt.atdl.ui.Menu;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image("net/goldfoxyt/atdl/icon.jpg"));
        TaskManager taskManager = new TaskManager();
        FileManager fileManager = new FileManager(primaryStage);
        Menu menu = new Menu(taskManager, fileManager);
        menu.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}