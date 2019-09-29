package human_resource_web;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrangeHRMFunctionalities extends Utils
{
    LoadProps loadProps = new LoadProps();

    @BeforeMethod
    public void openBrowser()
    {
        System.setProperty("webdriver.chrome.driver", "src\\main\\Resources\\BrowserDrivers\\chromedriver.exe");
        //open the browser
        driver = new ChromeDriver();
        //maximise the window
        driver.manage().window().maximize();
        //set the implicit wait time on driver level
        implicitWaitTime(10);
        //open the website
        getUrl("Url");
    }

    @AfterMethod
    public void quitPage()
    {
        driver.quit();
    }
    @Test (priority = 1)
    public void userShouldAbleToLogin()
    {
        //enter login details
        enterText(By.id("txtUsername"), loadProps.getProperty("UserName"));
        //enter password
        enterText(By.id("txtPassword"), loadProps.getProperty("Password"));
        //click on login
        clickOnElements(By.id("btnLogin"));
        //compare the result to check test case pass or fail
        assertMethod(By.id("welcome"), "Welcome Admin");
    }
    @Test (priority = 2)
    public void userShouldAbleToAddNewEmployee()
    {
        //enter login details
        enterText(By.id("txtUsername"), loadProps.getProperty("UserName"));
        //enter password
        enterText(By.id("txtPassword"), loadProps.getProperty("Password"));
        //click on login
        clickOnElements(By.id("btnLogin"));
        //hover on Admin
        hoverCursor(By.id("menu_admin_viewAdminModule"));
        //hover on user management
        hoverCursor(By.id("menu_admin_UserManagement"));
        //click on users
        clickOnElements(By.id("menu_admin_viewSystemUsers"));
        //click on Add
        clickOnElements(By.id("btnAdd"));
        //select User Role and select value from dropdown
        //clickOnElements(By.xpath("//select[@id='systemUser_userType']"));
        selectByValue(By.xpath("//select[@id='systemUser_userType']"), "2");
        //enter new employee's name
        enterText(By.xpath("//input[@id='systemUser_employeeName_empName']"), loadProps.getProperty("NewEmployee"));
        //enter UserName
        enterText(By.id("systemUser_userName"), loadProps.getProperty("NewEmployeeUserName") + (randomDate()));
        //select status from dropdown
        selectByValue(By.id("systemUser_status"), "1");
        //enter Password
        enterText(By.id("systemUser_password"), loadProps.getProperty("NewEmployeePassword"));
        //enter Confirm Password
        enterText(By.id("systemUser_confirmPassword"), loadProps.getProperty("NewEmployeeConfirmPassword"));
        //click Save
        clickOnElements(By.xpath("//input[@id='btnSave']"));
       //check the result. here, employee name will be checked against actual result
        assertMethod(By.xpath("//td[contains(text(),'Jasmine Morgan')]"), loadProps.getProperty("NewEmployee"));
    }
    @Test (priority = 3)
    public void userShouldAbleToSearchAndVerifyNewAddedEmployeeInDirectory()
    {
        //enter login details
        enterText(By.id("txtUsername"), loadProps.getProperty("UserName"));
        //enter password
        enterText(By.id("txtPassword"), loadProps.getProperty("Password"));
        //click on login
        clickOnElements(By.id("btnLogin"));
        //click on Directory
        clickOnElements(By.xpath("//b[contains(text(),'Directory')]"));
        //enter new employee's name
        enterText(By.id("searchDirectory_emp_name_empName"),loadProps.getProperty("NewEmployee"));
        //click on Search
        clickOnElements(By.id("searchBtn"));
        //check the result; will verify employee's name with actual result
        assertMethod(By.xpath("//b[contains(text(),'Jasmine Morgan')]"),loadProps.getProperty("NewEmployee"));
    }
}