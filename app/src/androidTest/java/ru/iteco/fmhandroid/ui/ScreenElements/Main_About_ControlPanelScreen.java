package ru.iteco.fmhandroid.ui.ScreenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static java.util.EnumSet.allOf;
import static ru.iteco.fmhandroid.ui.Data.Main_About_ControlPanelHelper.clickRecyclerViewItemControlPanel;
import static ru.iteco.fmhandroid.ui.Data.Main_About_ControlPanelHelper.clickRecyclerViewItemLoveIssAll;

import android.content.Intent;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;

import ru.iteco.fmhandroid.R;

public class Main_About_ControlPanelScreen {
    public void expandMaterialButton() {
        onView(ViewMatchers.withId(R.id.expand_material_button))
                .perform(ViewActions.click());
    }

    public void mainSwipeRefresh() {
        onView(ViewMatchers.withId(R.id.main_swipe_refresh))
                .check(matches(ViewMatchers.isDisplayed()));
    }

    public void allNewsText() {
        onView(ViewMatchers.withId(R.id.all_news_text_view))
                .check(matches(ViewMatchers.isDisplayed()));
    }

    public void navigateToAboutSection() {
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("About")).perform(click());
    }

    public void aboutPrivacyPolicy() {
        onView(withId(R.id.about_privacy_policy_value_text_view))
                .check(matches(withText("https://vhospice.org/#/privacy-policy/")));
    }

    public void aboutTermsOfUse() {
        onView(withId(R.id.about_terms_of_use_value_text_view))
                .check(matches(withText("https://vhospice.org/#/terms-of-use")));
    }

    public void aboutBack() {
        onView(withId(R.id.about_back_image_button))
                .perform(click());
    }

    public void containerListNewsIncludeOnFragment() {
        onView(withId(R.id.container_list_news_include_on_fragment_main))
                .check(matches(isDisplayed()));
    }

    public void ourMission() {
        onView(ViewMatchers.withId(R.id.our_mission_image_button))
                .perform(ViewActions.click());
    }

    public void positionLoveIssAll(Integer position) {
        clickRecyclerViewItemLoveIssAll(position);
    }

    public void newsListRecycler(Integer position) {
        clickRecyclerViewItemControlPanel(R.id.news_list_recycler_view, position);
    }

    public void sortNewsMaterial() {
        onView(withId(R.id.sort_news_material_button)).perform(click());
    }

    public void navigateToMainSection() {
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("Main")).perform(click());
    }

    public void containerListNewsIncludeOnFragmentMain() {
        onView(ViewMatchers.withId(R.id.container_list_news_include_on_fragment_main))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
