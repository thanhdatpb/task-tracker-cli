package service;

import model.Task;
import storage.FileStorage;
import java.util.List;
import java.util.Optional;

public class TaskService {
    private FileStorage fileStorage;
    private List<Task> tasks;

    public TaskService(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
        this.tasks = fileStorage.loadTasks();
    }

    public void addTask(String title, String description) {
        int newId = tasks.isEmpty() ? 1 : tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0) + 1;
        
        Task newTask = new Task(newId, title, description);
        tasks.add(newTask);
        fileStorage.saveTasks(tasks);
        System.out.println("Task added successfully: " + newTask.getId());
    }

    public void updateTaskStatus(int id, String newStatus) {
        Optional<Task> task = tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
        
        if (task.isPresent()) {
            task.get().setStatus(newStatus);
            fileStorage.saveTasks(tasks);
            System.out.println("Task updated successfully");
        } else {
            System.out.println("Task not found with id: " + id);
        }
    }

    public void deleteTask(int id) {
        boolean removed = tasks.removeIf(t -> t.getId() == id);
        if (removed) {
            fileStorage.saveTasks(tasks);
            System.out.println("Task deleted successfully");
        } else {
            System.out.println("Task not found with id: " + id);
        }
    }

    public void listAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found");
            return;
        }
        tasks.forEach(System.out::println);
    }

    public void listTasksByStatus(String status) {
        List<Task> filteredTasks = tasks.stream()
                .filter(t -> t.getStatus().equalsIgnoreCase(status))
                .toList();
        
        if (filteredTasks.isEmpty()) {
            System.out.println("No tasks found with status: " + status);
            return;
        }
        filteredTasks.forEach(System.out::println);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
