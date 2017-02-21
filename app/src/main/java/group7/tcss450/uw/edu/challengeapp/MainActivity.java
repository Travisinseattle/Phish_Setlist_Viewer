package group7.tcss450.uw.edu.challengeapp;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.io.Serializable;
import java.security.spec.ECField;

import model.Setlist;


public class MainActivity extends AppCompatActivity implements MainFragment.invalidUserListener,
        MainFragment.validUserListener,
        DisplayFragment.RandomListener,
        DisplayFragment.RecentListener,
        DisplayFragment.SpecificListener {
    protected String mUser;
    protected String mPw;
    public static Typeface font;

    public static final String PARTIAL_URL =
            "http://cssgate.insttech.washington.edu/~thollow/test";

    public static final String PHISH_URL =
            "http://cssgate.insttech.washington.edu/~cfb3/TCSS450A-W17/phish/setlist/";

    public TextView mTextView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            if (findViewById(R.id.fragmentContainer) != null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, new MainFragment())
                        .commit();
            }
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        font = Typeface.createFromAsset(getBaseContext().getResources().getAssets(), "font.TTF");
    }

    @Override
    public void invalidUser() {
        UserRegistrationFragment userRegistrationFragment = new UserRegistrationFragment();
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, userRegistrationFragment)
                .addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void validUser(boolean status) {
        if (status) {
            DisplayFragment displayFragment = new DisplayFragment();
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, displayFragment)
                    .addToBackStack(null);
            transaction.commit();
        } else {
            LogInFragment logInFragment = new LogInFragment();
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, logInFragment)
                    .addToBackStack(null);
            transaction.commit();
        }
    }


    @Override
    public void randomSetlist(Datum datum) {
        SetListFragment setListFragment = new SetListFragment();
        Bundle b = new Bundle();
        try {
            b.putSerializable("datum", datum);
        } catch (Exception e) {
            Log.e("Random Datum to Bundle", e.getMessage());
        }
        setListFragment.setArguments(b);
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, setListFragment)
                .addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void recentSetlist(Setlist setlist) {
        RecentFragment recentFragment = new RecentFragment();
        Bundle b = new Bundle();
        b.putSerializable("setlist", setlist);
        recentFragment.setArguments(b);
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, recentFragment)
                .addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void specificSetlist() {
        SpecificFragment specificFragment = new SpecificFragment();
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, specificFragment)
                .addToBackStack(null);
        transaction.commit();

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

