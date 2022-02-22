package com;

import com.PO.MainPage;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@RunWith(Parameterized.class)
public class ConstructorTest {
    static MainPage mainPage;

    private final String testName;
    private final String pathToDriver;

    AllureLifecycle oLifecycle = Allure.getLifecycle();

    public ConstructorTest(String testName, String pathToDriver) {
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
    }

    @Test
    @Description("Checking if 'Buns' block is displayed after clicking on 'Buns' section")
    public void clickOnBunsSectionsOpensBunsBlock() {
        oLifecycle.updateTestCase(testResult -> testResult.setName("Check 'Buns' block is visible with " + testName));

        // Кликаем на раздел с соусами для того, чтобы потом "перейти" (это слово используется в задании) в раздел с булками
        mainPage.clickSaucesSection();
        mainPage.clickBunsSection();
        mainPage.checkBunsBlockIsVisible();
    }

    @Test
    @Description("Checking if 'Sauces' block is displayed after clicking on 'Sauces' section")
    public void clickOnSaucesSectionsOpensSaucesBlock() {
        oLifecycle.updateTestCase(testResult -> testResult.setName("Check 'Sauces' block is visible with " + testName));

        mainPage.clickSaucesSection();
        mainPage.checkSaucesBlockIsVisible();
    }

    @Test
    @Description("Checking if 'Fillings' block is displayed after clicking on 'Fillings' section")
    public void clickOnFillingsSectionsOpensFillingsBlock() {
        oLifecycle.updateTestCase(testResult -> testResult.setName("Check 'Fillings' block is visible with " + testName));

        mainPage.clickFillingsSection();
        mainPage.checkFillingsBlockIsVisible();
    }
}