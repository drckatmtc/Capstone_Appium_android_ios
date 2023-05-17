package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJSON {

	JSONParser parser = new JSONParser();
	String jsonVal = "";
	static ReadConfig cf = new ReadConfig();

	public String getJSON(String path, String cap) throws FileNotFoundException, IOException, ParseException {
		try {
			Object obj = parser.parse(new FileReader(path));
			JSONObject jsonObject = (JSONObject) obj;
			jsonVal = (String) jsonObject.get(cap);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonVal;
	}

}
