package data;

public class ClackData
{
    private String userName;                    //private or public?
    private int type;                           //private or public?
    private ClackData date = new ClackData();   //private or public?


    //CONSTRUCTORS

    // constructor to set up userName and type, date should be created automatically here
    public ClackData(String userName, int type)
    {

    }

    //constructor to create anonymous user, whose name should be “Anon”, should call another constructor
    public ClackData(int type)
    {

    }

    //default constructor, should call another constructor.
    //What should “type” get defaulted to?
    public ClackData()
    {

    }

    //FUNCTIONS
    public void getType()
    {

    }

    public void getUserName()
    {

    }
    public void getDate()
    {

    }

    //abstract method to return the data contained in this class (contents of instant
    //message or contents of a file)
    public void getData()
    {

    }


}

