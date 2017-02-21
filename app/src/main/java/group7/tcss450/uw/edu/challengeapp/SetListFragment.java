package group7.tcss450.uw.edu.challengeapp;


import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.Space;
import android.text.Html;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Setlist;

import static group7.tcss450.uw.edu.challengeapp.R.color.white;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetListFragment extends Fragment {

    public SetListFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_set_list, container, false);
        setText(v);
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    void setText(View v) {
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.setlist_layout);

        int j = 0;
        Datum datum = null;
        List<Pair<String, String>> list = null;

        try {
            datum = (Datum) getArguments().getSerializable("datum");
        } catch (Exception e) {
            Log.e("Setlist Bundle Fail", e.getMessage());
        }

        try {
            assert datum != null;
            list = datum.getList();
        } catch (Exception e) {
            Log.e("Retrive Datum failed", e.getMessage());
        }

        assert list != null;
        for (Pair<String, String> pairs : list) {
            String key = pairs.first;
            String value = pairs.second;
            TextView textView = new TextView(getActivity());
            textView.setId(j);
            j++;
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setBackground(getResources().getDrawable(R.drawable.customborder));
            String result = key + value;
            textView.setTextColor(getResources().getColor(white));
            textView.setText(Html.fromHtml("<font color='white'>" +String.valueOf(result) + "</font>"));
            textView.setTextColor(getResources().getColor(white));
            linearLayout.addView(textView);

        }
    }
}
