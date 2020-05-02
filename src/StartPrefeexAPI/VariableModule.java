package StartPrefeexAPI;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.TrayIcon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.security.auth.spi.LoginModule;
import javax.swing.SwingUtilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
//import org.openqa.selenium.server.browserlaunchers.DrivenSeleniumLauncher;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import LoginAPI.MyLoginAPI;
import MethodsRepo.MyMethods;

public class VariableModule {
	// ActionModule actiondo = new ActionModule();
	static Logger logVariableModule = Logger.getLogger(VariableModule.class.getName());
	static MySQLPINGModule ThreadMysqlPingdo = new MySQLPINGModule();
	// log.info("Error in: " + this.getClass.getName() + "at line #"+
	// this.getClass.getActualLine());



	//public static String driverlog = "D:/WorkSpace/PrefeexAPI/Configuration"; // linux=/usr/local/iTelTestAutomation //
	//public static String driverlog="";															// Windows=D:/WorkSpace/PrefeexAPI/Configuration
    //@SuppressWarnings("resource")
	static Scanner in = new Scanner(System.in); 
	public static String driverlog  = in.nextLine(); 
	public static String jsonStrTop1="{\r\n" + 
			"	\"statuses\": [{\r\n" + 
			"		\"id\": 1,\r\n" + 
			"		\"name\": \"PENDING\"\r\n" + 
			"	}, {\r\n" + 
			"		\"id\": 2,\r\n" + 
			"		\"name\": \"B2B_INPROGRESS\"\r\n" + 
			"	}, {\r\n" + 
			"		\"id\": 3,\r\n" + 
			"		\"name\": \"B2C_INPROGRESS\"\r\n" + 
			"	}]\r\n" + 
			"}";
	public static String jsonStrTop="{\r\n" + 
			"	\"data\": [";
	public static String jsonStrMid="{\r\n" + 
			"		\"ids\": [VARIDS],\r\n" + 
			"		\"task\": \"SHEET_NAME\"\r\n" + 
			"	}, ";
	public static String jsonStrMid1="{\r\n" + 
			"		\"ids\": [VARIDS],\r\n" + 
			"		\"task\": \"SHEET_NAME\"\r\n" + 
			"	}";
	public static String jsonStrBot="]\r\n" + 
			"}";
	public static String finalJSON=jsonStrTop;
	public static String sleepTime = readVariable("sleepTime", 1); // in minute
	public static String token = "";
	public static String isGUI = readVariable("isGUI", 1);
	public static String driverselection = readVariable("driverselection", 1); // 1=firefox, 2=chrome, 3=unitdriver

	public static String offsettime = readVariable("offsettime", 1);

	public static String InputFile = "";
	public static String SummaryFile = "";
	public static String indexFile = "";

	public static String offset;
	public static String limit;

	public static Connection Ownconn = null;
	public static String MySQLIP = readVariable("MySQLIP", 1); // "149.20.188.8";
	public static String DBName = readVariable("DBName", 1);// testautomation //SQADatabase
	public static String DBUser = readVariable("DBUser", 1);
	public static String DBPass = readVariable("DBPass", 1);

	public static String adminURL = readVariable("adminURL", 1);
	public static String adminuser = readVariable("adminuser", 1);
	public static String adminpass = readVariable("adminpass", 1);
	public static String isAllAPITest = readVariable("isAllAPITest", 1);

	public static String testingMode = readVariable("testingMode", 1);

	public static String[] OrderStatus = { "PENDING", "DECLINED", "APPROVED", "CONFIRMED", "CANCELED" };
	public static String[] TicketStatus = { "PENDING", "B2B_INPROGRESS", "WAITING_FOR_B2B", "B2C_PENDING",
			"B2C_INPROGRESS", "WAITING_FOR_B2C", "WAITING_FOR_RESOLVED", "RESOLVED" };

	public static String Enduser_Token1 = "";
	// t@p.com
	public static String Enduser_Token2 = "";
	// endu3@test.com
	public static String Admin_Token1 = "";
	// mizan@prefeex.com
	public static String Admin_Token2 = "";

	public static String Business_Token1 = "";
	// test@test.com
	public static String Business_Token2 = "";
	// mizan@p.com
	public static String Support_Token1 = "";
	// musfiq.test@prefeex.com
	public static String Support_Token2 = "";
	// pemnd@test.com

	public static int sheetno;
	public static String endUser1 = "enduser1-" + MyMethods.getCurrentDate() + "@enduser.com";
	public static String enduser1ID = "";
	public static String endUser2 = "enduser2-" + MyMethods.getCurrentDate() + "@enduser.com";
	public static String enduser2ID = "";

	public static String supportUser1 = "support1-" + MyMethods.getCurrentDate() + "@support.com";
	public static String supportUser1ID = "";
	public static String supportUser2 = "support2-" + MyMethods.getCurrentDate() + "@support.com";
	public static String supportUser2ID = "";

	public static String bussinessName1 = "bussiness1-" + MyMethods.getCurrentDate();
	public static String bussinessName1ID = "";
	public static String bussinessName2 = "bussiness2-" + MyMethods.getCurrentDate();
	public static String bussinessName2ID = "";
	public static String bussinessEmail1 = bussinessName1 + "@bussiness.com";
	public static String bussinessEmail2 = bussinessName2 + "@bussiness.com";
	public static String bussinessPhone1 = RandomNumberGen(8);
	public static String bussinessPhone2 = RandomNumberGen(8);

	public static String bussinessUser1 = "bussinessuser1-" + MyMethods.getCurrentDate() + "@bussinessuser.com";
	public static String bussinessUser1ID = "";
	public static String bussinessUserPhone1 = RandomNumberGen(8);
	public static String bussinessUser2 = "bussinessuser2-" + MyMethods.getCurrentDate() + "@bussinessuser.com";
	public static String bussinessUser2ID = "";
	public static String bussinessUserPhone2 = RandomNumberGen(8);
	
	public static String TagName1 = "Tag1-" + MyMethods.getCurrentDate();
	public static String TagName1ID = "";
	
	public static String FacilityName1 = "Facility1-" + MyMethods.getCurrentDate();
	public static String FacilityName1ID = "";
	
	public static String FoodTypeName1 = "FoodType1-" + MyMethods.getCurrentDate();
	//public static String FoodTypeName1ID = "";
	
	public static String fmStatus1 = "FoodStatus1-" + MyMethods.getCurrentDate();
	//public static String fmStatus1ID = "";
	
	public static String AddonTypeName1 = "AddonTypeName1-" + MyMethods.getCurrentDate();
	//public static String AddonTypeName1ID = "";
	
	public static String AddonStatus1 = "AddonStatus1-" + MyMethods.getCurrentDate();
	//public static String AddonStatus1ID = "";
	
	public static String FMiTem1 = "FMiTem1-" + MyMethods.getCurrentDate();
	public static String FMiTem1ID = "";
	
	public static String AddonName1 = "AddonName1-" + MyMethods.getCurrentDate();
	public static String AddonName1ID = "";
	public static String AddonOption1 = "AddonOption1-" + MyMethods.getCurrentDate();
	public static String AddonOption1ID = "";
	
	public static String Order1ID = "";
	public static String Ticket1ID = "";
	
	public static String NotifTitle1 = "NotificationTitle1-" + MyMethods.getCurrentDate();;
	public static String NotifTitle1ID = "";
	
	public static String EmailTemp1ID = "";
	public static String bankAccount1 = RandomNumberGen(16);
	public static String bankAccount1ID="";
	
	
	
	public static WebDriver DriverSelection(String flag) {
		WebDriver driver = null;
		if (flag.equals("1")) {
			/*
			 * System.setProperty("webdriver.firefox.marionette",driverlog+
			 * "/geckodriver_new.exe"); DesiredCapabilities capabilities =
			 * DesiredCapabilities.firefox(); capabilities.setCapability("marionette",true);
			 * //DesiredCapabilities capability = DesiredCapabilities.firefox(); driver =
			 * new FirefoxDriver();
			 */

			System.setProperty("webdriver.gecko.driver", driverlog + "/geckodriver_new.exe");
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability("marionette", true);
			driver = new FirefoxDriver(cap);
		} else if (flag.equals("2")) {
			System.setProperty("webdriver.chrome.driver", driverlog + "/chromedriver_new.exe");
			// DesiredCapabilities capability = DesiredCapabilities.firefox();
			// driver = new ChromeDriver();

			// Create prefs map to store all preferences
			Map<String, Object> prefs = new HashMap<String, Object>();

			// Put this into prefs map to switch off browser notification
			prefs.put("profile.default_content_setting_values.notifications", 2);

			// Create chrome options to set this prefs
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);

			if (VariableModule.isGUI.equals("2")) {
				options.addArguments("--headless");
			}

			// Now initialize chrome driver with chrome options which will switch off this
			// browser notification on the chrome browser
			driver = new ChromeDriver(options);
		} else {
			driver = new HtmlUnitDriver();
		}
		return driver;
	}

	// public static WebDriver driver= DriverSelection(driverselection);

	@SuppressWarnings("null")
	public static void StartModule() {

		try {
			// MyMethods.MysqlConnectOwn(Ownconn);
			// ThreadMysqlPingdo.setPriority(Thread.MAX_PRIORITY);
			// ThreadMysqlPingdo.start();
			//System.out.println("Location from VariableModule: "+driverlog);
	        
			for (int i = 3; i < 4; i++) {

				if (i == 0) {
					MyMethods.HeaderInitialization();
					InputFile = "/HTML/APIReportHTML.xlsx";
					SummaryFile = "/HTML/summaryreport.html";
					indexFile = "/HTML/index.html";
					System.out.println("HTML Process");
					doOperation();

					// MyMethods.UploadAPI("D:/FoodImages/Food.jpg",Enduser_Token1);
				} else if (i == 1) {
					MyMethods.HeaderInitialization();
					InputFile = "/Order/OrderProcess.xlsx";
					SummaryFile = "/Order/summaryreport.html";
					indexFile = "/Order/index.html";
					System.out.println("Order Process");
					doOrderProcessTest();
				} else if (i == 2) {
					MyMethods.HeaderInitialization();
					InputFile = "/Ticket/TicketProcess.xlsx";
					SummaryFile = "/Ticket/summaryreport.html";
					indexFile = "/Ticket/index.html";
					System.out.println("Ticket Process");
					doTicketProcessTest();
				}

				if (i == 3) {
					MyMethods.HeaderGenerator("Admin_Token1", VariableModule.adminuser, VariableModule.adminpass);
					InputFile = "/FINAL/APIReportFINAL.xlsx";
					SummaryFile = "/FINAL/summaryreport.html";
					indexFile = "/FINAL/index.html";
					System.out.println("FINAL Process");
					doFinalOperation();

				}
				System.out.println("Full API Test execution completed!!!");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static void doOperation() {
		try {
			HTMLProcess();
			/*
			 * if(testingMode.equals("1")) {
			 * 
			 * System.out.println("Local");
			 * 
			 * 
			 * try { MyMethods.copyFileUsingStream(); } catch (IOException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); } int
			 * row=(MyMethods.ExcelRowCount(testingMode)-1);
			 * System.out.println("Summary Rows: "+row);
			 * 
			 * //MyMethods.ExcelFileEmptyforSummary(testingMode, row);
			 * 
			 * 
			 * if(isAllAPITest.equals("1")) { String[]
			 * sheetName=MyMethods.ExcelFileSheetData(); for(int i=0;i<sheetno;i++) {
			 * if(!sheetName[i].equals("Summary")) {
			 * System.out.println("Sheet Name: "+sheetName[i]);
			 * MyLoginAPI.SingleAPITest(sheetName[i]); } } } else //isAllAPITest=2 {
			 * String[] apiArray=null; String apiArray1=readVariable("apiArray", 1);
			 * //System.out.println("Array: "+apiArray1); if(apiArray1.contains(",")) {
			 * apiArray=apiArray1.split(","); for(int i=0;i<apiArray.length;i++) {
			 * System.out.println("Sheet Name: "+apiArray[i]);
			 * MyLoginAPI.SingleAPITest(apiArray[i]); }
			 * 
			 * } else { System.out.println("Sheet Name: "+apiArray1);
			 * MyLoginAPI.SingleAPITest(apiArray1); }
			 * 
			 * 
			 * }
			 * 
			 * 
			 * 
			 * //MyLoginAPI.UserInfoAPITest(); //MyLoginAPI.UserInfoUpdateAPITest();
			 * System.out.println("API Test execution completed!!!");
			 * 
			 * } else if(testingMode.equals("2")) //Google sheet {
			 * 
			 * System.out.println("Google");
			 * 
			 * 
			 * try { MyMethods.copyFileUsingStream(); } catch (IOException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 * 
			 * //WebDriver driver= DriverSelection(driverselection);
			 * //downloadAPISheet(driver);
			 * 
			 * //System.out.println("Total Rows: "+MyMethods.ExcelRowCount(testingMode));
			 * //MyMethods.ExcelFileEmptyforSummary(testingMode,
			 * (MyMethods.ExcelRowCount(testingMode)-1));
			 * 
			 * 
			 * if(isAllAPITest.equals("1")) { String[]
			 * sheetName=MyMethods.ExcelFileSheetData();
			 * 
			 * for(int i=0;i<sheetno;i++) { if(!sheetName[i].equals("Summary")) {
			 * System.out.println("Sheet Name: "+sheetName[i]);
			 * MyLoginAPI.SingleAPITest(sheetName[i]); } } } else //isAllAPITest=2 {
			 * 
			 * String[] apiArray=null; String apiArray1=readVariable("apiArray", 1);
			 * //System.out.println("Array: "+apiArray1); if(apiArray1.contains(",")) {
			 * apiArray=apiArray1.split(","); for(int i=0;i<apiArray.length;i++) {
			 * System.out.println("Sheet Name: "+apiArray[i]);
			 * MyLoginAPI.SingleAPITest(apiArray[i]); }
			 * 
			 * } else { System.out.println("Sheet Name: "+apiArray1);
			 * MyLoginAPI.SingleAPITest(apiArray1); } }
			 * 
			 * 
			 * //uploadAPISheet(driver);
			 * 
			 * MyMethods.ExcelFileDeleteAction();
			 * 
			 * //driver.quit(); System.out.println("API Test execution completed!!!");
			 * 
			 * }
			 * 
			 */

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void doFinalOperation() {
		try {
			FINALProcess();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void HTMLProcess() {

		try {
			System.out.println("HTML");

			MyMethods.copyFileUsingStream();

			// MyMethods.ExcelFileEmptyforSummary(testingMode,
			// (MyMethods.ExcelRowCount(testingMode)-1));

			if (isAllAPITest.equals("1")) {
				String[] sheetName = MyMethods.ExcelFileSheetData();

				for (int i = 0; i < sheetno; i++) {
					if (!sheetName[i].equals("Summary")) {
						System.out.println("Sheet Name: " + sheetName[i]);
						MyLoginAPI.SingleAPITest(sheetName[i]);
					}
				}
			} else // isAllAPITest=2
			{

				String[] apiArray = null;
				String apiArray1 = readVariable("apiArray", 1);
				// System.out.println("Array: "+apiArray1);
				if (apiArray1.contains(",")) {
					apiArray = apiArray1.split(",");
					for (int i = 0; i < apiArray.length; i++) {
						System.out.println("Sheet Name: " + apiArray[i]);
						MyLoginAPI.SingleAPITest(apiArray[i]);
					}

				} else {
					System.out.println("Sheet Name: " + apiArray1);
					MyLoginAPI.SingleAPITest(apiArray1);
				}
			}

			MyMethods.ExceltoHTML();

			System.out.println("HTML API Test execution completed!!!");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void FINALProcess() {

		
		try {
			System.out.println("FINAL");

			MyMethods.copyFileUsingStream();

			// MyMethods.ExcelFileEmptyforSummary(testingMode,
			// (MyMethods.ExcelRowCount(testingMode)-1));

			if (isAllAPITest.equals("1")) {
				String[] sheetName = MyMethods.ExcelFileSheetData();

				for (int i = 0; i < sheetno; i++) {
					if (!sheetName[i].equals("Summary")) {
						System.out.println("Sheet Name: " + sheetName[i]);
						MyLoginAPI.ChainAPITest(sheetName[i]);
					}
				}
			} else // isAllAPITest=2
			{

				String[] apiArray = null;
				String apiArray1 = readVariable("apiArray", 1);
				// System.out.println("Array: "+apiArray1);
				if (apiArray1.contains(",")) {
					apiArray = apiArray1.split(",");
					for (int i = 0; i < apiArray.length; i++) {
						System.out.println("Sheet Name: " + apiArray[i]);
						MyLoginAPI.ChainAPITest(apiArray[i]);
					}

				} else {
					System.out.println("Sheet Name: " + apiArray1);
					MyLoginAPI.ChainAPITest(apiArray1);
				}
			}

			MyMethods.ExceltoHTML();

			System.out.println("FINAL API Test execution completed!!!");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void doOrderProcessTest() {

		try {
			MyMethods.copyFileUsingStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// MyMethods.ExcelFileEmptyforSummary(testingMode,
		// (MyMethods.ExcelRowCount(testingMode)-1));

		try {
			if (isAllAPITest.equals("1")) {
				String[] sheetName = MyMethods.ExcelFileSheetData();

				for (int i = 0; i < sheetno; i++) {
					if (!sheetName[i].equals("Summary")) {
						System.out.println("Sheet Name: " + sheetName[i]);
						MyLoginAPI.OrderProcessTest(sheetName[i]);
					}
				}
			} else // isAllAPITest=2
			{

				String[] apiArray = null;
				String apiArray1 = readVariable("apiArray", 1);
				// System.out.println("Array: "+apiArray1);
				if (apiArray1.contains(",")) {
					apiArray = apiArray1.split(",");
					for (int i = 0; i < apiArray.length; i++) {
						System.out.println("Sheet Name: " + apiArray[i]);
						MyLoginAPI.OrderProcessTest(apiArray[i]);
					}

				} else {
					System.out.println("Sheet Name: " + apiArray1);
					MyLoginAPI.OrderProcessTest(apiArray1);
				}
			}

			MyMethods.ExceltoHTML();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Order Process API Test execution completed!!!");

	}

	public static void doTicketProcessTest() {

		try {
			MyMethods.copyFileUsingStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// MyMethods.ExcelFileEmptyforSummary(testingMode,
		// (MyMethods.ExcelRowCount(testingMode)-1));

		try {
			if (isAllAPITest.equals("1")) {
				String[] sheetName = MyMethods.ExcelFileSheetData();

				for (int i = 0; i < sheetno; i++) {
					if (!sheetName[i].equals("Summary")) {
						System.out.println("Sheet Name: " + sheetName[i]);
						MyLoginAPI.TicketProcessTest(sheetName[i]);
					}
				}
			} else // isAllAPITest=2
			{

				String[] apiArray = null;
				String apiArray1 = readVariable("apiArray", 1);
				// System.out.println("Array: "+apiArray1);
				if (apiArray1.contains(",")) {
					apiArray = apiArray1.split(",");
					for (int i = 0; i < apiArray.length; i++) {
						System.out.println("Sheet Name: " + apiArray[i]);
						MyLoginAPI.TicketProcessTest(apiArray[i]);
					}

				} else {
					System.out.println("Sheet Name: " + apiArray1);
					MyLoginAPI.TicketProcessTest(apiArray1);
				}
			}

			MyMethods.ExceltoHTML();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Ticket Process API Test execution completed!!!");

	}

	public static void uploadAPISheet(WebDriver driver) {

		try {

			String sheetLink = readVariable("sheetLink", 1);
			driver.get(sheetLink);
			MyMethods.Sleep(30000);

			driver.findElement(By.xpath("//*[@id=\"docs-file-menu\"]")).click();
			MyMethods.Sleep(5000);
			driver.findElement(By.xpath("//*[@id=\":6f\"]/div")).click();
			MyMethods.Sleep(15000);

			System.out.println("Size: " + driver.findElements(By.tagName("iframe")).size());
			driver.switchTo().frame(driver.findElement(By.className("picker-frame")));
			MyMethods.Sleep(15000);

			driver.findElement(By.xpath("//*[@id=\":8\"]/div")).click();
			MyMethods.Sleep(2000);
			// driver.findElement(By.xpath("//*[@id=\":12\"]/div")).click();
			// driver.findElement(By.xpath("//*[@id=\":12\"]/div")).sendKeys(driverlog+"/InputFiles/GoogleSheet/APIReportdownload.xlsx");

			WebElement to = driver.findElement(By.className("kf-Fc-pg-nq-Ri"));
			DropFile(new File(driverlog + "/InputFiles/GoogleSheet/APIReportdownload.xlsx"), to, 0, 0);

			// Actions act = new Actions(driver);
			// act.dragAndDrop((WebElement) new
			// File(driverlog+"/InputFiles/GoogleSheet/APIReportdownload.xlsx"),to).build().perform();

			MyMethods.Sleep(15000);

			driver.findElement(By.xpath("//*[@id=\"destinationOptions\"]/div[3]/div/span[1]")).click();
			driver.findElement(By.name("import")).click();
			MyMethods.Sleep(30000);

			driver.switchTo().defaultContent();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void DropFile(File filePath, WebElement target, int offsetX, int offsetY) {
		if (!filePath.exists())
			throw new WebDriverException("File not found: " + filePath.toString());

		WebDriver driver = ((RemoteWebElement) target).getWrappedDriver();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 30);

		String JS_DROP_FILE = "var target = arguments[0]," + "    offsetX = arguments[1],"
				+ "    offsetY = arguments[2]," + "    document = target.ownerDocument || document,"
				+ "    window = document.defaultView || window;" + "" + "var input = document.createElement('INPUT');"
				+ "input.type = 'file';" + "input.style.display = 'none';" + "input.onchange = function () {"
				+ "  var rect = target.getBoundingClientRect(),"
				+ "      x = rect.left + (offsetX || (rect.width >> 1)),"
				+ "      y = rect.top + (offsetY || (rect.height >> 1)),"
				+ "      dataTransfer = { files: this.files };" + ""
				+ "  ['dragenter', 'dragover', 'drop'].forEach(function (name) {"
				+ "    var evt = document.createEvent('MouseEvent');"
				+ "    evt.initMouseEvent(name, !0, !0, window, 0, 0, 0, x, y, !1, !1, !1, !1, 0, null);"
				+ "    evt.dataTransfer = dataTransfer;" + "    target.dispatchEvent(evt);" + "  });" + ""
				+ "  setTimeout(function () { document.body.removeChild(input); }, 25);" + "};"
				+ "document.body.appendChild(input);" + "return input;";

		WebElement input = (WebElement) jse.executeScript(JS_DROP_FILE, target, offsetX, offsetY);
		input.sendKeys(filePath.getAbsoluteFile().toString());
		wait.until(ExpectedConditions.stalenessOf(input));
	}

	public static void downloadAPISheet(WebDriver driver) {

		try {

			String downloadLink = readVariable("downloadLink", 1);
			String id = readVariable("id", 1);
			driver.get(downloadLink);
			MyMethods.Sleep(2000);
			driver.findElement(By.name("identifier")).sendKeys("mninety@gmail.com");
			driver.findElement(By.id("identifierNext")).click();
			MyMethods.Sleep(2000);
			driver.findElement(By.name("password")).sendKeys(id);
			driver.findElement(By.id("passwordNext")).click();
			MyMethods.Sleep(10000);

			Path temp = Files.move(Paths.get("C:/Users/Lenovo/Downloads/Prefeex API Report.xlsx"),
					Paths.get(driverlog + "/InputFiles/GoogleSheet/APIReportdownload.xlsx"));

			if (temp != null) {
				System.out.println("File renamed and moved successfully");
			} else {
				System.out.println("Failed to move the file");
			}

			MyMethods.Sleep(5000);

			try {
				MyMethods.copyFileUsingStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// driver.quit();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String readVariable(String var, int configflag) {
		int flag = 0;
		int gotvar = 0;
		Character ch = new Character('a');
		StringBuilder Test = new StringBuilder("");
		StringBuilder tempvar = new StringBuilder("");
		String path = null;
		int data;
		try {
			//System.out.println("Location from readVariable: "+driverlog);
			if (configflag == 1) {
				path = driverlog + "/PrefeexAPI.txt";
			} else {
				// path=VariableModule.driverlog+"/WebDriver/Config/API-Config.txt";

			}
			Reader fileReader = new FileReader(path);
			data = fileReader.read();
			while (data != -1) {
				ch = (char) data;
				// System.out.println("String: "+ch+flag+gotvar);
				if (flag == 0 && ch != '=') {
					Test.append(ch);
				} else if (flag == 1 && gotvar == 1) {
					if (ch != '=' && ch != ';') {
						tempvar.append(ch);

					}
				}
				if (ch == '\n') {
					flag = 0;
					Test = new StringBuilder("");
				} else if (ch == '=') {
					flag = 1;
					String Test1 = Test.toString();
					// System.out.println("Flag:"+Test1);
					// System.out.println("Actual:"+var);
					if (Test1.equals(var)) {
						// System.out.println("Match Equal"+Test);
						gotvar = 1;
					}
				} else if (ch == ';' && gotvar == 1) {
					// System.out.println("Variable Found:"+tempvar);
					break;
				}

				data = fileReader.read();
				// System.out.println((char)data);
			}
			// String tempvar1=tempvar.toString();

			fileReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempvar.toString();
	}

	public static WebDriver HtmlUnitDriver() {
		WebDriver drive;
		drive = new HtmlUnitDriver();
		return drive;
	}

	public static String RandomStringGen(int len) {

		final String AB = "abcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();

		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));

		return sb.toString();

	}

	public static String RandomStringGenCAP(int len) {

		final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		SecureRandom rnd = new SecureRandom();

		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));

		return sb.toString();

	}

	public static String offsetTime() {
		String time = "";
		Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.HOUR, Integer.valueOf(offsettime));
		Date addMilliSeconds = calendar.getTime();

		System.out.println("Offset Time: " + addMilliSeconds.getTime());
		time = Long.toString(addMilliSeconds.getTime());
		return time;
	}

	public static String offsetTime1() {
		String time = "";
		Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.HOUR, Integer.valueOf(offsettime) + 2);
		Date addMilliSeconds = calendar.getTime();

		System.out.println("Offset Time: " + addMilliSeconds.getTime());
		time = Long.toString(addMilliSeconds.getTime());
		return time;
	}

	public static String RandomNumberGen(int len) {

		final String AB = "0123456789";
		SecureRandom rnd = new SecureRandom();

		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));

		return sb.toString();

	}

}
