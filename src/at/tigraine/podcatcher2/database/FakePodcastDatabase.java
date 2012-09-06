package at.tigraine.podcatcher2.database;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import at.tigraine.podcatcher2.database.exceptions.DatabaseException;
import at.tigraine.podcatcher2.models.Podcast;



public class FakePodcastDatabase implements PodcastDatabase {

	private static ArrayList<Podcast> podcasts = new ArrayList<Podcast>(){{
		add(new Podcast(1, "Omega Tau", "http://omegataupodcast.net/category/podcast/feed"));
		add(new Podcast(2, "Hanselminutes", "http://feeds.feedburner.com/Hanselminutes"));
		add(new Podcast(3, "This Developers Life", "http://feeds.feedburner.com/thisdeveloperslife"));
		add(new Podcast(4, "Ruby Rogues", "http://rubyrogues.com/feed/"));
	}};
	
	public FakePodcastDatabase(Context context) {

	}
	
	@Override
	public List<Podcast> getPodcasts() {
		
		return podcasts;
	}

	@Override
	public void addPodcast(String uri) {
		podcasts.add(new Podcast(podcasts.size(), "Hello World", uri));
	}

	@Override
	public Podcast getById(int id) throws DatabaseException {
		for (Podcast podcast : podcasts) {
			if (podcast.getId() == id) {
				return podcast;
			}
		}
		throw new DatabaseException("Could not find Podcast with Id " + id);
	}
}
