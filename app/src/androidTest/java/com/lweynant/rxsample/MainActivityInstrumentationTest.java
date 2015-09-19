package com.lweynant.rxsample;

import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by lweynant on 18/09/15.
 */
// Tests for MainActivity
public class MainActivityInstrumentationTest {

    // Preferred JUnit 4 mechanism of specifying the activity to be launched before each test
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void atStartRegisterButtonIsDisabled(){
        onView(withId(R.id.btnRegister)).check(matches(not(isEnabled())));
    }
    @Test
    public void validUserNameAndEmail_EnablesRegisterButton() {
        onView(withId(R.id.edtUserName)).perform(typeText(getValidUserName()));
        onView(withId(R.id.edtEmail)).perform(typeText(getValidEmail()));
        onView(withId(R.id.btnRegister)).check(matches(isEnabled()));
    }


    @Test
    public void invalidUserNameValidEmail_DisablesRegisterButton(){
        onView(withId(R.id.edtUserName)).perform(typeText(getInvalidUserName()));
        onView(withId(R.id.edtEmail)).perform(typeText(getValidEmail()));
        onView(withId(R.id.btnRegister)).check(matches(not(isEnabled())));
    }

    @Test
    public void validUserNameInvalidEmail_DisablesRegisterButton(){
        onView(withId(R.id.edtUserName)).perform(typeText(getValidUserName()));
        onView(withId(R.id.edtEmail)).perform(typeText(getInvalidEmail()));
        onView(withId(R.id.btnRegister)).check(matches(not(isEnabled())));

    }

    @NonNull
    private String getInvalidEmail() {
        return "invalid@s";
    }

    @NonNull
    private String getValidUserName() {
        return "user-name";
    }

    @NonNull
    private String getValidEmail() {
        return "l@w.com";
    }
    @NonNull
    private String getInvalidUserName() {
        return "use";
    }
}