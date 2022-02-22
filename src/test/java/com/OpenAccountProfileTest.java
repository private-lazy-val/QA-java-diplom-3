package com;

import com.PO.AccountProfilePage;
import com.PO.LoginPage;
import com.PO.MainPage;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@RunWith(Parameterized.class)
public class OpenAccountProfileTest {
    static MainPage mainPage;

    private final UserOperations userOperations = new UserOperations();

    private final String testName;
    private final String pathToDriver;

    private String email;
    private String password;

    AllureLifecycle oLifecycle = Allure.getLifecycle();

    public OpenAccountProfileTest(String testName, String pathToDriver) {
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

        Map<String, String> userData = userOperations.register();
        email = userData.get("email");
        password = userData.get("password");
    }


    @After
    public void tearDown() {
        getWebDriver().quit();

        userOperations.delete();
    }

    @Test
    @Description("Checking redirect from Main page to Account profile page by clicking on 'Account Profile' button")
    public void openAccountProfileButtonRedirectsToAccountProfile() {
        oLifecycle.updateTestCase(testResult -> testResult.setName("Check Account Profile button with " + testName));

        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(email, password);

        mainPage.clickProfileButton();

        webdriver().shouldHave(url(AccountProfilePage.URL));
    }
}
