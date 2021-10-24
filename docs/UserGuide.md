---
layout: page
title: User Guide
---

SWEe-book is a **desktop app for CS2103T/CS2101 Computer Science students to manage contacts and tasks pertaining to CS2103T/CS2101 module, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, SWEe-book can get your contact management and task management done faster than traditional GUI apps.
SWEe-book offers **one-stop integration solution to your group work management.**


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `SWEe-book.jar` from [here](https://github.com/AY2122S1-CS2103T-W12-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your SWEe-book.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/UISS.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe g/CS2103T p/98765432 e/johnd@example.com tg/johndoe gh/johndoe` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `type/TYPE [date/DATE]` can be used as `type/deadline [date/2021-09-11]` or as `type/deadline`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME g/GROUP1 [g/GROUP2] p/PHONE_NUMBER e/EMAIL tg/TELEGRAM_USERNAME gh/GITHUB_USERNAME`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Usernames can optionally be prepended with '@'. SWEe-book takes care of it!
</div>

Examples:
* `add n/John Doe g/CS2103T p/98765432 e/johnd@example.com tg/@johndoe gh/johndoe`
* `add n/Betsy Crowe p/92221234 g/CS2103T g/CS2101 e/betsycrowe@example.com tg/betsyyy gh/crowebetsy`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [g/GROUP1] [g/GROUP2] [p/PHONE] [e/EMAIL] [tg/TELEGRAM] [gh/GITHUB]`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult1.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Filtering persons by a specified group: `group`
Retrieves the list of people to those who are in the specified group.

Format: `group GROUP_NAME`
* `GROUP_NAME` refers to one of the 2 groups: CS2101 or CS2103T.
* `GROUP_NAME` is not case-sensitive.

Examples:
* `group CS2103T` returns people in CS2103T
* `group CS2101` returns people in CS2101

### Adding a Task : `addTask`

Adds a new task (determined by what is inputted for type/TYPE) with task description as specified by d/DESCRIPTION
for the group specified by g/GROUP due at date specified by date/DATE.

Format: `addtask d/DESCRIPTION g/GROUP type/TYPE [date/DATE] [pty/PRIORITY] [recurring/RECURRING_FREQUENCY]`
* `GROUP` refers to one of the 2 groups: `CS2101` or `CS2103T`
* `TYPE` refers to one of the 3 types of tasks: `todo`, `event` or `deadline`
* `DATE` is in YYYY-MM-DD format and is only needed for events or deadlines (i.e. `DATE` is optional for todo tasks)
* `PRIORITY` refers to one of the 3 levels of priorities / importance of the task: `low`, `med` (default) or `high`
* `RECURRING_FREQUENCY` refers to one of the 3 different frequencies that the task could occur: `week`, `month` or `year` (where `week` means that the task is recurring weekly)

Examples:
* `addTask d/Project meeting g/CS2103T type/todo pty/low`
* `addTask d/Presentation 1 g/CS2101 type/deadline date/2020-11-02 pty/high`
* `addTask d/Mock QnA 1 g/CS2101 type/event date/2020-10-02 recurring/month`

### Editing a task: `editTask`

Edits an existing task in the task list.

Format: `editTask INDEX [d/DESCRIPTION] [g/GROUP] [type/TYPE] [date/DATE] [pty/PRIORITY] [recurring/RECURRING_FREQUENCY]`
* Edits the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* To set a recurring task to be non-recurring, specify `recurring/none`.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Please specify a date if you are changing a todo with no associated date into a deadline/event or recurring task!
</div>

Examples:
* `editTask 1 d/OP2 rehearsal g/CS2101 type/Event date/2021-11-11` Edits the description, group, type of task, date of the first task in the task list.

### Deleting a task: `deleteTask`

Format: `deleteTask INDEX`

Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …

Examples:
* `deleteTask 1` deletes the 1st task in the task list.

### Marking a task as done: `doneTask`

Format: `doneTask INDEX`

Marks the task at the specified `INDEX` as done.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …

Examples:
* `doneTask 1` marks the 1st task in the task list as done.

### Sorting tasks: `sortTasks`
Sort tasks based on their description or their deadlines (chronologically, or the reverse), or by time added.

Format: `sortTasks param/PARAMETER o/ORDER`
* The sort is case-insensitive. e.g CS2103 will be lexicographically identical to cs2103
* PARAMETER includes 'desc' (for description), 'date' (for deadline / time of event), and 'group'.
* ORDER includes 'a' for ascending order (0-9 and A-Z, oldest to newest) and 'd' for descending order(Z-A and 9-0 / newest to oldest)
* When the tasks are sorted by date, 'todo' tasks will always be at the bottom of the list.

Examples:
If the following are in the task list:

Type | Description | Date
 ----- | ----- | -----
 `Todo`| user guide  | 28-09-2020
 `Deadline`| quiz | 25-09-2020
 `Event` | test | 21-09-2020
 `Event` | exam | 22-09-2020

* `sortTasks param/desc o/a` returns tasks with the following descriptions  `user guide`, `test`, `quiz`, `exam`
* `sortTasks param/date o/a` returns returns tasks with the following descriptions   `test`, `exam`, `quiz`, `user guide`

### Filtering tasks by modules: `filterTasks`
Filter tasks based on a criterion

Format: `filterTasks FILTER_CRITERION`
* Filters the task by the specified `FILTER_CRITERION`
* `FILTER_CRITERION` refers to either `g/GROUP`, `date/DATE`, `type/TASKTYPE`, `d/DESCRIPTION` or `pty/PRIORITY`
* Tasks corresponding to the criterion specified will be shown

Examples:
* `filterTasks g/CS2101` shows all the tasks related to CS2101 group
* `filterTasks date/2021-10-10` shows all the tasks with date of 10 Oct 2021

### Listing all tasks: `listTasks`
Shows a list of all tasks in the address book.

Format: `listTasks`
* Lists all tasks for the current user

Examples:
* `listTasks` returns all tasks

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

SWEe-book data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

SWEe-book data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME g/GROUP1 [g/GROUP2] p/PHONE_NUMBER e/EMAIL tg/TELEGRAM_USERNAME gh/GITHUB_USERNAME` <br> e.g., `add n/John Doe g/CS2103T p/98765432 e/johnd@example.com tg/@johndoe gh/johndoe`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [g/GROUP1] [g/GROUP2] [p/PHONE] [e/EMAIL] [tg/TELEGRAM] [gh/GITHUB]`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Group** | `group GROUP` <br> e.g., `group CS2103T`
**List** | `list`
**Help** | `help`
**Add Task** | `addTask d/DESCRIPTION g/GROUP type/TYPE [date/DATE] [pty/PRIORITY] [recurring/RECURRING_FREQUENCY]`
**Edit Task** | `editTask INDEX [d/DESCRIPTION] [g/GROUP] [type/TYPE] [date/DATE] [pty/PRIORITY] [recurring/RECURRING_FREQUENCY]`
**Delete Task** | `deleteTask INDEX`<br> e.g., `deleteTask 3`
**Done Task** | `doneTask INDEX`<br> e.g., `doneTask 3`
**Sort Tasks** | `sortTasks p/PARAMETER o/ORDER` <br> e.g., `sortTasks p/desc o/1`
**Filter Tasks** | `filterTasks FILTER_CRITERION` <br> e.g., `filterTasks g/CS2101`
**List Tasks** | `listtasks [date/DATE]`
