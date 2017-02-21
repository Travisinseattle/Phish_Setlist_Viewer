package group7.tcss450.uw.edu.challengeapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Travis Holloway on 2/5/2017.
 * get task
 */

class GetWebServiceTask extends AsyncTask<String, Void, String> {

    private static MainFragment.invalidUserListener mInvalidListener;
    private static MainFragment.validUserListener mValidListener;
    private Context mContext;

    GetWebServiceTask(Context context,
                      MainFragment.invalidUserListener iListener,
                      MainFragment.validUserListener vListener) {
        this.mContext = context;
        mInvalidListener = iListener;
        mValidListener = vListener;
    }

    @Override
    protected String doInBackground(String... strings) {
        if (strings.length != 2) {
            throw new IllegalArgumentException("Two String arguments required.");
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
                Log.e("Output", response);
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

        String userName = null;
        String pwd = null;
        // Something wrong with the network or the URL.
        if (result.startsWith("Unable to")) {
            Toast.makeText(mContext, result, Toast.LENGTH_LONG)
                    .show();
            return;
        } else {
            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    userName = jsonObject.getString("username");
                    pwd = jsonObject.getString("pwd");
                }
            } catch (JSONException e) {
                Log.e("USER DOESNT EXIST GWST", e.getMessage());
            }
        }
        FireListener(userName, pwd);
    }

    private void FireListener(String user, String pw) {

        if (user != null && pw != null) {
            if (mValidListener != null) {
                mValidListener.validUser(true);
            } else {
                Log.e("mValidListener Status", "mValidListener is null");
            }
        } else {
            mInvalidListener.invalidUser();
        }

    }
}