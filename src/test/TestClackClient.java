package test;

import main.ClackClient;

/**
 * the class TestClackClient is used to test ClackClient 
 * @author Elek Ye and Evan Couchman
 *
 */

public class TestClackClient {

	/**
	 * 
	 * @param args the command line argument.
	 */
	public static void main(String[] args) {
		/* test1: test constructor without any arguments */
		try {
			new ClackClient();
			System.out.println("test1: new ClackClient without any arguments pass");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("test1: new ClackClient without any arguments failed");
		}
		
		/* test2: test constructor with user name */
		try {
			new ClackClient("test_uesr");
			System.out.println("test2: new ClackClient with username pass");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("test2: new ClackClient with username failed");
		}
		
		/* test3: test constructor with user name and host name */
		try {
			new ClackClient("test_uesr", "localhost");
			System.out.println("test3: new ClackClient with username and hostname pass");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("test3: new ClackClient with username and hostname failed");
		}
		
		/* test4: test constructor with invalid user name */
		try {
			new ClackClient(null);
			System.out.println("test4: new ClackClient with invalid username failed");
		} catch (IllegalArgumentException e) {
			System.out.println("test4: new ClackClient with invalid username pass");
		}
		
		/* test5: test constructor with invalid user name */
		try {
			new ClackClient("test_uesr", null);
			System.out.println("test5: new ClackClient with invalid hostname failed");
		} catch (IllegalArgumentException e) {
			System.out.println("test5: new ClackClient with invalid hostname pass");
		}
		
		ClackClient client = new ClackClient("test_user", "localhost", 1234);
		
		/* test6: test getUserName() */
		if ("test_user".equals(client.getUserName())) {
			System.out.println("test6: test getUserName() pass");
		}
		else {
			System.out.println("test6: test getUserName() failed");
		}
		
		/* test7: test getHostName() */
		if ("localhost".equals(client.getHostName())) {
			System.out.println("test7: test getHostName() pass");
		}
		else {
			System.out.println("test7: test getHostName() failed");
		}
		
		/* test8: test getUserName() */
		if (client.getPort() == 1234) {
			System.out.println("test8: test getPort() pass");
		}
		else {
			System.out.println("test8: test getPort() failed");
		}
		
		/* test9: test toString() */
		System.out.println("toString:" + client);
		System.out.println("test9: test toString() pass");
		
		/* test10: test getUserName() */
		client.start();
		System.out.println("test10: test start() pass");
		
		/* test11: test readClientData()
		 *  type "SENDFILE Part2_document.txt" in stdin 
		 *  this case will test FileClackData
		 */
		client.readClientData();
		client.printData();
		
		/* 
		 * test12: test readClientData()
		 * type "hello world" in stdin
		 * this case will test MessageClackData
		 */
		client.readClientData();
		client.printData();
		
		/* 
		 * test12: test readClientData()
		 * type "DONE" in stdin
		 */
		client.readClientData();
	}
}