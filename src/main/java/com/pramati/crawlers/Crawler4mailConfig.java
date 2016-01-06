package com.pramati.crawlers;

import java.io.File;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;

public class Crawler4mailConfig extends CrawlConfig {
	
	 /**
	  * Path to save downloaded mails.
	  */
			
	  protected String storageFolder = System.getProperty("user.home")+File.separator+"mails";
	  
	  /**
	   * URL to crawl.
	   */
	  
	  protected String url = "";
	    
}
