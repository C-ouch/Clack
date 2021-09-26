package data;

public class MessageClackData {
    private String message;

    public MessageClackData(String userName, String message, int type)
    {

    }

    public MessageClackData()
    {}

    //FUNCTIONS
    public void getData()
    {

    }

    //should be correctly overridden
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
