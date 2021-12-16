package data;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;


/**
 * This class is a child class of ClackData.
 * It consists of the name and contents of a file.
 *
 * @author Elek Ye and Evan Couchman
 */

public class FileClackData extends ClackData {
    private static final String EOS = null;
    private String fileName;
    private String fileContents;
    public String mime;


    //Constructors

    /**
     * @param userName calls username from the parent class
     * @param fileName file's name
     * @param type represents the kind of data exchanged between the client and the server
     */
    public FileClackData(String userName, String fileName, int type) {
        super(userName, type);
        this.fileName = fileName;
        try{
            readFileContents();
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * default constructor
     */
    public FileClackData() {
        this("Anon", null, 0);
    }


    //METHODS

    /**
     * takes in a filename and sets the string fileName to it
     * @param fName user input file's name
     */
    public void setFileName(String fName) {
        this.fileName = fName;
    }

    /**
     * gives out what is in the String fileName
     * @return fileName The File's name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * tells what is in the String of file contents
     * @return fileContents
     */
    public String getData() {
        return fileContents;
    }

    /**
     * @param key- the key is the word that is used for encryption
     * @return decrypted file contents
     */
    public String getData(String key) {
        return decrypt(fileContents, key);
    }

    /**
     * This method reads the contents of the file lie by line
     * @throws IOException error in reading or closing file
     */
    public void readFileContents() throws IOException {
        fileContents = "";
        Path path = new File(fileName).toPath();
        mime = Files.probeContentType(path);
        System.err.println("File: " + fileName + "\nMIME: " + mime);
        byte[] buffer = Files.readAllBytes(path);
        fileContents = Base64.getEncoder().encodeToString(buffer);
    }

    /**
     *
     * @param key- the key is the word that is used for encryption
     * @throws IOException error in reading or closing file
     */
    public void readFileContents(String key) throws IOException {
        fileContents = "";
        readFileContents();
        fileContents = encrypt(fileContents, key);
    }

    /**
     * writes
     * @throws IOException error in reading or closing file
     */
    public void writeFileContents() throws IOException {
        File myFile = new File(fileName);
        Files.write(myFile.toPath(), Base64.getDecoder().decode(fileContents));
    }

    /**
     *
     * @param key- the key is the word that is used for encryption
     * @throws IOException error in reading or closing file
     */
    public void writeFileContents(String key) throws IOException {
        fileContents = decrypt(fileContents, key);
        writeFileContents();
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 37 * result + (fileName == null ? 0 : fileName.hashCode());         //String
        result = 37 * result + (fileContents == null ? 0 : fileContents.hashCode()); //String
        result = 37 * result + type;                                                 //Int

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
        return String.format("<%s file_name=\"%s\">", getClass().getSimpleName(), fileName == null ? "null" : fileName);
    }


}
