package ru.iteco.fmhandroid.ui.tests;

import androidx.test.espresso.IdlingPolicies;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.utils.EspressoIdlingResources;
import ru.iteco.fmhandroid.ui.utils.TestUtils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import java.util.concurrent.TimeUnit;

@LargeTest
public class BaseTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        // Настройка таймаутов
        IdlingPolicies.setMasterPolicyTimeout(30, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(30, TimeUnit.SECONDS);

        // Регистрация IdlingResource
        IdlingRegistry.getInstance().register(EspressoIdlingResources.getIdlingResource());

        // Проверка состояния приложения
        try {
            // Проверяем, находимся ли мы на экране авторизации
            TestUtils.waitForViewWithId(R.id.login_text_input_layout, 5000);
        } catch (Exception e) {
            // Если не на экране авторизации, пытаемся выйти
            try {
                // Попытка через кнопку авторизации
                TestUtils.waitForViewWithId(R.id.authorization_image_button, 3000).perform(click());
                TestUtils.waitForViewWithText("Log out", 2000).perform(click());
            } catch (Exception ex) {
                // Альтернативный вариант через меню
                TestUtils.waitForViewWithId(R.id.main_menu_image_button, 3000).perform(click());
                TestUtils.waitForViewWithText("Log out", 2000).perform(click());
            }
            // Ожидаем появления экрана авторизации
            TestUtils.waitForViewWithId(R.id.login_text_input_layout, 5000);
        }
    }

    @After
    public void tearDown() {
        // Отмена регистрации IdlingResource
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.getIdlingResource());

        // Дополнительная очистка состояния при необходимости
        try {
            // Проверяем, остались ли мы на экране авторизации
            TestUtils.waitForViewWithId(R.id.login_text_input_layout, 2000);
        } catch (Exception e) {
            // Если нет, пытаемся выйти
            try {
                TestUtils.waitForViewWithId(R.id.authorization_image_button, 2000).perform(click());
                TestUtils.waitForViewWithText("Log out", 2000).perform(click());
            } catch (Exception ex) {
                // Последняя попытка через меню
                TestUtils.waitForViewWithId(R.id.main_menu_image_button, 2000).perform(click());
                TestUtils.waitForViewWithText("Log out", 2000).perform(click());
            }
        }
    }

    protected void logout() {
        try {
            TestUtils.waitForViewWithId(R.id.authorization_image_button, 3000).perform(click());
            TestUtils.waitForViewWithText("Log out", 2000).perform(click());
        } catch (Exception e) {
            TestUtils.waitForViewWithId(R.id.main_menu_image_button, 3000).perform(click());
            TestUtils.waitForViewWithText("Log out", 2000).perform(click());
        }
        TestUtils.waitForViewWithId(R.id.login_text_input_layout, 5000);
    }
}