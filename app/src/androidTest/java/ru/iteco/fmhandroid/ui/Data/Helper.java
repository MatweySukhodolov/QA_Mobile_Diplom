package ru.iteco.fmhandroid.ui.Data;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;

import androidx.test.espresso.IdlingPolicies;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.EspressoIdlingResources;

public class Helper {
    // логин и пароль
    public static void enterTextInEditText(int layoutId, String text) {
        onView(Matchers.allOf(
                isDescendantOfA(withId(layoutId)),
                isAssignableFrom(EditText.class)
        )).perform(replaceText(text));

        // Закрываем клавиатуру
        closeSoftKeyboard();
    }

    // авторизация
    public static void authorizationUser() {
        enterTextInEditText(R.id.login_text_input_layout, "login2");
        enterTextInEditText(R.id.password_text_input_layout, "password2");
        onView(withId(R.id.enter_button)).perform(click());
    }

    public static String randomDescription = generateRandomCyrillicString(10);
    public static String randomDescription1 = generateRandomCyrillicString(10);
    public static String randomDescription14symbolCyryllice = generateRandomCyrillicString(14);
    public static String randomDescription15symbolLatinice = generateRandomLatinicString(15);
    public static String randomDescription150symbolLatinice = generateRandomLatinicString(150);
    public static String randomDescription151symbolLatinice = generateRandomLatinicString(151);

    // Метод для создания новости
    public static void createNews() {
        // Настройка таймаутов
        IdlingPolicies.setMasterPolicyTimeout(30, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(30, TimeUnit.SECONDS);

        // Регистрация IdlingResource
        IdlingRegistry.getInstance().register(EspressoIdlingResources.getIdlingResource());

        // Открываем меню
        onView(withId(R.id.main_menu_image_button)).perform(click());

        // Переходим в раздел "News"
        onView(withText("News")).perform(click());

        // Нажимаем кнопку редактирования новостей
        onView(withId(R.id.edit_news_material_button)).perform(click());

        // Нажимаем кнопку добавления новости
        onView(withId(R.id.add_news_image_view)).perform(click());

        // Выбираем категорию из выпадающего списка
        onView(allOf(withId(R.id.news_item_category_text_auto_complete_text_view)))
                .perform(click(),replaceText("День рождения"), ViewActions.closeSoftKeyboard());

        // Вводим титул
        onView(ViewMatchers.withId(R.id.news_item_title_text_input_edit_text))
                .perform(ViewActions.replaceText(randomDescription1), ViewActions.closeSoftKeyboard());

        // Вводим описание новости с использованием случайного значения
        onView(ViewMatchers.withId(R.id.news_item_description_text_input_edit_text))
                .perform(ViewActions.replaceText(randomDescription), ViewActions.closeSoftKeyboard());
        // Нажимаем на поле даты публикации
        onView(ViewMatchers.withId(R.id.news_item_publish_date_text_input_edit_text)).perform(ViewActions.click());

        // Выбираем дату (сегодня)
        onView(allOf(withId(R.id.news_item_publish_date_text_input_edit_text)));

        // Подтверждаем выбор даты
        onView(ViewMatchers.withId(android.R.id.button1)).perform(scrollTo(), ViewActions.click());

        // Нажимаем на поле времени публикации
        onView(ViewMatchers.withId(R.id.news_item_publish_time_text_input_edit_text)).perform(ViewActions.click());

        // Подтверждаем выбор времени
        onView(ViewMatchers.withId(android.R.id.button1)).perform(scrollTo(), ViewActions.click());

        // Сохраняем новость
        onView(ViewMatchers.withId(R.id.save_button)).perform(scrollTo(), ViewActions.click());

        // Отмена регистрации IdlingResource
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.getIdlingResource());
    }

    public static void deleteNews() {
        // Настройка таймаутов
        IdlingPolicies.setMasterPolicyTimeout(30, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(30, TimeUnit.SECONDS);

        // Регистрация IdlingResource
        IdlingRegistry.getInstance().register(EspressoIdlingResources.getIdlingResource());

        // Открываем меню
        onView(withId(R.id.main_menu_image_button)).perform(click());

        // Переходим в раздел "News"
        onView(withText("News")).perform(click());

        // Нажимаем кнопку редактирования новостей
        onView(withId(R.id.edit_news_material_button)).perform(click());

        // Ищем созданную новость в RecyclerView по заголовку и описанию
        onView(withId(R.id.news_list_recycler_view))
                .perform(RecyclerViewActions.actionOnItem(
                        allOf(
                                hasDescendant(withText(randomDescription1)),
                                hasDescendant(withText(randomDescription))
                        ),
                        click()
                ));

        // Нажимаем кнопку удаления новости
//        SystemClock.sleep(1000); // Задержка для уверенности, что элемент отобразился
        onView(allOf(
                withId(R.id.delete_news_item_image_view),
                isDescendantOfA(allOf(
                        withId(R.id.news_item_material_card_view),
                        hasDescendant(withText(randomDescription1))
                ))
        )).perform(click());

        // Подтверждаем удаление
        onView(withId(android.R.id.button1)).perform(scrollTo(), click());

        // Отмена регистрации IdlingResource
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.getIdlingResource());

    }

    // Метод для генерации случайной строки на кириллице
    public static String generateRandomCyrillicString(int length) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        String cyrillicAlphabet = "абвгдежзийклмнопрстуфхцчшщъыьэюя"; // кириллический алфавит

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(cyrillicAlphabet.length());
            result.append(cyrillicAlphabet.charAt(index));
        }

        return result.toString();
    }

    // Метод для генерации случайной строки на кириллице
    public static String generateRandomLatinicString(int length) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        String cyrillicAlphabet = "qweasdzxcvfrtgbnhyujmkiolp"; // кириллический алфавит

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(cyrillicAlphabet.length());
            result.append(cyrillicAlphabet.charAt(index));
        }

        return result.toString();
    }

}