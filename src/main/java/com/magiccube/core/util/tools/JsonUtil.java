package com.magiccube.core.util.tools;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xingling
 * @since JDK1.6
 * @history 2013-1-6 Xingling build
 */
public class JsonUtil {
	
	static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
	
	static ObjectMapper mapper =null;
	
	public static String objectToJson(Object object){
		if(mapper ==null){
			mapper = new ObjectMapper();
		}
		String result = null;
		try {
			result = mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			LOGGER.error(e.toString());
		} catch (JsonMappingException e) {
			LOGGER.error(e.toString());
		} catch (IOException e) {
			LOGGER.error(e.toString());
		}
		return result;
	}
}
