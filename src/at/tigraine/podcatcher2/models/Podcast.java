package at.tigraine.podcatcher2.models;

import java.util.concurrent.ExecutionException;

import org.mcsoxford.rss.RSSFeed;

import android.os.AsyncTask;
import android.util.Log;
import at.tigraine.podcatcher2.Constants;
import at.tigraine.podcatcher2.support.DownloadAndParseRSS;

public class Podcast {
	private String name;
	private int id;
	private String feedUri;
	private RSSFeed feed;
	
	public Podcast(int id, String name, String feedUri) {
		this.name = name;
		this.id = id;
		this.feedUri = feedUri;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getFeedUri() {
		return feedUri;
	}
	
	public RSSFeed getFeed() {
		return feed;
	}

	public void setFeed(RSSFeed feed) {
		this.feed = feed;
	}
	
	public void updateRss() {
		DownloadAndParseRSS xmlDownloader = new DownloadAndParseRSS();
		AsyncTask<String, Integer, RSSFeed> task = xmlDownloader.execute(this.getFeedUri());
		//we could display a loading bar or something here while fetching and parsing the stream
		try {
			this.feed = task.get();
			Log.d(Constants.LOG_TAG, "Loaded RSS Feed");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
