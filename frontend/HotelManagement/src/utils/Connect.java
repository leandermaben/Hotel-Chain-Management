package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	public static Connection dbConnect() {
    	Connection con=null;
    	try {
    	Class.forName("oracle.jdbc.driver.OracleDriver");  
	    con=DriverManager.getConnection(  "jdbc:oracle:thin:@localhost:1521:XE","system","Leroyale7"); 
        }
    	catch(Exception e) {e.printStackTrace();}
    	return con;
   }

}
