package ru.iteco.fmhandroid.ui.tests;

import static android.os.Trace.isEnabled;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static ru.iteco.fmhandroid.ui.Data.Helper.randomDescription14symbolCyryllice;
import static ru.iteco.fmhandroid.ui.Data.Helper.randomDescription150symbolLatinice;
import static ru.iteco.fmhandroid.ui.Data.Helper.randomDescription151symbolLatinice;
import static ru.iteco.fmhandroid.ui.Data.Helper.randomDescription15symbolLatinice;
import static ru.iteco.fmhandroid.ui.Data.NewsHelper.childAtPosition;

import android.os.SystemClock;

import androidx.test.espresso.IdlingPolicies;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Data.Helper;
import ru.iteco.fmhandroid.ui.ScreenElements.NewsScreen;
import ru.iteco.fmhandroid.ui.utils.EspressoIdlingResources;


public class CreatedNewsTest extends BaseTest {
    private final NewsScreen newsScreen = new NewsScreen();

    @Test
    @DisplayName("Создание нового события и ввод верхних граничных значений " +
            "в поле Титул и Описание.")
    public void CreatedNewNewsTest() {
        Helper.authorizationUser(); // авторизация
        //SystemClock.sleep(2000);

        // Настройка таймаутов
        IdlingPolicies.setMasterPolicyTimeout(30, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(30, TimeUnit.SECONDS);

        // Регистрация IdlingResource
        IdlingRegistry.getInstance().register(EspressoIdlingResources.getIdlingResource());

        newsScreen.navigateToNewsSection(); // Переход в раздел мень и в раздел новости
        newsScreen.clickEditNewsButton(); // Нажимаем кнопку редактирования новостей

        newsScreen.clickAddNews(); // выбираем создание новости
        newsScreen.categoryClickNews(); // выбираем категориь
        newsScreen.categoryEnterNews("Массаж"); // вводим и подтвердаем категори
        newsScreen.editNewsTitleNews(randomDescription14symbolCyryllice); // вводим титул
        newsScreen.editNewsDescriptionNews(randomDescription150symbolLatinice);
        newsScreen.clickDateNews();// кликаем по дате
        newsScreen.saveDateNews(); // Подтвердаем выбор даты
        newsScreen.clickTimeNews(); // кликаем по времени
        newsScreen.saveTimeNews(); // подтвердаем время
        newsScreen.saveNews(); // сохраняем
        // Проверка, что новость редактирована
        newsScreen.navigateToNewsSection(); // Переход в раздел мень и в раздел новости
        newsScreen.clickEditNewsButton(); // Нажимаем кнопку редактирования новостей
        newsScreen.clickOnNewsItem(randomDescription14symbolCyryllice, randomDescription150symbolLatinice); // Ищем созданную новость в RecyclerView по заголовку и описанию

        newsScreen.deleteNewsNews(randomDescription14symbolCyryllice); // удаление
    }

    @Test
    @DisplayName("Создание нового события пустыми данными и отмена создания.")
    public void CreatedNewSpaceNewsTest() {
        Helper.authorizationUser(); // авторизация
        //SystemClock.sleep(2000);

        // Настройка таймаутов
        IdlingPolicies.setMasterPolicyTimeout(30, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(30, TimeUnit.SECONDS);

        // Регистрация IdlingResource
        IdlingRegistry.getInstance().register(EspressoIdlingResources.getIdlingResource());

        newsScreen.navigateToNewsSection(); // Переход в раздел мень и в раздел новости
        newsScreen.clickEditNewsButton(); // Нажимаем кнопку редактирования новостей

        newsScreen.clickAddNews(); // выбираем создание новости
        newsScreen.categoryClickNews(); // выбираем категориь
        newsScreen.categoryEnterNews("Массаж"); // вводим и подтвердаем категори
        newsScreen.editNewsTitleNews(" "); // вводим титул
        newsScreen.editNewsDescriptionNews(" ");
        newsScreen.saveNews(); // сохраняем
        newsScreen.saveNotNews();// Проверка, что cобытие не сохраняется

        newsScreen.cancelEditingNews(); // отменяем сохранение и отменяем отмену
        newsScreen.cancelSaveNews(); // отменяем и подтверэдаем

        // Отмена регистрации IdlingResource
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.getIdlingResource());
    }

    @Test
    @DisplayName("Ввод в поле Титул и Описание данных за пределами верхнего" +
            " граничного значения, ввод в ручную поля Время при добавлении события")
    public void TitleAndDescriptionBeyondTheUpperLimitValueTest() {
        Helper.authorizationUser(); // авторизация
//        SystemClock.sleep(2000);

        // Настройка таймаутов
        IdlingPolicies.setMasterPolicyTimeout(30, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(30, TimeUnit.SECONDS);

        // Регистрация IdlingResource
        IdlingRegistry.getInstance().register(EspressoIdlingResources.getIdlingResource());

        newsScreen.navigateToNewsSection(); // Переход в раздел мень и в раздел новости
        newsScreen.clickEditNewsButton(); // Нажимаем кнопку редактирования новостей

        newsScreen.clickAddNews(); // выбираем создание новости
        newsScreen.categoryClickNews(); // выбираем категориь
        newsScreen.categoryEnterNews("Массаж"); // вводим и подтвердаем категори
        newsScreen.editNewsTitleNews(randomDescription15symbolLatinice); // вводим титул
        newsScreen.editNewsDescriptionNews(randomDescription151symbolLatinice); // ввод описания
        newsScreen.clickDateNews();// кликаем по дате
        newsScreen.saveDateNews(); // Подтвердаем выбор даты
        newsScreen.clickTimeNews(); // кликаем по времени
        newsScreen.clickClaviatureTimeNews(); // выбираем ручной ввод
        newsScreen.enterHoursNews(13);    // Вводим новое время (часы)
        newsScreen.enterMinuteNews(22); // Вводим новое время - минуты
        newsScreen.saveTimeNews(); // подтвердаем время
        newsScreen.saveNews(); // сохраняем
        newsScreen.saveNotNews();// Проверка, что cобытие не сохраняется

        // Отмена регистрации IdlingResource
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.getIdlingResource());
    }

    @Test
    @DisplayName("Валидация поля Категория")
    public void ValidationCategoryTest() {
        Helper.authorizationUser(); // авторизация
       // SystemClock.sleep(2000);

        // Настройка таймаутов
        IdlingPolicies.setMasterPolicyTimeout(30, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(30, TimeUnit.SECONDS);

        // Регистрация IdlingResource
        IdlingRegistry.getInstance().register(EspressoIdlingResources.getIdlingResource());

        newsScreen.navigateToNewsSection(); // Переход в раздел мень и в раздел новости
        newsScreen.clickEditNewsButton(); // Нажимаем кнопку редактирования новостей

        newsScreen.clickAddNews(); // выбираем создание новости
        newsScreen.categoryClickNews(); // выбираем категориь
        newsScreen.categoryEnterNews("Чистка обуви"); // вводим и подтвердаем категори

        newsScreen.editNewsTitleNews(randomDescription14symbolCyryllice); // вводим титул
        newsScreen.editNewsDescriptionNews(randomDescription150symbolLatinice);
        newsScreen.clickDateNews();// кликаем по дате
        newsScreen.saveDateNews(); // Подтвердаем выбор даты
        newsScreen.clickTimeNews(); // кликаем по времени
        newsScreen.saveTimeNews(); // подтвердаем время

        newsScreen.saveNews(); // сохраняем
        newsScreen.saveNotNews();// Проверка, что cобытие не сохраняется


        // Отмена регистрации IdlingResource
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.getIdlingResource());
    }

    @Test
    @DisplayName("Валидация поля Время при добавлении события")
    public void TimeValidationTest() {
        Helper.authorizationUser(); // авторизация

        // Настройка таймаутов
        IdlingPolicies.setMasterPolicyTimeout(30, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(30, TimeUnit.SECONDS);

        // Регистрация IdlingResource
        IdlingRegistry.getInstance().register(EspressoIdlingResources.getIdlingResource());
        SystemClock.sleep(2000);
        newsScreen.navigateToNewsSection(); // Переход в раздел мень и в раздел новости
        newsScreen.clickEditNewsButton(); // Нажимаем кнопку редактирования новостей

        newsScreen.clickAddNews(); // выбираем создание новости
        newsScreen.categoryClickNews(); // выбираем категориь
        newsScreen.categoryEnterNews("Массаж"); // вводим и подтвердаем категори
        newsScreen.editNewsTitleNews(randomDescription14symbolCyryllice); // вводим титул
        newsScreen.editNewsDescriptionNews(randomDescription150symbolLatinice);
        newsScreen.clickDateNews();// кликаем по дате
        newsScreen.saveDateNews(); // Подтвердаем выбор даты
        newsScreen.clickTimeNews(); // кликаем по времени
        newsScreen.clickClaviatureTimeNews(); // выбираем ручной ввод
        newsScreen.enterHoursNews(25);    // Вводим новое время (часы)
        newsScreen.enterMinuteNews(00); // Вводим новое время - минуты
        newsScreen.saveTimeNews(); // подтвердаем время
        newsScreen.enterValidTime(); // проверяем появление ошибки
        newsScreen.saveNotTimeNews(); // выходим из часов
        newsScreen.cancelSaveNews(); // отменяем и подтверэдаем

        // Отмена регистрации IdlingResource
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.getIdlingResource());
    }

}