package tr.customteam.point_time.core.options;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OptionsManager {
	private static HashMap<String, IOptions> optionsList;
		
	public static void addOption(String optionName, IOptions option) {
		optionsList.put(optionName, option);
	}
	
	public static IOptions getOption(String optionName) {
		return optionsList.get(optionName);
	}
	
	public static boolean verifyIfOptionExist(String resourcesPath, String optionName) {
		File optionFIle = null;
		
		optionFIle = new File(resourcesPath + File.separator + optionName + ".json");
		return optionFIle.exists();
	}

	public static IOptions loadOption(String resourcesPath, String optionName, Class optionClass) {
		File optionFIle = null;
		IOptions option = null;
		
		optionFIle = new File(resourcesPath + File.separator + optionName + ".json");
		
		if(optionFIle.exists()) {
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {
				option =  (IOptions) objectMapper.readValue(optionFIle, optionClass);
				
				if(optionsList == null) {
					optionsList = new HashMap<String, IOptions>();
				}
				
				optionsList.put(optionName, option);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return option;
	}
	
	public static boolean saveOption(String resourcesPath, String optionName) {
		File optionFIle = new File(resourcesPath + File.separator + optionName + ".json");
		IOptions option = null;
		ObjectMapper objectMapper = new ObjectMapper();
		
		option = getOption(optionName);
		
		try {
			objectMapper.writeValue(optionFIle, option);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}	
		
		return true;
	}
}
