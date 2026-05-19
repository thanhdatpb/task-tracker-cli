import service.TaskService;
import storage.FileStorage;
import java.util.Scanner;

public class Main {
    private static TaskService taskService;

    public static void main(String[] args) {
        // Initialize storage and service
        FileStorage fileStorage = new FileStorage("tasks.json");
        taskService = new TaskService(fileStorage);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Task Tracker CLI ===");
        System.out.println();

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addTask(scanner);
                    break;
                case "2":
                    listAllTasks();
                    break;
                case "3":
                    listTasksByStatus(scanner);
                    break;
                case "4":
                    updateTaskStatus(scanner);
                    break;
                case "5":
                    deleteTask(scanner);
                    break;
                case "6":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("1. Add Task");
        System.out.println("2. List All Tasks");
        System.out.println("3. List Tasks by Status");
        System.out.println("4. Update Task Status");
        System.out.println("5. Delete Task");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine().trim();
        
        System.out.print("Enter task description: ");
        String description = scanner.nextLine().trim();
        
        taskService.addTask(title, description);
    }

    private static void listAllTasks() {
        System.out.println("\n=== All Tasks ===");
        taskService.listAllTasks();
    }

    private static void listTasksByStatus(Scanner scanner) {
        System.out.print("Enter status (TODO/IN_PROGRESS/DONE): ");
        String status = scanner.nextLine().trim();
        System.out.println("\n=== Tasks with status: " + status + " ===");
        taskService.listTasksByStatus(status);
    }

    private static void updateTaskStatus(Scanner scanner) {
        System.out.print("Enter task ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        
        System.out.print("Enter new status (TODO/IN_PROGRESS/DONE): ");
        String status = scanner.nextLine().trim();
        
        taskService.updateTaskStatus(id, status);
    }

    private static void deleteTask(Scanner scanner) {
        System.out.print("Enter task ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        
        taskService.deleteTask(id);
    }
}
