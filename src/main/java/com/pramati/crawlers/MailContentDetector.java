package com.pramati.crawlers;

import java.net.URL;
import java.util.Vector;

import com.pramati.beans.Mail;

import edu.uci.ics.crawler4j.crawler.Configurable;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;

public  abstract class MailContentDetector implements Runnable{
	
	
	Vector<Mail> mails = new Vector<Mail>();
	String url = "";
	/**
	 * Will be overriden by the sub-classes.
	 * @param url
	 * 
	 */
	public  abstract void processUrl(String url);

	public void run(){
		if(shouldMatch(url))
		    processUrl(url);
	}
	
	public  void onMatch(String url){
		//TO DO when html content of the page matched the email search
		//Will be overriden by the subclassess.
	}
	public void addMail(Mail mail){
		mails.add(mail);
	}
	public boolean shouldMatch(String url){
		//Subclasses  can ovverride this method apply any  their requirement.
		return true;
	}
	
	
	
	 
}
