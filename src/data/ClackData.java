package data;

import java.util.Date;

/**
 *This class is a superclass of MessageClackData and FileClackData.
 * It consists of the username of the client user, the date and
 * time at which the data was sent and the data itself
 *
 * @author Elek Ye and Evan Couchman
 */

public abstract class ClackData
{
    protected String userName;                    //private or public?
    protected int type;                           //private or public?
    protected Date date;                          //private or public?

    //type variable's constant values:
    final static public int CONSTANT_LISTUSERS = 0;
    final static public int CONSTANT_LOGOUT = 1;
    final static public int CONSTANT_SENDMESSAGE = 2;
    final static public int CONSTANT_SENDFILE = 3;


    //CONSTRUCTORS
    /** constructor to set up userName and type, date should be created automatically here
     *
     * @param userName
     * @param type
     */
    public ClackData(String userName, int type)
    {
        this.userName = userName;
        this.type = type;
        this.date = new Date();

    }

    /**constructor to create anonymous user
     *
     * @param type
     */
    public ClackData(int type)
    {
        this("Anon", type);


    }

    /**default constructor*/
    public ClackData()
    {
        this("Anon",0);
    }

    //METHODS

    /**
     *
     * @return the type of constant
     */
    public int getType()
    {
        return type;
    }

    /**
     *
     * @return the users name
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     *
     * @return
     */
    public Date getDate()
    {
        return date;
    }

    /**abstract method
     * @return the data contained in this class
     *  (contents of instant message or contents of a file)
     */
    public abstract String getData();


}

