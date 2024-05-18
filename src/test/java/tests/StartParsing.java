package tests;

import org.testng.annotations.Test;
import pages.*;
import pages.autorisation.AutorisationPage;

import pages.board.ShowBoardEOD;
import pages.board.ShowBoardEOC;
import pages.board.ShowBoardEOB;
import pages.board.ShowBoardEOA;
import pages.board.ShowBoardEOM;

import pages.callput.*;
import pages.OpenInteresSB;
import pages.maxpain.*;

public class StartParsing extends TestBase {

    BasePage balance = new BasePage();

    private int timeOfPause = 15000;

    @Test
    public void make_Start() {
        //Заполняем форму авторизации и по кнопке сабмит переходим на
        //двойную аутентификацию - Duo
        new AutorisationPage(driver).enterAutorisation().pressLoginBtn();
        pause(40000);

        //После прохождения Duo и Капчи попадаем на главную страницу
        HomePage homePage = new HomePage(driver);
        //homePage.pause(15000);//пауза 15 секунд
        homePage.choiceHomeMenu1();//Выбираем меню
        homePage.choiceHomeMenu2();//Выбираем в меню -> подменю
        homePage.getSP500();//После прохода через меню и подменю выбираем инструмент SP500

        //get_QuikStrike() {
        QuikStrike quikStrike = new QuikStrike(driver);
        //quikStrike.pause(1000);//пауза 1 секунда
        quikStrike.getOpenInteres();//В главном меню переходим на вкладку Открытый Интерес

        //get_OpenInteres() {
        //На вкладке Открытий Интерес в вертикальном меню левого сайдбара выбираем Самурая
        OpenInteres openInteres = new OpenInteres(driver);
        openInteres.pause(1000);//пауза 1 секунда

        openInteres.getSummary();

        //get_Summary() {
        //На вкладке Summary вертикального меню левого сайдбара выбираем просмотр Колл-Пут НЕДЕЛИ
        Summary summary = new Summary(driver);
        summary.pause(timeOfPause);
        summary.getCallPutWeek();

        //get_CallPut_Week() {
        //В выпадающем меню выбираем показать все страйки по контракту
        ShowCallPutEOW showCallPutEOW = new ShowCallPutEOW(driver);
        showCallPutEOW.pause(timeOfPause);

        //showCallPutEOW.getOpenInteresSB();// <- Если разремить эту строку, а блоки :
        // =  Грузим коллы и путы контрактов в БД  = и =  Грузим балансы контрактов в БД  = заремить, то будем тестить блок:
        // =  Грузим опционные доски всех контрактов в БД  =

//==================  Грузим коллы и путы контрактов в БД  ==================================

        showCallPutEOW.selectAllRows();
        showCallPutEOW.pause(timeOfPause);
        showCallPutEOW.makeScreen();//Делаю скриншот для проверки даты по загружаемым данным
        showCallPutEOW.pause(timeOfPause);
        showCallPutEOW.loadingCallPut();//Грузим в БД коллы и путы недели
        //showCallPutEOW.pause(timeOfPause);

        //get_CallPut_Monday() {
        ShowCallPutEOA showCallPutEOA = new ShowCallPutEOA(driver);
        showCallPutEOA.pause(timeOfPause);
        showCallPutEOA.loadingCallPut();//Грузим в БД коллы и путы понедельника
        //showCallPutEOA.pause(timeOfPause);

        //get_CallPut_Tuesday() {
        ShowCallPutEOB showCallPutEOB = new ShowCallPutEOB(driver);
        showCallPutEOB.pause(timeOfPause);
        showCallPutEOB.loadingCallPut();//Грузим в БД коллы и путы вторника
        //showCallPutEOB.pause(timeOfPause);

        //get_CallPut_Wednesday() {
        ShowCallPutEOC showCallPutEOC = new ShowCallPutEOC(driver);
        showCallPutEOC.pause(timeOfPause);
        showCallPutEOC.loadingCallPut();//Грузим в БД коллы и путы среды
        //showCallPutEOC.pause(timeOfPause);

        //get_CallPut_Thursday() {
        ShowCallPutEOD showCallPutEOD = new ShowCallPutEOD(driver);
        showCallPutEOD.pause(timeOfPause);
        showCallPutEOD.loadingCallPut();//Грузим в БД коллы и путы четверга
        //showCallPutEOD.pause(timeOfPause);

        //get_CallPut_EOM() {
        ShowCallPutEOM showCallPutEOM = new ShowCallPutEOM(driver);
        showCallPutEOM.pause(timeOfPause);
        showCallPutEOM.loadingCallPut(balance);//Грузим в БД коллы и путы месяца
        //showCallPutEOM.pause(timeOfPause);

//==================  Грузим балансы контрактов в БД  ==================================

        OpenInteresSB openInteresSB = new OpenInteresSB(driver);
        openInteresSB.pause(timeOfPause);
        openInteresSB.getMaxPainEOM(balance);//Находим баланс месяца
        openInteresSB.pause(timeOfPause);
        openInteresSB.getMaxPainEOD(balance);//Находим баланс четверга
        //openInteresSB.pause(timeOfPause);

        ShowEOD_MaxPain showEODMaxPain = new ShowEOD_MaxPain(driver, balance);
        showEODMaxPain.pause(timeOfPause);
        showEODMaxPain.getMaxPain(balance);
        //showEODMaxPain.pause(timeOfPause);

        OpenInteresSB openInteresSB_EOC = new OpenInteresSB(driver);
        openInteresSB_EOC.pause(timeOfPause);
        openInteresSB_EOC.getMaxPainEOC(balance);//Находим баланс среды
        //openInteresSB_EOC.pause(timeOfPause);

        ShowEOC_MaxPain showEOCMaxPain = new ShowEOC_MaxPain(driver, balance);
        showEOCMaxPain.pause(timeOfPause);
        showEOCMaxPain.getMaxPain(balance);
        //showEOCMaxPain.pause(timeOfPause);

        OpenInteresSB openInteresSB_EOB = new OpenInteresSB(driver);
        openInteresSB_EOB.pause(timeOfPause);
        openInteresSB_EOB.getMaxPainEOB(balance);//Находим баланс вторника
        //openInteresSB_EOB.pause(timeOfPause);

        ShowEOB_MaxPain showEOBMaxPain = new ShowEOB_MaxPain(driver, balance);
        showEOBMaxPain.pause(timeOfPause);
        showEOBMaxPain.getMaxPain(balance);
        //showEOBMaxPain.pause(timeOfPause);

        OpenInteresSB openInteresSB_EOA = new OpenInteresSB(driver);
        openInteresSB_EOA.pause(timeOfPause);
        openInteresSB_EOA.getMaxPainEOA(balance);//Находим баланс вторника
        //openInteresSB_EOA.pause(timeOfPause);

        ShowEOA_MaxPain showEOAMaxPain = new ShowEOA_MaxPain(driver, balance);
        showEOAMaxPain.pause(timeOfPause);
        showEOAMaxPain.getMaxPain(balance);
        //showEOAMaxPain.pause(timeOfPause);

        OpenInteresSB openInteresSB_EOW = new OpenInteresSB(driver);
        openInteresSB_EOW.pause(timeOfPause);
        openInteresSB_EOW.getMaxPainEOW(balance);//Находим баланс вторника
        //openInteresSB_EOW.pause(timeOfPause);

        ShowEOW_MaxPain showEOWMaxPain = new ShowEOW_MaxPain(driver, balance);
        showEOWMaxPain.pause(timeOfPause);
        showEOWMaxPain.getMaxPain(balance);
        //showEOWMaxPain.pause(timeOfPause);

        OpenInteresSB openInteresSB_Balance = new OpenInteresSB(driver);
        openInteresSB_Balance.pause(timeOfPause);
        openInteresSB_Balance.sendBalancesToDB(balance);//Сохраняем в БД балансы всех контрактов
        openInteresSB_Balance.pause(timeOfPause);
        openInteresSB_Balance.getPricingSheets();
        //openInteresSB_Balance.pause(timeOfPause);

//==================  Грузим опционные доски всех контрактов в БД  ======================

        PricingSheets pricingSheets = new PricingSheets(driver);
        pricingSheets.pause(timeOfPause);
        pricingSheets.loadingBoardEOW();//Грузим оп.доску недельного контракта
        pricingSheets.pause(timeOfPause);
        pricingSheets.loadingBoardEOA();
        //pricingSheets.pause(timeOfPause);

        ShowBoardEOA showBoardEOA = new ShowBoardEOA(driver);
        showBoardEOA.pause(timeOfPause);
        showBoardEOA.loadingBoardEOA();//Грузим в БД оп.доску контракта с экспирацией в понедельник
        //showBoardEOA.pause(timeOfPause);

        PricingSheets pricingSheetsEOB = new PricingSheets(driver);
        pricingSheetsEOB.pause(timeOfPause);
        pricingSheetsEOB.loadingBoardEOB();
        //pricingSheetsEOB.pause(timeOfPause);

        ShowBoardEOB showBoardEOB = new ShowBoardEOB(driver);
        showBoardEOB.pause(timeOfPause);
        showBoardEOB.loadingBoardEOB();//Грузим в БД оп.доску контракта с экспирацией во вторник
        //showBoardEOB.pause(timeOfPause);

        PricingSheets pricingSheetsEOC = new PricingSheets(driver);
        pricingSheetsEOC.pause(timeOfPause);
        pricingSheetsEOC.loadingBoardEOC();
        //pricingSheetsEOC.pause(timeOfPause);

        ShowBoardEOC showBoardEOC = new ShowBoardEOC(driver);
        showBoardEOC.pause(timeOfPause);
        showBoardEOC.loadingBoardEOC();//Грузим в БД оп.доску контракта с экспирацией в среду
        //showBoardEOC.pause(timeOfPause);

        PricingSheets pricingSheetsEOD = new PricingSheets(driver);
        pricingSheetsEOD.pause(timeOfPause);
        pricingSheetsEOD.loadingBoardEOD();
        //pricingSheetsEOD.pause(timeOfPause);

        ShowBoardEOD showBoardEOD = new ShowBoardEOD(driver);
        showBoardEOD.pause(timeOfPause);
        showBoardEOD.loadingBoardEOD();//Грузим в БД оп.доску контракта с экспирацией в четверг
        //showBoardEOD.pause(timeOfPause);

        PricingSheets pricingSheetsEOM = new PricingSheets(driver);
        pricingSheetsEOM.pause(timeOfPause);
        pricingSheetsEOM.loadingBoardEOM();
        //pricingSheetsEOM.pause(timeOfPause);

        ShowBoardEOM showBoardEOM = new ShowBoardEOM(driver);
        showBoardEOM.pause(timeOfPause);
        showBoardEOM.loadingBoardEOM(balance);//Грузим в БД оп.доску месячного контракта
        //showBoardEOM.pause(timeOfPause);

        System.out.println("Финиш");
    }
}
