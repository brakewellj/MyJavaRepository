package com.jdb.socialNet;

public class SocialMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Create message hub
		SocialHub hub = new SocialHub();
		
		//Register/subscribe users.
		hub.registerUser(new SocialUser("Alice","Alice"));
		hub.registerUser(new SocialUser("Bob","Bob"));
		hub.registerUser(new SocialUser("Charlie","Charlie"));
		
		
		//Test following 	
		hub.getRegisteredUser("Charlie").follows(hub.getRegisteredUser("Alice"));
		hub.getRegisteredUser("Charlie").follows(hub.getRegisteredUser("Bob"));
		hub.getRegisteredUser("Bob").follows(hub.getRegisteredUser("Charlie"));

		
		//Test posting 	
		hub.post("Alice","hellow world");
		sleep(2);
		hub.post("Charlie","how are things");
		sleep(1);
		hub.post("Alice","things looking up");
		sleep(1);
		hub.post("Alice","Goodbye");
		
		//Test read messages
		System.out.println(hub.readMessages("Charlie"));
		System.out.println(hub.readMessages("Alice"));
		
		// add console stuff
		MyConsole myConsole = new MyConsole(hub);
		new Thread(myConsole).start();
		
		System.out.println("End of main");
			
	}
	
	private static void sleep(int t){
		try {
			Thread.sleep(t*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		
	}

}
