package edu.iu.perfin.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONUtil {

	public static String toJSON(Object clazz) {
		
		String strJSON = "";
		try {
			Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
			strJSON  = gsonPretty.toJson(clazz);
		} catch (Exception e) {
			strJSON = e.getMessage(); 
		}
		
		return strJSON;
	}
	
	public static <T> T importFromJSON(String jsonString, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return (T) mapper.readValue(jsonString, clazz);
		} catch (JsonGenerationException e) {
			jsonString = e.getMessage(); 
		} catch (JsonMappingException e) {
			jsonString = e.getMessage(); 
		} catch (IOException e) {
			jsonString = e.getMessage(); 
		}catch (Exception e) {
			jsonString = e.getMessage(); 
		}
		return null;

	}

}