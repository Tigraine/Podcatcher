package at.tigraine.podcatcher2.support;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.mcsoxford.rss.RSSConfig;
import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSParser;

import android.os.AsyncTask;

public class DownloadAndParseRSS extends AsyncTask<String, Integer, RSSFeed> {

	@Override
	protected RSSFeed doInBackground(String... params) {
		return getXmlFromUrl(params[0]);
	}
	
	
	private RSSFeed getXmlFromUrl(String url) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		HttpResponse execute;
		try {
			
			execute = httpClient.execute(get);
			HttpEntity entity = execute.getEntity();
			InputStream xml = entity.getContent();
			RSSParser parser = new RSSParser(new RSSConfig());
			RSSFeed rssFeed = parser.parse(xml);
			return rssFeed;
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
