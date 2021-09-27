package data;
/**
 *This class is a child class of ClackData.
 * It consists of the name and contents of a file.
 *
 * @author Evan Couchman
 */

public class MessageClackData extends ClackData {
    private String message;

    public MessageClackData(String userName, String message, int type)
    {

    }

    public MessageClackData()
    {}

    //FUNCTIONS
    public void getData()
    {

    }

    //should be correctly overridden
    @Override
    public int hashCode(){
        return 0;
    }

    @Override
    public boolean equals(Object obj){
        return true;
    }

    /*
    should be overridden to return a full description of the class with all instance
    variables, including those in super class
     */
    public String toString()
    {
        return "";
    }


}
