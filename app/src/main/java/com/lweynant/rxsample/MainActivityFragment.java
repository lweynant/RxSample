package com.lweynant.rxsample;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.regex.Pattern;

import rx.Observable;


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
        registerButton.setEnabled(false);
        Observable<Boolean> userNameValid = RxTextView.textChangeEvents(userNameEdit)
                .map(e -> e.text())
                .map(t -> t.length())
                .map(l -> l > 4);


        final Pattern emailPattern = Pattern.compile(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Observable<Boolean> emailValid = RxTextView.textChangeEvents(emailEdit)
                                        .map(e -> e.text())
                                        .map(t -> emailPattern.matcher(t).matches());

        Observable<Boolean> registerEnabled = Observable.combineLatest(userNameValid, emailValid, (a,b) -> a && b);


        registerEnabled.distinctUntilChanged()
                .doOnNext(b -> Log.d("lweynant", "button " + (b ? "enabled" : "disabled")))
                .subscribe(enabled -> registerButton.setEnabled(enabled));

        userNameValid.distinctUntilChanged()
                .doOnNext(b -> Log.d("lweynant", "username " + (b ? "valid" : "invalid")))
                .map(b -> b ? Color.BLACK : Color.RED)
                .subscribe(color -> userNameEdit.setTextColor(color));

        emailValid.distinctUntilChanged()
                .doOnNext(b -> Log.d("lweynant", "email " + (b ? "valid" : "invalid")))
                .map(b -> b ? Color.BLACK : Color.RED)
                .subscribe(color -> emailEdit.setTextColor(color));

    }
}
