package com.jdb.socialNet;

import java.io.Console;

public class MyConsole implements Runnable {
	
	private SocialHub hub;
	private enum Command {POSTING, READING, FOLLOWING, WALL, EXIT, SUBSCRIBE};
	
	public MyConsole(SocialHub hub){
		this.hub = hub;
	}

	@Override
	public void run() {
		boolean consoleRunning = true;
		System.out.println("Console running");
        Console console = System.console();
        if (console == null) {
            System.out.println("Unable to fetch console");
            consoleRunning = false;
        }
        
       while (consoleRunning)
       {
        	String line =  console.readLine();        	
	        String[] tokens = line.split(" ");
	        Command cmd = getCommand(tokens);
	        switch (cmd){
	        	case READING:{
	        		System.out.println("Reading");
	        		console.printf(hub.readMessages(tokens[0]));
	        		break;
	        	}
	        	case POSTING:{
	        		System.out.println("Posting");
	        		String uid = tokens[0];
	        		String param = line.substring(line.indexOf("->")+3);
	        		hub.post(uid,param);
	        		break;
	        	}
	        	case FOLLOWING:{
	        		System.out.println("Following");
	        		String uid = tokens[0];
	        		String param = tokens[2];
	        		if (hub.isUserRegistered(uid) && hub.isUserRegistered(param))
	        		{
	        			hub.getRegisteredUser(uid).follows(hub.getRegisteredUser(param));
	        		}
	        		else{
	        			System.out.println("user or follower not Registered");
	        		}
	        		break;  		
	        	}
	           	case WALL:{
	        		System.out.println("Wall");
	        		//TODO
	        		break;
	           	}
	           	case SUBSCRIBE:{
	        		System.out.println("Subscribe");
	        		String uid = tokens[0];
	        		if (!hub.isUserRegistered(uid)){
	        			hub.registerUser(new SocialUser(uid,uid));
	        		}
	        		else{
	        			System.out.println("User "+uid+" is already subscribed");
	        		}
	        		break;
	           	}
	           	case EXIT:{
	        		System.out.println("Exit");
	        		consoleRunning = false;
	        		break;
	        	}
	        }
        }  
		//sleep(20);
		System.out.println("Console exiting");

	}
	
	private Command getCommand(String tokens[]){
        Command cmd = Command.READING;
        if (tokens.length > 1){
        	if (tokens[1].equals("->")){
        		cmd = Command.POSTING;
        	}
        	else if (tokens[1].equals("follows")){
        		cmd = Command.FOLLOWING;
        	}
        	else if (tokens[1].equals("wall")){
        		cmd = Command.WALL;
        	}
        	else if (tokens[1].equals("subscribe")){
        		cmd = Command.SUBSCRIBE;
        	}
        }
        else {
        		if (tokens[0].equals("Exit")){
        			cmd = Command.EXIT;
        		}
        		else{
        			cmd = Command.READING;
        		}
        }
        return cmd;
	}
}
