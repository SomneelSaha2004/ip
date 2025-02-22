# taskmaster

TaskMaster is a simple task management application with a GUI built using JavaFX. It allows users to add, list, mark, unmark, delete tasks, and more.

## Features

- **Add Tasks**: Supports three task types - `ToDo`, `Deadline`, and `Event`
- **List All Tasks**: View all tasks in a formatted list
- **Mark Tasks as Done**: Easily mark tasks as completed
- **Unmark Tasks**: Undo completion status
- **Delete Tasks**: Remove tasks from the list
- **Agenda View**: View tasks due on a specific date
- **Find Tasks**: Search for tasks by keyword
- **Resizable GUI**: Modern UI with JavaFX, resizable for better readability
- **Profile Pictures**: Customizable display for chatbot interactions
- **Persistent Storage**: Tasks are saved locally and loaded on startup
- **Help Command**: Displays a guide on available commands

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- JavaFX 17+ (if running manually)

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/SomneelSaha2004/taskmaster.git
    ```
2. Navigate to the project directory:
    ```sh
    cd taskmaster
    ```

### Running the Application

#### Using Gradle
1. Compile and run:
    ```sh
    ./gradlew run
    ```

#### Running Manually
1. Compile Java files:
    ```sh
    javac --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml -d bin src/taskmaster/Main.java
    ```
2. Run the application:
    ```sh
    java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml -cp bin taskmaster.Main
    ```

## Usage

### Commands

- `list` - Lists all tasks
- `todo DESC` - Adds a to-do task (e.g., `todo read book`)
- `deadline DESC /by DEADLINE` - Adds a deadline task (e.g., `deadline return book /by 02/12/2019 1800`)
- `event DESC /from START /to END` - Adds an event task (e.g., `event project meeting /from 02/12/2019 0900 /to 02/12/2019 1100`)
- `mark INDEX` - Marks task #INDEX as done
- `unmark INDEX` - Marks task #INDEX as not done
- `delete INDEX` - Deletes task #INDEX
- `agenda DATE` - Lists all tasks due on the specified date (e.g., `agenda 02/12/2019`)
- `find KEYWORD` - Searches for tasks containing the keyword (e.g., `find book`)
- `help` - Shows the help message
- `bye` - Exits taskmaster

### Example Usage

```sh
> todo borrow book
Got it. I've added this task:
  [T][ ] borrow book
Now you have 1 task in the list.

> list
Here are the tasks in your list:
1. [T][ ] borrow book

> deadline return book /by 02/12/2019 1800
Got it. I've added this task:
  [D][ ] return book (by: 02/12/2019 1800)
Now you have 2 tasks in the list.

> event project meeting /from 02/12/2019 0900 /to 02/12/2019 1100
Got it. I've added this task:
  [E][ ] project meeting (from: 02/12/2019 0900 to: 02/12/2019 1100)
Now you have 3 tasks in the list.

> agenda 02/12/2019
Here are the tasks on 02/12/2019:
1. [D][ ] return book (by: 02/12/2019 1800)
2. [E][ ] project meeting (from: 02/12/2019 0900 to: 02/12/2019 1100)

> find book
Here are the matching tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: 02/12/2019 1800)

> help
Displays the list of commands

> bye
Goodbye! Hope to see you again soon.