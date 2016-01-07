package com.pramati.crawlers;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LinksExtractor implements Runnable {
	
	HashMap<String, HashSet<String>> outGoingUrls = null;
	String url = "";
	
	public LinksExtractor(String url,HashMap<String, HashSet<String>> outGoingUrls) {
		this.url = url;
		this.outGoingUrls = outGoingUrls;
	}
	public void run(){
		App app = new App();
		List urls;
		try {
			urls = app.extractOutGoingUrls(url);
		    app.setOutgoingUrls(urls);
			app.scheduleURL(MyEmailContentDetector.class,outGoingUrls.get("TO_BE_VISITED"));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
