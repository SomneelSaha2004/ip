# TaskMaster User Guide 📚

Welcome to **TaskMaster**, your personal task management assistant designed to keep you organized and productive! This user guide provides step-by-step instructions on how to use TaskMaster effectively.

---

## 🛠️ Installation & Setup

### Requirements:
- **Java 17 or higher** is required to run TaskMaster.
- Ensure JavaFX is properly set up on your system.

### Installation Steps:
1. **Download the latest release**:
   - Go to the [Releases](https://github.com/SomneelSaha2004/ip/releases/tag/A-Release) page and download the latest `.jar` file.

2. **Open a terminal** and navigate to the download directory:
    ```sh
    cd /path/to/downloaded/file
    ```

3. **Run the application** using:
    ```sh
    java -jar taskmaster.jar
    ```

---

## 🚀 Getting Started

After launching TaskMaster, you will see a **clean and modern user interface** with:
- A chat-like interface for entering commands.
- A scrollable display showing tasks and system messages.

To start using TaskMaster, simply **type your command** in the input box and hit `Enter`!

---

## 📋 Available Commands

### **Task Management Commands**

- **List all tasks**:
    ```
    list
    ```
  Displays all tasks in your task list.

- **Add a To-Do**:
    ```
    todo <DESCRIPTION>
    ```
  Example:
    ```
    todo Read book
    ```

- **Add a Deadline**:
    ```
    deadline <DESCRIPTION> /by <DATE_TIME>
    ```
  Example:
    ```
    deadline Submit report /by 02/12/2025 1800
    ```
  > **Date Formats Supported**:
   - `d/M/yyyy HHmm` (e.g., 02/12/2025 1800)
   - `d-M-yyyy HHmm` (e.g., 02-12-2025 1800)
   - `yyyy-MM-dd'T'HH:mm` (ISO format, e.g., 2025-12-02T18:00)

- **Add an Event**:
    ```
    event <DESCRIPTION> /from <START> /to <END>
    ```
  Example:
    ```
    event Meeting /from 02/12/2025 0900 /to 02/12/2025 1100
    ```

---

### ✅ **Task Status & Modification**

- **Mark Task as Done**:
    ```
    mark <INDEX>
    ```
  Example:
    ```
    mark 3
    ```

- **Unmark Task as Not Done**:
    ```
    unmark <INDEX>
    ```
  Example:
    ```
    unmark 3
    ```

- **Delete a Task**:
    ```
    delete <INDEX>
    ```
  Example:
    ```
    delete 2
    ```

---

### 📆 **Schedule & Search**

- **View Tasks Due on a Date**:
    ```
    agenda <DATE>
    ```
  Example:
    ```
    agenda 02/12/2025
    ```

- **Find Tasks by Keyword**:
    ```
    find <KEYWORD>
    ```
  Example:
    ```
    find book
    ```

---

### ℹ️ **Other Commands**

- **Display Help**:
    ```
    help
    ```
  Shows the list of available commands and usage instructions.

- **Exit the Application**:
    ```
    bye
    ```

---

## 🎨 User Interface Features

- **Responsive Design**: The window is resizable and adjusts content dynamically.
- **Error Handling**: Errors are displayed in a distinct red bubble for better visibility.
- **Modern Look**: Stylish chat bubbles for user inputs and system messages.
- **Emoji Support**: Commands and feedback are accompanied by relevant emojis for better UX.

---



