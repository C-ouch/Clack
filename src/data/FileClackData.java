package data;
import java.io.File;
import java.io.IOException;

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

    /**
     *
     * @param userName
     * @param fileName
     * @param type
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

    public void setFileName(String fName)
    {
        this.fileName = fName;
    }

    public String getFileName() {

        return fileName;
    }

    public String getData()
    {
        return null;
    }

    public void readFileContent() throws IOException {
    	
    }

    public void writeFileContents() throws IOException {

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
