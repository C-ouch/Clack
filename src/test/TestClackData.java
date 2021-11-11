package test;
import data.ClackData;
import data.MessageClackData;
import data.FileClackData;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Elek Ye and Evan Couchman
 * Test Class for Clack Data
 */

public class TestClackData {
    /* public static void main(String[] args)
     {
         MessageClackData h = new MessageClackData();
         h.getData();

         String words = ClackData.encrypt("funfun", "tup");
         System.out.println( words);
         System.out.println(ClackData.decrypt(words, "tup") );
     }*/
    public static void main(String arg[]) throws IOException {
        String plaintext = "Fun HY";
        String keyword = "TUP";
        //ClackData.encrypt(plaintext,keyword);
        String result = ClackData.encrypt(plaintext, keyword);
        System.out.println(result);
        System.out.println(ClackData.decrypt(result, keyword));

        FileClackData fcd = new FileClackData();
        fcd.setFileName("input.txt");
//        fcd.readFileContents();
        fcd.readFileContents(keyword);
        System.out.println(fcd.getData(keyword));
    }
}




