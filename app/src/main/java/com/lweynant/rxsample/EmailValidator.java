package com.lweynant.rxsample;

import java.util.regex.Pattern;

/**
 * Created by lweynant on 19/09/15.
 */
public class EmailValidator {
    public static boolean isValidEmail(CharSequence s) {
        final Pattern emailPattern = Pattern.compile(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        return emailPattern.matcher(s).matches();
    }
}
