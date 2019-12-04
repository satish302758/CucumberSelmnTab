package com.Tab.Driver;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class EventLog
{
	private static Logger log = null;
	public static Logger getLogInstance()
	{
		return log;
	}
	public static void setupLogger()
	{
		String dir = System.getProperty("user.dir") + "\\src\\test\\resources\\logs\\";
		// Check Whether Log Already Exists
		File file = new File(dir + "Events Log" + ".txt");
		if (file.exists())
		{
			file.delete();
		}
		FileHandler fh = null;
		log = Logger.getLogger("Webdriver Logger");
		try
		{
			fh = new FileHandler(file.getPath(), true);
		}
		catch (SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.addHandler(fh);
		log.setUseParentHandlers(false);
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
	}
}