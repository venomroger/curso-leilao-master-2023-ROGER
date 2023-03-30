package leilao;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class LoginTest {

    public static final String URL_LOGIN = "http://localhost:8080/login";
    private WebDriver browser;

    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
    }

    @BeforeEach
    public void beforeEach(){
        this.browser = new ChromeDriver();
        this.browser.navigate().to(URL_LOGIN);
        this.browser.manage().window().maximize();

    }
    @AfterEach
    public void afterEach(){
        this.browser.quit();

    }
    @Test
    public void deveriaEfetuarLoginComDadosValidos(){
        browser.findElement(By.name("username")).sendKeys("fulano");
        browser.findElement(By.name("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        Assert.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
        Assert.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos(){
        browser.findElement(By.name("username")).sendKeys("invalido");
        browser.findElement(By.name("password")).sendKeys("123123");
        browser.findElement(By.id("login-form")).submit();

        Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
        Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos"));
        Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));

    }
}
