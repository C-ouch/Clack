package test;

/**
 * @author Elek Ye and Evan Couchman
 * A class that represents the client user
 */

public class TestClackData {
        private String userName;                    //private or public?
        private int type;                           //private or public?
        private TestClackData date = new TestClackData();   //private or public?


        //CONSTRUCTORS

        // constructor to set up userName and type, date should be created automatically here
        public TestClackData(String user, int t) {
            userName= user;
            type =t;

        }

        //constructor to create anonymous user, whose name should be “Anon”, should call another constructor
        public TestClackData(int t) {
            this ("anna",t);

        }

        //default constructor, should call another constructor.
        //What should “type” get defaulted to?
        public TestClackData() {
            this("anna",0);

        }

        //FUNCTIONS
        public int getType() {
            return type;

        }

        public String getUserName() {
            return userName;

        }

        public TestClackData getDate() {
            return date;

        }

        //abstract method to return the data contained in this class (contents of instant
        //message or contents of a file)
        public  String getData() {


            return null;
        }
    }


