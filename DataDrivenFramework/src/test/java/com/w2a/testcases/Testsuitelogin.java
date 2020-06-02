package com.w2a.testcases;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;
public class Testsuitelogin extends TestBase {
	
	
	@BeforeSuite
	public void checkSuiteSkip() throws Exception
	{
		setUp() ;
		log.debug("Checking Runmode of suitexls");
	    if (!TestUtil.isSuiteRunnable(suitexls,"Login_Tester"))
		{
	    	
			//System.out.println(Smoketestsuitexls.getRowCount("testcase"));
	    	log.debug("Skipping the execution of priceGroupsSuitexls as the runmode of the suite was set to NO");
			throw new SkipException(" RunMode of priceGroupsSuitexls  is set to No, therefore skipping all test cases in priceGroupsSuitexls");
			
		}
		
	}
	
	@AfterSuite
		
		public void tearDown() {

			// driver.close();
			// driver.quit();
			 log.debug("test suite execution completed !!!");
			}

}
