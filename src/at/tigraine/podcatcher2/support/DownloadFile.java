package at.tigraine.podcatcher2.support;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.util.Log;
import at.tigraine.podcatcher2.Constants;

public class DownloadFile extends AsyncTask<String, Integer, String> {

	@Override
	protected String doInBackground(String... params) {
		try {
			URL url = new URL(params[0]);
			String targetPath = params[1];
			
			URLConnection connection = url.openConnection();
			connection.connect();
			
			int fileLength = connection.getContentLength();
			
			InputStream input = new BufferedInputStream(url.openStream());	
			OutputStream output = new FileOutputStream(targetPath);
			
			byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress when running as AsyncTask....
                publishProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();
            Log.d(Constants.LOG_TAG, "Finished RSS Download");
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
