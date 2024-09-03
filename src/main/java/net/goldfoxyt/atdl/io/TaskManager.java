package net.goldfoxyt.atdl.io;

import javafx.scene.control.Alert;
import net.goldfoxyt.atdl.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(String description) {
        tasks.add(new Task(description));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Task added successfully");
        alert.setHeaderText(description + " " + "was added");
        alert.show();
    }

    public void deleteTask(String description) {
        Alert alert;
        Task taskToRemove = null;
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                taskToRemove = task;
                break;
            }
        }
        if (taskToRemove != null) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Task deleted successfully");
            alert.setHeaderText(taskToRemove.getDescription() + " " + "was deleted");
            alert.show();
            tasks.remove(taskToRemove);
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Task not found");
            alert.setHeaderText("Task not found");
            alert.show();
        }
    }

    public List<String> getTaskDescriptions() {
        List<String> descriptions = new ArrayList<>();
        for (Task task : tasks) {
            descriptions.add(task.getDescription());
        }
        return descriptions;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}