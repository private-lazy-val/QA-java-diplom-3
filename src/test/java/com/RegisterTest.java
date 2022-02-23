package com;

import com.PO.LoginPage;
import com.PO.MainPage;
import com.PO.RegisterPage;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@RunWith(Parameterized.class)
public class RegisterTest {
    static MainPage mainPage;

    private final UserOperations userOperations = new UserOperations();

    private final String EMAIL = "@yandex.ru";
    private final String testName;
    private final String pathToDriver;

    private String email;
    private String password;

    AllureLifecycle oLifecycle = Allure.getLifecycle();

    public RegisterTest(String testName, String pathToDriver) {
        this.testName = testName;
        this.pathToDriver = pathToDriver;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Yandex driver", "/Users/valeriyalondonenko/Downloads/yandexdriver"},
                {"Chrome driver", "/Users/valeriyalondonenko/Downloads/chromedriver"}
        };
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        mainPage = open(MainPage.URL, MainPage.class);
    }


    @After
    public void tearDown() {
        getWebDriver().quit();
        if (email != null) {
            // Логиним пользователя для последующего удаления
            userOperations.loginUserAndSaveToken(email, password);
            // Удаляем пользователя
            userOperations.delete();
        }
    }

    @Test
    @Description("Checking redirect to Login page after successful registration")
    public void registerNewAccountWithCorrectDataRegistersNewUser() {
        oLifecycle.updateTestCase(testResult -> testResult.setName("Check registration with " + testName));

        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterButton();
        String name = RandomStringUtils.randomAlphabetic(10);
        email = RandomStringUtils.randomAlphabetic(10) + EMAIL;
        password = RandomStringUtils.randomAlphabetic(6);
        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.registerNewUser(name, email, password);

        webdriver().shouldHave(url(LoginPage.URL));
    }

    @Test
    @Description("Checking if incorrect password error is displayed in case password is less than 6 digits")
    public void registerNewAccountWithInvalidPasswordDisplaysError() {
        oLifecycle.updateTestCase(testResult -> testResult.setName("Check password error with " + testName));

        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterButton();
        String name = RandomStringUtils.randomAlphabetic(10);
        String email = RandomStringUtils.randomAlphabetic(10) + EMAIL;
        String password = RandomStringUtils.randomAlphabetic(5);
        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.registerNewUser(name, email, password);

        registerPage.getInvalidPasswordErrorMessage().shouldBe(visible);
    }
}