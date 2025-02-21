# TaskMaster

TaskMaster is a simple command-line application for managing tasks. It supports adding, listing, marking, unmarking, and deleting tasks. Tasks can be of three types: ToDo, Deadline, and Event.

## Features

- Add tasks (ToDo, Deadline, Event)
- List all tasks
- Mark tasks as done
- Unmark tasks as not done
- Delete tasks
- Display help message

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
    javac -d bin src/main/java/main/*.java
    ```
2. Run the application:
    ```sh
    java -cp bin main.TaskMaster
    ```

## Usage

### Commands

- `list` - Lists all tasks
- `todo DESC` - Adds a to-do task (e.g., `todo read book`)
- `deadline DESC /by DEADLINE` - Adds a deadline task (e.g., `deadline return book /by Sunday`)
- `event DESC /from START /to END` - Adds an event task (e.g., `event project meeting /from Mon 2pm /to 4pm`)
- `mark INDEX` - Marks task #INDEX as done
- `unmark INDEX` - Marks task #INDEX as not done
- `delete INDEX` - Deletes task #INDEX
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

> deadline return book /by Sunday
Got it. I've added this task:
  [D][ ] return book (by: Sunday)
Now you have 2 tasks in the list.

> event project meeting /from Mon 2pm /to 4pm
Got it. I've added this task:
  [E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.

> list
Here are the tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: Sunday)
3.[E][ ] project meeting (from: Mon 2pm to: 4pm)



