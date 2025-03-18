package ru.iteco.fmhandroid.ui.ScreenElements;

import static android.os.Trace.isEnabled;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static ru.iteco.fmhandroid.ui.Data.Helper.randomDescription;
import static ru.iteco.fmhandroid.ui.Data.Helper.randomDescription1;
import static ru.iteco.fmhandroid.ui.Data.Helper.randomDescription14symbolCyryllice;
import static ru.iteco.fmhandroid.ui.Data.NewsHelper.childAtPosition;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Data.Helper;

public class NewsScreen {

    public void navigateToNewsSection() {
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("News")).perform(click());
    }

    public void clickEditNewsButton() {
        onView(withId(R.id.edit_news_material_button)).perform(click());
    }

    public void clickOnNewsItem(String title, String description) {
        onView(withId(R.id.news_list_recycler_view))
                .perform(RecyclerViewActions.actionOnItem(
                        allOf(
                                hasDescendant(withText(title)),
                                hasDescendant(withText(description))
                        ),
                        click()
                ));
    }
  public void clickNewNews(String title) {
      onView(allOf(
              withId(R.id.edit_news_item_image_view),
              isDescendantOfA(allOf(
                      withId(R.id.news_item_material_card_view),
                      hasDescendant(withText(title))
              ))
      )).perform(click());
  }

    public void editNewsTitleNews(String newTitle) {
        onView(ViewMatchers.withId(R.id.news_item_title_text_input_edit_text))
                .perform(ViewActions.replaceText(newTitle), ViewActions.closeSoftKeyboard());

    }

    public void editNewsDescriptionNews(String newDescription) {
        onView(ViewMatchers.withId(R.id.news_item_description_text_input_edit_text))
                .perform(ViewActions.replaceText(newDescription), ViewActions.closeSoftKeyboard());
    }

    public void clickDateNews() {
        onView(ViewMatchers.withId(R.id.news_item_publish_date_text_input_edit_text)).perform(ViewActions.click());
    }

    public void nextMonthNews() {
        onView(allOf(withContentDescription("Next month")))
                .perform(scrollTo(), click());
    }

    public void saveDateNews() {
        onView(ViewMatchers.withId(android.R.id.button1)).perform(scrollTo(), ViewActions.click());
    }

    public void clickTimeNews() {
        onView(ViewMatchers.withId(R.id.news_item_publish_time_text_input_edit_text)).perform(ViewActions.click());
    }

    public void clickClaviatureTimeNews() {
        onView(withContentDescription("Switch to text input mode for the time input.")).perform(click());
    }

    public void enterHoursNews(Integer hours) {
        onView(allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
                childAtPosition(
                        allOf(withClassName(is("android.widget.RelativeLayout")),
                                childAtPosition(
                                        withClassName(is("android.widget.TextInputTimePickerView")),
                                        1)),
                        0),
                isDisplayed()))
                .perform(replaceText(String.valueOf(hours)), closeSoftKeyboard());
    }

    public void enterMinuteNews(Integer minute) {
        onView(allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
                childAtPosition(
                        allOf(withClassName(is("android.widget.RelativeLayout")),
                                childAtPosition(
                                        withClassName(is("android.widget.TextInputTimePickerView")),
                                        1)),
                        3),
                isDisplayed()))
                .perform(replaceText(String.valueOf(minute)), closeSoftKeyboard());
    }

    public void saveTimeNews() {
        onView(ViewMatchers.withId(android.R.id.button1)).perform(scrollTo(), ViewActions.click());
    }

    public void notActiveNews() {
    onView(allOf(withId(R.id.switcher), withText("Active")))
            .perform(scrollTo(), click());
}

    public void saveNews() {
        onView(ViewMatchers.withId(R.id.save_button)).perform(scrollTo(), ViewActions.click());
    }

    public void cancelEditingNews() {
        onView(withId(R.id.cancel_button)).perform(scrollTo(), click());
        onView(allOf(withId(android.R.id.button2), withText("Cancel"))).perform(scrollTo(), click());
    }

    public void cancelSaveNews() {
        onView(withId(R.id.cancel_button)).perform(scrollTo(), click());
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(scrollTo(), click());
    }

    public void categoryClickNews() {
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click());
    }

    public void categoryEnterNews(String category) {
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(replaceText(category));
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(closeSoftKeyboard());
    }

    public void saveNotNews() {
        onView(withId(R.id.save_button)).check(matches(not(isEnabled())));
    }

    // метод удаления новости
    public void deleteNewsNews(String titul) {
        SystemClock.sleep(1000); // Задержка для уверенности, что элемент отобразился
        onView(allOf(
                withId(R.id.delete_news_item_image_view),
                isDescendantOfA(allOf(
                        withId(R.id.news_item_material_card_view),
                        hasDescendant(withText(titul))
                ))
        )).perform(click());
        onView(withId(android.R.id.button1)).perform(scrollTo(), click());
    }

        public void clickAddNews() {
            onView(withId(R.id.add_news_image_view)).perform(click());
        }

        public void enterValidTime() {
            onView(withText("Enter a valid time")).check(matches(isDisplayed()));
        }
}