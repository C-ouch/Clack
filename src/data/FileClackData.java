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

    public FileClackData(String userName, String fileName, int type) {

    }

    public FileClackData() { this.fileName = getFileName();    }


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

    public void readFileContent(){

    }

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
