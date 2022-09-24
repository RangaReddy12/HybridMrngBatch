package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil{
	String inputpath ="D:\\MrngBatch\\FrameWork_Hybrid\\TestInput\\DataEngine.xlsx";
	String outputpath ="D:\\MrngBatch\\FrameWork_Hybrid\\TestOutPut\\HybridResults.xlsx";
	String TCSheet ="TestCases";
	String TSSheet ="TestSteps";
	@Test
	public void startTest()throws Throwable
	{
		boolean res=false;
		String tcres="";
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count rows in both sheets
		int TCCount =xl.rowCount(TCSheet);
		int TSCount =xl.rowCount(TSSheet);
		Reporter.log(TCCount+"      "+TSCount,true);
		//iterate all rows TCSheet
		for(int i=1;i<=TCCount;i++)
		{
			//read executionmode cell
			String ExecutionMode =xl.getCellData(TCSheet, i, 2);
			if(ExecutionMode.equalsIgnoreCase("Y"))
			{
			//read tcid cell
				String tcid =xl.getCellData(TCSheet, i, 0);
				//iterate all rows in TSSHeet
				for(int j=1;j<=TSCount;j++)
				{
					//read tcid cell
					String tsid =xl.getCellData(TSSheet, j, 0);
					if(tcid.equalsIgnoreCase(tsid))
					{
						//read keyword cell
						String KeyWord =xl.getCellData(TSSheet, j, 3);
						if(KeyWord.equalsIgnoreCase("AdminLogin"))
						{
							String para1 =xl.getCellData(TSSheet, j, 5);
							String para2 =xl.getCellData(TSSheet, j, 6);
							res =FunctionLibrary.verifyLogin(para1, para2);
						}
						else if(KeyWord.equalsIgnoreCase("NewBranch"))
						{
							String para1 =xl.getCellData(TSSheet, j, 5);
							String para2 =xl.getCellData(TSSheet, j, 6);
							String para3 =xl.getCellData(TSSheet, j, 7);
							String para4 =xl.getCellData(TSSheet, j, 8);
							String para5 =xl.getCellData(TSSheet, j, 9);
							String para6 =xl.getCellData(TSSheet, j, 10);
							String para7 =xl.getCellData(TSSheet, j, 11);
							String para8 =xl.getCellData(TSSheet, j, 12);
							String para9 =xl.getCellData(TSSheet, j, 13);
							FunctionLibrary.clickBranches();
							res =FunctionLibrary.verifyNewBranch(para1, para2, para3, para4, para5, para6, para7, para8, para9);
						}
						else if(KeyWord.equalsIgnoreCase("UpdateBranch"))
						{
							String para1 =xl.getCellData(TSSheet, j, 5);
							String para2 =xl.getCellData(TSSheet, j, 6);
							String para3 =xl.getCellData(TSSheet, j, 9);
							String para4 =xl.getCellData(TSSheet, j, 10);
							FunctionLibrary.clickBranches();
							res = FunctionLibrary.verifyBranchUpdate(para1, para2, para3, para4);
						}
						else if(KeyWord.equalsIgnoreCase("AdminLogout"))
						{
							res =FunctionLibrary.verifyLogout();
						}
						String tsres="";
						if(res)
						{
							//if res is true write as pass
							tsres="Pass";
							xl.setCellData(TSSheet, j, 4,tsres, outputpath);
						}
						else
						{
							//if res is false write as fail
							tsres="Fail";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
						}
						tcres=tsres;
					}
					
				}
				
				//write tcres into TCSheet
				xl.setCellData(TCSheet, i, 3, tcres, outputpath);
			}
			else
			{
				//write as blocked into status cell in TCSheet for flag N
				xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
			}
		}
		
	}

}













