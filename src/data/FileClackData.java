package data;
import java.io.File;
/**
 *This class is a child class of ClackData.
 * It consists of the name and contents of a file.
 *
 * @author Elek Ye and Evan Couchman
 */

public class FileClackData extends ClackData {
    private String fileName;
    private String fileContents;

    //Constructors

    /**Calls super constructor and sets up file name
     *
     * @param userName -name of client user
     * @param fileName - name of the file
     * @param type -  the kind of data exchanged between the client and the server
     */
    public FileClackData(String userName, String fileName, int type)
    {
        super(userName, type);
        this.fileName = fileName;
    }

    /**
     * default constructor
     */
    public FileClackData() { this("Anon", null,0);    }


    //METHODS

    /**
     *
     * @param fName sets the filename
     */
    public void setFileName(String fName)
    {
        this.fileName = fName;
    }

    /**
     *
     * @return the name of the file
     */
    public String getFileName() {

        return fileName;
    }

    /**
     *
     * @return null
     */
    public String getData()
    {
        return null;
    }

    /**
     *
     */
    public void readFileContent(){

    }

    /**
     *
     */
    public void writeFileContents(){

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
