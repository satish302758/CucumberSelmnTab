package com.Tab.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader
{
	public static List<HashMap<String, String>> ReturnMap(String sheetName)
	{
		FileInputStream fis = null;
		XSSFWorkbook workbook = null;
		String FilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TestDataSheet.xlsx";
		try
		{
			fis = new FileInputStream(FilePath);
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		sheetName = "OnBoarding";
		try
		{
			workbook = new XSSFWorkbook(fis);
		}
		catch (IOException e)
		{
			
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);
		List<HashMap<String, String>> mydata = new ArrayList<HashMap<String, String>>();
		Row HeaderRow = sheet.getRow(0);
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++)
		{
			Row currentRow = sheet.getRow(i);
			HashMap<String, String> currentHash = new HashMap<String, String>();
			for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++)
			{
				Cell currentCell = currentRow.getCell(j);
				switch (currentCell.getCellType())
				{
				case STRING:
					currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
					break;
				case NUMERIC:
					currentHash.put(HeaderRow.getCell(j).getStringCellValue(),
							String.valueOf(currentCell.getNumericCellValue()));
					break;
				default:
					try
					{
						throw new Exception("Cell Type Not Identified");
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			mydata.add(currentHash);
		}
		return mydata;
	}
	public static ConcurrentHashMap<String, List<String>> fillData(String sheetName) throws IOException
	{
		FileInputStream fis = null;
		XSSFWorkbook workbook = null;
		String FilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TestDataSheet.xlsx";
		try
		{
			fis = new FileInputStream(FilePath);
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		sheetName = "OnBoarding";
		try
		{
			workbook = new XSSFWorkbook(fis);
		}
		catch (IOException e)
		{
			
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);
		// ColumnName row
		ConcurrentHashMap<String, List<String>> hm = new ConcurrentHashMap<String, List<String>>();
		List<String> val ;
		
		XSSFRow headerRow = sheet.getRow(0);
		for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++)
		{
			String columnName  = headerRow.getCell(i).getStringCellValue();;
			 val = new ArrayList<String>();
			for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++)
			{
				
				
				Cell cell = sheet.getRow(j).getCell(i);
				if (cell.getCellType() == CellType.STRING)
				{
					val.add(cell.getStringCellValue());
				}
				else if (cell.getCellType() == CellType.NUMERIC)
				{
					 
					val.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
				}
			}
			hm.put(columnName, val);
			 
			
		}
	 
		try
		{
			fis.close();
		}
		catch (IOException e)
		{
			
			e.printStackTrace();
		}
		return hm;
	}
	public static void get() throws IOException
	{
		ConcurrentHashMap<String, List<String>> m= fillData(null);
	m.get("user").get(0);
		
	}
}
