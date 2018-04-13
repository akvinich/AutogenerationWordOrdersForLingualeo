package pages.leo;

import org.openqa.selenium.WebDriver;
import pages.AbstractPage;

public class StartPage extends AbstractPage{

    public StartPage(WebDriver driver) {
        super(driver);
    }

    public StartPage openPage(){
        this.open("https://lingualeo.com/ru");
        return this;
    }

    public DashboardPage login(String login, String password){
        this.findById("headEnterBtn").click();
        this.findByCss("#loginForm > div > div:nth-child(1) > input").sendKeys(login);
        this.findByCss("#loginForm > div > div:nth-child(2) > input").sendKeys(password);
        this.findByCss("#loginForm > button").click();
        return new DashboardPage(driver);
    }
}
