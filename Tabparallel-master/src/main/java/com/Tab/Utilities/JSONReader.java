package com.Tab.Utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader
{
	public static void main(String[] args) throws IOException, ParseException
	{
		JSONParser jsonParser = new JSONParser();
		try
		{
			FileReader reader = new FileReader(
					"C:\\Users\\abhay\\git\\Tabparallel\\src\\test\\resources\\TestData\\data.json");
			// Read JSON file
			Object obj = jsonParser.parse(reader);
			JSONArray usersList = (JSONArray) obj;
			System.out.println(usersList); // This prints the entire json file
			System.out.println(usersList.size());
			JSONObject user = (JSONObject) usersList.get(0);
			String result = (String) user.get("result");
			System.out.println(result);
			usersList.remove(user);
			// Write JSON file
			System.out.println(usersList); // This prints the entire json file
			System.out.println(usersList.size());
			try (FileWriter file = new FileWriter(
					"C:\\Users\\abhay\\git\\Tabparallel\\src\\test\\resources\\TestData\\data.json"))
			{
				file.append(usersList.toJSONString());
				file.flush();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			System.out.println(user);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
