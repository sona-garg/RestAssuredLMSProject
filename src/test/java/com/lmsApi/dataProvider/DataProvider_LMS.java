package com.lmsApi.dataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.lmsApi.utilities.Read_XL_Data;

public class DataProvider_LMS {
	
	@DataProvider(name="AuthCredentials")
	String [][] getCredentials() throws IOException{
		String path="C:/Users/manoj/Desktop/SDET Course/LMSdata.xlsx";
		Read_XL_Data logindatas = new Read_XL_Data(path,"Login");
		return logindatas.getInputData();
		
		}//end of getProgramID func
	
	
	
	@DataProvider(name="ProgramID")
	String [][] getProgramID() throws IOException{
		String path="C:/Users/manoj/Desktop/SDET Course/LMSdata.xlsx";
		Read_XL_Data programid = new Read_XL_Data(path,"GET");
		return programid.getInputData();
		
		}//end of getProgramID func
	
	@DataProvider(name="Login")
	String [][] Login() throws IOException{
		String path="C:/Users/manoj/Desktop/SDET Course/LMSdata.xlsx";
		Read_XL_Data logindatas = new Read_XL_Data(path,"LoginCredentials");
		return logindatas.getInputData();
		
		}//end of Login func
	@DataProvider(name="POSTrequest")
	String [][] Post_request() throws IOException{
		String path="C:/Users/manoj/Desktop/SDET Course/LMSdata.xlsx";
		Read_XL_Data Postdatas = new Read_XL_Data(path,"POST");
		return Postdatas.getInputData();
		
		}//end of  Post_request func
	
	@DataProvider(name="PUTrequest")
	String [][] Put_request() throws IOException{
		String path="C:/Users/manoj/Desktop/SDET Course/LMSdata.xlsx";
		Read_XL_Data Putdatas = new Read_XL_Data(path,"PUTData");
		return Putdatas.getInputData();
		
		}//end of Put_request func
	@DataProvider(name="Deleterequest")
	String [][] Delete_request() throws IOException{
		String path="C:/Users/manoj/Desktop/SDET Course/LMSdata.xlsx";
		Read_XL_Data deldatas = new Read_XL_Data(path,"DELETE");
		return deldatas.getInputData();
		
		}//end of getLoginDetails func
	
	
	
	
	
}//end of class
 