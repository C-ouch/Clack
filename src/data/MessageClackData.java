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

    /**Calls the super constructor and sets up message
     *
     * @param userName - name of client user
     * @param message - instant message
     * @param type -  the kind of data exchanged between the client and the server
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
     * @return the instant message
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
