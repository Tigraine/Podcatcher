package at.tigraine.podcatcher2.factory;

import android.content.Context;
import at.tigraine.podcatcher2.database.FakePodcastDatabase;
import at.tigraine.podcatcher2.database.PodcastDatabase;

public class TestingObjectFactory extends ObjectFactory {

	@Override
	public PodcastDatabase createDatabase(Context context) {
		return new FakePodcastDatabase(context);
	}

}
