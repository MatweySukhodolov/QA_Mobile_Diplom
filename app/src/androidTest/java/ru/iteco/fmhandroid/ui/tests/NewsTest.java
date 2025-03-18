package ru.iteco.fmhandroid.ui.tests;
import static ru.iteco.fmhandroid.ui.Data.Helper.deleteNews;
import static ru.iteco.fmhandroid.ui.Data.Helper.randomDescription;
import static ru.iteco.fmhandroid.ui.Data.Helper.randomDescription1;
import static ru.iteco.fmhandroid.ui.Data.Helper.randomDescription14symbolCyryllice;
import static ru.iteco.fmhandroid.ui.Data.Helper.randomDescription15symbolLatinice;

import android.os.SystemClock;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.Data.Helper;
import ru.iteco.fmhandroid.ui.ScreenElements.NewsScreen;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class NewsTest extends BaseTest {
      private final NewsScreen newsScreen = new NewsScreen();

    @Test
    @DisplayName("Редактирование события.")
    public void testEditingNews() {
        Helper.authorizationUser(); // авторизация
        Helper.createNews(); // создание события

        newsScreen.navigateToNewsSection(); // Переход в раздел мень и в раздел новости
        newsScreen.clickEditNewsButton(); // Нажимаем кнопку редактирования новостей
        newsScreen.clickOnNewsItem(Helper.randomDescription1, randomDescription); // Ищем созданную новость в RecyclerView по заголовку и описанию
        newsScreen.clickNewNews(randomDescription1); // Нажимаем кнопку редактирования новости
        SystemClock.sleep(1000);

        newsScreen.editNewsTitleNews(randomDescription); // редактируем титул
        newsScreen.editNewsDescriptionNews(Helper.randomDescription1); // редактируем описание
        newsScreen.clickDateNews();// кликаем по дате
        newsScreen.nextMonthNews(); // выбираем следуьщий месяц
        newsScreen.saveDateNews(); // Подтвердаем выбор даты
        newsScreen.clickTimeNews(); // кликаем по времени
        newsScreen.saveTimeNews(); // подтвердаем время
        newsScreen.notActiveNews(); // Отключить кнопку активности
        newsScreen.saveNews(); // сохраняем
        // Проверка, что новость редактирована
        newsScreen.navigateToNewsSection(); // Переход в раздел мень и в раздел новости
        newsScreen.clickEditNewsButton(); // Нажимаем кнопку редактирования новостей
        newsScreen.clickOnNewsItem(randomDescription, Helper.randomDescription1); // Ищем созданную новость в RecyclerView по заголовку и описанию

        newsScreen.deleteNewsNews(randomDescription); // удаление

    }

    @Test
    @DisplayName("Ввод в поле \"Категория\", ввод времени вручную, " +
            "ввод верхних граничных значений в поле титул и описание, " +
            "а также отмена сохранения в редактировании события")
    public void
    TheCategoryFieldManualTimeCancelingASaveTest() {
        Helper.authorizationUser(); // авторизация
        Helper.createNews(); // создание события

        newsScreen.navigateToNewsSection(); // Переход в раздел мень и в раздел новости
        newsScreen.clickEditNewsButton(); // Нажимаем кнопку редактирования новостей
        newsScreen.clickOnNewsItem(Helper.randomDescription1, randomDescription); // Ищем созданную новость в RecyclerView по заголовку и описанию
        newsScreen.clickNewNews(randomDescription1); // Нажимаем кнопку редактирования новости
        SystemClock.sleep(1000);

        newsScreen.categoryClickNews(); // выбираем категориб
        newsScreen.categoryEnterNews(randomDescription14symbolCyryllice); // вводим вручнуь категориь и сворачиваем список

        newsScreen.editNewsTitleNews(Helper.randomDescription14symbolCyryllice); // редактируем титул
        newsScreen.editNewsDescriptionNews(Helper.randomDescription150symbolLatinice); // редактируем описание
        newsScreen.clickDateNews();// кликаем по дате
        newsScreen.saveTimeNews(); // подтвердаем время
        newsScreen.clickTimeNews();// Выбираем время публикации
        newsScreen.clickClaviatureTimeNews();
        newsScreen.enterHoursNews(13);    // Вводим новое время (часы)
        newsScreen.enterMinuteNews(22); // Вводим новое время - минуты
        newsScreen.saveTimeNews(); // подтвердаем время
        newsScreen.saveNews(); // сохраняем

        newsScreen.cancelEditingNews(); // отменяем сохранение и отменяем отмену
        newsScreen.cancelSaveNews(); // отменяем и подтверэдаем
        deleteNews(); // Удаление новости
    }

    @Test
    @DisplayName("Валидация поля Титул и Описание за пределами" +
            "верхнего граничного значения в редактировании события")
    public void testTitleAndDescriptionBeyondTheBoundaryValue() {
        Helper.authorizationUser(); // авторизация
        Helper.createNews(); // создание события

            newsScreen.navigateToNewsSection(); // Переход в раздел мень и в раздел новости
            newsScreen.clickEditNewsButton(); // Нажимаем кнопку редактирования новостей
            newsScreen.clickOnNewsItem(Helper.randomDescription1, randomDescription); // Ищем созданную новость в RecyclerView по заголовку и описанию
            newsScreen.clickNewNews(randomDescription1); // Нажимаем кнопку редактирования новости
            SystemClock.sleep(1000);

            newsScreen.editNewsTitleNews(randomDescription15symbolLatinice); // редактируем титул
            newsScreen.editNewsDescriptionNews(Helper.randomDescription151symbolLatinice); // редактируем описание
            newsScreen.saveNews(); // сохраняем
            newsScreen.saveNotNews();// Проверка, что cобытие не сохраняется

    }

    @Test
    @DisplayName("Редактирование события пустыми данными")
    public void testEditingAnEventWithEmptyData() {
        Helper.authorizationUser(); // авторизация
        Helper.createNews(); // создание события

        newsScreen.navigateToNewsSection(); // Переход в раздел мень и в раздел новости
        newsScreen.clickEditNewsButton(); // Нажимаем кнопку редактирования новостей
        newsScreen.clickOnNewsItem(Helper.randomDescription1, randomDescription); // Ищем созданную новость в RecyclerView по заголовку и описанию
        newsScreen.clickNewNews(randomDescription1); // Нажимаем кнопку редактирования новости
        SystemClock.sleep(1000);

        newsScreen.editNewsTitleNews(" "); // редактируем титул
        newsScreen.editNewsDescriptionNews(" "); // редактируем описание
        newsScreen.saveNews(); // сохраняем
        newsScreen.saveNotNews();// Проверка, что cобытие не сохраняется;
        newsScreen.cancelSaveNews(); // отменяем и подтверэдаем
        deleteNews(); // Удаление новости
    }
}

