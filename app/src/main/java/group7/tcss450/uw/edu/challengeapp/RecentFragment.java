package group7.tcss450.uw.edu.challengeapp;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

import model.Setlist;

import static group7.tcss450.uw.edu.challengeapp.R.color.white;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecentFragment extends Fragment {

    private DisplayFragment.RandomListener mListener;
    private Context mContext;

    View v;
    JSONObject jsonObject;
    JSONArray mJsonArray;



    public RecentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_recent, container, false);
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onStart() {
        super.onStart();
        createButtons(v);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mListener = (DisplayFragment.RandomListener) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createButtons(View v) {
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.recent_layout);
        List<Datum> datumList = null;
        Setlist setlist = null;
        int j = 0;

        try {
            setlist = (Setlist) getArguments().getSerializable("setlist");
        } catch (Exception e) {
            Log.e("Recentfrag Bundle fail", e.getMessage());
        }

        try {
            assert setlist != null;
            datumList = setlist.getResponse().getData();
        } catch (Exception e) {
            Log.e("Recentfrag list fail", e.getMessage());
        }

        for (Datum datum : datumList) {
            Button button = (Button) new Button(getActivity());
            button.setId(j);
            button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setBackground(getResources().getDrawable(R.drawable.customborder));
            Pair<String, String> pair = datum.getShowdate();
            String text = pair.first + pair.second;
            button.setText(text);
            button.setTextColor(getResources().getColor(white));
            linearLayout.addView(button);
            Button temp = (Button) linearLayout.findViewById(button.getId());
            button.setOnClickListener(fireSetlist(temp, datum));
            j++;
        }
    }



    View.OnClickListener fireSetlist(final Button button, Datum datum) {
        final Datum theDatum = datum;
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mListener.randomSetlist(theDatum);
                } catch (Exception e) {
                    Log.e("OnClick method", e.getMessage());
                }


            }
        };
    }
}
