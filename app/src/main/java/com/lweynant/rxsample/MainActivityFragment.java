package com.lweynant.rxsample;

import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseFragment {

    @Bind(R.id.edtUserName)
    EditText userNameEdit;
    @Bind(R.id.edtUserNameLayout)
    TextInputLayout userNameInputLayout;
    @BindString(R.string.invalid_user_msg)
    String invalidUserMsg;
    @Bind(R.id.edtEmail)
    EditText emailEdit;
    @Bind(R.id.edtEmailLayout)
    TextInputLayout emailInputLayout;
    @Bind(R.id.btnRegister)
    Button registerButton;
    @BindString(R.string.invalid_email_msg)
    String invalidEmailMsg;
    EmailValidator emailValidator;

    private CompositeSubscription subscriptions;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Timber.d("onCreateView");
        emailValidator = new EmailValidator();
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Timber.d("onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        registerButton.setEnabled(false);
        subscriptions = new CompositeSubscription();
        Observable<Boolean> userNameValid = RxTextView.textChangeEvents(userNameEdit).skip(1)
                .map(e -> e.text())
                .map(t -> t.length())
                .map(l -> l > 4);


        Observable<Boolean> emailValid = RxTextView.textChangeEvents(emailEdit).skip(1)
                .map(e -> e.text())
                .map(t -> emailValidator.isValidEmail(t));

        Observable<Boolean> registerEnabled = Observable.combineLatest(userNameValid, emailValid, (a, b) -> a && b);


        subscriptions.add(registerEnabled.distinctUntilChanged()
                .doOnNext(b -> Timber.d("button %s", (b ? "enabled" : "disabled")))
                .subscribe(enabled -> registerButton.setEnabled(enabled)));

        subscriptions.add(userNameValid.distinctUntilChanged()
                .doOnNext(b -> Timber.d("username %s", (b ? "valid" : "invalid")))
                .subscribe(isValid -> userNameInputLayout.setErrorEnabled(!isValid)));
        subscriptions.add(userNameValid.distinctUntilChanged()
                .filter(isValid -> !isValid)
                .subscribe(notValid -> userNameInputLayout.setError(invalidUserMsg)));

        subscriptions.add(
                emailValid.distinctUntilChanged()
                        .doOnNext(b -> Timber.d("email %s", (b ? "valid" : "invalid")))
                        .subscribe(isValid -> emailInputLayout.setErrorEnabled(!isValid)));
        subscriptions.add(
                emailValid.distinctUntilChanged()
                        .filter(isValid -> !isValid)
                        .subscribe(notValid -> emailInputLayout.setError(invalidEmailMsg)));
    }

    @Override
    public void onDestroyView() {
        Timber.d("onDestroyView");
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscriptions != null) {
            subscriptions.unsubscribe();
        }
    }
}
