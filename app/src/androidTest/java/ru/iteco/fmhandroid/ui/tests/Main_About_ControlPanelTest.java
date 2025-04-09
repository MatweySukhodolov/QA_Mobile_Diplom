package ru.iteco.fmhandroid.ui.tests;

import android.os.SystemClock;

import org.junit.Test;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.Data.Helper;
import ru.iteco.fmhandroid.ui.ScreenElements.Main_About_ControlPanelScreen;
import ru.iteco.fmhandroid.ui.ScreenElements.NewsScreen;

public class Main_About_ControlPanelTest extends BaseTest {
    private final Main_About_ControlPanelScreen main_About_ControlPanelScreen = new Main_About_ControlPanelScreen();
    private final NewsScreen newsScreen = new NewsScreen();

    @Test
    @DisplayName("Развертывание и сворачивание вкладки News.")
    public void deployingAndMinimizingTheNewsTabTest() {
        Helper.authorizationUser(); // авторизация
        main_About_ControlPanelScreen.expandMaterialButton(); // Находим кнопку развертывания/сворачивания вкладки News и кликаем на нее
        main_About_ControlPanelScreen.mainSwipeRefresh(); // Проверяем, что вкладка News свернута
        main_About_ControlPanelScreen.expandMaterialButton(); // Снова кликаем на кнопку, чтобы свернуть вкладку
        main_About_ControlPanelScreen.allNewsText(); // Проверяем, что вкладка News свернута (например, проверяем, что заголовок больше не отображается)
    }

    @Test
    @DisplayName("Функциональность вкладки About.")
    public void aboutFunctionalityTabTest() {
        Helper.authorizationUser(); // авторизация
      //  SystemClock.sleep(2000);
        main_About_ControlPanelScreen.navigateToAboutSection(); // открываем главное меню и переходим в раздел About
        main_About_ControlPanelScreen.aboutPrivacyPolicy(); // проверяем ссылку Privace
        main_About_ControlPanelScreen.aboutTermsOfUse(); // проверяем ссылку Terms
        main_About_ControlPanelScreen.aboutBack(); // возвращение обратно
        main_About_ControlPanelScreen.containerListNewsIncludeOnFragment(); // Проверяем, что основной экран отображается
            }


    @Test
    @DisplayName("Функциональность вкладки Love iss all.")
    public void loveIssAllTest() {
        Helper.authorizationUser();
        //SystemClock.sleep(2000);
        main_About_ControlPanelScreen.ourMission();// Кликаем на кнопку "Our Mission"
        main_About_ControlPanelScreen.positionLoveIssAll(0);// Кликаем на первый элемент в RecyclerView
        main_About_ControlPanelScreen.positionLoveIssAll(0);// Кликаем снова на первый элемент
        main_About_ControlPanelScreen.positionLoveIssAll(5);// Кликаем на 5-й элемент
        main_About_ControlPanelScreen.positionLoveIssAll(7);// Кликаем на 7-й элемент
        }

    @Test
    @DisplayName("Функциональность раздела Control panel.")
    public void controlPanelTest() {
        Helper.authorizationUser();
        //SystemClock.sleep(2000);
        newsScreen.navigateToNewsSection(); // Переход в раздел мень и в раздел новости
        newsScreen.clickEditNewsButton(); // Нажимаем кнопку редактирования новостей

        main_About_ControlPanelScreen.newsListRecycler(2);// Кликаем на второй элемент в RecyclerView
        main_About_ControlPanelScreen.newsListRecycler(2);// Снова кликаем на второй элемент в RecyclerView
        main_About_ControlPanelScreen.sortNewsMaterial();// Кликаем на кнопку сортировки новостей
        main_About_ControlPanelScreen.sortNewsMaterial();// Снова кликаем на кнопку сортировки новостей
        main_About_ControlPanelScreen.navigateToMainSection();// Кликаем на кнопку меню и открываем Main
        main_About_ControlPanelScreen.containerListNewsIncludeOnFragmentMain();// Проверяем, что контейнер с новостями отображается
    }

}

