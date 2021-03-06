/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package app;

import java.io.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class MainTest
{
    private Main main;

    @BeforeAll
    public void startApp()
    {
        main = new Main();
    }

    // No input
    @Test()
    @DisplayName("Tests for unsupported commands")
    public void testUnsupportedCommands() throws IllegalArgumentException {

        assertThrows(IllegalArgumentException.class, () -> {
            main.runApp("");
        }, "Empty string");

        assertThrows(IllegalArgumentException.class, () -> {
            main.runApp(".");
        }, "Not supported meta command");
    }

    // INsufficient arguements - insert
    // Too many arguements  - insert
    @Test()
    public void testInsertCommand() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> {
            main.runApp("insert 1");
        }, "Not enough arguments");

        assertThrows(IllegalArgumentException.class, () -> {
            main.runApp("insert 1 test bad test");
        }, "Too many arguments");

        assertThrows(IllegalArgumentException.class, () -> {
            main.runApp("insert carlton test test");
        }, "Invalid id type");
    }

    // No arguements required - select
    @Test()
    public void testSelectCommand() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> {
            main.runApp("select 1");
        }, "Unnecessary arugments provided");
    }

    @Test()
    public void testFaultyInsertValues() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> {
            main.executeInsert("got");
        }, "Insufficient insert arguments");

        assertThrows(IllegalArgumentException.class, () -> {
            main.executeInsert("1 " + "a".repeat(33) + " test");
        }, "Username length should be invalid");

        assertThrows(IllegalArgumentException.class, () -> {
            main.executeInsert("1 " + "ater " + "t".repeat(256));
        }, "Email length should be invalid");

        assertThrows(IllegalArgumentException.class, () -> {
            main.executeInsert("-20 test got");
        }, "Negative id should not works.");
    }

    // Full page
    @Test()
    public void testFullPage() throws IndexOutOfBoundsException {
        Page page = new Page();


        for(int i = 0; i < Page.MAX_ROWS ; i++)
        {
            Row row = new Row(i, "bob", "bob@example.com");
            page.add(row);
        }

        Row row = new Row(Page.MAX_ROWS, "bob", "bob@example.com");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            page.add(row);
        }, "Page should be full");
    }

    // Table being full
    @Test()
    public void testFullTable()
    {
        Table table = new Table();

        for(int i = 0; i < Table.TABLE_MAX_ROWS; i++) {
            Row row = new Row(i, "bob", "bob@example.com");
            table.add(row);
        }

        Row row = new Row(Table.TABLE_MAX_ROWS + 1, "bob", "bob@example.com");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            table.add(row);
        }, "Table should be full");
    }

    @Test()
    public void testSupportedCommands()
    {
        int id = 20;
        String username = "cat420";
        String email = "cat420@420.com";
        main.runApp(String.format("insert %d %s %s", id, username, email));
        // assertEquals("Executed.\n", outContent.toString(), "App not executed as expected");
        // String[] output = outContent.toString().split("\n");
        // main.runApp("select");
        // assertEquals(String.format("(%d, %s, %s)\n", id, username, email), output[0], "Select should show row " + output[0]);
        //
        // for(String element : output) {
        // assertEquals(String.format("(%d, %s, %s)\n", id, username, email), element, "Select should show row " + element);
        // }
    }

}
