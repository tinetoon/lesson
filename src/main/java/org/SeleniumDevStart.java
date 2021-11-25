package org;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;

public class SeleniumDevStart {

    public static void main(String[] args) {

        // Регистрируем chromedriver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        // Создаём экземпляр класса webdriver
        WebDriver webDriver = new ChromeDriver();

        // Откроем сайт Google
        webDriver.get("https://www.google.com");

        // Устанавливаем паузу 10 секунд
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Закрываем вкладку браузера
//        webDriver.close();

        // Закрываем браузере
        webDriver.quit();

        // Регистрация драйвера с помощью WebDriverManager
        WebDriverManager.operadriver().setup();
        WebDriver operaDriver = new OperaDriver();
        operaDriver.get("https://yandex.ru");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        operaDriver.quit();
    }
}
