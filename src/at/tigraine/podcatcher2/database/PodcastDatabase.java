package at.tigraine.podcatcher2.database;

import java.util.List;

import at.tigraine.podcatcher2.models.Podcast;

public interface PodcastDatabase {

	public abstract List<Podcast> getPodcasts();
	public abstract void addPodcast(String uri);
}