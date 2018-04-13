package com;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.leo.*;
import read.Reader;
import selenium.WebDriverFactory;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Runner {
    protected WebDriver driver;

    @Parameters({"browser"})
    @BeforeTest
    public void configureBrowser(String br){
        switch (br){
            case "chrome":
                driver = WebDriverFactory.configureChrome();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                break;
            case "firefox":
                driver = WebDriverFactory.configureFirefox();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                break;
            default:
                try {
                    throw new Exception("not supported browser");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    @Test
    @Parameters({"login", "password", "nameOrder", "countWords", "organize", "filePath"})
    public void runApplication(String login, String password, String nameOrder, int countWords, String organize, String filePath){
        StartPage startPage = new StartPage(driver);
        DictionaryPage dictionaryPage = startPage.openPage().login(login, password).clickDictionary().clickMyWords();
        Reader reader = new Reader();
        int nameIndex = 0;
        List<List<String>> lists = new ArrayList<>();

        switch (organize){
            case "alphabet":
                for (Map.Entry<String, List<String>> entry : reader.readWordsOrderByAlphabet(filePath).entrySet()){
                    lists.add(entry.getValue());
                }
                break;
            case  "default":
                lists.add(reader.readWords(filePath));
                break;
            default:
                throw new IllegalArgumentException();
        }

        for (int k=0; k<lists.size(); k++){
            List<String> listWords = lists.get(k);
            int countListWords = listWords.size();
            int countOrders = countListWords / countWords;
            int mod = countListWords % countWords;
            int count = 0;
            if (mod==0 && countOrders==0){
                count = 0;
            }
            else if (mod==0 && countOrders>0){
                count = countOrders;
            } else if (mod>0 && countOrders>0){
                count = countOrders+1;
            }else if (mod>0 && countOrders==0){
                count = 1;
            }
            int a = Integer.toString(count).length();
            int indexWord = 0;
            for (int i=0; i<count; i++) {
                String str = "000000" + (nameIndex + 1);
                nameIndex++;
                String substring = str.substring(str.length() - a - 1);
                OrderPage orderPage = dictionaryPage.clickCreateOrder().addOrder(substring + " " + nameOrder).clickOrderByName(substring + " " + nameOrder);
                if (countListWords - countWords >= 0) {
                    for (int j = indexWord; j < indexWord + countWords; j++) {
                        orderPage.addWord(listWords.get(j));
                    }
                    countListWords = countWords - countWords;
                    indexWord = indexWord + countWords;
                    dictionaryPage = orderPage.clickMyWords();
                } else {
                    for (int j = indexWord; j < indexWord + mod; j++) {
                        orderPage.addWord(listWords.get(j));
                    }
                    dictionaryPage = orderPage.clickMyWords();
                }
            }
        }


    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }


}
