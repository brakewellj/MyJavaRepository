package com.jdb.socialNet;

import java.util.HashSet;
import java.util.Set;

public class SocialUser {
	
	public SocialUser(String uid, String name){	
		this.followers = new  HashSet<SocialUser>() ;
		this.follows = new HashSet<SocialUser>();
		this.uid = uid; // must be unique
		this.name = name;
	}
	
	private String uid;
	private String name;
	private Set<SocialUser> followers;
	private Set<SocialUser> follows;

	// ...

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the name
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getUid() {
		return uid;
	}
	
	public void addFollower(SocialUser usr) {
		followers.add(usr);
	}
	
	public void follows(SocialUser usr) {
		follows.add(usr);
		usr.addFollower(this);
	}
	
	public Set<SocialUser> getFollowers() {
		return followers;
	}
	
	public Set<SocialUser> getFollows() {
		return follows;
	}
	
	

}
