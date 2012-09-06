package at.tigraine.podcatcher2.factory;

import android.content.Context;
import at.tigraine.podcatcher2.database.FakePodcastDatabase;
import at.tigraine.podcatcher2.database.PodcastDatabase;

public class TestingObjectFactory extends ObjectFactory {

	private static PodcastDatabase database = null;
	@Override
	public PodcastDatabase createDatabase(Context context) {
		if (database == null) {
			synchronized(PodcastDatabase.class) {
				if (database == null) {
					database = new FakePodcastDatabase(context);
				}
			}
		}
		return database;
	}
}
