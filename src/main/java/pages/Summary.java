package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.callput.ShowCallPutEOW;

public class Summary extends BasePage{

    public Summary(WebDriver driver) {
        super(driver);
    }

    //Неделя      ctl00_ucSelector_lvGroupsExpirations_ctrl1_lvExpirations_ctrl0_lbExpiration
    //Понедельник ctl00_ucSelector_lvGroupsExpirations_ctrl2_lvExpirations_ctrl0_lbExpiration
    //Вторник     ctl00_ucSelector_lvGroupsExpirations_ctrl3_lvExpirations_ctrl0_lbExpiration
    //Среда       ctl00_ucSelector_lvGroupsExpirations_ctrl4_lvExpirations_ctrl0_lbExpiration
    //Четверг     ctl00_ucSelector_lvGroupsExpirations_ctrl5_lvExpirations_ctrl0_lbExpiration
    //Месяц       ctl00_ucSelector_lvGroupsExpirations_ctrl6_lvExpirations_ctrl0_lbExpiration

    @FindBy(xpath = "//*[@id='ctl00_ucSelector_hlExpiration']")
    WebElement clickmenu;

    @FindBy(xpath = "//*[@id='ctl00_ucSelector_lvGroupsExpirations_ctrl1_lvExpirations_ctrl0_lbExpiration']")//Колл-Пут Недели
    WebElement callputweek;

    public ShowCallPutEOW getCallPutWeek() {//Смотрим Колл-Пут недельного контракта
        click(clickmenu);
        pause(5000);
        click(callputweek);
        pause(5000);
        return new ShowCallPutEOW(driver);
    }

}
