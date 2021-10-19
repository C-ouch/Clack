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
     * @return current date
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

    /**overloaded version of getData()
     *
     * @param key
     * @return
     */
    public abstract String getData( String key );


    /**
     * Takes String and encrypt's it with Vigenere cipher with the key
     * @param inputStringToEncrypt
     * @param key
     * @return
     */
    //Still don't know how to skip  non-alphabet characters
    public static String encrypt(String inputStringToEncrypt, String key)
    {

        //inputStringToEncrypt  = inputStringToEncrypt.toUpperCase();

        //Character Array
        char[] inStr = inputStringToEncrypt.toCharArray();
        int inStrLen = inStr.length;
        int i,j;

        // Creating new char arrays
        char keyword[] = new char[inStrLen];
        char encrypted[] = new char[inStrLen];


/*
        //generate key same size as inputString
        for(i = 0, j= 0; i< inStrLen; ++i, ++j)
        {
            if(j == key.length())
                j=0;
            keyword[i] = key.charAt(j);
            //decrypt("","");
        }
*/
        for(i = 0, j = 0; i < inStrLen; ++i, ++j) {
            if (Character.isAlphabetic(inStr[i])) {
                j = j % key.length();
                char k;
                char a;

                if (Character.isUpperCase(inStr[i])) {
                    k = Character.toUpperCase(key.charAt(j));
                    a = 'A';
                } else {
                    k = Character.toLowerCase(key.charAt(j));
                    a = 'a';
                }

                encrypted[i] = (char) (((inStr[i] - a + k - a) % 26) + a);
            }
        }

        return String.valueOf(encrypted);
    }

    /**
     *This function decrypts the encrypted text
     * @param inputStringToDecrypt
     * @param key
     * @return
     */
    public static String decrypt( String inputStringToDecrypt, String key )
    {
        //inputStringToDecrypt = inputStringToDecrypt.toUpperCase();

        //Character Array
        char inStr[] = inputStringToDecrypt.toCharArray();
        int inStrLen = inStr.length;
        int i,j;

        char keyword[] = new char[inStrLen];
        char decrypted[] = new char[inStrLen];
/*
        for(i = 0, j= 0; i< inStrLen; ++i, ++j)
        {
            if(j == key.length())
                j=0;
            keyword[i] = key.charAt(j);
        }
*/
        for(i = 0, j = 0; i < inStrLen; ++i, ++j) {
            if (Character.isAlphabetic(inStr[i])) {
                j = j % key.length();
                char k;
                char a;

                if (Character.isUpperCase(inStr[i])) {
                    k = Character.toUpperCase(key.charAt(j));
                    a = 'A';
                } else {
                    k = Character.toLowerCase(key.charAt(j));
                    a = 'a';
                }

                decrypted[i] = (char) (((inStr[i] - a - k - a) % 26) + a);
            }
        }


        return String.valueOf(decrypted);

    }




}

