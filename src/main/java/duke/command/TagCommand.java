package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidParameterDukeException;
import duke.exception.InvalidTagDukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A TagCommand class that extends from the Command class.
 * @author KelvinSoo
 * @version C-Tagging
 */
public class TagCommand extends Command{

    private final String description;
    private static final String SUCCESS_MESSAGE = "I've tagged this task as %s :\n  %s %s";


    /**
     * A constructor to initialize a tag command.
     * @param description the parameter of the tag command, should be a task number and tag.
     */
    public TagCommand(String description) {
        this.description = description;
    }

    /**
     * Execute a tag command.
     * Tag a task from the task list.
     * @param taskList The task list to execute the command on.
     * @param storage The place to store the session.
     * @throws DukeException task does not exist.
     * @return The response.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        String[] parameter = description.split(" ");
        boolean isValidTagParameter = parameter.length == 2;
        if (!isValidTagParameter) {
            throw new InvalidParameterDukeException();
        }

        boolean isValidNumber = parameter[0].matches("\\d+");
        if (!isValidNumber) {
            throw new InvalidTagDukeException();
        }
        Task task = taskList.getTask(Integer.parseInt(parameter[0]));
        taskList.tagTask(Integer.parseInt(parameter[0]), parameter[1]);
        storage.save(taskList);
        return String.format(
                SUCCESS_MESSAGE, task.showTag(), task.getStatusIcon(), task.getDescription());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}