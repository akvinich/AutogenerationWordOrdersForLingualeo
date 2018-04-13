package pages.leo;

import org.openqa.selenium.WebDriver;
import pages.AbstractPage;

public class DashboardPage extends AbstractPage{
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public DictionaryPage clickDictionary(){
        this.findByCss("a.b-dict-link.b-header__dict").click();
        return new DictionaryPage(driver);
    }




}
