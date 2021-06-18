package app;

import java.util.Scanner;

public class Main
{
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
            statement.setCommandType(SupportedCommands.METAEXIT);
            System.exit(0);
        } else
        {
            throw new IllegalArgumentException("Unrecognised command '" + command + "'.");
        }
    }


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
                }
            } catch(IllegalArgumentException error) {
                System.out.println(error.getMessage());
            }
        }
    }
}
