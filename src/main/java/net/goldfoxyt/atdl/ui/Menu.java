package net.goldfoxyt.atdl.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import net.goldfoxyt.atdl.io.TaskManager;
import net.goldfoxyt.atdl.manager.FileManager;

public class Menu extends Application {
    private final TaskManager taskManager;
    private final FileManager fileManager;
    private ListView<String> taskListView;

    public Menu(TaskManager taskManager, FileManager fileManager) {
        this.taskManager = taskManager;
        this.fileManager = fileManager;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("To-Do List");

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(Background.fill(Color.ALICEBLUE));

        Label welcomeLabel = new Label("Welcome to my To-Do List!");

        Button addButton = new Button("Add Task");
        addButton.setOnAction(_ -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Task");
            dialog.setHeaderText("Please type the task");
            dialog.showAndWait().ifPresent(taskManager::addTask);
            updateTaskListView();
        });

        Button deleteButton = new Button("Delete Task");
        deleteButton.setOnAction(_ -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Delete Task");
            dialog.setHeaderText("Please type the task you want to delete");
            dialog.showAndWait().ifPresent(taskManager::deleteTask);
            updateTaskListView();
        });

        Button exportButton = new Button("Export Tasks to Txt");
        exportButton.setOnAction(_ -> fileManager.exportListToTxt(taskManager.getTasks()));

        Button importButton = new Button("Import Tasks from Txt");
        importButton.setOnAction(_ -> {
            fileManager.importListFromTxt(taskManager.getTasks());
            updateTaskListView();
        });

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(_ -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exiting");
            alert.setHeaderText("Thank you for using my first gui app!");
            alert.show();
            primaryStage.close();
        });

        taskListView = new ListView<>();
        taskListView.setPrefHeight(150);

        HBox hbox = new HBox(10, vbox, taskListView);
        hbox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(welcomeLabel, addButton, deleteButton, exportButton, importButton, exitButton);

        Scene scene = new Scene(hbox, 500, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateTaskListView() {
        taskListView.getItems().clear();
        taskListView.getItems().addAll(taskManager.getTaskDescriptions());
    }

    public static void main(String[] args) {
        launch(args);
    }
}