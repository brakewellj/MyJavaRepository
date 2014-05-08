package com.jdb.socialNet;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SocialHub {
	
	private List <SocialMessage> socialMessages;
	private Map <String,SocialUser> registeredUsers;
	
	public SocialHub(){
		socialMessages = new ArrayList<SocialMessage>();
		registeredUsers = new HashMap<String,SocialUser>();
	}
	
	public void registerUser(SocialUser user){
		registeredUsers.put(user.getUid(),user);
	}
	
	public boolean isUserRegistered(String uid){
		return registeredUsers.containsKey(uid);
	}
	
	public SocialUser getRegisteredUser(String uid){
		//TODO handle no registered users
		return registeredUsers.get(uid);
	}
	
	public void addMessage(SocialMessage msg){
		socialMessages.add(msg);
	}

	public List<SocialMessage> getAllMessages(){
		return socialMessages;
	}
	
	public List<SocialMessage> getMessages(String uid){
		SocialUser user = getRegisteredUser(uid);
		List <SocialMessage> userMessages = new ArrayList<SocialMessage>();
		for (SocialMessage msg: socialMessages){
			//can see own messages
			if (msg.getSocialuser().getUid().equals(user.getUid())) {
				userMessages.add(msg);
			}
			//can see messages of follows. OK maybe should use uids.
			if (user.getFollows().contains(msg.getSocialuser())){
				userMessages.add(msg);
			}
		}
		return userMessages;
	}
	
	public synchronized void post(String uid, String msg){
		addMessage(new SocialMessage(getRegisteredUser(uid),msg));
	}	
	
	public String readMessages(String uid){
		StringBuilder sb = new StringBuilder();
		SocialUser user = getRegisteredUser(uid);
		if (user != null){
			sb.append("Messages for "+user.getName()+"\n");
			for (SocialMessage msg : getMessages(user.getUid())){
				Date now = new Date();
				long secDiff = ((now.getTime() - msg.getMessageDate().getTime()))/1000;
				sb.append(msg.getSocialuser().getName()+" - "+msg.getMessageText()+" ("+secDiff+" seconds ago)\n" );
			}
			sb.append("\n");
		}
		else{
			sb.append("User "+uid+" is not registered\n");
		}
		return sb.toString();
	}	
	

}
