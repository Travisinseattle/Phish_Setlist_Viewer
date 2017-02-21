package group7.tcss450.uw.edu.challengeapp;


import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static group7.tcss450.uw.edu.challengeapp.MainActivity.PARTIAL_URL;
import static group7.tcss450.uw.edu.challengeapp.MainActivity.font;
import static group7.tcss450.uw.edu.challengeapp.MainFragment.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends Fragment implements  View.OnClickListener {

    private invalidUserListener mInvalidListener;
    private validUserListener mValidListener;
    private Context mContext;

    EditText user;
    EditText pw;
    TextView text;

    public LogInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /// Inflate the layout for this fragmentView
        View v = inflater.inflate(R.layout.fragment_log_in, container, false);
        Button b = (Button) v.findViewById(R.id.display_login_results_button);
        user = (EditText) v.findViewById(R.id.log_in_editText);
        pw = (EditText) v.findViewById(R.id.pw_editText);
        text = (TextView) v.findViewById(R.id.log_in_header);
        b.setOnClickListener(this);
        text.setTypeface(font);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mInvalidListener = (invalidUserListener) context;
        mValidListener = (validUserListener) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mInvalidListener = null;
        mValidListener = null;
    }

    @Override
    public void onClick(View view) {
        if (mInvalidListener != null || mValidListener != null ) {
            switch (view.getId()) {
                case R.id.display_login_results_button:
                    if (user.getText().toString().trim().length() <= 0) {
                        user.setError(getString(R.string.user_error));
                    } else if (pw.getText().toString().trim().length() <= 0) {
                        pw.setError(getString(R.string.pw_error));
                    } else {
                        AsyncTask<String, Void, String> task = null;
                        task = new GetWebServiceTask(mContext, mInvalidListener, mValidListener);
                        String url = PARTIAL_URL +
                                getString(R.string._get) +
                                user.getText().toString().trim() +
                                getString(R.string.and_pw) +
                                pw.getText().toString().trim() + "'";
                        task.execute(url, "Retrieve User");
                    }
                    break;
            }
        }

    }
}
