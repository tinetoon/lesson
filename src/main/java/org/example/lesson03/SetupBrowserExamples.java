package org.example.lesson03;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SetupBrowserExamples {

    public static void main(String[] args) throws InterruptedException {

        // Инициализируем драйвер менеджер
        WebDriverManager.chromedriver().setup();

        // Создаём экземпляр класса ChromeOptions
        ChromeOptions chromeOptions = new ChromeOptions();

        // Передаём в объект chromeOptions настройки для браузера
        // --no-sandbox - для работы в докер-контейнере для Chrome
        // --disable-notification - отключение всплывающих окон
        chromeOptions
                .addArguments("--no-sandbox")
                .addArguments("--disable-notification")
                .addArguments("user-agent=Googlebot/2.1 (+http://www.google.com/bot.html)");

        // Создаём экземпляр класса webdriver и передаём в него объект настроек chromeOptions
        WebDriver webDriver = new ChromeDriver(chromeOptions);

        // Откроем сайт Google
        webDriver.get("https://www.google.com");

        // Устанавливаем паузу 10 секунд
        Thread.sleep(3000);

        // Открываем новую вкладку с помощью выполнения скрипта
        ((JavascriptExecutor)webDriver).executeScript("window.open()");
        Thread.sleep(3000);

        // Для переключения на другую вкладку необходимо:
        // - получить список дескрипторов .getWindowHandles()
        // список вкладок помещаем в лист в порядке их открытия
        List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        // - переключиться на нужный с помощью метода .switchTo()
        webDriver.switchTo().window(tabs.get(0));
        Thread.sleep(3000);

        // Переключение на iFrame
//        String LOCATOR = "";
//        webDriver.switchTo().frame(LOCATOR);

        // Возврат обратно в страницу
//        webDriver.switchTo().defaultContent();

        // Закрываем браузере
        webDriver.quit();
    }
}
