package seedu.address.model.task;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.function.Predicate;

import seedu.address.model.group.Group;

public class FilterTaskPredicate implements Predicate<Task> {
    public static final String MESSAGE_CONSTRAINTS =
            "FilterTaskPredicate can be either 'g/GROUP', 'date/DATE', 'type/TASKTYPE', 'pty/PRIORITY', "
                    + "or 'd/DESCRIPTION' ";

    private final String paramAndKeywords;
    private boolean isValid = true;

    /**
     * Constructs a {@code FilterTaskPredicate}.
     *
     * @param paramAndKeywords A valid parameter and keywords to filter by.
     */
    public FilterTaskPredicate(String paramAndKeywords) {
        requireNonNull(paramAndKeywords);
        try {
            checkArgument(isValidCriterion(paramAndKeywords), MESSAGE_CONSTRAINTS);
        } catch (IllegalArgumentException e) {
            this.isValid = false;
        }
        this.paramAndKeywords = paramAndKeywords;
    }

    /**
     * Returns true if a given string is a valid FilterTaskPredicate.
     */
    public static boolean isValidCriterion(String test) {
        if (isNull(test)) {
            return false;
        }
        boolean isValid;
        String[] criterion = test.split("/");
        String prefix = criterion[0];
        switch(prefix) {
        case "date":
            isValid = Date.isValidDate(test.substring(5));
            break;
        case "type":
            isValid = TaskType.isValidTaskType(test.substring(5));
            break;
        case "g":
            isValid = Group.isValidGroup(test.substring(2));
            break;
        case "pty":
            isValid = Priority.isValidPriority(test.substring(4));
            break;
        case "d":
            isValid = Description.isValidDescription(test.substring(2));
            break;
        default:
            isValid = false;
            break;
        }
        return isValid;
    }

    @Override
    public boolean test(Task task) {
        requireNonNull(task);
        String[] criterion = paramAndKeywords.split("/");
        String prefix = criterion[0];
        switch(prefix) {
        case "date":
            Date date = new Date(paramAndKeywords.substring(5));
            if (isNull(task.getDate())) {
                return false;
            } else {
                return task.getDate().equals(date);
            }
        case "type":
            TaskType taskType = new TaskType(paramAndKeywords.substring(5));
            return task.getTaskType().equals(taskType);
        case "g":
            Group group = new Group(paramAndKeywords.substring(2));
            return task.getGroup().equals(group);
        case "pty":
            Priority priority = new Priority(paramAndKeywords.substring(4));
            if (isNull(task.getPriority())) {
                return false;
            } else {
                return task.getPriority().equals(priority);
            }
        case "d":
            Description description = new Description(paramAndKeywords.substring(2));
            return task.getDescription().contains(description);
        default:
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FilterTaskPredicate // instanceof handles nulls
            && paramAndKeywords.equals(((FilterTaskPredicate) other).paramAndKeywords)); // state check
    }

    @Override
    public String toString() {
        String[] criterion = paramAndKeywords.split("/");
        String prefix = criterion[0];
        String suffix = criterion[1];
        String string;
        switch(prefix) {
        case "date":
            string = "DATE";
            break;
        case "type":
            string = "TASKTYPE";
            break;
        case "g":
            string = "GROUP";
            break;
        case "d":
            string = "DESCRIPTION";
            suffix = "\"" + suffix + "\"";
            break;
        case "pty":
            string = "PRIORITY";
            break;
        default:
            string = "";
            break;
        }
        return string + " " + suffix.toUpperCase() + " criterion: ";

    }

    public boolean isValidPredicate() {
        return isValid;
    }

}
