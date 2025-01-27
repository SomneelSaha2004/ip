# TaskMaster

TaskMaster is a simple command-line application for managing tasks. It supports adding, listing, marking, unmarking, and deleting tasks. Tasks can be of three types: ToDo, Deadline, and Event.

## Features

- Add tasks (ToDo, Deadline, Event)
- List all tasks
- Mark tasks as done
- Unmark tasks as not done
- Delete tasks
- Display help message
- Agenda: View tasks due on a specific date
- Supports multiple date formats (e.g., `d/M/yyyy HHmm`, `d-M-yyyy HHmm`, ISO format)

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/SomneelSaha2004/TaskMaster.git
    ```
2. Navigate to the project directory:
    ```sh
    cd TaskMaster
    ```

### Running the Application

1. Compile the Java files:
    ```sh
    javac -d bin src/TaskMaster/java/TaskMaster/*.java
    ```
2. Run the application:
    ```sh
    java -cp bin TaskMaster.TaskMaster
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
- `help` - Shows the help message
- `bye` - Exits TaskMaster

### Example

```sh
> todo borrow book
Got it. I've added this task:
  [T][ ] borrow book
Now you have 1 task in the list.

> list
Here are the tasks in your list:
1.[T][ ] borrow book

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
1.[D][ ] return book (by: 02/12/2019 1800)
2.[E][ ] project meeting (from: 02/12/2019 0900 to: 02/12/2019 1100)
