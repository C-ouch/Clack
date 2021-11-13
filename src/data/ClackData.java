package data;

import java.util.Date;
import java.io.Serializable;

/**
 * This class is a superclass of MessageClackData and FileClackData.
 * It consists of the username of the client user, the date and
 * time at which the data was sent and the data itself
 *
 * @author Elek Ye and Evan Couchman
 */

public abstract class ClackData implements Serializable {
    protected String userName;
    protected int type;
    protected Date date;

    //type variable's constant values:
    final static public int CONSTANT_LISTUSERS = 0;
    final static public int CONSTANT_LOGOUT = 1;
    final static public int CONSTANT_SENDMESSAGE = 2;
    final static public int CONSTANT_SENDFILE = 3;


    //CONSTRUCTORS

    /**
     * constructor to set up userName and type, date should be created automatically here
     *
     * @param userName user's name
     * @param type represents the kind of data exchanged between the client and the server
     */
    public ClackData(String userName, int type) {
        this.userName = userName;
        this.type = type;
        this.date = new Date();

    }

    /**
     * constructor to create anonymous user
     *
     * @param type represents the kind of data exchanged between the client and the server
     */
    public ClackData(int type) {
        this("Anon", type);


    }

    /**
     * default constructor
     */
    public ClackData() {
        this("Anon", 0);
    }

    //METHODS

    /**
     * @return the type of constant
     */
    public int getType() {
        return type;
    }

    /**
     * @return the users name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return current date
     */
    public Date getDate() {
        return date;
    }

    /**
     * abstract method
     *
     * @return the data contained in this class
     * (contents of instant message or contents of a file)
     */
    public abstract String getData();

    /**
     * overloaded version of getData()
     *
     * @param key word that is used to encrypt
     */
    public abstract String getData(String key);


    /**
     * Takes String and encrypt's it with Vigenere cipher with the key
     * @param inputStringToEncrypt the string of letters to be encrypted
     * @param key word that is used to encrypt/decrypt string
     * @return  encrypted string
     */

    public static String encrypt(String inputStringToEncrypt, String key) {


        //Character Array
        char[] inStr = inputStringToEncrypt.toCharArray();
        int inStrLen = inStr.length;
        int i, j;

        // Creating new char arrays
        char[] encrypted = new char[inStrLen];


        for (i = 0, j = 0; i < inStrLen; ++i, ++j) {
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
            } else {
                encrypted[i] = inStr[i];
            }
        }

        return String.valueOf(encrypted);
    }

    /**
     * This function decrypts the encrypted text
     *
     * @param inputStringToDecrypt the encrypted string to be derypted
     * @param key word that is used to encrypt/decrypt string
     * @return decrypted string back to its original self
     */
    public static String decrypt(String inputStringToDecrypt, String key) {

        //Character Array
        char inStr[] = inputStringToDecrypt.toCharArray();
        int inStrLen = inStr.length;
        int i, j;

        char[] decrypted = new char[inStrLen];

        for (i = 0, j = 0; i < inStrLen; ++i, ++j) {
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

                decrypted[i] = (char) (((inStr[i] + 26 - k) % 26) + a);
            } else {
                decrypted[i] = inStr[i];
            }
        }


        return String.valueOf(decrypted);

    }


}

