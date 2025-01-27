package TaskMaster.Commands;

import TaskMaster.Storage.Storage;
import TaskMaster.Ui.Ui;
import TaskMaster.Utils.TaskList;

public class FindCommand extends Command {
    private final String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTasks().isEmpty()) {
            ui.show("Your task list is empty!");
        } else {
            ui.show("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                if(tasks.getTasks().get(i).getTaskDescription().contains(keyword)) {
                    ui.show((i + 1) + ". " + tasks.getTasks().get(i));
                }
            }
        }
    }
}

