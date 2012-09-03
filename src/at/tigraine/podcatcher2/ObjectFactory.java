package at.tigraine.podcatcher2;
import android.content.Context;


public class ObjectFactory {
	
	public static PodcastDatabase createDatabase(Context context) {
		return new FakePodcastDatabase(context);
	}
}
