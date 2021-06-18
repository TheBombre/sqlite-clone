package app;

public class Page
{
    public static final int SIZE = 4096; // 4 kilobytes
    public static final int MAX_ROWS = SIZE / Row.SIZE;
    private Row[] rows;
    private int numberOfRows;

    /**
     * Constructor for Page
     * */
    public Page()
    {
        rows = new Row[MAX_ROWS];
        numberOfRows = 0;
        System.out.println("MAX_ROWS: " + MAX_ROWS);
    }

    /**
     * Adds a row onto the page if there is space available
     * @param row to add
     * @throws IndexOutOfBoundsException when no space left on page
     * */
    public void add(Row row) throws IndexOutOfBoundsException {
        if(!canTakeRow())
        {
            throw new IndexOutOfBoundsException("Page doesn't have enough space to store additional row.");
        }

        rows[numberOfRows] = row;
        numberOfRows++;
    }

    /**
     * Determines if another row can be added to page
     * @return boolean
     * */
    public boolean canTakeRow()
    {
        return numberOfRows + 1 < MAX_ROWS;
    }
}
