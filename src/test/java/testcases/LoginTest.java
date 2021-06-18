package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginTest extends CommonMethods {

    @Test(groups = "sanity")
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

    @Test(dataProvider = "invalidData", groups = "smoke")
    public void invalidLoginErrorMessageValidation(String username, String password, String errorMessage){
        LoginPage loginPage =new LoginPage();
        sendText(loginPage.usernamebox, username);
        sendText(loginPage.passwordbox, password);
        click(loginPage.loginButton);

        String actualError = loginPage.errorMessage.getText();

        Assert.assertEquals(actualError,errorMessage, "Error message is not matched");


    }

    @DataProvider
    public Object[][] invalidData(){
        Object[][] data={
                {"James","123","Invalid credentials"},
                {"Admin1", "Syntax123!","Invalid credentials"},
                {"James","","Password cannot be empty"},
                {"","Syntax123!","Username cannot be empty"}
        };
        return data;
    }

}
