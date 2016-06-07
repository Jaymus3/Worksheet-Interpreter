package org.sturtevantauto.main;

import java.io.IOException;
import java.sql.SQLException;

import org.sturtevantauto.io.WorksheetReader;

public class Main {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException
	{
		WorksheetReader.readAcu();
	}

}
