package app;

import java.util.Scanner;

public class Main
{
    private final int numberOfInsertArguments = 3;

    public static void main(String[] args)
    {
        Main main = new Main();
        Scanner inputReader = new Scanner(System.in);
        main.printStartingText();

        while(true) {
            System.out.print("db > ");
            String command = inputReader.nextLine();
            Statement statement = new Statement(command);

            try {
                if(command.charAt(0) == '.') {
                    main.handleMetaCommands(statement);
                } else {
                    main.handleStandardCommands(statement);
                }
            } catch(RuntimeException error) {
                System.out.println(error.getMessage());
            }
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
    public void handleMetaCommands(Statement statement) throws IllegalArgumentException {
        String command = statement.getSource();

        if(command.equals(".exit"))
        {
            statement.setCommandType(SupportedCommands.META_EXIT);
            System.exit(0);
        } else
        {
            throw new IllegalArgumentException("Unrecognised command '" + command + "'.");
        }
    }

    /**
     * Checks the statement source if it has a supported command then handles it appropriately
     * @param statement a statement object
     * @throws IllegalArgumentException
     * */
    public void handleStandardCommands(Statement statement) throws IllegalArgumentException {
        String command = statement.getSource();

        if(command.startsWith("insert"))
        {
            int argCount = getArgumentCount(command);

            if(argCount < numberOfInsertArguments) {
                throw new IllegalArgumentException("Insufficient arguments supplied to insert command.");
            } else if(argCount > numberOfInsertArguments) {
                throw new IllegalArgumentException("Too many arguments supplied to insert command.");
            }

            statement.setCommandType(SupportedCommands.INSERT);
        }
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

        return count - 1; // not counting the first element being the command
    }


}
