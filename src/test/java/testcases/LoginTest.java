package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginTest extends CommonMethods {

    @Test
    public void adminLogin(){

        //Login to hrms application
        LoginPage loginPage= new LoginPage();
        sendText(loginPage.usernamebox, ConfigReader.getPropertyValues("username"));
        sendText(loginPage.passwordbox, ConfigReader.getPropertyValues("password"));
        click(loginPage.loginButton);

        //Validation and Assertion

        DashBoardPage dashBoard=new DashBoardPage();
        Assert.assertTrue(dashBoard.welcomeMessage.isDisplayed(), "welcome message is not displayed");




    }

}
