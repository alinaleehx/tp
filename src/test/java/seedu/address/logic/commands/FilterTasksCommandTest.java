package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.DEADLINE1;
import static seedu.address.testutil.TypicalTasks.EVENT1;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskRecords;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.FilterTaskPredicate;
import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model).
 */
public class FilterTasksCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskRecords());

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FilterTasksCommand(null));
    }

    @Test
    public void execute_validDate_success() throws CommandException {
        ModelManager expectedModel =
                new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskRecords());
        Task toFilter = DEADLINE1;
        FilterTaskPredicate criterion = new FilterTaskPredicate("date/" + toFilter.getDate().getString());
        FilterTasksCommand command = new FilterTasksCommand(criterion);
        String expectedMessage = command.execute(expectedModel).getFeedbackToUser();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_validType_success() throws CommandException {
        ModelManager expectedModel =
                new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskRecords());
        Task toFilter = EVENT1;
        FilterTaskPredicate criterion = new FilterTaskPredicate("type/" + toFilter.getTaskType().toString());
        FilterTasksCommand command = new FilterTasksCommand(criterion);
        String expectedMessage = command.execute(expectedModel).getFeedbackToUser();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validGroup_success() throws CommandException {
        ModelManager expectedModel =
                new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskRecords());
        Task toFilter = EVENT1;
        FilterTaskPredicate criterion = new FilterTaskPredicate("g/" + toFilter.getGroup().toString());
        FilterTasksCommand command = new FilterTasksCommand(criterion);
        String expectedMessage = command.execute(expectedModel).getFeedbackToUser();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validDesc_success() throws CommandException {
        ModelManager expectedModel =
                new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskRecords());
        Task toFilter = EVENT1;
        FilterTaskPredicate criterion = new FilterTaskPredicate("d/" + toFilter.getDescription().toString());
        FilterTasksCommand command = new FilterTasksCommand(criterion);
        String expectedMessage = command.execute(expectedModel).getFeedbackToUser();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validPty_success() throws CommandException {
        ModelManager expectedModel =
                new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskRecords());
        Task toFilter = EVENT1;
        FilterTaskPredicate criterion = new FilterTaskPredicate("pty/" + toFilter.getPriority().toString());
        FilterTasksCommand command = new FilterTasksCommand(criterion);
        String expectedMessage = command.execute(expectedModel).getFeedbackToUser();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidDate_failure() {
        Task toFilter = EVENT1;
        String invalidDate = "date/" + toFilter.getDate().toString() + "invalid date";
        FilterTaskPredicate criterion = new FilterTaskPredicate(invalidDate);
        FilterTasksCommand command = new FilterTasksCommand(criterion);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void execute_invalidPty_failure() {
        Task toFilter = EVENT1;
        String invalidPty = "pty/" + toFilter.getPriority().toString() + "invalid pty";
        FilterTaskPredicate criterion = new FilterTaskPredicate(invalidPty);
        FilterTasksCommand command = new FilterTasksCommand(criterion);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void execute_invalidGroup_failure() {
        Task toFilter = EVENT1;
        String invalidGroup = "g/" + toFilter.getGroup().toString() + "invalid group";
        FilterTaskPredicate criterion = new FilterTaskPredicate(invalidGroup);
        FilterTasksCommand command = new FilterTasksCommand(criterion);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void execute_invalidCriterionSuffix_failure() {
        Task toFilter = EVENT1;
        String invalidSuffix = "y/" + toFilter.getPriority().toString() + "invalid pty";
        FilterTaskPredicate criterion = new FilterTaskPredicate(invalidSuffix);
        FilterTasksCommand command = new FilterTasksCommand(criterion);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_COMMAND_FORMAT);
    }

}
