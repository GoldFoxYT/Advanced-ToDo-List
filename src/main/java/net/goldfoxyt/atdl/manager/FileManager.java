package net.goldfoxyt.atdl.manager;

import javafx.scene.control.Alert;
import net.goldfoxyt.atdl.model.Task;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private final Stage stage;

    public FileManager(Stage stage) {
        this.stage = stage;
    }

    public void exportListToTxt(ArrayList<Task> tasks) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));

        File fileToSave = fileChooser.showSaveDialog(stage);
        if (fileToSave != null) {
            if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getParentFile(), fileToSave.getName() + ".txt");
            }
            writeTasksToFile(tasks, fileToSave);
        }
    }

    private void writeTasksToFile(ArrayList<Task> tasks, File file) {
        Alert alert;
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");
            }
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exporting was successful");
            alert.setHeaderText("List exported successfully to " + file.getAbsolutePath());
            alert.show();
        } catch (IOException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exporting Failed");
            alert.setHeaderText("Error exporting list to txt file: " + e.getMessage());
            alert.show();
        }
    }

    public void importListFromTxt(ArrayList<Task> tasks) {
        Alert alert;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));

        File fileToLoad = fileChooser.showOpenDialog(stage);
        if (fileToLoad != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileToLoad))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    tasks.add(new Task(line));
                }
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Importing was successful");
                alert.setHeaderText("List imported successfully from " + fileToLoad.getAbsolutePath());
                alert.show();
            } catch (IOException e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Importing Failed");
                alert.setHeaderText("Error importing list from txt file: " + e.getMessage());
                alert.show();
            }
        }
    }
}