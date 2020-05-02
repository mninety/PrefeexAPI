package StartPrefeexAPI;

import org.apache.log4j.Logger;

import MethodsRepo.MyMethods;


public class MySQLPINGModule extends Thread {
	static Logger logMySQLPINGModule = Logger.getLogger(MySQLPINGModule.class.getName());
	 //int sum=0;
	@Override
    public void run() {
    	while(true) {
			try {


		    	if(VariableModule.Ownconn==null)
		    	{
		    		VariableModule.Ownconn=MyMethods.MysqlConnectionOwn();
		    		
		    		logMySQLPINGModule.debug("Mysql connection reconnect: "+VariableModule.Ownconn);
			    	if(VariableModule.Ownconn==null)
			    	{
			    		//EmailModule.sendEmailWithAttachments("nafiul@revesoft.com", "MySQL connection is null", "Dear Team,\n\n162.222.186.235 server MySQL connection is getting null.\nPlease fix the MySQL connection issue otherwise Mail thread will be failed to send SQA mail.","5");
			    	}
		    		//sum=0;
		    	}
		    	else
		    	{
		    		
		    		logMySQLPINGModule.debug("Mysql connection is alive: "+VariableModule.Ownconn);
		    	}


			} catch (Exception e1) {
				// TODO Auto-generated catch block
				logMySQLPINGModule.debug("MySQL Connection Exception3: "+e1);
			}finally{
				logMySQLPINGModule.debug("MySQL connection checker Module is going to sleep for 120 minute");
				
	    		
				try {
		    		
					Thread.sleep(3600000); //60min
					
					String Test=MyMethods.MysqlConnectionAction("select 1", VariableModule.Ownconn, ",");
					logMySQLPINGModule.debug("Mysql is PINGing: "+Test);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


			
    	}
    }
    
}