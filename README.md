# Task Tracker CLI

A simple command-line task management application built with Java.

## Project Structure

```
task-tracker-cli/
├── src/
│   ├── Main.java                 # Entry point of the application
│   ├── model/
│   │   └── Task.java            # Task model class
│   ├── service/
│   │   └── TaskService.java      # Business logic for task operations
│   ├── storage/
│   │   └── FileStorage.java      # File I/O operations
│   └── utils/
│       ├── JsonUtils.java        # JSON serialization/deserialization
│       └── LocalDateTimeAdapter.java  # Custom Gson adapter for LocalDateTime
├── tasks.json                    # JSON file storing tasks
└── README.md                     # Project documentation
```

## Features

- **Add Task**: Create new tasks with title and description
- **List Tasks**: View all tasks or filter by status
- **Update Status**: Change task status (TODO, IN_PROGRESS, DONE)
- **Delete Task**: Remove tasks by ID
- **Persistent Storage**: Tasks are saved to `tasks.json`

## Requirements

- Java 11 or higher
- Gson library (for JSON serialization)

## Usage

### Compile

```bash
javac -cp ".:gson-2.x.x.jar" src/**/*.java -d out/
```

### Run

```bash
java -cp "out/:gson-2.x.x.jar" Main
```

### Menu Options

1. **Add Task** - Create a new task
2. **List All Tasks** - Display all tasks
3. **List Tasks by Status** - Filter tasks by status
4. **Update Task Status** - Change a task's status
5. **Delete Task** - Remove a task
6. **Exit** - Close the application

## Example Workflow

```
1. Add Task
   Enter task title: Implement feature X
   Enter task description: Add new feature to the project
   
2. List All Tasks
   View all created tasks
   
3. Update Task Status
   Enter task ID: 1
   Enter new status: IN_PROGRESS
   
4. Delete Task
   Enter task ID: 1
```

## Task Model

Each task contains:
- **ID**: Unique identifier
- **Title**: Task name
- **Description**: Task details
- **Status**: Current status (TODO, IN_PROGRESS, DONE)
- **Created At**: Creation timestamp
- **Updated At**: Last modification timestamp

## Notes

- Tasks are automatically saved after each operation
- Task IDs are auto-generated
- Date/time is stored in ISO format in JSON
