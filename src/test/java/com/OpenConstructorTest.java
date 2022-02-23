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

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@RunWith(Parameterized.class)
public class OpenConstructorTest {
    static MainPage mainPage;

    private final UserOperations userOperations = new UserOperations();

    private final String testName;
    private final String pathToDriver;

    private String email;
    private String password;

    AllureLifecycle oLifecycle = Allure.getLifecycle();

    public OpenConstructorTest(String testName, String pathToDriver) {
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
    @Description("Checking redirect from Account profile page to Main page by clicking on 'Constructor' button")
    public void openConstructorRedirectsToMainPageWhereAssembleBurgerButtonIsVisible() {
        oLifecycle.updateTestCase(testResult -> testResult.setName("Open constructor with " + testName));

        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(email, password);

        mainPage.clickProfileButton();
        AccountProfilePage accountProfile = page(AccountProfilePage.class);
        accountProfile.clickConstructorButton();

        mainPage.getAssembleBurgerText().shouldBe(visible);
    }
}
