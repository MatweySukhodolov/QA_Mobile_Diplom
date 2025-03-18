package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.Data.Helper;
import ru.iteco.fmhandroid.ui.ScreenElements.AuthorizationScreen;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AuthorizationTest extends BaseTest {
    private final AuthorizationScreen authorizationScreen = new AuthorizationScreen();

    @Test
    @DisplayName("Авторизация пользователя.")
    public void authorizationTest() {

        Helper.authorizationUser();
    }

    @Test
    @DisplayName("Авторизация пользователя с пустым полем login.")
    public void authInvalideLoginSpaceTest() {
        authorizationScreen.loginText(" ");
        authorizationScreen.passwordText("password2");
        authorizationScreen.clickAuthEnter();
        authorizationScreen.textDescription("Login and password cannot be empty");
    }

    @Test
    @DisplayName("Авторизация пользователя с пустым полем password.")
    public void authInvalidePasswordSpaceTest() {
        authorizationScreen.loginText("login2");
        authorizationScreen.passwordText(" ");
        authorizationScreen.clickAuthEnter();
        authorizationScreen.textDescription("Login and password cannot be empty");
 }

    @Test
    @DisplayName("Авторизация пользователя с несуществующим логином.")
    public void authInvalidLoginNameTest() {
        authorizationScreen.loginText("login3");
        authorizationScreen.passwordText("password2");
        authorizationScreen.clickAuthEnter();
        authorizationScreen.textDescription("Something went wrong. Try again later");
 }

    @Test
    @DisplayName("Авторизация пользователя с неверным паролем.")
    public void authFalsePasswordTest() {
        authorizationScreen.loginText("login2");
        authorizationScreen.passwordText("password3");
        authorizationScreen.clickAuthEnter();
        authorizationScreen.textDescription("Something went wrong. Try again later");
 }

    @Test
    @DisplayName("Ввод нижнего граничного значения в поле login - 5 символов.")
    public void enteringTheLowerLimitValueLoginTest() {
        authorizationScreen.loginText("login");
        authorizationScreen.passwordText("password2");
        authorizationScreen.clickAuthEnter();
        authorizationScreen.textDescription1("The field is at least 6 and no more than 14 characters long.");
    }

    @Test
    @DisplayName("Ввод нижнего граничного значения в поле password - 5 символов.")
    public void enteringTheLowerLimitPasswordLoginTest() {
        authorizationScreen.loginText("login2");
        authorizationScreen.passwordText("passw");
        authorizationScreen.clickAuthEnter();
        authorizationScreen.textDescription1("The field is at least 6 and no more than 14 characters long.");
 }

    @Test
    @DisplayName("Ввод верхнего граничного значения в поле password - 15 символов.")
    public void enteringTheUpperLimitPasswordLoginTest() {
        authorizationScreen.loginText("login2");
        authorizationScreen.passwordText("passwpasswpassw");
        authorizationScreen.clickAuthEnter();
        authorizationScreen.textDescription1("The field is at least 6 and no more than 14 characters long.");
    }

    @Test
    @DisplayName("Ввод верхнего граничного значения в поле login - 15 символов")
    public void enteringTheUpperLimitLoginLoginTest() {
        authorizationScreen.loginText("passwpasswpassw");
        authorizationScreen.passwordText("password2");
        authorizationScreen.clickAuthEnter();
        authorizationScreen.textDescription1("The field is at least 6 and no more than 14 characters long.");
 }
}
