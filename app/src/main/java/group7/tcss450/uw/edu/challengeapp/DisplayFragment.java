package group7.tcss450.uw.edu.challengeapp;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import model.Setlist;

import static group7.tcss450.uw.edu.challengeapp.MainActivity.PHISH_URL;
import static group7.tcss450.uw.edu.challengeapp.MainActivity.font;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment implements  View.OnClickListener {

    private RandomListener mRandom;
    private RecentListener mRecent;
    private SpecificListener mSpecific;
    private Context mContext;
    TextView text;

    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_display, container, false);
        Button b = (Button) v.findViewById(R.id.randomButton);
        b.setOnClickListener(this);
        b = (Button) v.findViewById(R.id.recentButton);
        b.setOnClickListener(this);
        b = (Button) v.findViewById(R.id.specificButton);
        b.setOnClickListener(this);
        text = (TextView) v.findViewById(R.id.display_text);
        text.setTypeface(font);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mRandom = (RandomListener) context;
        mRecent = (RecentListener) context;
        mSpecific = (SpecificListener) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mRandom = null;
        mRecent = null;
        mSpecific = null;
    }

    @Override
    public void onClick(View view) {

        if (mRandom != null || mRecent != null || mSpecific != null) {
            switch (view.getId()) {
                case R.id.randomButton:
                    AsyncTask<String, Void, String> task = null;
                    task = new GetRandomSetlistTask(mContext, mRandom);
                    String url = PHISH_URL +
                            getString(R.string.random);
                    task.execute(url, "Random Setlist");
                    break;
                case R.id.recentButton:
                    task = new GetRecentSetlistTask(mContext, mRecent);
                    url = PHISH_URL +
                            getString(R.string.recent);
                    task.execute(url, "Recent Setlist");
                    break;
                case R.id.specificButton:
                    mSpecific.specificSetlist();
                    break;
            }
        }

    }

    public interface RandomListener {
        void randomSetlist(Datum datum);
    }

    public interface RecentListener {
        void recentSetlist(Setlist setlist);
    }

    public interface SpecificListener {
        void specificSetlist();
    }
}
