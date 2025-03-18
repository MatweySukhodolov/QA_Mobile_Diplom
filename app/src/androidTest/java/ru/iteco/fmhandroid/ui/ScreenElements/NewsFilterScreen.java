package ru.iteco.fmhandroid.ui.ScreenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import ru.iteco.fmhandroid.R;

public class NewsFilterScreen {
    public void clickFilterNews() {
        onView(withId(R.id.filter_news_material_button)).perform(click());
    }

    public void clickFilterButton() {
        onView(withId(R.id.filter_button)).perform(click());
    }

    public void layotBackgroundImageFilter() {
        onView(withId(R.id.layout_background_image_view)).check(matches(isDisplayed()));
    }

    public void dateStartFilterNews() {
        onView(ViewMatchers.withId(R.id.news_item_publish_date_start_text_input_edit_text)).perform(ViewActions.click());
    }

    public void clickDateStartNewsFilter() {
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(scrollTo(), click());
    }

    public void dateEndNewsFilter() {
        onView(ViewMatchers.withId(R.id.news_item_publish_date_end_text_input_edit_text)).perform(ViewActions.click());
    }

    public void nextMonthFilterNews() {
        onView(allOf(withContentDescription("Next month")))
                .perform(scrollTo(), click());
    }

    public void clickDateEndNews() {
        onView(ViewMatchers.withId(android.R.id.button1)).perform(scrollTo(), ViewActions.click());
    }

    public void filterNewsActive() {
        onView(withId(R.id.filter_news_active_material_check_box)).perform(click());
    }

    public void filterNewsNotActive() {
        onView(withId(R.id.filter_news_inactive_material_check_box)).perform(click());

    }

    public void mainMenuFilterNews() {
        onView(withId(R.id.main_menu_image_button)).check(matches(isDisplayed()));
    }

    public void cancelFilterNews() {
        onView(withId(R.id.cancel_button)).perform(click());
    }

}
