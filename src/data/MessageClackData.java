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

    /**This constructor should immediately encrypt the message using the key, i.e., this.message
     should be an encrypted message.
     *
     * @param userName - name of client user
     * @param message - instant message
     * @param key - the key is the word that is used for encryption
     * @param type-  the kind of data exchanged between the client and the server
     */
    public MessageClackData( String userName, String message, String key, int type )
    {
        super(userName, type);
        this.message = encrypt(message, key);
    }
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

    /**
     *
     * @param key- the key is the word that is used for encryption
     * @return decrypted message
     */
    public String getData(String key) {return decrypt(message, key);}





    @Override
    public int hashCode()
    {
        int r = 17;

        r = 37 * r + (message == null ? 0 : message.hashCode());

        return r;
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
    public String toString() {
        return String.format("<%s file_name=\"%s\">", getClass().getSimpleName(), message.substring(0, Math.min(message.length(), 8)));
    }

}
