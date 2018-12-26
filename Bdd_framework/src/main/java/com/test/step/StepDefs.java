package com.test.step;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import com.test.dataprovider.ConfigFileReader;
import com.test.page.DataDrivenWithoutExample;
import com.test.page.DropDownRegister;
import com.test.page.DropDownTest;
import com.test.page.FBLoginPage;
import com.test.page.MicrosoftLoginPage;
import com.test.page.MicrosoftPwdPage;
import com.test.page.MultipleWindow;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs {
	private FBLoginPage fblogin;
	private MicrosoftLoginPage msLogin;
	private MicrosoftPwdPage msPwd;
	private MultipleWindow multi;
	protected WebDriver driver;
	ConfigFileReader configFileReader;
	private DropDownRegister dropDownRegister;
	private DataDrivenWithoutExample dataDrivenWithoutExample;
	private DropDownTest dropdoenTest;

	String childwindow;
	String Mainwindow;

	@Before
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "D://drivers//chromedriver.exe");
		driver = new ChromeDriver();
	}

	@After(order = 1)
	public void afterTesr(Scenario scenario) throws IOException {
		/* driver.close(); */
		if (scenario.isFailed()) {
			String screenshot = scenario.getName().replaceAll(" ", "_");

			try {
				File SourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				File destinationPath = new File(
						System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshot + ".png");

				Files.copy(SourcePath, destinationPath);
				Reporter.addScreenCaptureFromPath(destinationPath.toString());

			} catch (WebDriverException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@After(order = 0)
	public void afterTest() {
		driver.close();
	}

	@Given("^User navigates to Fb login page$")
	public void user_navigates_to_fb_login_page() throws Throwable {

		fblogin = new FBLoginPage(driver);
	/*	driver.manage().window().maximize();*/
		fblogin.navigateTo("https://outlook.office365.com");
	}

	@When("^User should be login to FB$")
	public void user_should_be_login_to_fb() throws Throwable {
		/* fblogin.login(); */
		driver.findElement(By.id("i0116")).sendKeys("venkata.pasupuleti@qualitestgroup.com");
	}

	@Then("^User should be on home $")
	public void user_should_be_on_home() throws Throwable {
		System.out.println("Sample");

	}

	@Given("^User navigates to microsoft login page$")
	public void user_navigates_to_microsoft_login_page() throws Throwable {
		msLogin = new MicrosoftLoginPage(driver);
		msLogin.navigateToMS();

	}

	@When("^User should be login to microsoft account$")
	public void user_should_be_login_to_microsoft_account() throws Throwable {
		msLogin.loginIDMtd().btnNextMtd();
	}

	@Then("^User should be on MShome$")
	public void user_should_be_on_MShome() throws Throwable {
		msPwd = new MicrosoftPwdPage(driver);
		msPwd.entertxtPwd().clickSigninbtnMtd();
		WebElement outlook = driver.findElement(By.id("_ariaId_55"));
		String s = outlook.getText();
		Assert.assertEquals("Folders", s);
	}

	@Given("^User navigates to toolsQA  page$")
	public void user_navigates_to_toolsqa_page() throws Throwable {
		multi = new MultipleWindow(driver);
		multi.navigateTo("http://toolsqa.wpengine.com/automation-practice-switch-windows/");

	}

	@When("^User should be on main window and click in button$")
	public void user_should_be_on_main_window_and_click_in_button() throws Throwable {
		Mainwindow = driver.getWindowHandle();
		multi.clickbtnNewWindow();
		driver.manage().window().maximize();
		Set<String> windowlist = driver.getWindowHandles();
		for (String handle : windowlist) {
			if (handle != Mainwindow) {
				driver.switchTo().window(handle);
				childwindow = handle;
				driver.manage().window().maximize();
			}

		}
	}

	@Then("^User should on child window$")
	public void user_should_on_child_window() throws Throwable {
		multi.clickolllink();
		Set<String> windowlist2 = driver.getWindowHandles();
		for (String handle2 : windowlist2) {
			if (handle2 != Mainwindow) {
				if (handle2 != childwindow) {

				} else {
					driver.switchTo().window(handle2);
					multi.enterName();

				}

			}
		}
	}

    @Given("^User navigates to guru99 register page$")
    public void user_navigates_to_guru99_register_page() throws Throwable {
    	dropDownRegister=new DropDownRegister(driver);
    
    	dropDownRegister.naviagteToReister();
    	
    }

    @When("^User should be on register page$")
    public void user_should_be_on_register_page() throws Throwable {
    	
       System.out.println("User on home ");
        
    }

    @Then("^User should be on register_success page$")
    public void user_should_be_on_registersuccess_page() throws Throwable {
    	
   String success=driver.findElement(By.linkText(" sign-in ")).getText();
   Assert.assertEquals(" sign-in ", success);
    }

    @And("^User selects the country in dropdown$")
    public void user_selects_the_country_in_dropdown() throws Throwable {
    	dropDownRegister.selectValueinnDropDown();
    }

    @And("^user ckick on submit$")
    public void user_ckick_on_submit() throws Throwable {
        dropDownRegister.clickSubmit();
    }
    @Given("^User is on Home Page$")
    public void user_is_on_home_page() throws Throwable {
    	dataDrivenWithoutExample=new DataDrivenWithoutExample(driver);
    	dataDrivenWithoutExample.navigatoDemo();
        
    }

    @When("^User Navigate to LogIn Page$")
    public void user_navigate_to_login_page() throws Throwable {
       dataDrivenWithoutExample.navigatoLoginPage();
    }

    @When("^User LogOut from the Application$")
    public void user_logout_from_the_application() throws Throwable {
        System.out.println("Login");
    }

    @Then("^Message displayed Login Successfully$")
    public void message_displayed_login_successfully() throws Throwable {
     System.out.println("Login");
    }

    @Then("^Message displayed LogOut Successfully$")
    public void message_displayed_logout_successfully() throws Throwable {
        System.out.println("LogOut");
    }

   
    
   /* @And("^User enters \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_enters_something_and_something(String UserName, String Password) throws Throwable {

        driver.findElement(By.cssSelector("#log")).sendKeys(UserName);
    	driver.findElement(By.cssSelector("#pwd")).sendKeys(Password);
    }*/
    @And("^User enters Credentials to login$")
    public void user_enters_credentials_to_login(DataTable usercredentials) throws Throwable {
       List<List<String>> data=usercredentials.raw();
       driver.findElement(By.cssSelector("#log")).sendKeys(data.get(0).get(0));
   	driver.findElement(By.cssSelector("#pwd")).sendKeys(data.get(0).get(1));
   	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Given("^User navigates to toolsQA  dropdown page$")
    public void user_navigates_to_toolsqa_dropdown_page() throws Throwable {
    	dropdoenTest=new DropDownTest(driver);
    	dropdoenTest.navigatetoUrl();
      
    }

    @When("^User should select the down$")
    public void user_should_select_the_down() throws Throwable {
    	
    	dropdoenTest.dropdownmtd();
    	
        
    }

    @Then("^User closes the browser$")
    public void user_closes_the_browser() throws Throwable {
        driver.close();
    }
}
