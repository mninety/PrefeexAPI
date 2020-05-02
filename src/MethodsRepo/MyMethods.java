package MethodsRepo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.json.Json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import LoginAPI.MyLoginAPI;
import StartPrefeexAPI.VariableModule;

public class MyMethods {
	static Logger logMyMethods = Logger.getLogger(MyMethods.class.getName());
	static int RowCount=1;
	static int initvalue=0;
	static String init="";
	static int m=0;
	static String[] endpoint = new String[100];
	
	static String headerhtml="<!DOCTYPE html>\r\n" + 
			"<html lang=\"en\">\r\n" + 
			"\r\n" + 
			"<head>\r\n" + 
			"\r\n" + 
			"    <!-- Required meta tags -->\r\n" + 
			"    <meta charset=\"utf-8\">\r\n" + 
			"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n" + 
			"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\r\n" + 
			"    <title>Prefeex Test Report</title>\r\n" + 
			"    <!-- Bootstrap CSS -->\r\n" + 
			"    <link rel=\"stylesheet\" href=\"./dist/css/bootstrap.min.css\">\r\n" + 
			"    <link rel=\"stylesheet\" href=\"./dist/css/style.css\">\r\n" + 
			"</head>\r\n" + 
			"\r\n" + 
			"<body>\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"    <div class=\" container-fluid\">\r\n" + 
			"        <div class=\"row\">\r\n" + 
			"            <div class=\"col-12\">\r\n" + 
			"                <div class=\"d-flex justify-content-between mt-2 mb-2\">\r\n" + 
			"                    <h1 class=\"mb-0\">Test Report</h1>\r\n" + 
			"                    <a href=\"summaryreport.html\" class=\"btn btn-primary\" role=\"button\" aria-pressed=\"true\">API Summery Report</a>\r\n" + 
			"                </div>\r\n" + 
			"\r\n" + 
			"                <div class=\" table-responsive\">\r\n" + 
			"                    <table class=\"table  table-bordered\">\r\n" + 
			"                        <thead class=\"thead-pf\">\r\n" + 
			"                            <tr>\r\n" + 
			"                                <th style=\"width: 10%;\" scope=\"col\">API Name</th>\r\n" + 
			"                                <th style=\"width: 10%;\" scope=\"col\">API Cases</th>\r\n" + 
			"                                <th style=\"width: 10%;\" scope=\"col\">End Point</th>\r\n" + 
			"                                <th style=\"width: 10%;\" scope=\"col\">Method Type</th>\r\n" + 
			"                                <th style=\"width: 10%;\" scope=\"col\">Body Input</th>\r\n" + 
			"                                <th style=\"width: 10%;\" scope=\"col\">Header</th>\r\n" + 
			"                                <th style=\"width: 10%;\" scope=\"col\">Output JSON</th>\r\n" + 
			"                                <th style=\"width: 10%;\" scope=\"col\">JSON Path</th>\r\n" + 
			"                                <th style=\"width: 10%;\" scope=\"col\">Path Value</th>\r\n" + 
			"                                <th style=\"width: 10%;\" scope=\"col\">HTTP Code</th>\r\n" + 
			"                                <th style=\"width: 10%;\" scope=\"col\">Execution Time</th>\r\n" + 
			"                                <th style=\"width: 10%;\" scope=\"col\">Test Result</th>\r\n" + 
			"                            </tr>\r\n" + 
			"                        </thead>\r\n" + 
			"                        <tbody>";

	
	static String headersummaryhtml="<!DOCTYPE html>\r\n" + 
			"<html lang=\"en\">\r\n" + 
			"\r\n" + 
			"<head>\r\n" + 
			"\r\n" + 
			"    <!-- Required meta tags -->\r\n" + 
			"    <meta charset=\"utf-8\">\r\n" + 
			"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n" + 
			"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\r\n" + 
			"    <title>Prefeex API Summary Report</title>\r\n" + 
			"    <!-- Bootstrap CSS -->\r\n" + 
			"    <link rel=\"stylesheet\" href=\"./dist/css/bootstrap.min.css\">\r\n" + 
			"    <link rel=\"stylesheet\" href=\"./dist/css/style.css\">\r\n" + 
			"</head>\r\n" + 
			"\r\n" + 
			"<body>\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"    <div class=\" container-fluid\">\r\n" + 
			"        <div class=\"row\">\r\n" + 
			"            <div class=\"col-12\">\r\n" + 
			"\r\n" + 
			"                <div class=\"d-flex justify-content-between mt-2 mb-2\">\r\n" + 
			"                    <h1 class=\"mb-0\">API Summary Report</h1>\r\n" + 
			"                    <a href=\"index.html\" class=\"btn btn-primary\" role=\"button\" aria-pressed=\"true\">Test Report</a>\r\n" + 
			"                </div>\r\n" + 
			"                <div class=\" table-responsive\">\r\n" + 
			"                    <table class=\"table  table-bordered\">\r\n" + 
			"                        <thead class=\"thead-pf\">\r\n" + 
			"                            <tr>\r\n" + 
			"                                <th style=\"width: 10%;\" scope=\"col\">API Name</th>\r\n" + 
			"                                <th class=\"text-center\" style=\"width: 10%;\" scope=\"col\">End Point</th>\r\n" +
			"                                <th style=\"width: 10%;\" scope=\"col\">Total no. of Test Cases</th>\r\n" + 
			"                                <th style=\"width: 10%;\" scope=\"col\">Total no. of Successful Cases</th>\r\n" + 
			"                                <th style=\"width: 10%;\" scope=\"col\">Total no. of Failed Cases</th>\r\n" + 
			"                            </tr>\r\n" + 
			"                        </thead>\r\n" + 
			"                        <tbody>";

	static String footerhtml="                        </tbody>\r\n" + 
			"                    </table>\r\n" + 
			"                </div>\r\n" + 
			"            </div>\r\n" + 
			"        </div>\r\n" + 
			"    </div>\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"    <!-- Optional JavaScript -->\r\n" + 
			"    <!-- jQuery first, then Popper.js, then Bootstrap JS -->\r\n" + 
			"    <script src=\"./dist/js/jquery.min.js\"></script>\r\n" + 
			"    <script src=\"./dist/js/popper.js\"></script>\r\n" + 
			"    <script src=\"./dist/js/bootstrap.min.js\"></script>\r\n" + 
			"</body>\r\n" + 
			"\r\n" + 
			"</html>";
	
	static String footersummaryhtml="                        </tbody>\r\n" + 
			"                    </table>\r\n" + 
			"                </div>\r\n" + 
			"            </div>\r\n" + 
			"        </div>\r\n" + 
			"    </div>\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"    <!-- Optional JavaScript -->\r\n" + 
			"    <!-- jQuery first, then Popper.js, then Bootstrap JS -->\r\n" + 
			"    <script src=\"./dist/js/jquery.min.js\"></script>\r\n" + 
			"    <script src=\"./dist/js/popper.js\"></script>\r\n" + 
			"    <script src=\"./dist/js/bootstrap.min.js\"></script>\r\n" + 
			"</body>\r\n" + 
			"\r\n" + 
			"</html>";
	
	
	public static void FolderCreateAction(File T) {
        if(!T.exists()){  
        	if(T.mkdir()){ 
        		//System.out.println("directory is  created"); 
        		}
        	}  
        else { 
        	//System.out.println("directory exist");  
        	}
	}
	
    public static String getCurrentDate() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateobject = new SimpleDateFormat("ddMMyyyy-HHmmss");
        
        return dateobject.format(date);
    }
    
    public static void HeaderInitialization()
    {
    	System.out.println("Header Initialization ");
    	for(int i=0;i<7;i++)
    	{
    		String body="";
    		
    		if(i==0)
    		{
				body="{\r\n" + 
						"    \"email\": \"t@p.com\",\r\n" + 
						"    \"password\": \"aaaaa\",\r\n" + 
						"    \"userType\": \"END_USER\"\r\n" + 
						"}";
	    		String output[]=postAPI("/api/login",body,2,"");
	    		//System.out.println("Response: "+output[0]);
				String parseStr1=MyMethods.jsonMaker(output[0],"data.token");
				String parseStr[]=parseStr1.split(",");
				//System.out.println("Token: "+parseStr[1]);
				VariableModule.Enduser_Token1=parseStr[1];
    		}
    		else if(i==1)
    		{
    			body="{\r\n" + 
    					"    \"email\": \"endu3@test.com\",\r\n" + 
    					"    \"password\": \"1234\",\r\n" + 
    					"    \"userType\": \"END_USER\"\r\n" + 
    					"}";
	    		String output[]=postAPI("/api/login",body,2,"");
	    		//System.out.println("Response: "+output[0]);
				String parseStr1=MyMethods.jsonMaker(output[0],"data.token");
				String parseStr[]=parseStr1.split(",");
				//System.out.println("Token: "+parseStr[1]);
				VariableModule.Enduser_Token2=parseStr[1];
    		}
    		else if(i==2)
    		{
    			body="{\r\n" + 
    					"    \"email\": \"mizan@prefeex.com\",\r\n" + 
    					"    \"password\": \"1234\",\r\n" + 
    					"    \"userType\": \"INTERNAL\"\r\n" + 
    					"}";
	    		String output[]=postAPI("/api/login",body,2,"");
	    		//System.out.println("Response: "+output[0]);
				String parseStr1=MyMethods.jsonMaker(output[0],"data.token");
				String parseStr[]=parseStr1.split(",");
				//System.out.println("Token: "+parseStr[1]);
				VariableModule.Admin_Token1=parseStr[1];
    		}
    		else if(i==3)
    		{
    			body="{\r\n" + 
    					"    \"email\": \"test@test.com\",\r\n" + 
    					"    \"password\": \"1234\",\r\n" + 
    					"    \"userType\": \"BUSINESS\"\r\n" + 
    					"}";
    			
	    		String output[]=postAPI("/api/login",body,2,"");
	    		//System.out.println("Response: "+output[0]);
				String parseStr1=MyMethods.jsonMaker(output[0],"data.token");
				String parseStr[]=parseStr1.split(",");
				//System.out.println("Token: "+parseStr[1]);
				VariableModule.Business_Token1=parseStr[1];
				
    		}
    		else if(i==4)
    		{
    			body="{\r\n" + 
    					"    \"email\": \"zahid980@gmail.com\",\r\n" + 
    					"    \"password\": \"1234\",\r\n" + 
    					"    \"userType\": \"BUSINESS\"\r\n" + 
    					"}";
    			
	    		String output[]=postAPI("/api/login",body,2,"");
	    		//System.out.println("Response: "+output[0]);
				String parseStr1=MyMethods.jsonMaker(output[0],"data.token");
				String parseStr[]=parseStr1.split(",");
				//System.out.println("Token: "+parseStr[1]);
				VariableModule.Business_Token2=parseStr[1];
				
    		}
    		else if(i==5)
    		{
    			body="{\r\n" + 
    					"    \"email\": \"musfiq.test@prefeex.com\",\r\n" + 
    					"    \"password\": \"1234\",\r\n" + 
    					"    \"userType\": \"INTERNAL\"\r\n" + 
    					"}";
    			
	    		String output[]=postAPI("/api/login",body,2,"");
	    		//System.out.println("Response: "+output[0]);
				String parseStr1=MyMethods.jsonMaker(output[0],"data.token");
				String parseStr[]=parseStr1.split(",");
				//System.out.println("Token: "+parseStr[1]);
				VariableModule.Support_Token1=parseStr[1];
				
    		}
    		else if(i==6)
    		{
    			body="{\r\n" + 
    					"    \"email\": \"pemnd@test.com\",\r\n" + 
    					"    \"password\": \"1234\",\r\n" + 
    					"    \"userType\": \"INTERNAL\"\r\n" + 
    					"}";
    			
	    		String output[]=postAPI("/api/login",body,2,"");
	    		//System.out.println("Response: "+output[0]);
				String parseStr1=MyMethods.jsonMaker(output[0],"data.token");
				String parseStr[]=parseStr1.split(",");
				//System.out.println("Token: "+parseStr[1]);
				VariableModule.Support_Token2=parseStr[1];
				
    		}



    	}
    }
    
    public static void HeaderGenerator(String token, String userID, String userPass)
    {
    	System.out.println("Header Generator ");

    		String body="";
    		
    		if(token.equals("Enduser_Token1")) //Enduser_Token1
    		{
				body="{\r\n" + 
						"    \"email\": \""+userID+"\",\r\n" + 
						"    \"password\": \""+userPass+"\",\r\n" + 
						"    \"userType\": \"END_USER\"\r\n" + 
						"}";
	    		String output[]=postAPI("/api/login",body,2,"");
	    		//System.out.println("Response: "+output[0]);

					String parseStr1=MyMethods.jsonMaker(output[0],"data.token");
					String parseStr[]=parseStr1.split(",");
					//System.out.println("Token: "+parseStr[1]);
					if(!parseStr[1].equals(""))
					{
						VariableModule.Enduser_Token1=parseStr[1];
					}
					else
					{
						System.out.println("Enduser_Token1: Token Empty!!!");
					}
					

    		}
    		else if(token.equals("Enduser_Token2")) //Enduser_Token2
    		{
    			body="{\r\n" + 
						"    \"email\": \""+userID+"\",\r\n" + 
						"    \"password\": \""+userPass+"\",\r\n" + 
    					"    \"userType\": \"END_USER\"\r\n" + 
    					"}";
	    		String output[]=postAPI("/api/login",body,2,"");
	    		//System.out.println("Response: "+output[0]);
				String parseStr1=MyMethods.jsonMaker(output[0],"data.token");
				String parseStr[]=parseStr1.split(",");
				//System.out.println("Token: "+parseStr[1]);
				if(!parseStr[1].equals(""))
				{
					VariableModule.Enduser_Token2=parseStr[1];
				}
				else
				{
					System.out.println("Enduser_Token2: Token Empty!!!");
				}
    		}
    		else if(token.equals("Admin_Token1")) //Admin_Token1
    		{
    			body="{\r\n" + 
						"    \"email\": \""+userID+"\",\r\n" + 
						"    \"password\": \""+userPass+"\",\r\n" + 
    					"    \"userType\": \"INTERNAL\"\r\n" + 
    					"}";
	    		String output[]=postAPI("/api/login",body,2,"");
	    		//System.out.println("Response: "+output[0]);
				String parseStr1=MyMethods.jsonMaker(output[0],"data.token");
				String parseStr[]=parseStr1.split(",");
				//System.out.println("Token: "+parseStr[1]);
				if(!parseStr[1].equals(""))
				{
					VariableModule.Admin_Token1=parseStr[1];
				}
				else
				{
					System.out.println("Admin_Token1: Token Empty!!!");
				}
    		}
    		else if(token.equals("Business_Token1"))// Business_Token1
    		{
    			body="{\r\n" + 
						"    \"email\": \""+userID+"\",\r\n" + 
						"    \"password\": \""+userPass+"\",\r\n" + 
    					"    \"userType\": \"BUSINESS\"\r\n" + 
    					"}";
    			
	    		String output[]=postAPI("/api/login",body,2,"");
	    		//System.out.println("Response: "+output[0]);
				String parseStr1=MyMethods.jsonMaker(output[0],"data.token");
				String parseStr[]=parseStr1.split(",");
				//System.out.println("Token: "+parseStr[1]);
				if(!parseStr[1].equals(""))
				{
					VariableModule.Business_Token1=parseStr[1];
				}
				else
				{
					System.out.println("Business_Token1: Token Empty!!!");
				}
				
    		}
    		else if(token.equals("Business_Token2"))// Business_Token2
    		{
    			body="{\r\n" + 
						"    \"email\": \""+userID+"\",\r\n" + 
						"    \"password\": \""+userPass+"\",\r\n" +  
    					"    \"userType\": \"BUSINESS\"\r\n" + 
    					"}";
    			
	    		String output[]=postAPI("/api/login",body,2,"");
	    		//System.out.println("Response: "+output[0]);
				String parseStr1=MyMethods.jsonMaker(output[0],"data.token");
				String parseStr[]=parseStr1.split(",");
				//System.out.println("Token: "+parseStr[1]);
				if(!parseStr[1].equals(""))
				{
					VariableModule.Business_Token2=parseStr[1];
				}
				else
				{
					System.out.println("Business_Token2: Token Empty!!!");
				}
				
    		}
    		else if(token.equals("Support_Token1")) //Support_Token1
    		{
    			body="{\r\n" + 
						"    \"email\": \""+userID+"\",\r\n" + 
						"    \"password\": \""+userPass+"\",\r\n" + 
    					"    \"userType\": \"INTERNAL\"\r\n" + 
    					"}";
    			
	    		String output[]=postAPI("/api/login",body,2,"");
	    		//System.out.println("Response: "+output[0]);
				String parseStr1=MyMethods.jsonMaker(output[0],"data.token");
				String parseStr[]=parseStr1.split(",");
				//System.out.println("Token: "+parseStr[1]);
				if(!parseStr[1].equals(""))
				{
					VariableModule.Support_Token1=parseStr[1];
				}
				else
				{
					System.out.println("Support_Token1: Token Empty!!!");
				}
				
    		}
    		else if(token.equals("Support_Token2")) //Support_Token2
    		{
    			body="{\r\n" + 
						"    \"email\": \""+userID+"\",\r\n" + 
						"    \"password\": \""+userPass+"\",\r\n" + 
    					"    \"userType\": \"INTERNAL\"\r\n" + 
    					"}";
    			
	    		String output[]=postAPI("/api/login",body,2,"");
	    		//System.out.println("Response: "+output[0]);
				String parseStr1=MyMethods.jsonMaker(output[0],"data.token");
				String parseStr[]=parseStr1.split(",");
				//System.out.println("Token: "+parseStr[1]);
				if(!parseStr[1].equals(""))
				{
					VariableModule.Support_Token2=parseStr[1];
				}
				else
				{
					System.out.println("Support_Token2: Token Empty!!!");
				}
				
    		}

    }
    
    
    public static String GetHeader(String token)
    {
    	String header="";
    	try {
			
    		if(token.equals("Enduser_Token1"))
    		{
    			header=VariableModule.Enduser_Token1;
    		}
    		else if(token.equals("Enduser_Token2"))
    		{
    			header=VariableModule.Enduser_Token2;
    		}
    		else if(token.equals("Admin_Token1"))
    		{
    			header=VariableModule.Admin_Token1;
    		}
    		else if(token.equals("Business_Token1"))
    		{
    			header=VariableModule.Business_Token1;
    		}
    		else if(token.equals("Business_Token2"))
    		{
    			header=VariableModule.Business_Token2;
    		}
    		else if(token.equals("Support_Token1"))
    		{
    			header=VariableModule.Support_Token1;
    		}
    		else if(token.equals("Support_Token2"))
    		{
    			header=VariableModule.Support_Token2;
    		}
    		
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	return header;
    }
    public static Connection MysqlConnectionOwn()
    {
    	//long startTime = System.currentTimeMillis();
    	Connection connt = null;
	    try {
	        // STEP 2: Register JDBC driver
	        Class.forName("com.mysql.jdbc.Driver");
	        //String jdbcUrl = "jdbc:mysql://" + VariableModule.MySQLIP +"/" + VariableModule.DBName + "?user=" + VariableModule.DBUser + "&password=" + VariableModule.DBPass;
	        connt = DriverManager.getConnection("jdbc:mysql://"+VariableModule.MySQLIP+"/"+VariableModule.DBName, VariableModule.DBUser, VariableModule.DBPass);
	        //connt = DriverManager.getConnection(jdbcUrl);
	        writing(" Own Database Connected!\n");
	    }catch(Exception e) {
	        e.printStackTrace();
	    }

	    
/*	    public void close() throws SQLException {
	        databaseConnection.close();
	    }*/
	    //CommonAction.ExecuteTimeMeasure(startTime,"ProcessParserforCPU");
		return connt;
    }
    
    public static void MysqlConnectOwn(Connection connect)
    {
    	if(connect==null)
    	{
    		VariableModule.Ownconn=MysqlConnectionOwn();
    		MyMethods.PrintMe("Mysql Connection is getting: "+VariableModule.Ownconn,"");
    		writing("Mysql Connection is getting: "+VariableModule.Ownconn);
/*	    	if(VariableModule.Ownconn==null)
	    	{
	    		try {
					EmailModule.sendEmailWithAttachments("nafiul@revesoft.com", "MySQL connection is null", "Dear Team,\n\n"+VariableModule.server_IP+" server MySQL connection is getting null.\nPlease fix the MySQL connection issue otherwise Mail thread will be failed to send SQA mail.","5");
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (javax.mail.MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}*/
    	}
    }
    
    
	public static String MysqlConnectionAction(String Myquery, Connection conn, String character)
	{

		String columnValue1="";
		String columnValue="";
		String[] URL=null;
	    //Connection connection = null;
	    java.sql.Statement stmt = null;
		    
	    try {
	    	//connection=CommonOSModule.MysqlConnection();
	    	stmt = conn.createStatement();
		    ResultSet rs=stmt.executeQuery(Myquery);
		    ResultSetMetaData rsmd = rs.getMetaData();
		    int columnsNumber = rsmd.getColumnCount();
		    int i;
		    while (rs.next()) {

		        for (i = 1; i <= columnsNumber; i++) {
		            //if (i > 1) System.out.print("\n");
		            columnValue = rs.getString(i);
		            if(columnValue==null)
		            {
		            	columnValue="";
		            }
		            if(columnValue.equals("NULL"))
		            {
		            	//System.out.print("NULL found:");
		            	columnValue="";
		            }
		            //DBValues[i]=columnValue;
		            //System.out.print("Test:"+columnValue);
		            //System.out.print(columnValue + " " + rsmd.getColumnName(i));
		            if(i<columnsNumber)
		            {
		            	columnValue1=columnValue1.concat(columnValue+character);
		            }
		            else
		            {

		            	columnValue1=columnValue1.concat(columnValue);

		            }
		        }
		        i=1;
		        if(!columnValue1.equals(""))
		        {
		        	columnValue1=columnValue1.concat("\n");
		        }
		    }
		    //System.out.print("Test:"+columnValue1);
		    stmt.close();
		    //connection.close();
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		return columnValue1;
	}
	
	
	public static void FileBackupAction() {
        
        File fileexcel = new File(VariableModule.driverlog+"/InputFiles");
		String currentDate = getCurrentDate();
        FolderCreateAction(fileexcel);
        ExcelFileCreateAction();
	}
	
	public static void copyFileUsingStream() throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	    	String[] fileName1=VariableModule.InputFile.split("\\.");
	    	String fileName=fileName1[0];
			File source= new File(VariableModule.driverlog+"/InputFiles"+fileName+".xlsx");
			File dest = new File(VariableModule.driverlog+"/InputFiles"+fileName+"-"+MyMethods.getCurrentDate()+".xlsx");
			
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    }catch(Exception e)
	    {
	    	
	    }

	    if(is != null)
	    {
	        is.close();
	        os.close();
	    }
	}
	
	public static void ExcelFileDeleteAction() {
		
		File source= new File(VariableModule.driverlog+"/InputFiles"+VariableModule.InputFile);

		try {
			MyMethods.copyFileUsingStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
        if(source.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 
        
	}
	public static void ExcelFileCreateAction() {
			 try {
					String currentDate = getCurrentDate();
					int randomValue = (int )(Math.random() * 500 + 1);
			        File oldName = new File(VariableModule.driverlog+"/Reports/ReportAPI.xlsx");
			        File newName = new File(VariableModule.driverlog+"/Reports/ReportAPI"+'-'+currentDate+'-'+randomValue+".xlsx");
			        oldName.renameTo(newName);
				XSSFWorkbook workbook = new XSSFWorkbook();
				FileOutputStream out = new FileOutputStream(new File(VariableModule.driverlog+"/Reports/ReportAPI.xlsx"));
				XSSFSheet Spreadsheet = workbook.createSheet("ReportAPI-"+currentDate);
				XSSFRow header = Spreadsheet.createRow(0);
				//XSSFCellStyle style = workbook.createCellStyle();
				header.createCell(0).setCellValue("Feature");
			    header.createCell(1).setCellValue("Test Case");
			    header.createCell(2).setCellValue("Testing Result");
			    header.createCell(3).setCellValue("Execution Time");
			    header.createCell(4).setCellValue("Message");
			    header.createCell(5).setCellValue("Input Paramters");
	/*			    style.setBorderTop(BorderStyle.THIN);
			    style.setBorderBottom(BorderStyle.MEDIUM);
			    style.setBorderLeft(BorderStyle.MEDIUM);
			    style.setBorderRight(BorderStyle.MEDIUM);*/
				workbook.write(out);
				out.close();
			}
			catch(Exception e) {
				System.out.println(e);
				
			}
		}
	public static void Sleep(long milis)
	{
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void PrintMe(String str1, String str2)
	{
		System.out.println(str1+str2);
	}

	public static void ExcelFileWriteAction(String feature, String testcase, String result, String date, String message, String param, int noofCase) {
		
		if(result!="") { //Cases added
		       

			XSSFCell cell = null; 
		
				//System.out.println("Writer Enabled"+RowCount);
				try {
					
					
					
					FileInputStream fIPS= new FileInputStream(VariableModule.driverlog+"/Reports/ReportAPI.xlsx");
					XSSFWorkbook workbook = new XSSFWorkbook(fIPS);
					XSSFSheet worksheet = workbook.getSheetAt(0);
					
					fIPS.close();
					FileOutputStream out = new FileOutputStream(new File(VariableModule.driverlog+"/Reports/ReportAPI.xlsx"));
					//XSSFSheet worksheet = workbook.getSheetAt(0);
			        XSSFCellStyle headerStyle = workbook.createCellStyle();
			        Font headerFont = workbook.createFont();
					XSSFRow row = worksheet.createRow(RowCount);

				       //indexOf return -1 if String does not contain specified word
				       if(result.indexOf("Failed") != -1){
				           //System.err.printf("Yes '%s' contains word 'Failed' %n" , result);
					        //font.setColor(IndexedColors.RED.getIndex());
					        //style.setFont(font);
							row.createCell(0).setCellValue(feature);
						    //header.createCell(1).setCellValue(result);
						    cell = row.createCell(1);
						    cell.setCellValue(testcase);
						    
						    
						    cell = row.createCell(2);
						    cell.setCellValue(result);
						    
						    cell = row.createCell(3);
						    cell.setCellValue(date);
						    
						    cell = row.createCell(4);
						    cell.setCellValue(message);
						    
						    cell = row.createCell(5);
						    cell.setCellValue(param);
					        //headerStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
					        
					        //headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
						    //headerFont.setColor(IndexedColors.RED.getIndex());
					        headerStyle.setFont(headerFont);
					        
					        
				       }
				       else if(result.indexOf("null") != -1){
				           //System.err.printf("Yes '%s' contains word 'null' %n" , result);
					        //font.setColor(IndexedColors.RED.getIndex());
					        //style.setFont(font);
							row.createCell(0).setCellValue(feature);
						    //header.createCell(1).setCellValue(result);
						    cell = row.createCell(1);
						    cell.setCellValue("");

						    cell = row.createCell(2);
						    cell.setCellValue(result);
						    
						    cell = row.createCell(3);
						    cell.setCellValue(date);
						    
						    cell = row.createCell(4);
						    cell.setCellValue(message);
						    
						    cell = row.createCell(5);
						    cell.setCellValue(param);
					        //headerStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
					        headerFont.setColor(IndexedColors.BLUE.getIndex());
					        //headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
					        headerStyle.setFont(headerFont);
					        
					        
				       }
				       else {
							row.createCell(0).setCellValue(feature);
						    //header.createCell(1).setCellValue(result);
						    cell = row.createCell(1);
						    cell.setCellValue(testcase);

						    cell = row.createCell(2);
						    cell.setCellValue(result);
						    
						    cell = row.createCell(3);
						    cell.setCellValue(date);
						    
						    cell = row.createCell(4);
						    cell.setCellValue(message);
						    
						    cell = row.createCell(5);
						    cell.setCellValue(param);
				       }
				    cell.setCellStyle(headerStyle);
				    
				    if(noofCase>1)
				    {
				    	initvalue++;
				    	init=init+String.valueOf(RowCount)+",";
					    if(noofCase==initvalue)
					    {
					    	String[] initstr=init.split(",");
					    	//MyMethods.PrintMe("Row1: ", String.valueOf(RowCount));
					    	worksheet.addMergedRegion(new CellRangeAddress(Integer.valueOf(initstr[0]),RowCount,0,0));
					    	initvalue=0;
					    	init="";
					    }
				    }
					workbook.write(out);
					out.close();
					
					
				} catch (IOException e) {
		
					e.printStackTrace();
				}
				//MyMethods.PrintMe("Row2: ", String.valueOf(RowCount));
				RowCount++;
		}
		else //Module name added
		{
			
			RowCount=RowCount+3;
			//System.out.println("Writer Enabled with null"+RowCount);
			XSSFCell cell = null; 
			try {
				FileInputStream fIPS= new FileInputStream(VariableModule.driverlog+"/WebDriver/Reports/ReportAPI.xlsx");
				XSSFWorkbook workbook = new XSSFWorkbook(fIPS);
				XSSFSheet worksheet = workbook.getSheetAt(0);
				
				fIPS.close();
				
				FileOutputStream out = new FileOutputStream(new File(VariableModule.driverlog+"/WebDriver/Reports/ReportAPI.xlsx"));
				XSSFRow row = worksheet.createRow(RowCount);
				cell = row.createCell(0);
				//cell = row.createCell(1);

				worksheet.addMergedRegion(new CellRangeAddress(RowCount,RowCount,0,1));
			    cell.setCellValue(testcase);
				workbook.write(out);
				out.close();
				
				
			} catch (IOException e) {

				e.printStackTrace();
			}
			
			RowCount=RowCount+3;
		}
	}

	public static void ExcelFileWriteforAPI(String[] output, String sheetName, int rowno, String testresult) {
		
		if(output[0]!="") { //Cases added
		       

			XSSFCell cell = null; 
			FileInputStream fIPS;
			FileOutputStream out;
			XSSFWorkbook workbook;
				//System.out.println("Writer Enabled"+RowCount);
				try {
					
					fIPS= new FileInputStream(VariableModule.driverlog+"/InputFiles"+VariableModule.InputFile);
					workbook = new XSSFWorkbook(fIPS);
					out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles"+VariableModule.InputFile));
					XSSFSheet worksheet = workbook.getSheet(sheetName);
					
					fIPS.close();

			        XSSFCellStyle headerStyle = workbook.createCellStyle();
			        Font headerFont = workbook.createFont();
					XSSFRow row = worksheet.getRow(rowno);

					if(output[0].length()>32766)
					{
						row.getCell(6).setCellValue(output[0].substring(0, 32766));
						//System.out.println("Broken: "+output[0].substring(0, 32766));
					}
					else
					{
						row.getCell(6).setCellValue(output[0]);
					}

					row.getCell(9).setCellValue(output[1]);
					row.getCell(10).setCellValue(MyMethods.getCurrentDate());
					row.getCell(11).setCellValue(testresult);
				       //indexOf return -1 if String does not contain specified word

				    //cell.setCellStyle(headerStyle);
				    
					workbook.write(out);
					out.close();
					
					
				} catch (IOException e) {
		
					e.printStackTrace();
				}

		}

	}

	public static void ExcelFileWriteforReplace(String sheetName, int rowno, String endpoint, String inputbody) {
		
		       

			XSSFCell cell = null; 
			FileInputStream fIPS;
			FileOutputStream out;
			XSSFWorkbook workbook;
				//System.out.println("Writer Enabled"+RowCount);
				try {
					
					fIPS= new FileInputStream(VariableModule.driverlog+"/InputFiles"+VariableModule.InputFile);
					workbook = new XSSFWorkbook(fIPS);
					out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles"+VariableModule.InputFile));
					XSSFSheet worksheet = workbook.getSheet(sheetName);
					
					fIPS.close();

			        XSSFCellStyle headerStyle = workbook.createCellStyle();
			        Font headerFont = workbook.createFont();
					XSSFRow row = worksheet.getRow(rowno);

					row.getCell(12).setCellValue(endpoint);
					row.getCell(13).setCellValue(inputbody);
				       //indexOf return -1 if String does not contain specified word

				    //cell.setCellStyle(headerStyle);
				    
					workbook.write(out);
					out.close();
					
					
				} catch (IOException e) {
		
					e.printStackTrace();
				}

	}

	
	public static void ExcelFileWriteforSummary(String apiName, String rowno, String noofsuccess, String nooffailed) {

			XSSFCell cell = null; 
			FileInputStream fIPS;
			FileOutputStream out;
			XSSFWorkbook workbook;
				//System.out.println("Writer Enabled"+RowCount);
				try {
					
					fIPS= new FileInputStream(VariableModule.driverlog+"/InputFiles"+VariableModule.InputFile);
					workbook = new XSSFWorkbook(fIPS);
					out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles"+VariableModule.InputFile));

					XSSFSheet worksheet = workbook.getSheet("Summary");
					
					fIPS.close();

			        XSSFCellStyle headerStyle = workbook.createCellStyle();
			        Font headerFont = workbook.createFont();
			        System.out.println("Total Found Rows: "+worksheet.getPhysicalNumberOfRows());
					XSSFRow row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());

					for(int j=0;j<m;j++)
					{
						String[] endpoint1=endpoint[j].split(",");
						if(endpoint1[0].equals(apiName))
						{
							row.createCell(1).setCellValue(endpoint1[1]);
						}
					}
					
					row.createCell(0).setCellValue(apiName);
					row.createCell(2).setCellValue(rowno);
					row.createCell(3).setCellValue(noofsuccess);
					row.createCell(4).setCellValue(nooffailed);
				       //indexOf return -1 if String does not contain specified word

				    //cell.setCellStyle(headerStyle);
				    
					workbook.write(out);
					out.close();
					
					
				} catch (IOException e) {
		
					e.printStackTrace();
				}


	}



	@SuppressWarnings("null")
	public static void ExcelFileEmptyforSummary(String testingMode, int noofrows) {



		FileOutputStream out;
		XSSFWorkbook workbook = null;
			//System.out.println("Writer Enabled"+RowCount);
			try {
				
				out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles"+VariableModule.InputFile));
				XSSFSheet worksheet = workbook.getSheet("Summary");


				//Sheet worksheet = workbook.getSheet("Summary");
				for (int i = noofrows; i >= 1; i--) 
				{
					worksheet.removeRow(worksheet.getRow(i));
				}
			    
				workbook.write(out);
				out.close();
				
				
			} catch (IOException e) {
	
				e.printStackTrace();
			}
			



}

	public static int ExcelRowCount(String testingMode)
	{
		int notNullCount = 0;
		FileInputStream fIPS;
		XSSFWorkbook workbook;
		FileOutputStream out;
		try {

			fIPS= new FileInputStream(VariableModule.driverlog+"/InputFiles"+VariableModule.InputFile);
			workbook = new XSSFWorkbook(fIPS);
			out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles"+VariableModule.InputFile));
			
			XSSFSheet worksheet = workbook.getSheet("Summary");
			
			
			//Sheet sheet = wb.getSheetAt(0);
			for (Row row : worksheet) {
			    for (Cell cell : row) {
			        if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
			            if (cell.getCellType() != Cell.CELL_TYPE_STRING ||
			                cell.getStringCellValue().length() > 0) {
			                notNullCount++;
			                break;
			            }
			        }
			    }
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notNullCount;
	}
	
	//@SuppressWarnings({ "null", "resource" })
	public static String[][] ExcelFileReadAction(String sheetName)
	{
		String input="";
		String[][] data=null;

		FileInputStream fs;
		XSSFWorkbook wb;
		try {
			//System.out.println("File Name: "+VariableModule.InputFile);
			fs= new FileInputStream(VariableModule.driverlog+"/InputFiles"+VariableModule.InputFile);
			wb = new XSSFWorkbook(fs);

		    XSSFSheet sheet = wb.getSheet(sheetName);
		    XSSFRow row;
		    XSSFCell cell;

		    int rows = sheet.getPhysicalNumberOfRows();
		    MyLoginAPI.noofrow=rows-1;
		    //MyMethods.PrintMe("Rows: ", String.valueOf(rows));
		    
		    data= new String[rows-1][7];
		    //MyMethods.PrintMe("endpoint: ", sheetName+","+sheet.getRow(1).getCell((short)2).toString());
		    //System.out.println("M Value is: "+m);
		    endpoint[m]=sheetName+","+sheet.getRow(1).getCell((short)2).toString();
		    
		    for(int r = 0; r < rows; r++) {
		    	if(r>0)
		    	{
			        row = sheet.getRow(r);
			        if(row != null) {

			        	data[r-1][0]=row.getCell((short)2).toString(); //end point
			        	data[r-1][1]=row.getCell((short)3).toString(); //method Type
			        	data[r-1][2]=row.getCell((short)4).toString(); //body input
			        	data[r-1][4]=row.getCell((short)5).toString(); //header
			        	data[r-1][5]=row.getCell((short)7).toString(); //JSON path
			        	data[r-1][3]=row.getCell((short)8).toString(); //path value
			        	data[r-1][6]=row.getCell((short)1).toString(); //Test Cases

			            
			        }
		    	}
		    }
		    //System.out.println("Input Data: "+input);
		    fs.close();
		    
		    m++;
		} catch(Exception ioe) {
		    ioe.printStackTrace();
		}
		return data;
	}
	
	
	//@SuppressWarnings("null")
	@SuppressWarnings("resource")
	public static String[] ExcelFileSheetData()
	{
		String input="";
		String[] data=null;
		try {
			FileInputStream fs=null;
			XSSFWorkbook wb=null;
			
			fs= new FileInputStream(VariableModule.driverlog+"/InputFiles"+VariableModule.InputFile);
			wb = new XSSFWorkbook(fs);
			
		    VariableModule.sheetno = wb.getNumberOfSheets();
		    data = new String[VariableModule.sheetno];
            // for each sheet in the workbook
            for (int i = 0; i < VariableModule.sheetno; i++) {

                //System.out.println("Sheet name: " + wb.getSheetName(i));
                data[i]=wb.getSheetName(i);
            }


		    //System.out.println("Input Data: "+input);
		    fs.close();
		} catch(Exception ioe) {
		    ioe.printStackTrace();
		}
		return data;
	}
	
	public static String HTMLMakerforAll(String body, XSSFWorkbook wb, int i)
	{
		XSSFSheet sheet = wb.getSheet(wb.getSheetName(i));
		String[] apiName=wb.getSheetName(i).split("-");
	    XSSFRow row;
	    XSSFCell cell;

	    int rows = sheet.getPhysicalNumberOfRows();
	    
	    
	    String span="";
	    String resulthtml="";
	    for(int r = 0; r < rows; r++) {
	    	if(r>0)
	    	{
	    		if(r==1)
	    		{
	    			span="                                <td class=\"align-middle\" rowspan=\""+(rows-1)+"\">"+apiName[0]+"</td>\r\n";
	    		}
	    		else
	    		{
	    			span="";
	    		}

		        row = sheet.getRow(r);
		        
	    		String result= row.getCell((short)11).toString();
	    		if(result.equals("Successful"))
	    		{
	    			resulthtml="                            <tr class=\"Successful\">\r\n";
	    		}
	    		else
	    		{
	    			resulthtml="                            <tr class=\"Failed\">\r\n";
	    		}
	    		
		        if(row != null) {
		        	body= body+ resulthtml + span + 		            
		        	
		        			"                                <td class=\"\" style=\"width: 10%;\">"+row.getCell((short)1).toString()+"</td>\r\n" + 
		        			"                                <td class=\"text-center\" style=\"width: 10%;\">"+row.getCell((short)2).toString()+"</td>\r\n" + 
		        			"                                <td class=\"text-center\" style=\"width: 10%;\">"+row.getCell((short)3).toString()+"</td>\r\n" + 
		        			"                                <td style=\"width: 10%;\">\r\n" + 
		        			"                                    <pre>"+row.getCell((short)4).toString()+"</pre>\r\n" + 
		        			"                                </td>\r\n" + 
		        			"                                <td style=\"width: 10%;\"><div style=\"width: 100px\">"+row.getCell((short)5).toString()+"</div></td>\r\n" + 
		        			"                                <td style=\"width: 10%;\" class=\"outputjson\"><pre>"+row.getCell((short)6).toString()+"</pre></td>\r\n" + 
		        			"								<td class=\"text-center\" style=\"width: 10%;\">"+row.getCell((short)7).toString()+"</td>\r\n" + 
		        			"								<td class=\"text-center\" style=\"width: 10%;\">"+row.getCell((short)8).toString()+"</td>\r\n" + 
		        			"								<td class=\"text-center\" style=\"width: 10%;\">"+row.getCell((short)9).toString()+"</td>\r\n" + 
		        			"								<td style=\"width: 10%;\">"+row.getCell((short)10).toString()+"</td>\r\n" + 
		        			"								<td class=\"text-center\" style=\"width: 10%;\">"+result+"</td>\r\n" + 
		        			"</tr>";
		        	
		        }
	    	}
	    }
	    
	    return body;
	}
	
	public static String HTMLMakerforSummary(String body, XSSFWorkbook wb, int i)
	{
		XSSFSheet sheet = wb.getSheet(wb.getSheetName(i));
		//String[] apiName=wb.getSheetName(i).split("-");
	    XSSFRow row;
	    XSSFCell cell;

	    int rows = sheet.getPhysicalNumberOfRows();
	    
	    
	    String span="";
	    String resulthtml="";
	    for(int r = 0; r < rows; r++) {
	    	if(r>0)
	    	{


		        row = sheet.getRow(r);
		        
	    		//String result= row.getCell((short)11).toString();
	    		
		        if(row != null) {
		        	body= body+"                            <tr>\r\n" + 
		        			"                                <td style=\"width: 10%;\">"+row.getCell((short)0).toString()+"</td>\r\n" +
		        			"                                <td  class=\"text-center\" style=\"width: 10%;\">"+row.getCell((short)1).toString()+"</td>\r\n" + 
		        			"                                <td  class=\"text-center\" style=\"width: 10%;\">"+row.getCell((short)2).toString()+"</td>\r\n" + 
		        			"                                <td  class=\"text-center\" style=\"width: 10%;\">"+row.getCell((short)3).toString()+"</td>\r\n" + 
		        			"                                <td  class=\"text-center\" style=\"width: 10%;\">"+row.getCell((short)4).toString()+"</td>\r\n" + 
		        			"                            </tr>";
		        	
		        }
	    	}
	    }
	    
	    return body;
	}
	
	
	@SuppressWarnings("resource")
	public static void ExceltoHTML()
	{

		
		try {
			
			String input="";
		    String body="";
			FileInputStream fs = new FileInputStream(VariableModule.driverlog+"/InputFiles"+VariableModule.InputFile);
		    XSSFWorkbook wb = new XSSFWorkbook(fs);
		    
			if(VariableModule.isAllAPITest.equals("1"))
			{
				


	            for (int i = 0; i < VariableModule.sheetno; i++) {

	                //System.out.println("Sheet name: " + wb.getSheetName(i));
    				if(!wb.getSheetName(i).equals("Summary"))
    				{

    					body=HTMLMakerforAll(body,wb,i);
    					
    				}
    				else
    				{
    					body=HTMLMakerforSummary("",wb,i);
    					writingSummary(headersummaryhtml+body+footersummaryhtml);
    					body="";
    				}

	            }
	            
			}
			
			
			else //isAllAPITest=2
			{
				
				String[] apiArray=null;
				String apiArray1=VariableModule.readVariable("apiArray", 1);
				//System.out.println("Array: "+apiArray1);
				if(apiArray1.contains(","))
				{
    				apiArray=apiArray1.split(",");

    	            for (int i = 0; i < wb.getNumberOfSheets(); i++) {

    	            	for(int j=0;j<apiArray.length;j++)
    	            	{
    	            		if(wb.getSheetName(i).equals(apiArray[j]))
    	            		{
    	            			body=HTMLMakerforAll(body, wb, i);
    	        				
    	            			
    	        				
    	            		}
    	            		else
    	            		{
    	        				if(wb.getSheetName(i).equals("Summary"))
    	        				{

    	        					body=HTMLMakerforSummary("",wb,i);
    	        					writingSummary(headersummaryhtml+body+footersummaryhtml);
    	        					body="";
    	        				}

    	            		}
    	            	}
        				
        				
    	            }
    	            
        			
				}
				else
				{
					//System.out.println("Sheet Name: "+apiArray1);
					
		            for (int i = 0; i < wb.getNumberOfSheets(); i++) {

		            		if(wb.getSheetName(i).equals(apiArray1))
		            		{
		            			
		            			body=HTMLMakerforAll(body, wb, i);
		        				break;
		        				
		            		}
		            		else
		            		{
    	        				if(wb.getSheetName(i).equals("Summary"))
    	        				{

    	        					body=HTMLMakerforSummary("",wb,i);
    	        					writingSummary(headersummaryhtml+body+footersummaryhtml);
    	        					body="";
    	        				}
		            		}
	    				
	    				
		            }
		            
				}
				

			    
			}

            fs.close();
		    writing(headerhtml+body+footerhtml);
			
		} catch(Exception ioe) {
		    ioe.printStackTrace();
		}

		
	}
	
    @SuppressWarnings("resource")
	public static void writing(String wget) {
        try {
        	

        	File WFile = new File(VariableModule.driverlog+"/InputFiles"+VariableModule.indexFile);
            FileWriter fw1 = new FileWriter(WFile, false);
            fw1.flush();
            System.out.println("File truncated");
            
        	FileWriter fw = new FileWriter(WFile,true);
/*            FileOutputStream is = new FileOutputStream(WFile);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            Writer w = new BufferedWriter(osw);*/
        	
        	if(wget!=null)
        	{
        	fw.write("\n"+wget+"\n");
        	}
            //fw.write(wget);
            //fw.write("\n");
            fw.close();
        } catch (IOException e) {
            System.err.println("Problem writing to the file Output.txt");
        }
    }
    
    @SuppressWarnings("resource")
	public static void writingSummary(String wget) {
        try {
        	
        	File WFile = new File(VariableModule.driverlog+"/InputFiles"+VariableModule.SummaryFile);
            FileWriter fw1 = new FileWriter(WFile, false);
            fw1.flush();
            System.out.println("File truncated");
            
        	FileWriter fw = new FileWriter(WFile,true);
        	
        	if(wget!=null)
        	{
        	fw.write("\n"+wget+"\n");
        	}
            //fw.write(wget);
            //fw.write("\n");
            fw.close();
        } catch (IOException e) {
            System.err.println("Problem writing to the file Output.txt");
        }
    }
    
    
	public static String JsonBuilder(HashMap<String, String> chash)
	{
		//System.out.println("Size: "+chash.size());
		
		JSONObject jo = new JSONObject();
		
	    for (String i : chash.keySet()) {
	    	  jo.put(i, chash.get(i));
	    	}
	    
	    return jo.toString();
	}
	
	public static String jsonMaker(String input, String path)
	{
		String data="";
		String result=",";
		String[] pathValue;
		JSONArray arr;
		//String param="message.en";
		

		
		if(input.indexOf("Error")!=-1)
		{
			result=result+input;
		}
		else
		{
			JSONObject obj = new JSONObject(input);
			if(path.contains(","))
			{
				pathValue=path.split(",");
				path=pathValue[0];
				String[] arrayData=pathValue[1].split("\\.");
				if(obj.getJSONObject(arrayData[0]).length() != 0)
				{
					arr = obj.getJSONObject(arrayData[0]).getJSONArray(arrayData[1]);
					result=String.valueOf(arr.length())+",";
				}

			}
			if(path.indexOf(".")!=-1)
			{
				//System.out.println("Dot Found");
				String[] sepa=path.split("\\.");
				data = obj.getJSONObject(sepa[0]).getString(sepa[1]);
			}
			else
			{
				data = obj.getString(path);
			}


				result=result+data;

			
		}

        //System.out.println("Get Data: "+result);
        
        /*
        
		        String json="{
		   "pageInfo": {
		         "pageName": "Homepage",
		         "logo": "https://www.example.com/logo.jpg"
		    },
		    "posts": [
		         {
		              "post_id": "0123456789",
		              "actor_id": "1001",
		              "author_name": "Jane Doe",
		              "post_title": "How to parse JSON in Java",
		              "comments": [],
		              "time_of_post": "1234567890"
		         }
		    ]
		}";
		
        JSONObject obj = new JSONObject(json);
        String pageName = obj.getJSONObject("pageInfo").getString("pageName");

        System.out.println(pageName);

        JSONArray arr = obj.getJSONArray("posts");
        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("post_id");
            System.out.println(post_id);
        }
	    */
        
        return result;
	}
	
	public static void CreateOrder()
	{
		
		try {
			String header="eyJhbGciOiJSUzI1NiJ9.eyJ1c2VyIjp7ImlkIjo4MywiZmlyc3ROYW1lIjoiTWl6YW51ciIsImxhc3ROYW1lIjoiUmFobWFuIiwiZW1haWwiOiJ0QHAuY29tIiwiZ2VuZGVyIjoxLCJwaG9uZSI6IjAxMjM0NTY3ODkiLCJwaG90byI6Imh0dHBzOi8vczMuYXAtc291dGhlYXN0LTEuYW1hem9uYXdzLmNvbS9kZXYucHJlZmVleC5jb20vMTU2NDU1NTU4MDI1NF9JTUdfMjAxOTA3MjlfMjIwNDE1LmpwZyIsImRvYiI6IjE5ODMtMDctMTIiLCJhZGRyZXNzIjoiZ3cgYXVhamEgYWlhIHN1d25xYmFpYSBzIGFqYSB3YmFqYSB3aXdvcSAgYWpzamEgIHdpa3EiLCJjb3VudHJ5IjoiQmFuZ2xhZGVzaCIsImNyZWF0ZWRBdCI6IjIwMTktMDctMDdUMDQ6NTQ6MDIuMDAwWiIsInVwZGF0ZWRBdCI6IjIwMTktMDctMzFUMDY6NDY6MjIuMDAwWiIsImJ1c2luZXNzSWQiOm51bGwsInVzZXJUeXBlIjp7ImlkIjoxLCJuYW1lIjoiRU5EX1VTRVIifSwidXNlclN0YXR1cyI6eyJpZCI6MSwibmFtZSI6IkFDVElWRSJ9fSwiaWF0IjoxNTY0NTU5OTA5LjQyMiwiZXhwIjoxNTY5NzQzOTA5LjQyMn0.nqx0B847qKthaNqBzgYUlH7oHBjVRBVr1pGUO_TG0cEE2sxI9g2n3zWab82AzO1HruTWxRgvc0MZIwayDTdPpQ";
			//t@p.com header
			
			String input="{\r\n" + 
					"	\"businessId\" : 1,\r\n" + 
					"	\"scheduledAt\": 1596304800000,\r\n" + 
					"	\"foods\": [ {\r\n" + 
					"		\"foodMenuId\": 7,\r\n" + 
					"		\"unitCount\" : 2,\r\n" + 
					"		\"addons\" : [ {\r\n" + 
					"			\"addonId\" : 4,\r\n" + 
					"			\"addonOptionId\" : 7,\r\n" + 
					"			\"unitCount\" : 1\r\n" + 
					"		}, {\r\n" + 
					"			\"addonId\" : 6,\r\n" + 
					"			\"addonOptionId\" : 13,\r\n" + 
					"			\"unitCount\" : 2\r\n" + 
					"		} ]\r\n" + 
					"	},\r\n" + 
					"	{\r\n" + 
					"		\"foodMenuId\": 19,\r\n" + 
					"		\"unitCount\" : 3,\r\n" + 
					"		\"addons\" : [ {\r\n" + 
					"			\"addonId\" : 40,\r\n" + 
					"			\"addonOptionId\" : 67,\r\n" + 
					"			\"unitCount\" : 1\r\n" + 
					"		} ]\r\n" + 
					"	}]\r\n" + 
					"}";
			String[] output=MyMethods.postAPI("/api/orders",input,2,header);
			
			/*
			if(output[0].contains("Order created successfully"))
			{
				
			}
			*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static String GetBusinessID()
	{

		int value = 0;
		try {
			String header=VariableModule.Enduser_Token1;
			//t@p.com header
			
			String[] input=MyMethods.getAPI("/api/users/me/orders?offset=0&limit=10&status=PENDING",2,header);
			JSONArray arr;

			JSONObject obj = new JSONObject(input[0]);
			arr = obj.getJSONObject("data").getJSONArray("orders");
			value = arr.getJSONObject(0).getInt("id");

	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
        //System.out.println("Result: "+value);



    
        return String.valueOf(value);

	}
	
	public static void PickedaTicket(String TicketID)
	{

		try {
			String header="eyJhbGciOiJSUzI1NiJ9.eyJ1c2VyIjp7ImlkIjozNTQsImZpcnN0TmFtZSI6bnVsbCwibGFzdE5hbWUiOm51bGwsImVtYWlsIjoibXVzZmlxLnRlc3RAcHJlZmVleC5jb20iLCJnZW5kZXIiOm51bGwsInBob25lIjpudWxsLCJwaG90byI6bnVsbCwiZG9iIjpudWxsLCJhZGRyZXNzIjpudWxsLCJjb3VudHJ5IjpudWxsLCJjcmVhdGVkQXQiOiIyMDE5LTA3LTMwVDEwOjA5OjI0LjAwMFoiLCJ1cGRhdGVkQXQiOiIyMDE5LTA3LTMwVDEwOjA5OjI0LjAwMFoiLCJidXNpbmVzc0lkIjpudWxsLCJ1c2VyVHlwZSI6eyJpZCI6NiwibmFtZSI6IkNVU1RPTUVSX1NVUFBPUlQifSwidXNlclN0YXR1cyI6eyJpZCI6MSwibmFtZSI6IkFDVElWRSJ9fSwiaWF0IjoxNTY0NjU3MDI1LjQ3NCwiZXhwIjoxNTY5ODQxMDI1LjQ3NH0.d4j89k5JehZH18__bizXgCPn9oY3Y-e02fM2aICCx5-53VC9tS4tsUpm6D-aJ-0EOdfvnyO3uM3O7pqMCRco2A";
			//musfiq.test@prefeex.com header
			
			String[] input=MyMethods.getAPI("/api/tickets/"+TicketID+"/pick",2,header);


	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	}
	
	public static String GetTicketID(String orderID)
	{
		
		String header=VariableModule.Support_Token1; 
		//musfiq.test@prefeex.com header
		String input="{\r\n" + 
				" \"statuses\": [\"PENDING\"],\r\n" + 
				" \"types\": []\r\n" + 
				"}";
		String[] output=MyMethods.postAPI("/api/tickets/filtered?offset=0&limit=10",input,2,header);
		
		JSONArray arr;
		int ticketvalue = 0;
		//String param="message.en";
		

			JSONObject obj = new JSONObject(output[0]);
			arr = obj.getJSONObject("data").getJSONArray("tickets");
	        for (int i = 0; i < arr.length(); i++) {
	        	int busivalue = arr.getJSONObject(i).getInt("orderId");
	        	if(orderID.equals(String.valueOf(busivalue)))
	        	{
	        		ticketvalue = arr.getJSONObject(i).getInt("id");
	        		break;
	        	}
	        	else
	        	{
	        		continue;
	        	}
	        }
			
			
            //System.out.println("Result: "+value);



        
        return String.valueOf(ticketvalue);
	}
	
	public static String GetOrderStatus(String orderID)
	{
		String status="";
		try {
			String header="eyJhbGciOiJSUzI1NiJ9.eyJ1c2VyIjp7ImlkIjo4MywiZmlyc3ROYW1lIjoiTWl6YW51ciIsImxhc3ROYW1lIjoiUmFobWFuIiwiZW1haWwiOiJ0QHAuY29tIiwiZ2VuZGVyIjoxLCJwaG9uZSI6IjAxMjM0NTY3ODkiLCJwaG90byI6Imh0dHBzOi8vczMuYXAtc291dGhlYXN0LTEuYW1hem9uYXdzLmNvbS9kZXYucHJlZmVleC5jb20vMTU2NDU1NTU4MDI1NF9JTUdfMjAxOTA3MjlfMjIwNDE1LmpwZyIsImRvYiI6IjE5ODMtMDctMTIiLCJhZGRyZXNzIjoiZ3cgYXVhamEgYWlhIHN1d25xYmFpYSBzIGFqYSB3YmFqYSB3aXdvcSAgYWpzamEgIHdpa3EiLCJjb3VudHJ5IjoiQmFuZ2xhZGVzaCIsImNyZWF0ZWRBdCI6IjIwMTktMDctMDdUMDQ6NTQ6MDIuMDAwWiIsInVwZGF0ZWRBdCI6IjIwMTktMDctMzFUMDY6NDY6MjIuMDAwWiIsImJ1c2luZXNzSWQiOm51bGwsInVzZXJUeXBlIjp7ImlkIjoxLCJuYW1lIjoiRU5EX1VTRVIifSwidXNlclN0YXR1cyI6eyJpZCI6MSwibmFtZSI6IkFDVElWRSJ9fSwiaWF0IjoxNTY0NTU5OTA5LjQyMiwiZXhwIjoxNTY5NzQzOTA5LjQyMn0.nqx0B847qKthaNqBzgYUlH7oHBjVRBVr1pGUO_TG0cEE2sxI9g2n3zWab82AzO1HruTWxRgvc0MZIwayDTdPpQ";
			//t@p.com header
			
			String[] input=MyMethods.getAPI("/api/orders/"+orderID,2,header);

			

				JSONObject obj = new JSONObject(input[0]);
				status = obj.getJSONObject("data").getJSONObject("order").getJSONObject("orderStatus").getString("name");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
            //System.out.println("Result: "+value);



        
        return status;
	}
	
	public static String GetTicketStatus(String ticketID)
	{

		String status="";
		try {
			String header="eyJhbGciOiJSUzI1NiJ9.eyJ1c2VyIjp7ImlkIjozNTQsImZpcnN0TmFtZSI6bnVsbCwibGFzdE5hbWUiOm51bGwsImVtYWlsIjoibXVzZmlxLnRlc3RAcHJlZmVleC5jb20iLCJnZW5kZXIiOm51bGwsInBob25lIjpudWxsLCJwaG90byI6bnVsbCwiZG9iIjpudWxsLCJhZGRyZXNzIjpudWxsLCJjb3VudHJ5IjpudWxsLCJjcmVhdGVkQXQiOiIyMDE5LTA3LTMwVDEwOjA5OjI0LjAwMFoiLCJ1cGRhdGVkQXQiOiIyMDE5LTA3LTMwVDEwOjA5OjI0LjAwMFoiLCJidXNpbmVzc0lkIjpudWxsLCJ1c2VyVHlwZSI6eyJpZCI6NiwibmFtZSI6IkNVU1RPTUVSX1NVUFBPUlQifSwidXNlclN0YXR1cyI6eyJpZCI6MSwibmFtZSI6IkFDVElWRSJ9fSwiaWF0IjoxNTY0NjU3MDI1LjQ3NCwiZXhwIjoxNTY5ODQxMDI1LjQ3NH0.d4j89k5JehZH18__bizXgCPn9oY3Y-e02fM2aICCx5-53VC9tS4tsUpm6D-aJ-0EOdfvnyO3uM3O7pqMCRco2A";
			//musfiq.test@prefeex.com header
			
			String[] input=MyMethods.getAPI("/api/tickets/"+ticketID,2,header);

			

				JSONObject obj = new JSONObject(input[0]);
				status = obj.getJSONObject("data").getJSONObject("ticket").getJSONObject("ticketStatus").getString("name");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return status;
	}
	
	public static void AssignTicket(String ticketID)
	{
		try {
			String header="eyJhbGciOiJSUzI1NiJ9.eyJ1c2VyIjp7ImlkIjozNTQsImZpcnN0TmFtZSI6bnVsbCwibGFzdE5hbWUiOm51bGwsImVtYWlsIjoibXVzZmlxLnRlc3RAcHJlZmVleC5jb20iLCJnZW5kZXIiOm51bGwsInBob25lIjpudWxsLCJwaG90byI6bnVsbCwiZG9iIjpudWxsLCJhZGRyZXNzIjpudWxsLCJjb3VudHJ5IjpudWxsLCJjcmVhdGVkQXQiOiIyMDE5LTA3LTMwVDEwOjA5OjI0LjAwMFoiLCJ1cGRhdGVkQXQiOiIyMDE5LTA3LTMwVDEwOjA5OjI0LjAwMFoiLCJidXNpbmVzc0lkIjpudWxsLCJ1c2VyVHlwZSI6eyJpZCI6NiwibmFtZSI6IkNVU1RPTUVSX1NVUFBPUlQifSwidXNlclN0YXR1cyI6eyJpZCI6MSwibmFtZSI6IkFDVElWRSJ9fSwiaWF0IjoxNTY0NjU3MDI1LjQ3NCwiZXhwIjoxNTY5ODQxMDI1LjQ3NH0.d4j89k5JehZH18__bizXgCPn9oY3Y-e02fM2aICCx5-53VC9tS4tsUpm6D-aJ-0EOdfvnyO3uM3O7pqMCRco2A";
			//musfiq.test@prefeex.com header
			
			String[] input=MyMethods.getAPI("/api/tickets/"+ticketID+"/assignto/354",2,header);

			PickedaTicket(ticketID);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	public static void ConfirmedOrder(String orderID)
	{
		try {
			String header="eyJhbGciOiJSUzI1NiJ9.eyJ1c2VyIjp7ImlkIjo4MywiZmlyc3ROYW1lIjoiTWl6YW51ciIsImxhc3ROYW1lIjoiUmFobWFuIiwiZW1haWwiOiJ0QHAuY29tIiwiZ2VuZGVyIjoxLCJwaG9uZSI6IjAxMjM0NTY3ODkiLCJwaG90byI6Imh0dHBzOi8vczMuYXAtc291dGhlYXN0LTEuYW1hem9uYXdzLmNvbS9kZXYucHJlZmVleC5jb20vMTU2NDU1NTU4MDI1NF9JTUdfMjAxOTA3MjlfMjIwNDE1LmpwZyIsImRvYiI6IjE5ODMtMDctMTIiLCJhZGRyZXNzIjoiZ3cgYXVhamEgYWlhIHN1d25xYmFpYSBzIGFqYSB3YmFqYSB3aXdvcSAgYWpzamEgIHdpa3EiLCJjb3VudHJ5IjoiQmFuZ2xhZGVzaCIsImNyZWF0ZWRBdCI6IjIwMTktMDctMDdUMDQ6NTQ6MDIuMDAwWiIsInVwZGF0ZWRBdCI6IjIwMTktMDctMzFUMDY6NDY6MjIuMDAwWiIsImJ1c2luZXNzSWQiOm51bGwsInVzZXJUeXBlIjp7ImlkIjoxLCJuYW1lIjoiRU5EX1VTRVIifSwidXNlclN0YXR1cyI6eyJpZCI6MSwibmFtZSI6IkFDVElWRSJ9fSwiaWF0IjoxNTY0NTU5OTA5LjQyMiwiZXhwIjoxNTY5NzQzOTA5LjQyMn0.nqx0B847qKthaNqBzgYUlH7oHBjVRBVr1pGUO_TG0cEE2sxI9g2n3zWab82AzO1HruTWxRgvc0MZIwayDTdPpQ";
			//t@p.com header
			
			String[] input=MyMethods.getAPI("/api/orders/"+orderID+"/confirm",2,header);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	

	public static void JsonParserforToken(String jsonStr)
	{
        JSONObject obj = new JSONObject(jsonStr);
        String tok = obj.getJSONObject("data").getString("token");
        VariableModule.token=tok;

        System.out.println("Token: "+VariableModule.token);
        
	}
	
	public static String JSONArrayProcessor(String output, String token, String comwith, String need)
	{

		int value = 0;
		String result="";
		try {
			

				JSONArray arr;
				String[] tokenArray=token.split("\\."); //data.orders.id
				String[] tokenArray1=need.split("\\."); //data.orders.id
				JSONObject obj = new JSONObject(output);
				arr = obj.getJSONObject(tokenArray[0]).getJSONArray(tokenArray[1]);
				System.out.println("Array Len: "+arr.length());
				for(int i=0;i<arr.length();i++)
				{
					result = arr.getJSONObject(i).getString(tokenArray[2]);
					if(result.equals(comwith))
					{
						value = arr.getJSONObject(i).getInt(tokenArray1[2]);
						result=String.valueOf(value);
						System.out.println("ID Found: "+result);
						break;
					}
					else
					{
						continue;
					}

				}
				/*
				if(valueType.equals("intt")) //valueType=intt or str
				{
					value = arr.getJSONObject(position).getInt(tokenArray[2]);
					result=String.valueOf(value);
				}
				else
				{
					result = arr.getJSONObject(position).getString(tokenArray[2]);
				}
				*/
			


	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
        //System.out.println("Result: "+value);



    
        return result;

	}
	
	
	public static String AddonOptionProcessor(String output, String token, String comwith, String need,String token1, String comwith1)
	{

		int value = 0;
		String result="";
		String result1="";
		try {
			

				JSONArray arr;
				JSONArray arr1;
				String[] tokenArray=token.split("\\."); //data.addons.addonOptions.name
				String[] tokenArray1=need.split("\\."); //data.addons.addonOptions.name
				String[] tokenArray2=token1.split("\\."); //data.addons.addonOptions.name
				JSONObject obj = new JSONObject(output);
				arr = obj.getJSONObject(tokenArray[0]).getJSONArray(tokenArray[1]);
				//System.out.println("Array Len: "+arr.length());
				for(int i=0;i<arr.length();i++)
				{

					result = arr.getJSONObject(i).getString(tokenArray2[2]);
					if(result.equals(comwith1))
					{
						arr1 = arr.getJSONObject(i).getJSONArray(tokenArray[2]);
						//System.out.println("Array Len1: "+arr1.length());
						for(int j=0;j<arr1.length();j++)
						{
							result = arr1.getJSONObject(j).getString(tokenArray[3]);
							//System.out.println("Name Found: "+result);
							
							if(result.equals(comwith))
							{
									value = arr1.getJSONObject(j).getInt(tokenArray1[3]);
									result1=String.valueOf(value);
									//System.out.println("ID Found: "+result1);
									break;
							}
							else
							{
								continue;
							}
							
						}
						break;
					}
					else
					{
						continue;
					}

						

					
					

				}
			


	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}




    
        return result1;

	}
	
	public static String TicketProcessor(String output, String token, String comwith, String need)
	{

		int value = 0;
		int result1;
		String result="";
		
		try {
			

				JSONArray arr;
				String[] tokenArray=token.split("\\."); //data.orders.id
				String[] tokenArray1=need.split("\\."); //data.orders.id
				JSONObject obj = new JSONObject(output);
				arr = obj.getJSONObject(tokenArray[0]).getJSONArray(tokenArray[1]);
				System.out.println("Array Len: "+arr.length());
				for(int i=0;i<arr.length();i++)
				{
					result1= arr.getJSONObject(i).getInt(tokenArray[2]);
					if(result1==Integer.valueOf(comwith))
					{
						value = arr.getJSONObject(i).getInt(tokenArray1[2]);
						result=String.valueOf(value);
						System.out.println("ID Found: "+result);
						break;
					}
					else
					{
						continue;
					}

				}
				/*
				if(valueType.equals("intt")) //valueType=intt or str
				{
					value = arr.getJSONObject(position).getInt(tokenArray[2]);
					result=String.valueOf(value);
				}
				else
				{
					result = arr.getJSONObject(position).getString(tokenArray[2]);
				}
				*/
			


	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
        //System.out.println("Result: "+value);



    
        return result;

	}
	
	
	public static String JSONObjectProcessor(String output, String token)
	{

		int value = 0;
		String result="";
		try {
			
				String[] tokenArray=token.split("."); //data.token
		        JSONObject obj = new JSONObject(output);
		        result = obj.getJSONObject(tokenArray[0]).getString(tokenArray[1]);
			


	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
        //System.out.println("Result: "+value);



    
        return result;

	}
	
	
	public static void  JsonParser(String jsonStr)
	{
		
		JSONArray jsonarray = new JSONArray(jsonStr);
		for (int i = 0; i < jsonarray.length(); i++) {
		    JSONObject jsonobject = jsonarray.getJSONObject(i);
		    for (int j = 0; j < jsonobject.length(); j++) {
			    String name = jsonobject.getString("name");
			    System.out.println("Name: "+name);
			    String price = jsonobject.getString("price");
			    System.out.println("Price: "+price);
		    }

		}
	    	
	}

	public static String[] getAPI(String apiurl, int header, String hdata) throws Exception {

		String[] myArray= new String[2];
		try {
			apiurl = VariableModule.adminURL+apiurl;
			System.out.println("URL: "+apiurl);
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(apiurl);

			request.addHeader("Authorization", hdata);
			HttpResponse response = client.execute(request);

			//System.out.println("\nSending 'GET' request to URL : " + apiurl);
			myArray[1]= String.valueOf(response.getStatusLine().getStatusCode());
			//System.out.println("Response Code : " + myArray[1]);
			               

			BufferedReader rd = new BufferedReader(
			               new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

			myArray[0]=result.toString();
	        
	        
			MyMethods.PrintMe("API Response: ", myArray[0]);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return myArray;
	}

	
	public static String[] postAPI(String apiurl, String input, int header, String hdata)
	{
				String result = "";
				boolean connected = false;
				String[] myArray= new String[2];
				try {
					apiurl = VariableModule.adminURL+apiurl;
					System.out.println("URL: "+apiurl);
					URL url = new URL(apiurl);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					
					//conn.setConnectTimeout(5000);
					conn.setRequestProperty("Content-Type", "application/json");
					conn.setRequestProperty("Authorization", hdata);

					conn.setDoOutput(true);
					conn.setDoInput(true);
					conn.setRequestMethod("POST");
					
					conn.connect();

					OutputStream os = conn.getOutputStream();
					os.write(input.getBytes("UTF-8"));
					os.close();
					
					int res=conn.getResponseCode();
					System.out.println("Resp: "+res);
					
					/*
					if(res!=200)
					{
						result="HTTP Response Code: "+String.valueOf(res);
						MyMethods.PrintMe("API Response: ", result);
					}
					*/
					
					
			        switch (conn.getResponseCode()) 
			        {
			            case HttpURLConnection.HTTP_OK:
			            	//System.out.println(" **OK**");
			                connected = true;
			                break; // fine, go on
			            case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
			            	//System.out.println(" **gateway timeout**");
			                break;// retry
			            case HttpURLConnection.HTTP_UNAVAILABLE:
			            	//System.out.println("**unavailable**");
			                break;// retry, server is unstable
			            default:
			            	//System.out.println(" **unknown response code**.");
			                break; // abort
			        }
			        
			        System.out.println("Connection: "+connected);
					
			        // read the response
			        if(res==200)
			        {
						InputStream in = new BufferedInputStream(conn.getInputStream());
						result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
						MyMethods.PrintMe("API Response: ", result);
						in.close();
			        }
			        else if(res==201)
			        {
						InputStream in = new BufferedInputStream(conn.getInputStream());
						result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
						MyMethods.PrintMe("API Response: ", result);
						in.close();
			        }
			        else
			        {
				        InputStream error= new BufferedInputStream(conn.getErrorStream());
				        result = org.apache.commons.io.IOUtils.toString(error, "UTF-8");
				        MyMethods.PrintMe("API Response: ", result);
				        error.close();
			        }
			        
			        myArray[0]=result;
			        myArray[1]=String.valueOf(res);
			        
					
					
					
					
					conn.disconnect();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					//System.err.println("Problem in executing web url");
					//e.printStackTrace();
					
				}
	
	            return myArray;
	    }

	public static String[] delAPI(String apiurl, String input, int header, String hdata)
	{
				String result = "";
				boolean connected = false;
				String[] myArray= new String[2];
				try {
					apiurl = VariableModule.adminURL+apiurl;
					System.out.println("URL: "+apiurl);
					URL url = new URL(apiurl);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					
					//conn.setConnectTimeout(5000);
					conn.setRequestProperty("Content-Type", "application/json");
					conn.setRequestProperty("Authorization", hdata);

					conn.setDoOutput(true);
					conn.setDoInput(true);
					conn.setRequestMethod("DELETE");
					
					conn.connect();

					OutputStream os = conn.getOutputStream();
					os.write(input.getBytes("UTF-8"));
					os.close();
					
					int res=conn.getResponseCode();
					System.out.println("Resp: "+res);
					
					/*
					if(res!=200)
					{
						result="HTTP Response Code: "+String.valueOf(res);
						MyMethods.PrintMe("API Response: ", result);
					}
					*/
					
					
			        switch (conn.getResponseCode()) 
			        {
			            case HttpURLConnection.HTTP_OK:
			            	//System.out.println(" **OK**");
			                connected = true;
			                break; // fine, go on
			            case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
			            	//System.out.println(" **gateway timeout**");
			                break;// retry
			            case HttpURLConnection.HTTP_UNAVAILABLE:
			            	//System.out.println("**unavailable**");
			                break;// retry, server is unstable
			            default:
			            	//System.out.println(" **unknown response code**.");
			                break; // abort
			        }
			        
			        System.out.println("Connection: "+connected);
					
			        // read the response
			        if(res==200)
			        {
						InputStream in = new BufferedInputStream(conn.getInputStream());
						result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
						MyMethods.PrintMe("API Response: ", result);
						in.close();
			        }
			        else if(res==201)
			        {
						InputStream in = new BufferedInputStream(conn.getInputStream());
						result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
						MyMethods.PrintMe("API Response: ", result);
						in.close();
			        }
			        else
			        {
				        InputStream error= new BufferedInputStream(conn.getErrorStream());
				        result = org.apache.commons.io.IOUtils.toString(error, "UTF-8");
				        MyMethods.PrintMe("API Response: ", result);
				        error.close();
			        }
			        
			        myArray[0]=result;
			        myArray[1]=String.valueOf(res);
			        
					
					
					
					
					conn.disconnect();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					//System.err.println("Problem in executing web url");
					//e.printStackTrace();
					
				}
	
	            return myArray;
	    }

	public static String[] UploadAPI(String fileName, String hdata)
	{
				String result = "";
				boolean connected = false;
				String[] myArray= new String[2];
				try {
					String apiurl = VariableModule.adminURL+"/api/upload";
					//System.out.println("URL: "+apiurl);
					URL url = new URL(apiurl);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					
					FileBody fileBody = new FileBody(new File(fileName));
					MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.STRICT);
					multipartEntity.addPart("files", fileBody);
					
					//conn.setConnectTimeout(5000);
					conn.setRequestProperty("Content-Type", multipartEntity.getContentType().getValue());//"multipart/form-data"
					conn.setRequestProperty("Authorization", hdata);

					conn.setDoOutput(true);
					conn.setDoInput(true);
					conn.setRequestMethod("POST");
					
					conn.connect();

					OutputStream out = conn.getOutputStream();
					try {
					    multipartEntity.writeTo(out);
					} finally {
					    out.close();
					}
					
					int res=conn.getResponseCode();
					System.out.println("Resp: "+res);
					
					/*
					if(res!=200)
					{
						result="HTTP Response Code: "+String.valueOf(res);
						MyMethods.PrintMe("API Response: ", result);
					}
					*/
					
					
			        switch (conn.getResponseCode()) 
			        {
			            case HttpURLConnection.HTTP_OK:
			            	//System.out.println(" **OK**");
			                connected = true;
			                break; // fine, go on
			            case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
			            	//System.out.println(" **gateway timeout**");
			                break;// retry
			            case HttpURLConnection.HTTP_UNAVAILABLE:
			            	//System.out.println("**unavailable**");
			                break;// retry, server is unstable
			            default:
			            	//System.out.println(" **unknown response code**.");
			                break; // abort
			        }
			        
			        System.out.println("Connection: "+connected);
					
			        // read the response
			        if(res==200)
			        {
						InputStream in = new BufferedInputStream(conn.getInputStream());
						result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
						MyMethods.PrintMe("API Response: ", result);
						in.close();
			        }
			        else if(res==201)
			        {
						InputStream in = new BufferedInputStream(conn.getInputStream());
						result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
						MyMethods.PrintMe("API Response: ", result);
						in.close();
			        }
			        else
			        {
				        InputStream error= new BufferedInputStream(conn.getErrorStream());
				        result = org.apache.commons.io.IOUtils.toString(error, "UTF-8");
				        MyMethods.PrintMe("API Response: ", result);
				        error.close();
			        }
			        
			        myArray[0]=result;
			        myArray[1]=String.valueOf(res);
			        
	
					conn.disconnect();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					//System.err.println("Problem in executing web url");
					//e.printStackTrace();
					
				}
	
	            return myArray;
	    }


	public static String[] putAPI(String apiurl, String input, int header, String hdata) throws IOException, JSONException {
		
		String result="";
		boolean connected = false;
		String[] myArray= new String[2];
		
		try {
			apiurl = VariableModule.adminURL+apiurl;
			System.out.println("URL: "+apiurl);
			URL url = new URL(apiurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", hdata);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("PUT");
	
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes("UTF-8"));
			os.close();
	
			int res=conn.getResponseCode();
			//System.out.println("Resp: "+res);
			
			switch (conn.getResponseCode()) 
			{
			    case HttpURLConnection.HTTP_OK:
			    	//System.out.println(" **OK**");
			        connected = true;
			        break; // fine, go on
			    case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
			    	//System.out.println(" **gateway timeout**");
			        break;// retry
			    case HttpURLConnection.HTTP_UNAVAILABLE:
			    	//System.out.println("**unavailable**");
			        break;// retry, server is unstable
			    default:
			    	//System.out.println(" **unknown response code**.");
			        break; // abort
			}
			
			System.out.println("Connection: "+connected);
			
			// read the response
			if(res==200)
			{
				InputStream in = new BufferedInputStream(conn.getInputStream());
				result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
				in.close();
			}
			else
			{
			    InputStream error= new BufferedInputStream(conn.getErrorStream());
			    result = org.apache.commons.io.IOUtils.toString(error, "UTF-8");
			    
			    error.close();
			}
			myArray[0]=result;
			myArray[1]=String.valueOf(res);
			
			MyMethods.PrintMe("API Response: ", result);
			
			conn.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    return myArray;
	}


	public static boolean isJsonValid(String test) {
		/*
	    boolean isValid = false;
	    try {
	        JSONSerializer.toJSON(test);
	        isValid = true;
	    } catch (JSONException je) {
	        isValid = false;
	    }
	    return isValid;
		 */
	
	    try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
			JsonFactory factory = mapper.getFactory();
			com.fasterxml.jackson.core.JsonParser parser = factory.createParser(test);
			JsonNode jsonObj = mapper.readTree(parser);
			//System.out.println("JSON: "+jsonObj.toString());
			return true;
		} catch (JsonParseException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	    
		/*
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        mapper.readTree(test);
	        return true;
	     } catch (IOException e) {
	    	 System.err.println("JSON Problem: "+e);
	        return false;
	     }
	    */
		/*
		   try {
		       new JSONObject(test);
		   } catch (JSONException ex) {
		       try {
		           new JSONArray(test);
		       } catch (JSONException ex1) {
		           return false;
		       }
		   }
		   return true;
		   */
		}

	public static boolean isJSONvalid(WebDriver driver, String input)
	{
		String json="";
	    try {
			driver.get("https://jsonlint.com/");
			MyMethods.Sleep(3000);
			
			//driver.findElement(By.xpath("/html/body/div[3]/form/div[1]/div[1]/textarea")).click();
			driver.findElement(By.xpath("/html/body/div[3]/form/div[1]/div[1]/textarea")).sendKeys(input);
			
			driver.findElement(By.cssSelector(".validate > button:nth-child(1)")).click();
			MyMethods.Sleep(2000);
			
			json=driver.findElement(By.id("result")).getText();
			
			driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
			if(json.indexOf("Valid JSON")!=-1)
			{
				 System.out.println("JSON is valid!!!");
				return true;
			}
			else
			{
				 System.out.println("JSON is invalid!!!");
				return false;
			}

	}


}
