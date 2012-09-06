package at.tigraine.podcatcher2.factory;
import android.content.Context;
import at.tigraine.podcatcher2.database.PodcastDatabase;



public abstract class ObjectFactory {
	public abstract PodcastDatabase createDatabase(Context context);
	
	private static ObjectFactory factoryInstance = null;
	public static ObjectFactory instance() {
		if (factoryInstance == null) {
			synchronized (ObjectFactory.class) {
				if (factoryInstance == null) {
					factoryInstance = new TestingObjectFactory();
				}
			}
		}
		return factoryInstance;
	}
}
