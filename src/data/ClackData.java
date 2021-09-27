package data;
import java.io.File;

import java.util.Date;

/**
 *This class is a superclass of MessageClackData and FileClackData.
 * It consists of the username of the client user, the date and
 * time at which the data was sent and the data itself
 *
 * @author Evan Couchman
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

    // constructor to set up userName and type, date should be created automatically here
    public ClackData(String userName, int type)
    {
        this.userName = userName;
        this.type = type;
        this.date = null;

    }

    //constructor to create anonymous user, whose name should be “Anon”, should call another constructor
    public ClackData(int type)
    {
        this("Anon", type,);


    }

    //default constructor, should call another constructor.
    //What should “type” get defaulted to?
    public ClackData()
    {
        this("Anon",0,);
    }

    //FUNCTIONS
    public int getType()
    {
        return type;
    }

    public String getUserName()
    {
        return userName;
    }
    public Date getDate()
    {
        return date;
    }

    //abstract method to return the data contained in this class (contents of instant
    //message or contents of a file)
    public abstract File getData();


}

