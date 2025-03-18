package ru.iteco.fmhandroid.ui.tests;

import android.os.SystemClock;

import org.junit.Test;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.Data.Helper;
import ru.iteco.fmhandroid.ui.ScreenElements.NewsFilterScreen;
import ru.iteco.fmhandroid.ui.ScreenElements.NewsScreen;

public class NewsFilterTest extends BaseTest {
    private final NewsScreen newsScreen = new NewsScreen();
    private final NewsFilterScreen newsFilterScreen = new NewsFilterScreen();

    @Test
    @DisplayName("Фильтр по категориям.")
    public void filterCategoryTest() {
        Helper.authorizationUser(); // авторизация
        SystemClock.sleep(2000);
        newsScreen.navigateToNewsSection(); // Переход в раздел мень и в раздел новости
        newsScreen.clickEditNewsButton(); // Нажимаем кнопку редактирования новостей
        newsFilterScreen.clickFilterNews(); // Нажимаем кнопку фильтрации новостей
        newsScreen.categoryClickNews();// Выбираем категорию
        newsScreen.categoryEnterNews("Обьявления"); // вводим и подтвердаем категориь
        newsFilterScreen.clickFilterButton();// Применяем фильтр
        newsFilterScreen.mainMenuFilterNews();// Проверяем список отфильтрованных событий
    }

    @Test
    @DisplayName("Фильтр по дате.")
    public void filterDateTest() {
        Helper.authorizationUser(); // авторизация
        SystemClock.sleep(2000);
        newsScreen.navigateToNewsSection(); // Переход в раздел мень и в раздел новости
        newsScreen.clickEditNewsButton(); // Нажимаем кнопку редактирования новостей
        newsFilterScreen.clickFilterNews(); // Нажимаем кнопку фильтрации новостей
        newsFilterScreen.dateStartFilterNews();// Нажимаем на начальное поле даты публикации
        newsFilterScreen.clickDateStartNewsFilter(); // подтвердаем начальнуь дату
        newsFilterScreen.dateEndNewsFilter();// Нажимаем на окончательное поле даты публикации
        newsFilterScreen.nextMonthFilterNews(); // Выбираем дату - следущий месяц
        newsFilterScreen.clickDateEndNews() ;// Подтверждаем выбор даты
        newsFilterScreen.clickFilterButton();// Применяем фильтр
        newsFilterScreen.mainMenuFilterNews();// Проверяем список отфильтрованных событий
    }

    @Test
    @DisplayName("Фильтр по активным событиям.")
    public void filterActiveTest() {
        Helper.authorizationUser(); // авторизация
        SystemClock.sleep(2000);
        newsScreen.navigateToNewsSection(); // Переход в раздел мень и в раздел новости
        newsScreen.clickEditNewsButton(); // Нажимаем кнопку редактирования новостей
        newsFilterScreen.clickFilterNews(); // Нажимаем кнопку фильтрации новостей
        newsFilterScreen.filterNewsActive(); // Фильтруем по статусу "Активные"
        newsFilterScreen.clickFilterButton();// Применяем фильтр
        newsFilterScreen.mainMenuFilterNews(); // Проверяем список отфильтрованных событий
    }

    @Test
    @DisplayName("Фильтр по неактивным событиям.")
    public void filterNotActiveTest() {
        Helper.authorizationUser(); // авторизация
        SystemClock.sleep(2000);
        newsScreen.navigateToNewsSection(); // Переход в раздел мень и в раздел новости
        newsScreen.clickEditNewsButton(); // Нажимаем кнопку редактирования новостей
        newsFilterScreen.clickFilterNews(); // Нажимаем кнопку фильтрации новостей
        newsFilterScreen.filterNewsNotActive(); // Фильтруем по статусу "Активные"
        newsFilterScreen.clickFilterButton();// Применяем фильтр
        newsFilterScreen.mainMenuFilterNews(); // Проверяем список отфильтрованных событий
 }

    @Test
    @DisplayName("Выход из фильтра.")
    public void filterCancelTest() {
        Helper.authorizationUser(); // авторизация
        SystemClock.sleep(2000);
        newsScreen.navigateToNewsSection(); // Переход в раздел мень и в раздел новости
        newsScreen.clickEditNewsButton(); // Нажимаем кнопку редактирования новостей
        newsFilterScreen.clickFilterNews(); // Нажимаем кнопку фильтрации новостей
        newsFilterScreen.cancelFilterNews();
        newsFilterScreen.mainMenuFilterNews(); // Переходим на страницу мень
    }
}
