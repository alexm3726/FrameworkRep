package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class AddEmployeePage extends CommonMethods {

    @FindBy(id = "firstName")
    public WebElement firstName;
    @FindBy(id = "middleName")
    public WebElement middleName;
    @FindBy(id = "lastName")
    public WebElement lastName;
    @FindBy(id = "employeeId")
    public WebElement employeeId;
    @FindBy(id = "photofile")
    public WebElement photograph;
    @FindBy(id = "btnSave")
    public WebElement saveButton;
    @FindBy(id = "chkLogin")
    public WebElement loginCheckBox;
    @FindBy(id = "user_name")
    public WebElement createUsername;
    @FindBy(id = "user_password")
    public WebElement userPassword;
    @FindBy(id = "re_password")
    public WebElement re_password;



   public AddEmployeePage(){
       PageFactory.initElements(driver,this);
   }
}
