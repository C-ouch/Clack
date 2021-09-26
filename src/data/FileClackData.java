package data;

import java.io.File;

public class FileClackData {
    private String fileName;
    private String fileContents;

    public FileClackData(String userName, String fileName, int type) {

    }

    public FileClackData() {

    }

    public void setFileName(String fileName) {

    }

    public String getFileName() {

        return fileName;
    }

    public File getData()
    {
        return null ;
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
    public String toString()
    {
        return "";
    }



}
