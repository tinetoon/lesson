package org.example.lesson03;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CrmCheck {

    public static void main(String[] args) throws InterruptedException {

        // Инициализируем драйвер менеджер
        WebDriverManager.chromedriver().setup();

        // Создаём экземпляр класса webdriver
        WebDriver webDriver = new ChromeDriver();

        // Дополнительно можно задать настройку времени ожидания в течение которого будет работать метод поиска элемента
//        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Откроем сайт CRM
        webDriver.get("https://crm.geekbrains.space/user/login");

        // Выполняем авторизацию на сайте CRM
        login(webDriver);

        // Устанавливаем паузу 10 секунд
        Thread.sleep(10000);

        // Переходим на страницу продажи (!!! исправить XPath)
        List<WebElement> mainMenuItems = getMenuItems(webDriver, "");
        WebElement expensesMenuItems = mainMenuItems
                .stream()
                .filter(e -> e.getText()
                        .equals("Расходы"))
                        .findFirst().get();

        goTo(webDriver, expensesMenuItems, "");
        Thread.sleep(10000);

                // Закрываем браузер
        webDriver.quit();

    }

    // Метод авторизации
    static void login(WebDriver driver) {

        // Создаём экземпляр класса (для примера)
        WebElement element = driver.findElement(By.id("prependedInput"));

        // Заполняем поле логин
        element.sendKeys("Applanatest1");

        // Заполняем поле пароль
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");

        // Кликнем на кнопке Войти
        driver.findElement(By.id("_submit")).click();
    }

    // Метод перехода на страницу
    static void goToSales(WebDriver driver, String xpathHover, String xpathClick) {

        // Создаём экземпляр класса (для примера)
        Actions action = new Actions(driver);

        // Находим нужный пункт меню (для примера)
        WebElement element = driver.findElement(By.xpath(xpathHover));

        // Наведём курсор мыши на нужный пункт меню
        action.moveToElement(element).build().perform();

        // Выберем нужный пункт меню
        driver.findElement(By.xpath(xpathClick)).click();

    }

    // Метод перехода на страницу
    static void goTo(WebDriver driver, WebElement elementHover, String xpathClick) {

        // Создаём экземпляр класса (для примера)
        Actions action = new Actions(driver);

        // Наведём курсор мыши на нужный пункт меню
        action.moveToElement(elementHover).build().perform();

        // Выберем нужный пункт меню
        driver.findElement(By.xpath(xpathClick)).click();

    }

    // Метод поиска элементов меню
    static List<WebElement> getMenuItems(WebDriver driver, String xpath) {
        List<WebElement> menuItems = driver.findElements(By.xpath(xpath));
        return menuItems;
    }
}
