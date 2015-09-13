package com.lweynant.rxsample;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import rx.Observable;
import rx.android.widget.OnTextChangeEvent;
import rx.android.widget.WidgetObservable;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();
    private EditText userNameEdit;
    private EditText emailEdit;
    private Button registerButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        userNameEdit = (EditText)rootView.findViewById(R.id.edtUserName);
        emailEdit = (EditText)rootView.findViewById(R.id.edtEmail);
        registerButton = (Button) rootView.findViewById(R.id.btnRegister);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        Observable<OnTextChangeEvent> userNameText = WidgetObservable.text(userNameEdit);
        userNameText.filter(e -> e.text().length() > 4)
                .subscribe( e -> Log.d("lweynant", e.text().toString()));
    }
}
