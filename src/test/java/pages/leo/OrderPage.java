package pages.leo;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import pages.AbstractPage;
import java.util.List;

public class OrderPage extends AbstractPage{

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    private void expectDisplayTranslateWords(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(this.findByCss("div.transwidget.arrow-up")));
    }
    private void expectNotFoundWord(String word){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(this.findByXPath("//b[normalize-space(text())='"+word+"']")));
    }


    public OrderPage addWord(String word){
        System.out.println(word);
        WebElement input = this.findByCss("input.input-text.f-left.placeholder-light");
        input.sendKeys(word);
        this.expectNotFoundWord(word);
        input.sendKeys(Keys.ENTER);
        this.expectDisplayTranslateWords();

        if(areElementsPresent(driver, By.cssSelector("div.transword.selected"))){
            List<WebElement> list = driver.findElements(By.cssSelector("div.transword.selected"));
            WebElement translate = list.get(0).findElement(By.cssSelector("a.transword__text.t-ellps"));
            JavascriptExecutor jse2 = (JavascriptExecutor)driver;
            jse2.executeScript("arguments[0].scrollIntoView()", translate);
            jse2.executeScript("arguments[0].click();", translate);
        }
        else {
            List<WebElement> list = driver.findElements(By.cssSelector("div.transword"));
            WebElement translate = list.get(0).findElement(By.cssSelector("a.transword__text.t-ellps"));
            JavascriptExecutor jse2 = (JavascriptExecutor)driver;
            jse2.executeScript("arguments[0].scrollIntoView()", translate);
            jse2.executeScript("arguments[0].click();", translate);
        }
       // this.expectNotFoundWord(word);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public DictionaryPage clickMyWords() {
        this.findByCss("li[data-filter-entry='myWordSets']").click();
        return new DictionaryPage(driver);
    }
}
