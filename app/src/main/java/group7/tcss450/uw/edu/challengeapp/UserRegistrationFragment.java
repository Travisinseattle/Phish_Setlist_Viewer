package group7.tcss450.uw.edu.challengeapp;


import android.content.Context;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class UserRegistrationFragment extends Fragment implements  View.OnClickListener {

    private MainFragment.validUserListener mListener;
    private Context mContext;

    EditText user;
    EditText pw_1;
    EditText pw_2;
    TextView textView;

    public UserRegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment/// Inflate the layout for this fragmentView
        View v = inflater.inflate(R.layout.fragment_user_registration, container, false);
        Button regButton = (Button) v.findViewById(R.id.display_registration_results_button);
        regButton.setOnClickListener(this);
        user = (EditText) v.findViewById(R.id.registration_editText);
        pw_1 = (EditText) v.findViewById(R.id.pw_edit_text_1);
        pw_2 = (EditText) v.findViewById(R.id.pw_edit_text_2);
        textView = (TextView) v.findViewById(R.id.registration_textview);
        textView.setTypeface(font);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mListener = (MainFragment.validUserListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            switch (view.getId()) {
                case R.id.display_registration_results_button:
                    if (user.getText().toString().trim().length() <= 0) {
                        user.setError(getString(R.string.user_error));
                    } else if (pw_1.getText().toString().trim().length() <= 0) {
                        pw_1.setError(getString(R.string.pw_error));
                    } else if (pw_2.getText().toString().trim().length() <= 0) {
                        pw_2.setError(getString(R.string.pw_error));
                    } else if (!(pw_1.getText().toString().trim()
                            .equals(pw_2.getText().toString().trim()))) {
                        pw_1.setError(getString(R.string.pw_no_match));
                        pw_2.setError(getString(R.string.pw_no_match));
                    } else {
                        AsyncTask<String, Void, String> task = null;
                        task = new PostWebServiceTask(mContext);
                        String url = PARTIAL_URL +
                                getString(R.string._insert) +
                                user.getText().toString().trim() +
                                getString(R.string.and_pw) +
                                pw_1.getText().toString().trim() + "'";
                        task.execute(url, "Create User");
                        mListener.validUser(false);
                    }
                    break;
            }
        }
    }
}
