package app;

public class Row
{
    public static final int USERNAME_SIZE = 32;
    public static final int EMAIL_SIZE = 255;
    public static final int SIZE = (Character.SIZE * (USERNAME_SIZE + EMAIL_SIZE)) + Integer.SIZE;

    private int id;
    private char[] email;
    private char[] username;

    /**
     * Constructor for Row
     * @param id the id for the row object
     * @param username
     * @param email
     * @throws IllegalArgumentException
     * */
    public Row(int id, String username, String email) throws IllegalArgumentException {
        if(username.length() > USERNAME_SIZE || email.length() > EMAIL_SIZE)
        {
            throw new IllegalArgumentException("Error: Please ensure that username size is " + USERNAME_SIZE + " and email size is " + EMAIL_SIZE);
        }

        if(id < 0)
            throw new IllegalArgumentException("Error: id value can only be a positive value.");

        this.id = id;
        this.username = username.toCharArray();
        this.email = email.toCharArray();
    }

    /**
     * Returns id for row
     * @return id
     * */
    public long getId()
    {
        return id;
    }

    /**
         * Returns username for row
         * @return username
         * */
    public String getUsername()
    {
        return String.valueOf(username);
    }

    /**
         * Returns email for row
         * @return email
         * */
    public String getEmail()
    {
        return String.valueOf(email);
    }

    /**
     * Returns the string representation of the object
     * */
    @Override
    public String toString()
    {
        return "(" + id + ", " + String.valueOf(username) + ", " + String.valueOf(email) + ")";
    }
}
