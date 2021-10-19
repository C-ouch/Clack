package data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is a child class of ClackData.
 * It consists of the name and contents of a file.
 *
 * @author Elek Ye and Evan Couchman
 */

public class FileClackData extends ClackData {
    private String fileName;
    private String fileContents;

    //Constructors

    /**
     * @param userName
     * @param fileName
     * @param type
     */
    public FileClackData(String userName, String fileName, int type) {
        super(userName, type);
        this.fileName = fileName;
    }

    /**
     * default constructor
     */
    public FileClackData() {
        this("Anon", null, 0);
    }


    //METHODS

    public void setFileName(String fName) {
        this.fileName = fName;
    }

    public String getFileName() {

        return fileName;
    }

    public String getData() {
        return fileContents;
    }

    public String getData(String key) {return decrypt(fileContents, key);}


    public void readFileContent() throws IOException
    {
        FileReader reader = new FileReader(fileName);
        boolean readFin = false;


        while (!readFin) {
            int nextCharAsInt = reader.read();

            readFin = nextCharAsInt == -1;

            if (!readFin) {
                char nextChar = (char) nextCharAsInt;
                System.out.println(nextChar);
            }
        }
        reader.close();
        System.out.println();
    }

    public void writeFileContents() throws IOException {

    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 37 * result + (fileName == null ? 0 : fileName.hashCode());
        result = 37 + result + (fileContents == null ? 0 : fileContents.hashCode());
        result = 37 + result + type;

        return result;

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        FileClackData f = (FileClackData) obj;
        return (this.getFileName() == f.getFileName());
    }

    /*
    should be overridden to return a full description of the class with all instance
    variables, including those in super class
     */
    @Override
    public String toString() {
        return "";
    }


}
