package data;
/**
 *This class is a child class of ClackData.
 * It consists of the name and contents of a file.
 *
 * @author Elek Ye and Evan Couchman
 */

public class MessageClackData extends ClackData {
    private String message;

    //CONSTRUCTORS

    /**
     *
     * @param userName
     * @param message
     * @param type
     */
    public MessageClackData(String userName, String message, int type)
    {
        super(userName, type);
        this.message = message;
    }

    /**
     * default
     */
    public MessageClackData()
    {this("Anon", null,0);}

    //METHODS

    /**
     *
     * @return
     */
    public String getData()
    {
        return message;
    }


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
    @Override
    public String toString()
    {

        return "";
    }


}
