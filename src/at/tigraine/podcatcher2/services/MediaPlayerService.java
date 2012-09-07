package at.tigraine.podcatcher2.services;

import java.io.IOException;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import at.tigraine.podcatcher2.Constants;

public class MediaPlayerService extends IntentService {

	private static MediaPlayer mediaPlayer = null;
	private static String currentEnclosureUrl = null;
	
	public MediaPlayerService() {
		super("MediaPlayerService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		
		try {
			if (mediaPlayer == null) {
				Log.d(Constants.LOG_TAG, "New MediaPlayer instantiated");
				mediaPlayer = new MediaPlayer();
			}
			String requestedEnclosureUrl = intent.getStringExtra(Constants.ENCLOSURE_URL);
			if (currentEnclosureUrl == null || !currentEnclosureUrl.equals(requestedEnclosureUrl)) {
				Log.d(Constants.LOG_TAG, "MediaPlayer resetting because of new URL");
				
				mediaPlayer.reset();
				currentEnclosureUrl = requestedEnclosureUrl; 
				mediaPlayer.setDataSource(requestedEnclosureUrl);
				mediaPlayer.prepare();
			}
			Log.d(Constants.LOG_TAG, "MediaPlayer State: Playing - " + mediaPlayer.isPlaying());
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.pause();
			} else {
				mediaPlayer.start();				
			}
			
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
