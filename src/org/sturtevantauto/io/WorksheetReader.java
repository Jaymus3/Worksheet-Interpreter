package org.sturtevantauto.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class WorksheetReader {
	
	public static void readAcu() throws IOException, ClassNotFoundException, SQLException
	{
	String line;
	File worksheet = new File("/Users/sturtevantauto/Pictures/Car_Pictures/XPS/6715329.acu");
		BufferedReader reader = new BufferedReader(new FileReader(worksheet));
		int i = 0;
		while ((line = reader.readLine()) != null)
		{
			if(line.contains("-"))
			{
				String[] lines = line.split("-");
				if(Character.isDigit(lines[0].charAt((lines[0].length() - 1))))
						{
							String[] endlines = lines[1].split("   ");
							endlines[0] = endlines[0].trim();
							int partnum = Integer.parseInt(lines[0].substring((lines[0].length() - 3), lines[0].length()));
							String partend = endlines[0];
							System.out.println(DATReader.findPartName(partnum));
							System.out.println(partnum + "-" + partend);
						}
			}
			i++;
	    }
		reader.close();

	}
}
