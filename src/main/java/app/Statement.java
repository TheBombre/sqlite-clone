package app;

public class Statement
{
    private String source;
    private SupportedCommands commandType;

    public Statement(String source, SupportedCommands type)
    {
        this.source = source;
        this.commandType = type;
    }

    public Statement(String source)
    {
        this.source = source;
        this.commandType = null;
    }

    /**
     * Returns the source string
     * @return source
     * */
    public String getSource()
    {
        return source;
    }

    /**
     * Returns the command type
     * @return SupportedCommands enum
     * */
    public SupportedCommands getCommandType()
    {
        return commandType;
    }

    /**
     * Assigns the command type of the statement
     * */
    public void setCommandType(SupportedCommands type)
    {
        this.commandType = type;
    }

    @Override
    public String toString()
    {
        return "Source: " + source + "\nType: " + commandType + "\n";
    }
}
