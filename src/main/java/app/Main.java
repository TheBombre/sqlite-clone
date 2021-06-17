package app;

import java.util.Scanner;

public class Main
{
    // TODO: Print out starting text
    public void printStartingText()
    {
        System.out.println("Sqlite clone");
    }


    public static void main(String[] args)
    {
        Main main = new Main();
        Scanner inputReader = new Scanner(System.in);
        main.printStartingText();

        while(true) {
            System.out.print("db > ");
            String command = inputReader.nextLine();

            if(command.equals(".exit")) {
                System.exit(0);
            } else {
                System.out.println("Unrecognised command " + command);
            }
        }
    }
}
