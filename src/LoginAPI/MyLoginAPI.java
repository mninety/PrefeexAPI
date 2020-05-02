package LoginAPI;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpException;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import MethodsRepo.MyMethods;
import MethodsRepo.VariableRplaceModule;
import StartPrefeexAPI.VariableModule;

public class MyLoginAPI {

	public static int noofrow;
	public static String orderID;
	public static String orderStatus;
	public static String TicketID;
	public static void SingleAPITest(String sheetName) throws JSONException, IOException
	{
		try {
			int noofsuccess=0;
			int nooffailed=0;
			String[][] inputdata;
			String[] method;
			if(VariableModule.testingMode.equals("1")) //local
			{
				inputdata=MyMethods.ExcelFileReadAction(sheetName);
				method=sheetName.split("-");
			}
			else if(VariableModule.testingMode.equals("2")) //google
			{
				inputdata=MyMethods.ExcelFileReadAction(sheetName);
				method=sheetName.split("-");
			}
			else if(VariableModule.testingMode.equals("3"))//html
			{
				inputdata=MyMethods.ExcelFileReadAction(sheetName);
				method=sheetName.split("-");
			}
			else //OrderProcess
			{
				inputdata=MyMethods.ExcelFileReadAction(sheetName);
				method=sheetName.split("-");
			}

			
			for(int i=0;i<inputdata.length;i++)
			{
				String[] output = new String[2];
				String[] parseStr=null;
				String parseStr1="";
				
				if(!inputdata[i][6].contains("#"))
				{
					if(inputdata[i][2].contains("EMAIL"))
					{
						inputdata[i][2]=inputdata[i][2].replaceAll("EMAIL", VariableModule.RandomStringGen(8)+"@business.com");
						//System.out.println("Body1: "+inputdata[i][2]);
					}
					if(inputdata[i][2].contains("PHONE"))
					{
						inputdata[i][2]=inputdata[i][2].replaceAll("PHONE", VariableModule.RandomNumberGen(8));
						//System.out.println("Body2: "+inputdata[i][2]);
					}
					if(inputdata[i][2].contains("TAG"))
					{
						inputdata[i][2]=inputdata[i][2].replaceAll("TAG", VariableModule.RandomStringGenCAP(5));
						//System.out.println("Body3: "+inputdata[i][2]);
					}
					if(inputdata[i][2].contains("TIME"))
					{
						inputdata[i][2]=inputdata[i][2].replaceAll("TIME", VariableModule.offsetTime());
						//System.out.println("Body3: "+inputdata[i][2]);
					}
					if(inputdata[i][2].contains("TIME1"))
					{
						inputdata[i][2]=inputdata[i][2].replaceAll("TIME1", VariableModule.offsetTime1());
						//System.out.println("Body3: "+inputdata[i][2]);
					}
					
					//System.out.println("Body: "+inputdata[i][2]);

				
				if(inputdata[i][1].equals("POST"))
				{


					output=MyMethods.postAPI(inputdata[i][0],inputdata[i][2],2,MyMethods.GetHeader(inputdata[i][4]));

					parseStr1=MyMethods.jsonMaker(output[0],inputdata[i][5]);
					//System.out.println("Parse: "+parseStr1);
					parseStr=parseStr1.split(",");
					//System.out.println("Parse: "+parseStr);
					if(inputdata[i][0].contains("limit"))
					{
						String[] limitArray=inputdata[i][0].split("limit=");
						System.out.println("Lim: "+limitArray[1]);
						VariableModule.limit=limitArray[1];
					}
				}
				else if(inputdata[i][1].equals("DEL"))
				{


					output=MyMethods.delAPI(inputdata[i][0],inputdata[i][2],2,MyMethods.GetHeader(inputdata[i][4]));

					parseStr1=MyMethods.jsonMaker(output[0],inputdata[i][5]);
					//System.out.println("Parse: "+parseStr1);
					parseStr=parseStr1.split(",");
					//System.out.println("Parse: "+parseStr);
					if(inputdata[i][0].contains("limit"))
					{
						String[] limitArray=inputdata[i][0].split("limit=");
						System.out.println("Lim: "+limitArray[1]);
						VariableModule.limit=limitArray[1];
					}
				}
				else if(inputdata[i][1].equals("GET"))
				{

					
					output=MyMethods.getAPI(inputdata[i][0],2,MyMethods.GetHeader(inputdata[i][4]));
					parseStr1=MyMethods.jsonMaker(output[0],inputdata[i][5]);
					//System.out.println("Parse: "+parseStr1);
					parseStr=parseStr1.split(",");

					//System.out.println("Parse: "+parseStr);
					if(inputdata[i][0].contains("limit"))
					{
						String[] limitArray=inputdata[i][0].split("limit=");
						System.out.println("Lim: "+limitArray[1]);
						VariableModule.limit=limitArray[1];
					}
					
				} 
				else //PUT
				{

					
					output=MyMethods.putAPI(inputdata[i][0],inputdata[i][2],2,MyMethods.GetHeader(inputdata[i][4]));
					parseStr1=MyMethods.jsonMaker(output[0],inputdata[i][5]);
					//System.out.println("Parse: "+parseStr1);
					parseStr=parseStr1.split(",");
					//System.out.println("Parse: "+parseStr);
					
					if(inputdata[i][0].contains("limit"))
					{
						String[] limitArray=inputdata[i][0].split("limit=");
						System.out.println("Lim: "+limitArray[1]);
						VariableModule.limit=limitArray[1];
					}
					
				}
					
				
				//MyMethods.PrintMe("Output[0]: ", output[0]);
				//MyMethods.PrintMe("inputdata[i][3]: ", inputdata[i][3]);
				if(parseStr[1].contains(inputdata[i][3]))
				{
					if(parseStr[0].equals(""))
					{
						MyMethods.ExcelFileWriteforAPI(output,sheetName,i+1,"Successful");
						MyMethods.PrintMe("Test Result: ", "Successful");
						noofsuccess++;
					}
					else
					{
						if(VariableModule.limit.equals(parseStr[0]))
						{
							MyMethods.ExcelFileWriteforAPI(output,sheetName,i+1,"Successful");
							MyMethods.PrintMe("Test Result: ", "Successful");
							System.out.println("Limit: "+VariableModule.limit);
							noofsuccess++;
						}
						else
						{
							if(Integer.parseInt(VariableModule.limit)>Integer.parseInt(parseStr[0]))
							{
								MyMethods.ExcelFileWriteforAPI(output,sheetName,i+1,"Successful");
								MyMethods.PrintMe("Test Result: ", "Successful");
								System.out.println("Limit1: "+parseStr[0]);
								noofsuccess++;
							}
							else
							{
								MyMethods.ExcelFileWriteforAPI(output,sheetName,i+1,"Failed!");
								MyMethods.PrintMe("Test Result: ", "Failed");
								System.out.println("Limit Failed: "+parseStr[0]);
								noofsuccess++;
							}

						}
					}

				}
				else
				{
					MyMethods.ExcelFileWriteforAPI(output,sheetName, i+1,"Failed");
					MyMethods.PrintMe("Test Result: ", "Failed");
					nooffailed++;
				}
				
				
				/*
				if(i==(inputdata.length-1))
				{
					
			    	if(MyMethods.isJsonValid(output[0]))
			    	{
			    		MyMethods.ExcelFileWriteforAPI(output,sheetName, i+1,"Successful");
			    	}
			    	else
			    	{
			    		MyMethods.ExcelFileWriteforAPI(output,sheetName, i+1,"Failed");
			    	}
			    	
				}
				*/
				}
				else
				{
					System.out.println("# Found");
					continue;
				}
			}// for loop end
			MyMethods.ExcelFileWriteforSummary(sheetName, String.valueOf(noofrow), String.valueOf(noofsuccess), String.valueOf(nooffailed));
			System.out.println("Total Cases: "+noofrow);
			System.out.println("Total Successful: "+noofsuccess);
			System.out.println("Total Failed: "+nooffailed);
			noofrow=0;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	public static void ChainAPITest(String sheetName) throws JSONException, IOException
	{
		try {
			int noofsuccess=0;
			int nooffailed=0;
			String[][] inputdata;
			String[] method;

			inputdata=MyMethods.ExcelFileReadAction(sheetName);
			method=sheetName.split("-");
			

			int m=0;
			for(int i=0;i<inputdata.length;i++)
			{
				String[] output = new String[2];
				String[] parseStr=null;
				String parseStr1="";
				
				if(!inputdata[i][6].contains("#"))
				{
					if(inputdata[i][2].contains("VAR"))
					{
						
						inputdata[i][2]=VariableRplaceModule.VariableReplace(sheetName, inputdata[i][2]);
						System.out.println("Variable Found: "+inputdata[i][2]);
					}
					if(inputdata[i][0].contains("ENDP"))
					{

						inputdata[i][0]=VariableRplaceModule.EndpointReplace(sheetName, inputdata[i][0]);
						System.out.println("ENDP Found: "+inputdata[i][0]);
						
					}
					if(inputdata[i][2].contains("TIME"))
					{
						inputdata[i][2]=inputdata[i][2].replaceAll("TIME", VariableModule.offsetTime());
						//System.out.println("Body3: "+inputdata[i][2]);
					}
					

						if(!inputdata[i][2].equals(""))
						{
							MyMethods.ExcelFileWriteforReplace(sheetName,i+1,VariableModule.adminURL+inputdata[i][0],inputdata[i][2]);
						}
						else
						{
							MyMethods.ExcelFileWriteforReplace(sheetName,i+1,VariableModule.adminURL+inputdata[i][0],"");
						}

					
				if(inputdata[i][1].equals("POST"))
				{


					output=MyMethods.postAPI(inputdata[i][0],inputdata[i][2],2,MyMethods.GetHeader(inputdata[i][4]));

					
					parseStr1=MyMethods.jsonMaker(output[0],inputdata[i][5]);
					//System.out.println("Parse: "+parseStr1);
					parseStr=parseStr1.split(",");
					//System.out.println("Parse: "+parseStr);
					if(inputdata[i][0].contains("limit"))
					{
						String[] limitArray=inputdata[i][0].split("limit=");
						System.out.println("Lim: "+limitArray[1]);
						VariableModule.limit=limitArray[1];
					}
				}
				else if(inputdata[i][1].equals("DEL"))
				{


					output=MyMethods.delAPI(inputdata[i][0],inputdata[i][2],2,MyMethods.GetHeader(inputdata[i][4]));

					parseStr1=MyMethods.jsonMaker(output[0],inputdata[i][5]);
					//System.out.println("Parse: "+parseStr1);
					parseStr=parseStr1.split(",");
					//System.out.println("Parse: "+parseStr);
					if(inputdata[i][0].contains("limit"))
					{
						String[] limitArray=inputdata[i][0].split("limit=");
						System.out.println("Lim: "+limitArray[1]);
						VariableModule.limit=limitArray[1];
					}
				}
				else if(inputdata[i][1].equals("GET"))
				{

					
					output=MyMethods.getAPI(inputdata[i][0],2,MyMethods.GetHeader(inputdata[i][4]));
					parseStr1=MyMethods.jsonMaker(output[0],inputdata[i][5]);
					//System.out.println("Parse: "+parseStr1);
					parseStr=parseStr1.split(",");

					//System.out.println("Parse: "+parseStr);
					if(inputdata[i][0].contains("limit"))
					{
						String[] limitArray=inputdata[i][0].split("limit=");
						System.out.println("Lim: "+limitArray[1]);
						VariableModule.limit=limitArray[1];
					}
					
				} 
				else //PUT
				{

					
					output=MyMethods.putAPI(inputdata[i][0],inputdata[i][2],2,MyMethods.GetHeader(inputdata[i][4]));
					parseStr1=MyMethods.jsonMaker(output[0],inputdata[i][5]);
					//System.out.println("Parse: "+parseStr1);
					parseStr=parseStr1.split(",");
					//System.out.println("Parse: "+parseStr);
					
					if(inputdata[i][0].contains("limit"))
					{
						String[] limitArray=inputdata[i][0].split("limit=");
						System.out.println("Lim: "+limitArray[1]);
						VariableModule.limit=limitArray[1];
					}
					
				}
					
				
				//MyMethods.PrintMe("Output[0]: ", output[0]);
				//MyMethods.PrintMe("inputdata[i][3]: ", inputdata[i][3]);
				if(parseStr[1].contains(inputdata[i][3]))
				{
					if(parseStr[0].equals(""))
					{
						MyMethods.ExcelFileWriteforAPI(output,sheetName,i+1,"Successful");
						MyMethods.PrintMe("Test Result: ", "Successful");
						noofsuccess++;
					}
					else
					{
						if(VariableModule.limit.equals(parseStr[0]))
						{
							MyMethods.ExcelFileWriteforAPI(output,sheetName,i+1,"Successful");
							MyMethods.PrintMe("Test Result: ", "Successful");
							System.out.println("Limit: "+VariableModule.limit);
							noofsuccess++;
						}
						else
						{
							if(Integer.parseInt(VariableModule.limit)>Integer.parseInt(parseStr[0]))
							{
								MyMethods.ExcelFileWriteforAPI(output,sheetName,i+1,"Successful");
								MyMethods.PrintMe("Test Result: ", "Successful");
								System.out.println("Limit1: "+parseStr[0]);
								noofsuccess++;
							}
							else
							{
								MyMethods.ExcelFileWriteforAPI(output,sheetName,i+1,"Failed!");
								MyMethods.PrintMe("Test Result: ", "Failed");
								System.out.println("Limit Failed: "+parseStr[0]);
								noofsuccess++;
							}

						}
					}

				}
				else
				{
					MyMethods.ExcelFileWriteforAPI(output,sheetName, i+1,"Failed");
					MyMethods.PrintMe("Test Result: ", "Failed");
					nooffailed++;
				}

					if(sheetName.equals("GetCustomerUsersAPI-GET") && output[0].contains("User fetched successfully"))
					{
	
							VariableModule.enduser1ID=MyMethods.JSONArrayProcessor(output[0], "data.users.email", VariableModule.endUser1, "data.users.id");
							System.out.println("End user ID1: "+VariableModule.enduser1ID);
							VariableModule.enduser2ID=MyMethods.JSONArrayProcessor(output[0], "data.users.email", VariableModule.endUser2, "data.users.id");
							System.out.println("End user ID2: "+VariableModule.enduser2ID);
							if(m==0)
							{
								String jsonStrMid2=VariableModule.jsonStrMid.replaceAll("VARIDS", VariableModule.enduser1ID+","+VariableModule.enduser2ID);
								jsonStrMid2=jsonStrMid2.replaceAll("SHEET_NAME", sheetName);
								VariableModule.finalJSON=VariableModule.finalJSON+jsonStrMid2;
								System.out.println("JSON: "+VariableModule.finalJSON);
							}

							m++;
					}
					else if(sheetName.equals("GetInternalUsersAPI-GET") && output[0].contains("User fetched successfully"))
					{
	
							VariableModule.supportUser1ID=MyMethods.JSONArrayProcessor(output[0], "data.users.email", VariableModule.supportUser1, "data.users.id");
							System.out.println("Support user ID1: "+VariableModule.supportUser1ID);
							VariableModule.supportUser2ID=MyMethods.JSONArrayProcessor(output[0], "data.users.email", VariableModule.supportUser2, "data.users.id");
							System.out.println("Support user ID2: "+VariableModule.supportUser2ID);
							if(m==0)
							{
								String jsonStrMid2=VariableModule.jsonStrMid.replaceAll("VARIDS", VariableModule.supportUser1ID+","+VariableModule.supportUser2ID);
								jsonStrMid2=jsonStrMid2.replaceAll("SHEET_NAME", sheetName);
								VariableModule.finalJSON=VariableModule.finalJSON+jsonStrMid2;
								System.out.println("JSON: "+VariableModule.finalJSON);
							}
							
							m++;
					}
					else if(sheetName.equals("GetBusinessAPI-GET") && output[0].contains("Successfully fetched Businesses"))
					{
	
							VariableModule.bussinessName1ID=MyMethods.JSONArrayProcessor(output[0], "data.businesses.name", VariableModule.bussinessName1, "data.businesses.id");
							System.out.println("bussiness ID1: "+VariableModule.bussinessName1ID);
							VariableModule.bussinessName2ID=MyMethods.JSONArrayProcessor(output[0], "data.businesses.name", VariableModule.bussinessName2, "data.businesses.id");
							System.out.println("bussiness ID2: "+VariableModule.bussinessName2ID);
							
							if(m==0)
							{
								String jsonStrMid2=VariableModule.jsonStrMid.replaceAll("VARIDS", VariableModule.bussinessName1ID+","+VariableModule.bussinessName2ID);
								jsonStrMid2=jsonStrMid2.replaceAll("SHEET_NAME", sheetName);
								VariableModule.finalJSON=VariableModule.finalJSON+jsonStrMid2;
								System.out.println("JSON: "+VariableModule.finalJSON);
							}

							m++;
					}
					else if(sheetName.equals("GetallUserunderBusinessAPI-GET") && output[0].contains("User fetched successfully"))
					{
	
							VariableModule.bussinessUser1ID=MyMethods.JSONArrayProcessor(output[0], "data.users.email", VariableModule.bussinessUser1, "data.users.id");
							System.out.println("bussiness user ID1: "+VariableModule.bussinessUser1ID);


					}
					else if(sheetName.equals("GetallUserunderBusinessAPI1-GET") && output[0].contains("User fetched successfully"))
					{
	
							VariableModule.bussinessUser2ID=MyMethods.JSONArrayProcessor(output[0], "data.users.email", VariableModule.bussinessUser2, "data.users.id");
							System.out.println("bussiness user ID2: "+VariableModule.bussinessUser2ID);
	
							if(m==1)
							{
								String jsonStrMid2=VariableModule.jsonStrMid.replaceAll("VARIDS", VariableModule.bussinessUser1ID+","+VariableModule.bussinessUser2ID);
								jsonStrMid2=jsonStrMid2.replaceAll("SHEET_NAME", "GetallUserunderBusinessAPI-GET");
								VariableModule.finalJSON=VariableModule.finalJSON+jsonStrMid2;
								System.out.println("JSON: "+VariableModule.finalJSON);
							}

							m++;
					}
					else if(sheetName.equals("GetAllTagsAPI-GET") && output[0].contains("Tag fetched successfully"))
					{
	
							VariableModule.TagName1ID=MyMethods.JSONArrayProcessor(output[0], "data.tags.name", VariableModule.TagName1, "data.tags.id");
							System.out.println("Tag ID1: "+VariableModule.TagName1ID);
							
							if(m==0)
							{
								String jsonStrMid2=VariableModule.jsonStrMid.replaceAll("VARIDS", VariableModule.TagName1ID);
								jsonStrMid2=jsonStrMid2.replaceAll("SHEET_NAME", sheetName);
								VariableModule.finalJSON=VariableModule.finalJSON+jsonStrMid2;
								System.out.println("JSON: "+VariableModule.finalJSON);
							}

							m++;
					}
					else if(sheetName.equals("GetAllFacilitiesAPI-GET") && output[0].contains("Facilities fetched"))
					{
	
							VariableModule.FacilityName1ID=MyMethods.JSONArrayProcessor(output[0], "data.facilities.name", VariableModule.FacilityName1, "data.facilities.id");
							System.out.println("Facility ID1: "+VariableModule.FacilityName1ID);
	
							if(m==0)
							{
								String jsonStrMid2=VariableModule.jsonStrMid.replaceAll("VARIDS", VariableModule.FacilityName1ID);
								jsonStrMid2=jsonStrMid2.replaceAll("SHEET_NAME", sheetName);
								VariableModule.finalJSON=VariableModule.finalJSON+jsonStrMid2;
								System.out.println("JSON: "+VariableModule.finalJSON);
							}

							m++;
					}

					else if(sheetName.equals("GetAllMenuofaBusiAPI-GET") && output[0].contains("Fetched food menu"))
					{
	
							VariableModule.FMiTem1ID=MyMethods.JSONArrayProcessor(output[0], "data.foodMenus.itemName", VariableModule.FMiTem1, "data.foodMenus.id");
							System.out.println("Food Menu iTem ID1: "+VariableModule.FMiTem1ID);
							
							if(m==0)
							{
								String jsonStrMid2=VariableModule.jsonStrMid.replaceAll("VARIDS", VariableModule.FMiTem1ID);
								jsonStrMid2=jsonStrMid2.replaceAll("SHEET_NAME", sheetName);
								VariableModule.finalJSON=VariableModule.finalJSON+jsonStrMid2;
								System.out.println("JSON: "+VariableModule.finalJSON);
							}

							m++;
					}
					else if(sheetName.equals("GetAllAddonofaMenuAPI-GET") && output[0].contains("Addon fetched successfully"))
					{
	
							VariableModule.AddonName1ID=MyMethods.JSONArrayProcessor(output[0], "data.addons.name", VariableModule.AddonName1, "data.addons.id");
							System.out.println("Addon Name ID1: "+VariableModule.AddonName1ID);
							
							if(m==1)
							{
								String jsonStrMid2=VariableModule.jsonStrMid.replaceAll("VARIDS", VariableModule.AddonName1ID);
								jsonStrMid2=jsonStrMid2.replaceAll("SHEET_NAME", sheetName);
								VariableModule.finalJSON=VariableModule.finalJSON+jsonStrMid2;
								
								System.out.println("JSON: "+VariableModule.finalJSON);
							}

							m++;
					}
					else if(sheetName.equals("GetAllAddonOptofaMenuAPI-GET") && output[0].contains("Addon fetched successfully"))
					{
	
							VariableModule.AddonOption1ID=MyMethods.AddonOptionProcessor(output[0], "data.addons.addonOptions.name", VariableModule.AddonOption1, "data.addons.addonOptions.id","data.addons.name", VariableModule.AddonName1);
							System.out.println("Addon Option Name ID1: "+VariableModule.AddonOption1ID);
							
							if(m==1)
							{

								String jsonStrMid3=VariableModule.jsonStrMid.replaceAll("VARIDS", VariableModule.AddonOption1ID);
								jsonStrMid3=jsonStrMid3.replaceAll("SHEET_NAME", sheetName);
								VariableModule.finalJSON=VariableModule.finalJSON+jsonStrMid3;
								
								System.out.println("JSON: "+VariableModule.finalJSON);
							}

							m++;
					}
					else if(sheetName.equals("GetAllorderbyMeAPI-GET") && output[0].contains("Orders fetched successfully"))
					{
	
							VariableModule.Order1ID=MyMethods.JSONArrayProcessor(output[0], "data.orders.businessName", VariableModule.bussinessName1, "data.orders.id");
							System.out.println("Order ID1: "+VariableModule.Order1ID);
							
							if(m==0)
							{
								String jsonStrMid2=VariableModule.jsonStrMid.replaceAll("VARIDS", VariableModule.Order1ID);
								jsonStrMid2=jsonStrMid2.replaceAll("SHEET_NAME", sheetName);
								VariableModule.finalJSON=VariableModule.finalJSON+jsonStrMid2;
								System.out.println("JSON: "+VariableModule.finalJSON);
							}
							
							m++;
					}
					
					else if(sheetName.equals("GetFilteredTicketsAPI-POST") && output[0].contains("Ticket fetched"))
					{
	
							VariableModule.Ticket1ID=MyMethods.TicketProcessor(output[0], "data.tickets.orderId", VariableModule.Order1ID, "data.tickets.id");
							System.out.println("Ticket ID1: "+VariableModule.Ticket1ID);
							
							if(m==0)
							{
								String jsonStrMid2=VariableModule.jsonStrMid.replaceAll("VARIDS", VariableModule.Ticket1ID);
								jsonStrMid2=jsonStrMid2.replaceAll("SHEET_NAME", sheetName);
								VariableModule.finalJSON=VariableModule.finalJSON+jsonStrMid2;
								System.out.println("JSON: "+VariableModule.finalJSON);
							}
							
							m++;

					}
					else if(sheetName.equals("GetRegisteredBankofBusiAPI-GET") && output[0].contains("Bank info fetched"))
					{
	
							VariableModule.bankAccount1ID=MyMethods.JSONArrayProcessor(output[0], "data.bankInfo.accountNumber", VariableModule.bankAccount1, "data.bankInfo.id");
							System.out.println("Bank Account ID1: "+VariableModule.bankAccount1ID);
							
							if(m==0)
							{
								String jsonStrMid2=VariableModule.jsonStrMid.replaceAll("VARIDS", VariableModule.bankAccount1ID);
								jsonStrMid2=jsonStrMid2.replaceAll("SHEET_NAME", sheetName);
								VariableModule.finalJSON=VariableModule.finalJSON+jsonStrMid2;
								System.out.println("JSON: "+VariableModule.finalJSON);
							}
							
							m++;
					}
					else if(sheetName.equals("GetAllMarketingPushAPI-GET") && output[0].contains("Notification fetched"))
					{
	
							VariableModule.NotifTitle1ID=MyMethods.JSONArrayProcessor(output[0], "data.notifications.title", VariableModule.NotifTitle1, "data.notifications.id");
							System.out.println("Notification ID1: "+VariableModule.NotifTitle1ID);
							
							if(m==0)
							{
								String jsonStrMid2=VariableModule.jsonStrMid1.replaceAll("VARIDS", VariableModule.NotifTitle1ID);
								jsonStrMid2=jsonStrMid2.replaceAll("SHEET_NAME", sheetName);
								VariableModule.finalJSON=VariableModule.finalJSON+jsonStrMid2+VariableModule.jsonStrBot;
								System.out.println("Final JSON: "+VariableModule.finalJSON);
							}
							
							m++;
					}

					
					
				}
				else
				{
					System.out.println("# Found");
					continue;
				}
			}// for loop end
			if(sheetName.equals("SignupAPI-POST"))
			{
				MyMethods.HeaderGenerator("Enduser_Token1",VariableModule.endUser1,VariableModule.adminpass);
				MyMethods.HeaderGenerator("Enduser_Token2",VariableModule.endUser2,VariableModule.adminpass);
			}
			if(sheetName.equals("CreateInternalUsersAPI-POST"))
			{
				MyMethods.HeaderGenerator("Support_Token1",VariableModule.supportUser1,VariableModule.adminpass);
				MyMethods.HeaderGenerator("Support_Token2",VariableModule.supportUser2,VariableModule.adminpass);
			}
			if(sheetName.equals("UpdateMyPassword-PUT"))
			{
				MyMethods.HeaderGenerator("Admin_Token1",VariableModule.adminuser,VariableModule.adminpass);
			}
			if(sheetName.equals("CreateBusinessUserAPI-POST"))
			{
				MyMethods.HeaderGenerator("Business_Token1",VariableModule.bussinessUser1,VariableModule.adminpass);
				MyMethods.HeaderGenerator("Business_Token2",VariableModule.bussinessUser2,VariableModule.adminpass);
			}
			
			
			MyMethods.ExcelFileWriteforSummary(sheetName, String.valueOf(noofrow), String.valueOf(noofsuccess), String.valueOf(nooffailed));
			System.out.println("Total Cases: "+noofrow);
			System.out.println("Total Successful: "+noofsuccess);
			System.out.println("Total Failed: "+nooffailed);
			noofrow=0;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	
	public static void OrderProcessTest(String sheetName) throws JSONException, IOException
	{
		try {
			int noofsuccess=0;
			int nooffailed=0;
			String[][] inputdata;
			String[] method;

			inputdata=MyMethods.ExcelFileReadAction(sheetName);
			method=sheetName.split("-");
			//System.out.println("Array Lenght: "+inputdata.length);
			for(int i=0;i<inputdata.length;i++)
			{
				String[] output = new String[2];
				String[] parseStr=null;
				String parseStr1="";
				
				if(inputdata[i][0].contains("REPLACEoID"))
				{

						inputdata[i][0]=inputdata[i][0].replaceAll("REPLACEoID", orderID);
					
				}
				else if(inputdata[i][0].contains("REPLACEtID"))
				{
					inputdata[i][0]=inputdata[i][0].replaceAll("REPLACEtID", TicketID);
				}
				else
				{
					inputdata[i][0]=inputdata[i][0];
				}
				
				if(inputdata[i][1].equals("POST"))
				{
					//System.out.println("POST API");
					output=MyMethods.postAPI(inputdata[i][0],inputdata[i][2],2,MyMethods.GetHeader(inputdata[i][4]));
					
					if(output[0].contains("Order created successfully"))
					{
						orderID=MyMethods.GetBusinessID();
						System.out.println("Order ID: "+orderID);
						
						TicketID=MyMethods.GetTicketID(orderID);
						System.out.println("Ticket ID: "+TicketID);
						
					}
					if(sheetName.equals("DeclineAnOrder-POST"))
					{
						MyMethods.AssignTicket(TicketID);
					}


				}
				else if(inputdata[i][1].equals("GET"))
				{	
					
					output=MyMethods.getAPI(inputdata[i][0],2,MyMethods.GetHeader(inputdata[i][4]));

					
				} 
				else //PUT
				{

					output=MyMethods.putAPI(inputdata[i][0],inputdata[i][2],2,MyMethods.GetHeader(inputdata[i][4]));

				}
				
				parseStr1=MyMethods.jsonMaker(output[0],inputdata[i][5]);
				parseStr=parseStr1.split(",");
				
				//MyMethods.PrintMe("Output[0]: ", output[0]);
				//MyMethods.PrintMe("inputdata[i][3]: ", inputdata[i][3]);
				if(parseStr[1].contains(inputdata[i][3]))
				{
					if(parseStr[0].equals(""))
					{
						MyMethods.ExcelFileWriteforAPI(output,sheetName,i+1,"Successful");
						MyMethods.PrintMe("Test Result: ", "Successful");
						noofsuccess++;
					}
					else
					{
						if(VariableModule.limit.equals(parseStr[0]))
						{
							MyMethods.ExcelFileWriteforAPI(output,sheetName,i+1,"Successful");
							MyMethods.PrintMe("Test Result: ", "Successful");
							System.out.println("Limit: "+VariableModule.limit);
							noofsuccess++;
						}
						else
						{
							if(Integer.parseInt(VariableModule.limit)>Integer.parseInt(parseStr[0]))
							{
								MyMethods.ExcelFileWriteforAPI(output,sheetName,i+1,"Successful");
								MyMethods.PrintMe("Test Result: ", "Successful");
								System.out.println("Limit1: "+parseStr[0]);
								noofsuccess++;
							}
							else
							{
								MyMethods.ExcelFileWriteforAPI(output,sheetName,i+1,"Failed!");
								MyMethods.PrintMe("Test Result: ", "Failed");
								System.out.println("Limit Failed: "+parseStr[0]);
								noofsuccess++;
							}

						}
					}

				}
				else
				{
					MyMethods.ExcelFileWriteforAPI(output,sheetName, i+1,"Failed");
					MyMethods.PrintMe("Test Result: ", "Failed");
					nooffailed++;
				}
				
				
			}
			
			
			
			MyMethods.ExcelFileWriteforSummary(sheetName, String.valueOf(noofrow), String.valueOf(noofsuccess), String.valueOf(nooffailed));
			//System.out.println("Total Cases: "+noofrow);
			//System.out.println("Total Successful: "+noofsuccess);
			//System.out.println("Total Failed: "+nooffailed);
			noofrow=0;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	

	public static void TicketProcessTest(String sheetName) throws JSONException, IOException
	{
		try {
			int noofsuccess=0;
			int nooffailed=0;
			String[][] inputdata;
			String[] method;

			inputdata=MyMethods.ExcelFileReadAction(sheetName);
			method=sheetName.split("-");
			//System.out.println("Array Lenght: "+inputdata.length);
			for(int i=0;i<inputdata.length;i++)
			{
				String[] output = new String[2];
				String[] parseStr=null;
				String parseStr1="";
				
				if(inputdata[i][0].contains("REPLACEoID"))
				{

						inputdata[i][0]=inputdata[i][0].replaceAll("REPLACEoID", orderID);
					
				}
				else if(inputdata[i][0].contains("REPLACEtID"))
				{
					inputdata[i][0]=inputdata[i][0].replaceAll("REPLACEtID", TicketID);
				}
				else
				{
					inputdata[i][0]=inputdata[i][0];
				}
				
				if(inputdata[i][1].equals("POST"))
				{
					//System.out.println("POST API");
					output=MyMethods.postAPI(inputdata[i][0],inputdata[i][2],2,MyMethods.GetHeader(inputdata[i][4]));
					
					if(output[0].contains("Order created successfully"))
					{
						orderID=MyMethods.GetBusinessID();
						System.out.println("Order ID: "+orderID);
						
						TicketID=MyMethods.GetTicketID(orderID);
						System.out.println("Ticket ID: "+TicketID);
						
					}
					if(sheetName.equals("DeclineAnOrder-POST"))
					{
						MyMethods.AssignTicket(TicketID);
					}
					/*
					if(sheetName.equals("ConfirmAnOrder-POST") && (i==(inputdata.length-1)))
					{
						System.out.println("Test I: "+i);
						if(output[0].contains("WAITING_FOR_RESOLVED"))
						{
							MyMethods.ExcelFileWriteforAPI(output,sheetName,i+1,"Successful");
							MyMethods.PrintMe("Test Result: ", "Successful");
							noofsuccess++;
							
						}
						else
						{
							MyMethods.ExcelFileWriteforAPI(output,sheetName, i+1,"Failed");
							MyMethods.PrintMe("Test Result: ", "Failed");
							nooffailed++;
						}
						break;
					}
					*/
				}
				else if(inputdata[i][1].equals("GET"))
				{	
					
					output=MyMethods.getAPI(inputdata[i][0],2,MyMethods.GetHeader(inputdata[i][4]));

					
				} 
				else //PUT
				{

					output=MyMethods.putAPI(inputdata[i][0],inputdata[i][2],2,MyMethods.GetHeader(inputdata[i][4]));

				}
				
				parseStr1=MyMethods.jsonMaker(output[0],inputdata[i][5]);
				parseStr=parseStr1.split(",");
				
				//MyMethods.PrintMe("Output[0]: ", output[0]);
				//MyMethods.PrintMe("inputdata[i][3]: ", inputdata[i][3]);
				if(parseStr[1].contains(inputdata[i][3]))
				{
					if(parseStr[0].equals(""))
					{
						MyMethods.ExcelFileWriteforAPI(output,sheetName,i+1,"Successful");
						MyMethods.PrintMe("Test Result: ", "Successful");
						noofsuccess++;
					}
					else
					{
						if(VariableModule.limit.equals(parseStr[0]))
						{
							MyMethods.ExcelFileWriteforAPI(output,sheetName,i+1,"Successful");
							MyMethods.PrintMe("Test Result: ", "Successful");
							System.out.println("Limit: "+VariableModule.limit);
							noofsuccess++;
						}
						else
						{
							if(Integer.parseInt(VariableModule.limit)>Integer.parseInt(parseStr[0]))
							{
								MyMethods.ExcelFileWriteforAPI(output,sheetName,i+1,"Successful");
								MyMethods.PrintMe("Test Result: ", "Successful");
								System.out.println("Limit1: "+parseStr[0]);
								noofsuccess++;
							}
							else
							{
								MyMethods.ExcelFileWriteforAPI(output,sheetName,i+1,"Failed!");
								MyMethods.PrintMe("Test Result: ", "Failed");
								System.out.println("Limit Failed: "+parseStr[0]);
								noofsuccess++;
							}

						}
					}

				}
				else
				{
					MyMethods.ExcelFileWriteforAPI(output,sheetName, i+1,"Failed");
					MyMethods.PrintMe("Test Result: ", "Failed");
					nooffailed++;
				}
				
				
			}
			
			
			
			MyMethods.ExcelFileWriteforSummary(sheetName, String.valueOf(noofrow), String.valueOf(noofsuccess), String.valueOf(nooffailed));
			//System.out.println("Total Cases: "+noofrow);
			//System.out.println("Total Successful: "+noofsuccess);
			//System.out.println("Total Failed: "+nooffailed);
			noofrow=0;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	
	public static void CreateNewAddonAPI() throws JSONException, IOException
	{
		
	    HashMap<String, String> chash1 = new HashMap<String, String>();

	    chash1.put("name", "Beef Petti");
	    chash1.put("price", "10.22");
	    
	    HashMap<String, String> chash = new HashMap<String, String>();

	    chash.put("email", "mizan@prefeex.com");
	    chash.put("password", "1234");
	    chash.put("userType", chash1.toString());
	    
	    

	    
	    String input= MyMethods.JsonBuilder(chash);
	    System.out.println("Input: "+input);

	    //String[] output = MyMethods.postAPI("/api/login",input,1);
	    //MyMethods.JsonParserforToken(output[0]);

	    
	}
	
	public static void UserInfoAPITest()
	{
	    try {
			//MyMethods.getAPI("/api/users/me",2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void UserInfoUpdateAPITest()
	{
	    try {
		    String input = "{\r\n" + 
		    		"	\"lastName\" : \"Rahman1\"\r\n" + 
		    		"}";
		    
			//MyMethods.putAPI("/api/users/me",input,2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
