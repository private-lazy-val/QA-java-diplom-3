package com.PO;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class MainPage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement LogInButton;

    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    private SelenideElement profileButton;

    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bunsSection;

    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement saucesSection;

    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement fillingsSection;

    @FindBy(how = How.CLASS_NAME, using = "accordion__item")
    private ElementsCollection questionAnswerFields;

    @FindBy(how = How.XPATH, using = ".//h2[text()='Булки']")
    private SelenideElement BunsBlock;

    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
    private SelenideElement SaucesBlock;

    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
    private SelenideElement FillingsBlock;

    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement createOrderButton;

    @FindBy(how = How.XPATH, using = ".//h1[text()='Соберите бургер']")
    private SelenideElement assembleBurgerText;

    public void clickLogInButton() {
        LogInButton.click();
    }

    public void clickProfileButton() {
        profileButton.click();
    }

    public void clickBunsSection() {
        bunsSection.click();
    }

    public void clickSaucesSection() {
        saucesSection.click();
    }

    public void clickFillingsSection() {
        fillingsSection.click();
    }

    public void checkBunsBlockIsVisible() {
        BunsBlock.shouldBe(visible);
    }

    public void checkSaucesBlockIsVisible() {
        SaucesBlock.shouldBe(visible);
    }

    public void checkFillingsBlockIsVisible() {
        FillingsBlock.shouldBe(visible);
    }

    public void checkCreateOrderButtonIsVisible() {
        createOrderButton.shouldBe(visible);
    }

    public void checkAssembleBurgerTextIsVisible() {
        assembleBurgerText.shouldBe(visible);
    }
}
