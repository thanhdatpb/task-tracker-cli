package storage;

import model.Task;
import utils.JsonUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    private String filePath;

    public FileStorage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadTasks() {
        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                return new ArrayList<>();
            }
            
            String content = Files.readString(path);
            return JsonUtils.deserializeTasks(content);
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveTasks(List<Task> tasks) {
        try {
            String json = JsonUtils.serializeTasks(tasks);
            Path path = Paths.get(filePath);
            Files.createDirectories(path.getParent());
            Files.writeString(path, json);
            System.out.println("Tasks saved successfully");
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }
}
