package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddEmployeePage;
import pages.DashBoardPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;
import utils.ExcelReading;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeTest extends CommonMethods {

    @Test(groups = "smoke")
    public void addEmployee(){
        LoginPage loginPage=new LoginPage();
        loginPage.login(ConfigReader.getPropertyValues("username"), ConfigReader.getPropertyValues("password"));

        DashBoardPage dash= new DashBoardPage();
        click(dash.PIM);
        click(dash.addEmployeeButton);

        AddEmployeePage emp= new AddEmployeePage();
        sendText(emp.firstName, "Bacone");
        sendText(emp.lastName, "Maximone");
        click(emp.saveButton);

    }
    @Test(groups = "regression")
    public void addMultipleEmployees() throws InterruptedException {

        //login to page with Admin credentials
        LoginPage loginPage= new LoginPage();
        loginPage.login(ConfigReader.getPropertyValues("username"),ConfigReader.getPropertyValues("password"));

        // navigating to add employee page
        DashBoardPage dash = new DashBoardPage();
        AddEmployeePage addEmployeePagePage= new AddEmployeePage();
        EmployeeListPage empListPage= new EmployeeListPage();
        List<Map<String, String>> newEmployees = ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH,"EmployeeData");

        SoftAssert softAssert = new SoftAssert();

        Iterator<Map<String,String>> it= newEmployees.iterator();
        while(it.hasNext()){
            click(dash.PIM);
            click(dash.addEmployeeButton);
            Map<String,String> mapNewEmployee = it.next();
            sendText(addEmployeePagePage.firstName, mapNewEmployee.get("FirstName"));
            sendText(addEmployeePagePage.middleName, mapNewEmployee.get("MiddleName"));
            sendText(addEmployeePagePage.lastName, mapNewEmployee.get("LastName"));
            String empIDValue = addEmployeePagePage.employeeId.getAttribute("value");
            sendText(addEmployeePagePage.photograph, mapNewEmployee.get("Photograph"));

            //select checkbox
            if(!addEmployeePagePage.loginCheckBox.isSelected()){
                click(addEmployeePagePage.loginCheckBox);
            }

            //add login credentials for user
            sendText(addEmployeePagePage.createUsername, mapNewEmployee.get("Username"));
            sendText(addEmployeePagePage.userPassword, mapNewEmployee.get("Password"));
            sendText(addEmployeePagePage.re_password, mapNewEmployee.get("Password"));
            click(addEmployeePagePage.saveButton);

            //navigate to the employee list
            click(dash.PIM);
            click(dash.EmployeeListOption);

            // enter employee id
            waitForClickability(empListPage.idEmployee);
            sendText(empListPage.idEmployee, empIDValue);
            click(empListPage.searchButton);

            List<WebElement> rowData= driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));

            for (int i = 0; i < rowData.size(); i++) {
                System.out.println("I am inside the loop");
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);
                Thread.sleep(1000);
                String expectedEmployeeDetails = empIDValue+ " " + mapNewEmployee.get("FirstName")+" "+mapNewEmployee.get("MiddleName")+" "+mapNewEmployee.get("LastName");
                softAssert.assertEquals(rowText, expectedEmployeeDetails);

            }

        }
        softAssert.assertAll();

    }
}
