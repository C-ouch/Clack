package data;

import java.io.*;


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

    //Constructors

    /**
     * @param userName calls username from the parent class
     * @param fileName file's name
     * @param type represents the kind of data exchanged between the client and the server
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
        String line;
        try {
            File myFile = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(myFile));

            while ((line = br.readLine()) != EOS) {
                fileContents += line + "\n";

            }
            //System.out.println(fileContents);
            br.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("This file cannot be found.");
        } catch (IOException ioe) {
            System.err.println("Error in reading or closing the file.");
        }
    }

    /**
     *
     * @param key- the key is the word that is used for encryption
     * @throws IOException error in reading or closing file
     */
    public void readFileContents(String key) throws IOException {
        fileContents = "";
        String line;
        try {
            File myFile = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(myFile));

            while ((line = br.readLine()) != EOS) {
                fileContents += line + "\n";

            }
            fileContents = encrypt(fileContents, key);
            //System.out.println(fileContents);
            br.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("This file cannot be found.");
        } catch (IOException ioe) {
            System.err.println("Error in reading or closing the file.");
        }



    }

    /**
     * writes
     * @throws IOException error in reading or closing file
     */
    public void writeFileContents() throws IOException {

        try {
            File myFile = new File(fileName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(myFile));

            bw.write(fileContents);
            bw.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("This file cannot be found.");
        } catch (IOException ioe) {
            System.err.println("Error in reading or closing the file.");
        }


    }

    /**
     *
     * @param key- the key is the word that is used for encryption
     * @throws IOException error in reading or closing file
     */
    public void writeFileContents(String key) throws IOException {
        try {
            File myFile = new File(fileName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(myFile));
            fileContents = decrypt(fileContents,key);
            bw.write(fileContents);
            bw.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("This file cannot be found.");
        } catch (IOException ioe) {
            System.err.println("Error in reading or closing the file.");
        }

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
        return "";
    }


}
