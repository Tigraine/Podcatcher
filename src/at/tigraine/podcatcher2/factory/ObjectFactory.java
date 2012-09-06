package at.tigraine.podcatcher2.factory;
import android.content.Context;
import at.tigraine.podcatcher2.database.PodcastDatabase;



public abstract class ObjectFactory {
	public abstract PodcastDatabase createDatabase(Context context);
	
	public static ObjectFactory instance() {
		return new TestingObjectFactory();
	}
}
