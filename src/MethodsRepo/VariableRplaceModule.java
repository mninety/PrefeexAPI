package MethodsRepo;

import java.io.IOException;

import org.json.JSONException;

import StartPrefeexAPI.VariableModule;

public class VariableRplaceModule {

	public static String VariableReplace(String sheetName, String input) throws JSONException, IOException
	{
		
		try {
			
			if(sheetName.equals("LoginAPI-POST"))
			{
					input=input.replaceAll("VARadminuser", VariableModule.adminuser);
					input=input.replaceAll("VARadminpass", VariableModule.adminpass);
			}
			else if(sheetName.equals("SignupAPI-POST"))
			{
				//VariableModule.newUser="enduser-"+VariableModule.RandomStringGen(4)+"@enduser.com";
				input=input.replaceAll("VARenduser1", VariableModule.endUser1);
				input=input.replaceAll("VARenduser2", VariableModule.endUser2);
				input=input.replaceAll("VARadminpass", VariableModule.adminpass);
			}
			else if(sheetName.equals("CreateInternalUsersAPI-POST"))
			{
				//VariableModule.supportUser="support-"+VariableModule.RandomStringGen(4)+"@support.com";
				input=input.replaceAll("VARsupport1", VariableModule.supportUser1);
				input=input.replaceAll("VARsupport2", VariableModule.supportUser2);
				input=input.replaceAll("VARadminpass", VariableModule.adminpass);
			}
			else if(sheetName.equals("RequestforpassrecoveryAPI-POST"))
			{
				input=input.replaceAll("VARadminuser", VariableModule.adminuser);
			}
			else if(sheetName.equals("UpdateMyPassword-PUT"))
			{
				input=input.replaceAll("VARadminpass", VariableModule.adminpass);
			}
			else if(sheetName.equals("SearchCustomerAPI-POST"))
			{
				input=input.replaceAll("VARendUser1", VariableModule.endUser1);
			}
			else if(sheetName.equals("SearchInternalUsersAPI-POST"))
			{
				input=input.replaceAll("VARsupportUser1", VariableModule.supportUser1);
			}
			else if(sheetName.equals("CreateBusinessAPI-POST"))
			{
				input=input.replaceAll("VARbussinessName1", VariableModule.bussinessName1);
				input=input.replaceAll("VARbussinessName2", VariableModule.bussinessName2);
				input=input.replaceAll("VARbussinessEmail1", VariableModule.bussinessEmail1);
				//System.out.println("Email1: "+VariableModule.bussinessEmail1);
				input=input.replaceAll("VARbussinessEmail2", VariableModule.bussinessEmail2);
				//System.out.println("INPUT1: "+input);
				input=input.replaceAll("VARbussinessPhone1", VariableModule.bussinessPhone1);
				input=input.replaceAll("VARbussinessPhone2", VariableModule.bussinessPhone2);
			}
			
			else if(sheetName.equals("CreateBusinessUserAPI-POST"))
			{
				input=input.replaceAll("VARbussinessUser1", VariableModule.bussinessUser1);
				input=input.replaceAll("VARbussinessUserPhone1", VariableModule.bussinessUserPhone1);
				input=input.replaceAll("VARbussinessUser2", VariableModule.bussinessUser2);
				input=input.replaceAll("VARbussinessUserPhone2", VariableModule.bussinessUserPhone2);
				input=input.replaceAll("VARadminpass", VariableModule.adminpass);
			}
			else if(sheetName.equals("CreateNewTagAPI-POST"))
			{
				input=input.replaceAll("VARTagName1", VariableModule.TagName1);
			}
			else if(sheetName.equals("CreateNewFacilityAPI-POST"))
			{
				input=input.replaceAll("VARFacilityName1", VariableModule.FacilityName1);
			}
			else if(sheetName.equals("CreateFoodTypeAPI-POST"))
			{
				input=input.replaceAll("VARFoodTypeName1", VariableModule.FoodTypeName1);
			}
			else if(sheetName.equals("CreateFoodMenuStatusAPI-POST"))
			{
				input=input.replaceAll("VARfmStatus1", VariableModule.fmStatus1);
			}
			else if(sheetName.equals("CreateAddonTypeAPI-POST"))
			{
				input=input.replaceAll("VARAddonTypeName1", VariableModule.AddonTypeName1);
			}
			else if(sheetName.equals("CreateAddonStatusAPI-POST"))
			{
				input=input.replaceAll("VARAddonStatus1", VariableModule.AddonStatus1);
			}
			else if(sheetName.equals("CreateFoodMenuiTemAPI-POST"))
			{
				input=input.replaceAll("VARFMiTem1", VariableModule.FMiTem1);
			}
			else if(sheetName.equals("UpdateMenuiTemAPI-PUT"))
			{
				input=input.replaceAll("VARFMiTem1", VariableModule.FMiTem1);
			}
			else if(sheetName.equals("CreateNewAddonAPI-POST") || sheetName.equals("UpdateAddonAPI-PUT"))
			{
				input=input.replaceAll("VARAddonName1", VariableModule.AddonName1);
				input=input.replaceAll("VARAddonOption1", VariableModule.AddonOption1);
			}
			else if(sheetName.equals("CreateOrderAPI-POST"))
			{

				input=input.replaceAll("VARbussinessName1ID", VariableModule.bussinessName1ID);
				input=input.replaceAll("VARFMiTem1ID", VariableModule.FMiTem1ID);
				input=input.replaceAll("VARAddonName1ID", VariableModule.AddonName1ID);
				input=input.replaceAll("VARAddonOption1ID", VariableModule.AddonOption1ID);

			}
			else if(sheetName.equals("CreateMarketigPushAPI-POST"))
			{
				System.out.println("EndUserID: "+VariableModule.enduser1ID);
				input=input.replaceAll("VARenduser1ID", VariableModule.enduser1ID);
				input=input.replaceAll("VARNotifTitle1", VariableModule.NotifTitle1);

			}
			else if(sheetName.equals("RegisterBankofBusinessAPI-POST"))
			{
				input=input.replaceAll("VARbankAccount1", VariableModule.bankAccount1);

			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return input;
	}

	public static String EndpointReplace(String sheetName, String input) throws JSONException, IOException
	{
		
		try {
			
			if(sheetName.equals("GetParticularCustomerAPI-GET") || sheetName.equals("UpdateCustomerStatusAPI-PUT"))
			{
				input=input.replaceAll("ENDPenduser1ID", VariableModule.enduser1ID);
			}
			else if(sheetName.equals("UpdateInternalUsersAPI-PUT") || sheetName.equals("GetParticulInternalUsersAPI-GET"))
			{
				input=input.replaceAll("ENDPsupportUser1ID", VariableModule.supportUser1ID);
			}
			else if(sheetName.equals("GetParticularBusinessAPI-GET") || sheetName.equals("CreateBusinessUserAPI-POST") || sheetName.equals("UpdateBusinessAPI-PUT") || sheetName.equals("GetallUserunderBusinessAPI-GET") || sheetName.equals("GetallUserunderBusinessAPI1-GET") || sheetName.equals("CreateFoodMenuiTemAPI-POST") || sheetName.equals("GetActiveMenuofaBusiAPI-GET") || sheetName.equals("GetAllMenuofaBusiAPI-GET") || sheetName.equals("RegisterBankofBusinessAPI-POST") || sheetName.equals("GetRegisteredBankofBusiAPI-GET") || sheetName.equals("GetPartRegiBankofBusiAPI-GET"))
			{
				input=input.replaceAll("ENDPbussinessName1ID", VariableModule.bussinessName1ID);
				input=input.replaceAll("ENDPbussinessName2ID", VariableModule.bussinessName2ID);
			}
			else if(sheetName.equals("GetParticulaBusinessUserAPI-GET") || sheetName.equals("UpdateBusinessUserAPI-PUT"))
			{
				input=input.replaceAll("ENDPbussinessName1ID", VariableModule.bussinessName1ID);
				input=input.replaceAll("ENDPbussinessUser1ID", VariableModule.bussinessUser1ID);
			}
			else if(sheetName.equals("GetBusinesstagAPI-GET"))
			{
				input=input.replaceAll("ENDPTagName1ID", VariableModule.TagName1ID);

			}
			else if(sheetName.equals("GetParticularMenuofaBusiAPI-GET") || sheetName.equals("UpdateMenuiTemAPI-PUT") || sheetName.equals("CreateNewAddonAPI-POST") || sheetName.equals("GetAllAddonofaMenuAPI-GET") || sheetName.equals("GetAllAddonOptofaMenuAPI-GET"))
			{
				input=input.replaceAll("ENDPbussinessName1ID", VariableModule.bussinessName1ID);
				input=input.replaceAll("ENDPFMiTem1ID", VariableModule.FMiTem1ID);

			}
			else if(sheetName.equals("UpdateAddonAPI-PUT") || sheetName.equals("GetParticularAddonofMenuAPI-GET"))
			{
				input=input.replaceAll("ENDPbussinessName1ID", VariableModule.bussinessName1ID);
				input=input.replaceAll("ENDPFMiTem1ID", VariableModule.FMiTem1ID);
				input=input.replaceAll("ENDPAddonName1ID", VariableModule.AddonName1ID);

			}
			
			else if(sheetName.equals("GetParticularorderbyMeAPI-GET") || sheetName.equals("GetParticularOrderAPI-GET") || sheetName.equals("CancelAnOrderAPI1-POST") || sheetName.equals("ApproveAnOrder-GET") || sheetName.equals("DeclineAnOrder-POST") || sheetName.equals("CancelAnOrderAPI2-POST") || sheetName.equals("ConfirmAnOrder-POST") || sheetName.equals("GetAllActivityofaTicketAPI-GET") || sheetName.equals("GetTicketDetailsAPI-GET"))
			{
				input=input.replaceAll("ENDPOrder1ID", VariableModule.Order1ID);
				input=input.replaceAll("ENDPTicket1ID", VariableModule.Ticket1ID);
			}
			else if(sheetName.equals("GetMarketingPushDetailAPI-GET") || sheetName.equals("GetparticularpushrecipAPI-GET"))
			{
				input=input.replaceAll("ENDPNotifTitle1ID", VariableModule.NotifTitle1ID);

			}
			else if(sheetName.equals("GetPartRegiBankofBusiAPI-GET") || sheetName.equals("UnlinkBankfromBusiAPI-GET") || sheetName.equals("UpdateBankInfoAPI-PUT") || sheetName.equals("DeleteParticularBankInfoAPI-DEL"))
			{
				input=input.replaceAll("ENDPbussinessName1ID", VariableModule.bussinessName1ID);
				input=input.replaceAll("ENDPbankAccount1ID", VariableModule.bankAccount1ID);

			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return input;
	}

	
}
