package pages.leo;

import org.openqa.selenium.*;
import pages.AbstractPage;

public class DictionaryPage extends AbstractPage{

    public DictionaryPage(WebDriver driver) {
        super(driver);
    }

    public DictionaryPage clickMyWords() {
        this.findByCss("li[data-filter-entry='myWordSets']").click();
        return this;
    }

    public DictionaryPage clickCreateOrder(){
        this.findByCss("a.btn-blue-flat.gloss-add-group").click();
        return  this;
    }

    public DictionaryPage addOrder(String nameOrder){
        this.findByCss("input.input-simple").sendKeys(nameOrder);
        this.findByCss("a.btn.btn-blue.btn-gloss").click();
        return this;
    }

    public OrderPage clickOrderByName(String nameOrder){
        WebElement order = this.findByCss("a[title='"+nameOrder+"']");
        order.click();
        return new OrderPage(driver);
    }
}
