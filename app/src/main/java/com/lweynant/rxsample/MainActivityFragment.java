package com.lweynant.rxsample;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import timber.log.Timber;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @Bind(R.id.edtUserName) EditText userNameEdit;
    @Bind(R.id.edtEmail) EditText emailEdit;
    @Bind(R.id.btnRegister) Button registerButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Timber.d("onViewCreated");
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
                .doOnNext(b -> Timber.d("button %s", (b ? "enabled" : "disabled")))
                .subscribe(enabled -> registerButton.setEnabled(enabled));

        userNameValid.distinctUntilChanged()
                .doOnNext(b -> Timber.d("username %s", (b ? "valid" : "invalid")))
                .map(b -> b ? Color.BLACK : Color.RED)
                .subscribe(color -> userNameEdit.setTextColor(color));

        emailValid.distinctUntilChanged()
                .doOnNext(b -> Timber.d("email %s", (b ? "valid" : "invalid")))
                .map(b -> b ? Color.BLACK : Color.RED)
                .subscribe(color -> emailEdit.setTextColor(color));

    }

    @Override
    public void onDestroyView() {
        Timber.d("onDestroyView");
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
