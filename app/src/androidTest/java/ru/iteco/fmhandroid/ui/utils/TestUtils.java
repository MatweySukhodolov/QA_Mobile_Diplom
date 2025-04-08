package ru.iteco.fmhandroid.ui.utils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.not;

import android.os.SystemClock;
import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

public class TestUtils {

    public static ViewInteraction waitForViewWithId(int viewId, int timeoutMillis) {
        return waitForView(ViewMatchers.withId(viewId), timeoutMillis);
    }

    public static ViewInteraction waitForViewWithText(String text, int timeoutMillis) {
        return waitForView(ViewMatchers.withText(text), timeoutMillis);
    }

    public static ViewInteraction waitForView(Matcher<View> matcher, int timeoutMillis) {
        long endTime = System.currentTimeMillis() + timeoutMillis;

        do {
            try {
                ViewInteraction viewInteraction = onView(matcher);
                viewInteraction.check(matches(isDisplayed()));
                return viewInteraction;
            } catch (Exception e) {
                SystemClock.sleep(100);
            }
        } while (System.currentTimeMillis() < endTime);

        // Последняя попытка, которая выбросит исключение если элемент не найден
        ViewInteraction viewInteraction = onView(matcher);
        viewInteraction.check(matches(isDisplayed()));
        return viewInteraction;
    }

    public static void waitUntilViewDisappears(int viewId, int timeoutMillis) {
        waitForViewToDisappear(ViewMatchers.withId(viewId), timeoutMillis);
    }

    public static void waitForViewToDisappear(Matcher<View> matcher, int timeoutMillis) {
        long endTime = System.currentTimeMillis() + timeoutMillis;

        do {
            try {
                onView(matcher).check(matches(not(isDisplayed())));
                return;
            } catch (Exception e) {
                SystemClock.sleep(100);
            }
        } while (System.currentTimeMillis() < endTime);

        onView(matcher).check(matches(not(isDisplayed())));
    }
}