package app;

import java.util.Scanner;

public class Main
{
    private final int numberOfInsertArguments = 3;
    private Table table;

    public Main()
    {
        table = new Table();
    }

    public static void main(String[] args)
    {
        Main main = new Main();
        Scanner inputReader = new Scanner(System.in);
        main.printStartingText();

        while(true) {
            System.out.print("db > ");
            String command = inputReader.nextLine();
            // Statement statement = new Statement(command);

            try {
                main.runApp(command);
            } catch(Exception error) {
                System.out.println(error.getMessage());
            }
        }
    }

    /**
     * Resolves the given command
     * @param command instructions to do
     * @throws IllegalArgumentException
     * */
    public void runApp(String command) throws IllegalArgumentException{
        if(command.isEmpty())
            throw new IllegalArgumentException("Error: Please specify a command.");

        if(command.charAt(0) == '.')
        {
            handleMetaCommands(command);
        } else
        {
            handleStandardCommands(command);
        }
    }

    // TODO: Print out starting text
    public void printStartingText()
    {
        System.out.println("Sqlite clone");
    }

    /**
     * Checks for any meta commands and if so handles them appropriately
     * @param command the command
     * @throws IllegalArgumentException
     * */
    public void handleMetaCommands(String command) throws IllegalArgumentException {
        if(command.equals(".exit"))
        {
            System.exit(0);
        } else
        {
            throw new IllegalArgumentException("Error: Unrecognised command '" + command + "'.");
        }
    }

    /**
     * Checks the statement source if it has a supported command then handles it appropriately
     * @param statement a statement object
     * @throws IllegalArgumentException
     * */
    public void handleStandardCommands(String command) throws IllegalArgumentException {
        if(command.startsWith("insert"))
        {
            int argCount = getArgumentCount(command);

            if(argCount < numberOfInsertArguments) {
                throw new IllegalArgumentException("Error: Insufficient arguments supplied to insert command.");
            } else if(argCount > numberOfInsertArguments) {
                throw new IllegalArgumentException("Error: Too many arguments supplied to insert command.");
            }

            // statement.setCommandType(SupportedCommands.INSERT);
            executeInsert(command.substring(command.indexOf(" ") + 1));
        } else if(command.startsWith("select"))
        {
            if(getArgumentCount(command) > 0)
                throw new IllegalArgumentException("Error: select command doesn't take arguments.");

            executeSelect();
        } else
        {
            throw new IllegalArgumentException("Error: Unrecognised command '" + command + "'.");
        }
    }

    /**
     * Adds an a row to the table
     * @params commandArgs the arguments given with the insert command
     * @throws IndexOutOfBoundsException if there are not three arguments
     * */
    public void executeInsert(String commandArgs) throws IndexOutOfBoundsException, IllegalArgumentException {
        try
        {
            String[] args = commandArgs.split(" ");
            int id = Integer.parseInt(args[0]);
            String username = args[1];
            String email = args[2];
            Row row = new Row(id, username, email);
            table.add(row);
            System.out.println("Executed.");
        } catch(IndexOutOfBoundsException error)
        {
            throw new IndexOutOfBoundsException("Error: Ensure that the insert command has 3 arguments.");
        } catch(NumberFormatException error)
        {
            throw new IllegalArgumentException("Error: Ensure that id is a numerical value.");
        }
    }

    /**
     * Print all rows in table
     * */
    public void executeSelect()
    {
        table.printRows();
    }

    /**
     * Gets the number of space separated arguments excluding the command
     * @param command command entered by user
     * @return count
     * */
    private int getArgumentCount(String command)
    {
        int count = 0;

        for(String element : command.split(" ")) {
            count++;
        }

        return count - 1; // not counting the first element being the command word
    }


}
