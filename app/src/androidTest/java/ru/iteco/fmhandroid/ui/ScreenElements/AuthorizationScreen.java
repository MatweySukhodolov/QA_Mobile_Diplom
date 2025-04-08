package ru.iteco.fmhandroid.ui.ScreenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.Data.EspressoIdlingResources.waitFor;
import static ru.iteco.fmhandroid.ui.Data.EspressoIdlingResources.waitUntilElement;
import static ru.iteco.fmhandroid.ui.Data.Helper.enterTextInEditText;

import android.view.View;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class AuthorizationScreen {


    public void loginText(String login) {
        enterTextInEditText(R.id.login_text_input_layout, login);
    }

    public void passwordText(String password) {
        enterTextInEditText(R.id.password_text_input_layout, password);
    }

    public void clickAuthEnter() {
        onView(withId(R.id.enter_button)).perform(click());
    }

    public void textDescription(String text) {
        onView(allOf(withContentDescription(text), isDisplayed()));
    }

    public void textDescription1(String text) {
        onView(withText(text)).check(matches(isDisplayed()));
    }

}
