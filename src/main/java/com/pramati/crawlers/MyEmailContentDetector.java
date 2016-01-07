package com.pramati.crawlers;

public class MyEmailContentDetector  extends MailContentDetector{

	@Override
	public void processUrl(String url) {
		System.out.println("Visisiting URL "+url);
	}

}
