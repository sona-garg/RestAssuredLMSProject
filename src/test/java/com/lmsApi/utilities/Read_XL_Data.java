package com.lmsApi.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Read_XL_Data {
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	FileInputStream inputstream;
	 
	public Read_XL_Data(String excelPath,String Sheetname) {
		 try {
			 inputstream = new FileInputStream(excelPath);
			 workbook = new XSSFWorkbook(inputstream);
			 sheet = workbook.getSheet(Sheetname);
			 
			  }catch(IOException e) {
			 e.printStackTrace();
			 
		 }//end of catch
		 
	 }//end of Read_XLData func
	  
	 public String [][] getInputData() throws IOException{
		 int rowcount = sheet.getPhysicalNumberOfRows();
		 int cols= sheet.getRow(0).getLastCellNum();
		 String [][] Value = new String[rowcount-1][cols-1];
		 try {
			 for(int i=1;i<rowcount;i++) {
				 for(int j=1;j<cols;j++) {
					 Value[i-1][j-1]= getCellData(i,j);
					  }
				 System.out.println();
			 }
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return Value;
			 
	 }//end of getInputData func
	 public static String getCellData(int i,int j) throws IOException{
		 String data=null;
		 try {
			 DataFormatter formatter = new DataFormatter();
			 Row row = sheet.getRow(i);
			 Cell cell= row.getCell(j);
			 
			 data = formatter.formatCellValue(cell);
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return data;
	 } 
	

}
