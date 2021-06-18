package app;

public class Table
{
    public static final int MAX_PAGES = 100;
    public static final int TABLE_MAX_ROWS = Page.MAX_ROWS * MAX_PAGES;

    private Page[] pages;
    private int numberOfPages;
    private int numberOfRows;

    /**
     * A constructor for the Table class
     * */
    public Table()
    {
        pages = new Page[MAX_PAGES];
        numberOfRows = 0;
        numberOfPages = 0;
    }

    /**
     * Creates a page object and adds the row to it
     * @param row to add to created page
     * @return created page
     * */
    private Page createPageWithRow(Row row)
    {
        Page page = new Page();
        page.add(row);
        return page;
    }

    /**
     * Adds a row to the table
     * @param row
     * @throws IndexOutOfBoundsException
     * */
    public void add(Row row) throws IndexOutOfBoundsException {
        if(numberOfRows == 0)
        {
            pages[numberOfRows] = createPageWithRow(row);
            numberOfRows++;
            numberOfPages++;
        } else if(pages[numberOfRows - 1].canTakeRow())
        {
            pages[numberOfRows - 1].add(row);
            numberOfRows++;
        } else
        {
            if(numberOfPages + 1 > MAX_PAGES) {
                throw new IndexOutOfBoundsException("Table is full.");
            }

            pages[numberOfRows] = createPageWithRow(row);
            numberOfRows++;
            numberOfPages++;
        }
    }
}
