package com.Tab.Utilities;

import java.util.Map;
import java.util.TreeMap;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Fillio
{
	
	public static void UpdateTestResultsToExcel(String testDataFilePath,String sheetName,String tcStatus,String testCaseId)
	{
		Connection conn=null;
		Fillo fillo =new Fillo();
		try{
			conn=fillo.getConnection("C:\\Users\\abhay\\git\\Tabparallel\\src\\test\\resources\\TestData\\TestDataSheet.xlsx");
			String query=String.format("UPDATE %s SET TestCaseStatus='%s' where TestCaseID='%s'", sheetName,tcStatus,testCaseId);
			conn.executeUpdate(query);
		} catch(FilloException e){
			e.printStackTrace();
		}		
	}
	public static Map<String,String> getTestDataInMap(String testDataFile,String sheetName,String fieldName) throws Exception
	{
	 
	//	System.setProperty("ROW", "0");//Table start row
		 
		testDataFile="C:\\Users\\abhay\\git\\Tabparallel\\src\\test\\resources\\TestData\\TestDataSheet.xlsx";
		sheetName="OnBoarding";
		fieldName="SSN";
		Map<String,String> TestDataInMap=new TreeMap<String,String>();		
		String query=null;
		query=String.format("SELECT SSN FROM %s",sheetName,fieldName);
		Fillo fillo=new Fillo();
		Connection conn=null;
		Recordset recordset=null;
		try
		{
			conn=fillo.getConnection(testDataFile);
			recordset=conn.executeQuery(query);
			while(recordset.next())
			{
				for(String field:recordset.getFieldNames())
				{
					TestDataInMap.put(field, recordset.getField(field));
				}
			}
		}
		catch(FilloException e)
		{
			e.printStackTrace();
			throw new Exception("Test data cannot find . . .");			
		}
		conn.close();
		return TestDataInMap;		
	}
	

}
