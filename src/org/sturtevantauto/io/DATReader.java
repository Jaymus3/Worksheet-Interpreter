package org.sturtevantauto.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DATReader {
	private static String dbUrl = "jdbc:mysql://127.0.0.1:3306?autoReconnect=true&useSSL=false";
	private static String dbUsername = "worksheetinterpreter";
	private static String dbPassword = "ED#iNhNhOEt$!2gr";
	/**
	 * Remember not to run this function ever again. All of this information is in the database.
	 * This function is staying here for mysterious reasons.
	 */
	public static void readDat() throws IOException, ClassNotFoundException, SQLException
	{
		String line;
		File worksheet = new File("/Users/sturtevantauto/Pictures/Car_Pictures/XPS/INV.DAT");
		BufferedReader reader = new BufferedReader(new FileReader(worksheet));
		boolean founddata = false;
		while ((line = reader.readLine()) != null)
		{
			if(line.contains("FRONT END"))
			founddata = true;
			
			if(founddata)
			{
				String partname = line.substring(0, 19);
				String partnumS = line.substring(19, 23);
				partnumS = partnumS.trim();
				int partnum = Integer.parseInt(partnumS);
				System.out.println(partname + " " + partnum);
				writeToDatabase(partname, partnum);
				
			}
		}
		reader.close();
	}
	/**
	 * This function should not be run, ever.  It's already done its job, which was to populate the database with part names and numbers.
	 */
	public static void writeToDatabase(String partname, int partnum) throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
	    Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
	    Statement statement = connection.createStatement();
		ResultSet use = statement.executeQuery("USE car_parts");
		statement.executeUpdate("INSERT INTO `PartName_Number` (`Name`, `Number`) VALUES ('" + partname + "', '" + partnum + "')");
		use.close();
		statement.close();
		connection.close();
	}
	/**
	 * Finds the name of a part when given the part number by parsing the index in the SQL database.
	 * @param partnumber
	 * @return String partname
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String findPartName(int partnumber) throws ClassNotFoundException, SQLException
	{
		String partname = null;
		Class.forName("com.mysql.jdbc.Driver");
	    Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
	    Statement statement = connection.createStatement();
		ResultSet use = statement.executeQuery("USE car_parts");
	    ResultSet rs = statement.executeQuery("SELECT * FROM PartName_Number");
	    while(rs.next())
	    {
	    	if(rs.getInt("Number") == partnumber)
	    	{
	    		partname = rs.getString("Name");
	    	}
	    }
	    use.close();
		statement.close();
		connection.close();
		rs.close();
		return partname;
	}
}
