package com.pramati.crawlers;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Hello world!
 * 
 */
public class App {
	Logger log = Logger.getLogger(App.class.getName());
	HashMap<String, HashSet<String>> outGoingUrls = null;
	String url = "https://mail-archives.apache.org/";
	public static ExecutorService executorService = Executors.newFixedThreadPool(30);

	public App() {
		outGoingUrls = new HashMap<String, HashSet<String>>();
		outGoingUrls.put("VISITED", new HashSet<String>());
		outGoingUrls.put("TO_BE_VISITED", new HashSet<String>());
		outGoingUrls.put("IN_PROCESS", new HashSet<String>());
	}

	public static void main(String[] args) throws IOException,
			InstantiationException, IllegalAccessException {
		App app = new App();
		List urls = app.extractOutGoingUrls(app.url);
		app.setOutgoingUrls(urls);
		app.outGoingUrls.get("VISITED").add(app.url);
		app.scheduleURL(MyEmailContentDetector.class,app.outGoingUrls.get("TO_BE_VISITED"));

	}

	public List extractOutGoingUrls(String url) throws IOException {
		Document document = Jsoup.connect(this.url).get();
		List<Element> outgoingUrls = document.getElementsByTag("a");
		return outgoingUrls;
	}

	public String canonicalURL(String baseURL, String contextURL) {
		if (baseURL == "") {
			baseURL = this.url;
		}
		if (contextURL.contains("http"))
			return contextURL;
		else
			return baseURL + contextURL;
	}

	public void setOutgoingUrls(List outGoingURL) {

		Iterator<Element> iterator = outGoingURL.iterator();
		while (iterator.hasNext()) {
			String url = this.canonicalURL(this.url,
					iterator.next().attr("href").trim());
			if (this.outGoingUrls.get("VISITED").contains(url) || this.outGoingUrls.get("IN_PROCESS").contains(url)) {
				this.log.info(url + " is already visisted or in progress ");
				return;
			}
			if (!this.outGoingUrls.get("TO_BE_VISITED").contains(url)) {
				this.outGoingUrls.get("TO_BE_VISITED").add(url);
			}
		}
	}
	public <T extends MailContentDetector> void scheduleURL(Class<T> cls,
			Set outGoingURLs) throws InstantiationException,
			IllegalAccessException {
		Iterator<String> urls = outGoingURLs.iterator();
		while (urls.hasNext()) {
			MailContentDetector contentDetector = cls.newInstance();
			LinksExtractor extractor = new LinksExtractor(url, this.outGoingUrls);
			contentDetector.url = urls.next();
			this.executorService.execute(contentDetector);
			this.executorService.execute(extractor);
			this.outGoingUrls.get("IN_PROCESS").add(contentDetector.url);
		}

	}
}