package at.tigraine.podcatcher2.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.Toast;
import at.tigraine.podcatcher2.models.Podcast;

public class FilePodcastDatabase implements PodcastDatabase {
	Context context;
	public FilePodcastDatabase(Context context) {
		this.context = context;
		readPodcastList();
	}
	
	private ArrayList<Podcast> podcasts = new ArrayList<Podcast>();
	
	@Override
	public List<Podcast> getPodcasts() {
		return podcasts;
	}
	
	private void readPodcastList() {
		String filename = "list.txt";
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(filename));
			String line = fileReader.readLine();
			while (line != null) {
				podcasts.add(deserialize(line));
				
				line = fileReader.readLine();
			}
			fileReader.close();
			
		} catch (IOException ex) {
			Toast.makeText(context, "Something went wrong reading the Podcast List", Toast.LENGTH_LONG);
		}
	}
	
	private Podcast deserialize(String line) {
		String[] values = line.split(",");
		String name = values[0];
		return new Podcast(name);
	}
}