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

    /**
     *
     * @param fName
     */
    public void setFileName(String fName) {
        this.fileName = fName;
    }

    /**
     *
     * @return fileName
     */
    public String getFileName() {

        return fileName;
    }

    /**
     *
     * @return fileContents
     */
    public String getData() {
        return fileContents;
    }

    /**
     *
     * @param key
     * @return
     */
    public String getData(String key) {return decrypt(fileContents, key);}

    /**
     *
     * @throws IOException
     */
    public void readFileContents() throws IOException
    {
        fileContents= "";
        String line;
        try {
            File myFile = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(myFile));
            /*boolean readFin = false;


            while (!readFin) {
                int nextCharAsInt = reader.read();

                readFin = nextCharAsInt == -1;

                if (!readFin) {
                    char nextChar = (char) nextCharAsInt;
                    fileContents +=nextChar;

                }
            }*/
            while ( (line = br.readLine()) != EOS )
            {
                fileContents += line + "\n";

            }
            System.out.println( fileContents);
            //reader.close();
            //System.out.println(fileContents);
            //System.out.println();
        }catch (FileNotFoundException fnfe){
            System.err.println("This file cannot be found.");
        }catch (IOException ioe){
            System.err.println("Error in reading or closing the file.");
        }
    }

    public void readFileContents(String key) throws IOException
    {
        readFileContents();
        fileContents=encrypt(fileContents,key);
        System.out.println(fileContents);

    }

    public void writeFileContents() throws IOException {

        try {
            File myFile = new File(fileName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(myFile));

            bw.write(fileContents);
            bw.close();
        }catch (FileNotFoundException fnfe){
            System.err.println("This file cannot be found.");
        }catch (IOException ioe){
            System.err.println("Error in reading or closing the file.");
        }


    }

    public void writeFileContents(String key) throws IOException{

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
