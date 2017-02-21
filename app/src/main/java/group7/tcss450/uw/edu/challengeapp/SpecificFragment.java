package group7.tcss450.uw.edu.challengeapp;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static group7.tcss450.uw.edu.challengeapp.MainActivity.PHISH_URL;
import static group7.tcss450.uw.edu.challengeapp.MainActivity.font;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpecificFragment extends Fragment {

    private DisplayFragment.RandomListener mListener;
    Context mContext;
    EditText editText;
    TextView textView;
    Button button;
    View theV;

    public SpecificFragment() {}

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        theV = inflater.inflate(R.layout.fragment_specific, container, false);
        editText = (EditText) theV.findViewById(R.id.show_id_edittext);
        button = (Button) theV.findViewById(R.id.fire_specific_button);
        textView = (TextView) theV.findViewById(R.id.specific_textview);
        return theV;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onStart() {
        super.onStart();
        button.setOnClickListener(fireSetlist(button));
        textView.setTypeface(font);
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

    View.OnClickListener fireSetlist(final Button button) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    setup(theV);
                } catch (Exception e) {
                    Log.e("OnClick method", e.getMessage());
                }
            }
        };
    }

    private void setup(View v) {
        AsyncTask<String, Void, String> task = null;
        task = new GetSpecificSetlistTask(mContext, mListener);
        String url = PHISH_URL + "getShow.php?show_id=" + editText.getText();
        task.execute(url, "Specific Setlist");
    }
}
