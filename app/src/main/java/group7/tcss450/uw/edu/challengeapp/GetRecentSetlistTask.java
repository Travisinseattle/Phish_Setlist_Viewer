package group7.tcss450.uw.edu.challengeapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import model.Setlist;

/**
 * Created by Travis Holloway on 2/16/2017.
 * A Task to pull the last 10 most recent phish setlists from *.recent.php.
 */

public class GetRecentSetlistTask extends AsyncTask<String, Void, String> {

    private DisplayFragment.RecentListener mListener;
    private Context mContext;

    GetRecentSetlistTask(Context context, DisplayFragment.RecentListener recentListener) {
        this.mContext = context;
        this.mListener = recentListener;
    }

    @Override
    protected String doInBackground(String... strings) {
        if (strings.length != 2) {
            throw new IllegalArgumentException("8 String arguments required.");
        }
        String response = "";
        HttpURLConnection urlConnection = null;
        String url = strings[0];
        try {
            URL urlObject = new URL(url);
            urlConnection = (HttpURLConnection) urlObject.openConnection();
            InputStream content = urlConnection.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
            String s = "";
            while ((s = buffer.readLine()) != null) {
                response += s;
            }
        } catch (Exception e) {
            response = "Unable to connect, Reason: "
                    + e.getMessage();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {

        // Something wrong with the network or the URL.
        if (result.startsWith("Unable to")) {
            Toast.makeText(mContext, result, Toast.LENGTH_LONG)
                    .show();
        } else {
            try {
                JSONObject jsonObject = new JSONObject(result);
                Setlist setlist = Setlist.createSetlist(jsonObject);
                FireListener(setlist);
            } catch (JSONException e) {
                Log.e("JSONException GetRecent", e.toString());
            }
        }
    }

    private void FireListener(Setlist setlist) {
        try {
            mListener.recentSetlist(setlist);
        } catch (Exception e) {
            Log.e("GetRecentTask Failed", e.getMessage());
        }
    }
}
