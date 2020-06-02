package com.w2a.utilities;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;

public class TestUtil  {

	
//	public static ExcelReader excel = new ExcelReader(
			//System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\ " + currentTestSuiteXLS + ".xlsx");
	
	public static boolean isSuiteRunnable(ExcelReader excel,String suiteName)  //first test suite in Suite.xls is TestSuiteA
	
	{
	//finds if the test suite is runnable

	boolean isExecutable=false;
	for (int x=2; x<=excel.getRowCount("TestSuiteSheet"); x++)  // test sheet name is "TestSuiteSheet" in the file suite.xls
	  {
		System.out.println((excel.getCellData ("TestSuiteSheet", "TSID", x))+"------"+(excel.getCellData ("TestSuiteSheet", "Runmode", x)));
		
		String suite = excel.getCellData ("TestSuiteSheet", "TSID", x);
		String runmode = excel.getCellData ("TestSuiteSheet", "Runmode", x);

		if(suite.equals(suiteName))
		 {
		if(runmode.equalsIgnoreCase("Y"))
			{
			isExecutable=true;
			}
		else if(runmode.equalsIgnoreCase("N"))
			{
			isExecutable = false;
			}

	     }
	  }
	excel=null;
	return isExecutable ;
	}
	
	public static boolean isTestCaseRunnable(ExcelReader excel,String testCaseName){
		  
		boolean isExecutable=false;
		//  in TestSuiteA.xls and TestSuiteB.xls should have sheet name consistent as testcase
		for (int x=2; x<=excel.getRowCount("testcase"); x++)
		{
			System.out.println((excel.getCellData ("testcase","TCID",x))+"------"+(excel.getCellData ("testcase","Runmode",x)));
					
			if(excel.getCellData("testcase","TCID",x).equalsIgnoreCase(testCaseName))
			{
				if (excel.getCellData("testcase","Runmode",x).equalsIgnoreCase("Y"))
				{
					isExecutable=true;
				}
				else if(excel.getCellData("testcase","Runmode",x).equalsIgnoreCase("N"))
				{
					isExecutable=false;	
				}	
			
			}
		}
		
		return isExecutable;
		}
	@DataProvider
	public static Object[][] getdata(ExcelReader excel,String testCaseName)  // from the excel sheet with test sheet name = TestcaseName
	{
	//Pre- condition's:
	//TestCaseA should have corresponding sheet with same name "TestCaseA" should be available in that TestSuiteA.xls
	// if the test case with name TestCaseA doesn't have corresponding sheet name this means test case has no data and this program will execute only once.
	// otherwise it will extract all the data from the corresponding sheet (matching testcase name: TestCaseA sheet in TestSuiteA.xls file


	//   a test case will execute only n number of times, where n is the array number (number of rows) in the Object

		//if(! excel.isSheetExist(testCaseName))  // if sheet with same name "TestCaseName" doesn't exists then this function will return [! false] which is true and therefore go inside the if loop )
				//	{
					//	excel=null;  // for memory clean up
					//	return new Object[1][0];   // and it returns and object array with one row and zero column will return  i.e hypothetical array.
						                            // we are making sure that atleast once the test will execute
					
				//	}


		// if sheet is existing with name testCaseName
	int rows = excel.getRowCount(testCaseName);  				 // from the testcaseName= TestCaseA sheet 
	int columns = excel.getColumnCount(testCaseName);


	 Object[][] data =new Object[rows -1][1];
	// extract data from cell
	 Hashtable<String,String> table = null;
	 
	 for (int rowNum = 2;rowNum<= rows;rowNum++)
		{
		 table = new Hashtable<String,String>();
			for (int ColNum =0;ColNum< columns;ColNum++)
			{
				table.put(excel.getCellData(testCaseName, ColNum, 1), excel.getCellData(testCaseName, ColNum, rowNum));
				data[rowNum - 2][0] = table;
			
				//System.out.print(excel.getCellData(TestCaseA, ColNum, rowNum)+ "----");
			//	data[rowNum-2][ColNum]= excel.getCellData(testCaseName, ColNum, rowNum); // columns-3 because last 3 columns will record runmode, results and error due to failure 
		}
			//System.out.println();
	}
		
	 return data;


	}



	// up to this to.... return the test data from a test in 2 dimensional (nxn) array

	//--------------------------------------------------------
	//case 4:
	// Make a function to Check the runmode for datasets

	public static String[] getDataSetRunmodes (ExcelReader excel,String sheetName)
		{
		String[]Runmode= null;
			if(!excel.isSheetExist(sheetName))	
			{
				excel=null;
				sheetName=null;
				Runmode = new String [2];
				Runmode[0]= "Y";
				Runmode[1]= "N";
				
				excel=null;
				sheetName=null;
				return Runmode;
			}
			Runmode= new String [excel.getRowCount(sheetName)-1] ;
		for(int i = 2; i<=Runmode.length+1;i++)
			{
			Runmode[i-2]= excel.getCellData(sheetName, "Runmode", i);
			
			}
			excel=null;
			sheetName=null;
			return Runmode;
		}





	//up to this to.... check the runmode for datasets
	//--------------------------------------------------------
	//case 5:
	//Make a function to Update the Results column in testCaseName sheet - reportTestResult


	//Update Results for a particular dataset 
	public static void reportTestResult(ExcelReader excel, String testCaseName, int rowNum, String Results)
					{
					excel.setCellData("testcase", "Results", rowNum, Results);
					}	




	//up to this to.... to update the "Results" column in testcasesheet
	//----------------------------------------------------------------------------

	// case 6:
	//Make a function to Update the result column in data sheet - reportDataSetResult


	//Update Results for a particular dataset 
	public static void reportDataSetResult(ExcelReader excel, String testCaseName, int rowNum, String Results)
					{	
					excel.setCellData(testCaseName,"Results",rowNum,Results);
					}	




	//up to this to.... to update the "Results" column in testcase sheet
	//----------------------------------------------------------------------------


	// case 7:

	// return the row number for a test

	public static int getRowNum(ExcelReader excel,String id)
	  {
		for (int x=2; x<=excel.getRowCount("testcase"); x++)
			{
			String tcid = excel.getCellData("testcase","TCID",x);
			
			if(tcid.equalsIgnoreCase(id))
				{
				excel= null;
				return x;
				}
			}

		return -1;
	  }

	
	
	
}
