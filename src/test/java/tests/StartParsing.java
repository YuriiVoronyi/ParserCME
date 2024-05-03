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

    @Test
    public void make_Start() {
        //Заполняем форму авторизации и по кнопке сабмит переходим на
        //двойную аутентификацию - Duo
        new AutorisationPage(driver).enterAutorisation().pressLoginBtn();
        pause(40000);

        //После прохождения Duo и Капчи попадаем на главную страницу
        HomePage homePage = new HomePage(driver);
        homePage.pause(15000);//пауза 15 секунд
        homePage.choiceHomeMenu1();//Выбираем меню
        homePage.choiceHomeMenu2();//Выбираем в меню -> подменю
        homePage.getSP500();//После прохода через меню и подменю выбираем инструмент SP500

        //get_QuikStrike() {
        QuikStrike quikStrike = new QuikStrike(driver);
        quikStrike.pause(1000);//пауза 1 секунда
        quikStrike.getOpenInteres();//В главном меню переходим на вкладку Открытый Интерес

        //get_OpenInteres() {
        //На вкладке Открытий Интерес в вертикальном меню левого сайдбара выбираем Самурая
        OpenInteres openInteres = new OpenInteres(driver);
        openInteres.pause(1000);//пауза 1 секунда

        openInteres.getSummary();

        //get_Summary() {
        //На вкладке Summary вертикального меню левого сайдбара выбираем просмотр Колл-Пут НЕДЕЛИ
        Summary summary = new Summary(driver);
        summary.pause(5000);
        summary.getCallPutWeek();

        //get_CallPut_Week() {
        //В выпадающем меню выбираем показать все страйки по контракту
        ShowCallPutEOW showCallPutEOW = new ShowCallPutEOW(driver);
        showCallPutEOW.pause(5000);

        //showCallPutEOW.getOpenInteresSB();

//==================  Грузим коллы и путы контрактов в БД  ==================================

        showCallPutEOW.selectAllRows();
        showCallPutEOW.pause(10000);
        showCallPutEOW.makeScreen();//Делаю скриншот для проверки даты по загружаемым данным
        showCallPutEOW.pause(5000);
        showCallPutEOW.loadingCallPut();//Грузим в БД коллы и путы недели

        //get_CallPut_Monday() {
        ShowCallPutEOA showCallPutEOA = new ShowCallPutEOA(driver);
        showCallPutEOA.pause(2000);
        showCallPutEOA.loadingCallPut();//Грузим в БД коллы и путы понедельника
        showCallPutEOA.pause(5000);

        //get_CallPut_Tuesday() {
        ShowCallPutEOB showCallPutEOB = new ShowCallPutEOB(driver);
        showCallPutEOB.pause(2000);
        showCallPutEOB.loadingCallPut();//Грузим в БД коллы и путы вторника
        showCallPutEOB.pause(5000);

        //get_CallPut_Wednesday() {
        ShowCallPutEOC showCallPutEOC = new ShowCallPutEOC(driver);
        showCallPutEOC.pause(2000);
        showCallPutEOC.loadingCallPut();//Грузим в БД коллы и путы среды
        showCallPutEOC.pause(5000);

        //get_CallPut_Thursday() {
        ShowCallPutEOD showCallPutEOD = new ShowCallPutEOD(driver);
        showCallPutEOD.pause(2000);
        showCallPutEOD.loadingCallPut();//Грузим в БД коллы и путы четверга
        showCallPutEOD.pause(5000);

        //get_CallPut_EOM() {
        ShowCallPutEOM showCallPutEOM = new ShowCallPutEOM(driver);
        showCallPutEOM.pause(2000);
        showCallPutEOM.loadingCallPut(balance);//Грузим в БД коллы и путы месяца
        showCallPutEOM.pause(5000);

//==================  Грузим балансы контрактов в БД  ==================================

        OpenInteresSB openInteresSB = new OpenInteresSB(driver);
        openInteresSB.pause(5000);

        openInteresSB.getMaxPainEOM(balance);//Находим баланс месяца
        openInteresSB.pause(4000);

        openInteresSB.getMaxPainEOD(balance);//Находим баланс четверга
        ShowEOD_MaxPain showEODMaxPain = new ShowEOD_MaxPain(driver, balance);
        showEODMaxPain.pause(5000);
        showEODMaxPain.getMaxPain(balance);

        OpenInteresSB openInteresSB_EOC = new OpenInteresSB(driver);
        openInteresSB_EOC.pause(4000);

        openInteresSB_EOC.getMaxPainEOC(balance);//Находим баланс среды
        ShowEOC_MaxPain showEOCMaxPain = new ShowEOC_MaxPain(driver, balance);
        showEOCMaxPain.pause(5000);
        showEOCMaxPain.getMaxPain(balance);

        OpenInteresSB openInteresSB_EOB = new OpenInteresSB(driver);
        openInteresSB_EOB.pause(4000);

        openInteresSB_EOB.getMaxPainEOB(balance);//Находим баланс вторника
        ShowEOB_MaxPain showEOBMaxPain = new ShowEOB_MaxPain(driver, balance);
        showEOBMaxPain.pause(5000);
        showEOBMaxPain.getMaxPain(balance);

        OpenInteresSB openInteresSB_EOA = new OpenInteresSB(driver);
        openInteresSB_EOA.pause(4000);

        openInteresSB_EOA.getMaxPainEOA(balance);//Находим баланс вторника
        ShowEOA_MaxPain showEOAMaxPain = new ShowEOA_MaxPain(driver, balance);
        showEOAMaxPain.pause(5000);
        showEOAMaxPain.getMaxPain(balance);

        OpenInteresSB openInteresSB_EOW = new OpenInteresSB(driver);
        openInteresSB_EOW.pause(4000);

        openInteresSB_EOW.getMaxPainEOW(balance);//Находим баланс вторника
        ShowEOW_MaxPain showEOWMaxPain = new ShowEOW_MaxPain(driver, balance);
        showEOWMaxPain.pause(5000);
        showEOWMaxPain.getMaxPain(balance);

        OpenInteresSB openInteresSB_Balance = new OpenInteresSB(driver);
        openInteresSB_Balance.pause(2000);
        openInteresSB_Balance.sendBalancesToDB(balance);//Сохраняем в БД балансы всех контрактов
        openInteresSB_Balance.pause(4000);
        openInteresSB_Balance.getPricingSheets();

//==================  Грузим опционные доски всех контрактов в БД  ======================

        PricingSheets pricingSheets = new PricingSheets(driver);
        pricingSheets.pause(5000);
        pricingSheets.loadingBoardEOW();//Грузим оп.доску недельного контракта
        pricingSheets.pause(5000);
        pricingSheets.loadingBoardEOA();

        ShowBoardEOA showBoardEOA = new ShowBoardEOA(driver);
        showBoardEOA.pause(5000);
        showBoardEOA.loadingBoardEOA();//Грузим в БД оп.доску контракта с экспирацией в понедельник

        PricingSheets pricingSheetsEOB = new PricingSheets(driver);
        pricingSheetsEOB.pause(5000);
        pricingSheetsEOB.loadingBoardEOB();

        ShowBoardEOB showBoardEOB = new ShowBoardEOB(driver);
        showBoardEOB.pause(5000);
        showBoardEOB.loadingBoardEOB();//Грузим в БД оп.доску контракта с экспирацией во вторник

        PricingSheets pricingSheetsEOC = new PricingSheets(driver);
        pricingSheetsEOC.pause(5000);
        pricingSheetsEOC.loadingBoardEOC();

        ShowBoardEOC showBoardEOC = new ShowBoardEOC(driver);
        showBoardEOC.pause(5000);
        showBoardEOC.loadingBoardEOC();//Грузим в БД оп.доску контракта с экспирацией в среду

        PricingSheets pricingSheetsEOD = new PricingSheets(driver);
        pricingSheetsEOD.pause(5000);
        pricingSheetsEOD.loadingBoardEOD();

        ShowBoardEOD showBoardEOD = new ShowBoardEOD(driver);
        showBoardEOD.pause(5000);
        showBoardEOD.loadingBoardEOD();//Грузим в БД оп.доску контракта с экспирацией в четверг

        PricingSheets pricingSheetsEOM = new PricingSheets(driver);
        pricingSheetsEOM.pause(5000);
        pricingSheetsEOM.loadingBoardEOM();

        ShowBoardEOM showBoardEOM = new ShowBoardEOM(driver);
        showBoardEOM.pause(5000);
        showBoardEOM.loadingBoardEOM(balance);//Грузим в БД оп.доску месячного контракта

        System.out.println("Финиш");
    }
}
