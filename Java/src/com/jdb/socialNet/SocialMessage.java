package com.jdb.socialNet;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SocialMessage {
	
	private SocialUser socialuser;
	private String messageText;
	private Calendar messageCal = GregorianCalendar.getInstance();
	
	
	public SocialMessage(SocialUser socialUser, String messageText){
		
		this.messageText = messageText;
		this.socialuser = socialUser; 
	}
	
	/**
	 * @return the socialuser
	 */
	public SocialUser getSocialuser() {
		return socialuser;
	}
	/**
	 * @param socialuser the socialuser to set
	 */
	public void setSocialuser(SocialUser socialuser) {
		this.socialuser = socialuser;
	}
	/**
	 * @return the messageText
	 */
	public String getMessageText() {
		return messageText;
	}
	/**
	 * @param messageText the messageText to set
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	/**
	 * @return the messageDate
	 */
	public Date getMessageDate() {
		return messageCal.getTime();
	}


}
