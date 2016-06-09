package org.sturtevantauto.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

public class WorksheetReader {
	
	
	/**
	 * Reads the ACU file and strips it down into a more programmatically friendly version.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void readAcu() throws IOException, ClassNotFoundException, SQLException
	{
	String line;
	File worksheet = new File("/Users/sturtevantauto/Pictures/Car_Pictures/XPS/6715329.acu");
		BufferedReader reader = new BufferedReader(new FileReader(worksheet));
		int i = 1;
		String namebegin = null;
		boolean sw = false;
		int linebegin = 0;
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
							//System.out.println(findLine(DATReader.findPartName(partnum)));
							String name = DATReader.findPartName(partnum);
							if(!name.equals(namebegin))
							{
							namebegin = name;
							sw = true;
							}
							
							if(sw)
							{
							sw = false;
							linebegin = findLine(name);
							}
							
							String[] linetext = findText(linebegin, i, name);
							int q = 1;
							for(String print : linetext)
							{
								if(print != null)
								{ 
								print = print.replace(".", "");
								System.out.println(q + ": " + print);
								q++;
								}
							}
							linebegin = i;
							//System.out.println(partnum + "-" + partend);
							
						}
			}
			i++;
	    }
		reader.close();

	}
	
	/**
	 * Finds the line of a given part name.
	 * @param partname
	 * @return int linenumber
	 * @throws IOException
	 */
	
	public static int findLine(String partname) throws IOException
	{
		String line;
		File worksheet = new File("/Users/sturtevantauto/Pictures/Car_Pictures/XPS/6715329.acu");
			BufferedReader reader = new BufferedReader(new FileReader(worksheet));
			int i = 1;
			int linenum = 0;
			while ((line = reader.readLine()) != null)
			{
				if(line.contains(partname))
					linenum = i;
				
				i++;
			}
			reader.close();
			return linenum;
	}
	
	/**
	 * When given the integer linebegin, lineend, and a part name, this function scans the text from the line begin to the line
	 * end and grabs all of the text, excluding formatting.
	 * @param linebegin
	 * @param lineend
	 * @param partname
	 * @throws IOException
	 */
	public static String[] findText(int linebegin, int lineend, String partname) throws IOException
	{
		String line;
		String[] lines = new String[30];
		File worksheet = new File("/Users/sturtevantauto/Pictures/Car_Pictures/XPS/6715329.acu");
			BufferedReader reader = new BufferedReader(new FileReader(worksheet));
			int i = 0;
			int s = 0;
			while((line = reader.readLine()) != null)
			{
				if(i < linebegin)
				i++;
				
				if(i >= linebegin && i <= lineend)
				{
					if(line.contains(partname))
						line = line.substring(partname.length());
						
					
					String[] splitline1 = line.split("    1   ");
					String[] splitlines = splitline1[0].split("   1   ");
					splitlines[0] = splitlines[0].trim();
					if(!splitlines[0].contains("0   0   0   0   0       0") && !splitlines[0].isEmpty() &&
					  (StringUtils.countMatches(splitlines[0], "/") < 4) && !splitlines[0].contains("0   0   0       0")
					  && !(Character.isDigit(splitlines[0].charAt(0)) && splitlines[0].toCharArray().length == 1))
					{
					if(splitlines[0].charAt(0) == 'R' || splitlines[0].charAt(0) == 'L')
						splitlines[0] = splitlines[0].replace(".", "");
					
					
					lines[s] = splitlines[0];
					s++;
					i++;
					}
				}
			}
		reader.close();
		return lines;
	}
}
