package group7.tcss450.uw.edu.challengeapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static group7.tcss450.uw.edu.challengeapp.MainActivity.font;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link invalidUserListener} interface
 * to handle interaction events.
 */
public class MainFragment extends Fragment implements  View.OnClickListener {

    private invalidUserListener mInvalidListener;
    private validUserListener mValidListener;
    TextView text;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /// Inflate the layout for this fragmentView
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        Button b = (Button) v.findViewById(R.id.loginButton);
        text = (TextView) v.findViewById(R.id.main_text);
        b.setOnClickListener(this);
        text.setTypeface(font);
        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof invalidUserListener) {
            mInvalidListener = (invalidUserListener) context;
        } else if (context instanceof  validUserListener) {
            mValidListener = (validUserListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement invalidUserListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mInvalidListener = null;
        mValidListener = null;
    }

    @Override
    public void onClick(View view) {

        if (mInvalidListener != null || mValidListener != null) {
            switch (view.getId()) {
                case R.id.loginButton:
                    LogInFragment logInFragment = new LogInFragment();
                    this.getFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, logInFragment, "Main Fragment")
                            .addToBackStack(null)
                            .commit();
                    break;
            }
        }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface invalidUserListener {
        void invalidUser();
    }

    public interface validUserListener {
        void validUser(boolean value);
    }
}
