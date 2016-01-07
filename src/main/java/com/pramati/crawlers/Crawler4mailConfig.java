package com.pramati.crawlers;

import java.io.File;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.WebCrawler;

public class Crawler4mailConfig<T> extends CrawlConfig {

	/**
	 * Path to save downloaded mails.
	 */

	protected String storageFolder = System.getProperty("user.home")
			+ File.separator + "mails";

	/**
	 * URL to crawl.
	 */

	protected String url = "";

	

	public interface EmailCrawlerFactory<T extends MailContentDetector> {
		T newInstance() throws Exception;
	}

	private static class DefaultEmailCrawlerFactory<T extends MailContentDetector>
			implements EmailCrawlerFactory<T> {
		    final Class<T> _c;

		DefaultEmailCrawlerFactory(Class<T> _c) {
			this._c = _c;
		}

		public T newInstance() throws Exception {
			try {
				return _c.newInstance();
			} catch (ReflectiveOperationException e) {
				throw e;
			}
		}
	}
}
