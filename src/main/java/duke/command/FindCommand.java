package duke.command;

import duke.ui.Gui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.exception.DukeException;

/**
 * A FindCommand class that extends from the Command class.
 *
 * @author KelvinSoo
 * @version Level-9
 */
public class FindCommand extends Command{

    private final String parameter;

    /**
     * A constructor to initialize a find command.
     * @param parameter the parameter of the find command, keyword for searching task.
     */
    public FindCommand(String parameter) {
        this.parameter = parameter;
    }

    /**
     * Execute a find command.
     * find matching tasks from the task list.
     * @param taskList The task list to search.
     * @param gui The user interface to display the reply.
     * @param storage The place to store the session.
     * @throws DukeException no matching task found.
     */

    @Override
    public void execute(TaskList taskList, Gui gui, Storage storage) throws DukeException {
        TaskList searchList = new TaskList();
        for (Task t:taskList.getTaskList()) {
            if (t.getDescription().contains(parameter)) {
                searchList.addTask(t);
            }
        }

        if (searchList.size() == 0) {
            throw new DukeException("No matching tasks found.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        sb.append(searchList.toString());
        gui.showResponse(sb.toString());

    }

    /**
     * A boolean to notate if this is an exit command.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
