package com.w2a.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;
import com.w2a.testcases.Testsuitelogin;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.w2a.base.*;
public class BankmanagerLoginTest1 extends Testsuitelogin {
	String runmodes[]=null; 
	static int count=-1;
	static boolean pass=false;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPass=true;
	
	@BeforeTest
	public void checkTestSkip() throws Exception
	{
		
		
		if(!TestUtil.isTestCaseRunnable(loginsuite1xls,this.getClass().getSimpleName()))
		{
			log.debug("Skipping execution of"+this.getClass().getSimpleName()+" because runmode of test case set to NO "); // this information will appear in Log files generated.
			throw new SkipException("Skipping execution of"+this.getClass().getSimpleName()+" because runmode of test case set to NO ");  // this information will appear in TestNg report files generated.
		}
		// load the runmodes of the tests
		runmodes = TestUtil.getDataSetRunmodes(loginsuite1xls,this.getClass().getSimpleName() );
		
		
	}

	
	@Test(dataProvider="getTestData")
	public  void test1(Hashtable<String,String> data) throws Exception{
		
		String currentmethodname=  new Throwable() 
	            .getStackTrace()[0] 
	            .getMethodName();
		String Testcase1 = "BankmanagerLoginTest1";
		System.out.println("Name of current method: "
	            + currentmethodname); 
		
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y"))
		{
		skip= true;
		throw new SkipException("Run mode to test data was set to NO for row #" + count);
		}
		try{
			openBrowser();
			click("login.login_linktxt");
			
			input("login.username",data.get("username"));
			input("login.password",data.get("password"));
			click("login.button");
			closeBrowser();
		
		
		}catch(Throwable t)
		{
		
		//code to report the error in testng
		//ErrorUtil.addVerificationFailure(t);			
		// report the error in excel file
		fail=true;
		//return; // this is optional &is return will make the test case stop!! if you don't want to stop the test case here and go further don't use return here.
		}
		
		
		
	}
	@AfterMethod
	public void reportDatasetResult()  //after method will be executed for each dataset every time test case is executed....
		
	{
		
		if(skip)
		
			TestUtil.reportDataSetResult(loginsuite1xls,this.getClass().getSimpleName(), count+2, "Skipped");
			
		
		else if(fail)
		{
			TestUtil.reportDataSetResult(loginsuite1xls,this.getClass().getSimpleName(), count+2, "Fail");
			isTestPass= false;
		}
		else 
			TestUtil.reportDataSetResult(loginsuite1xls,this.getClass().getSimpleName(), count+2, "Pass");
		skip = false;
		fail= false;
		pass= false;
		
	}
	@DataProvider
	public Object[][] getTestData()
{
	return TestUtil.getdata(loginsuite1xls,this.getClass().getSimpleName());
			
	}	
 
}


	
	
