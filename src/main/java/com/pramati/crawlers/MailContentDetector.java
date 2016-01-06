package com.pramati.crawlers;

import edu.uci.ics.crawler4j.crawler.Configurable;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;

public abstract class MailContentDetector extends Configurable{

	protected MailContentDetector(Crawler4mailConfig config) {
		super(config);
	}
	 
}
