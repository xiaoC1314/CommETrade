package com.zhzx.uip.commons.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

public final class JSONUtils {

	private final static Log log = LogFactory.getLog(JSONUtils.class);
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

	@SuppressWarnings("unchecked")
	public static Map<String, Object> decodeJsonToMap(String json) {
		if (org.apache.commons.lang.StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			return objectMapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			log.error("解析JSON错误：" + json, e);
		} catch (JsonMappingException e) {
			log.error("解析JSON错误：" + json, e);
		} catch (IOException e) {
			log.error("解析JSON错误：" + json, e);
		}
		return null;
	}

//	public static void main(String[] args) {
//		Object map = decodeJsonToMap(
//				"{\"code\":\"A00000\",\"data\":{\"iconurl\":\"http:\\/\\/img2.qiyipic.com\\/farm\\/icon\\/l\\/20130313\\/x6\\/bc\\/20130313x6bczfyse5fc30f8b10c8e80_9085895804.jpeg\"}}");
//		System.out.println(map);
//	}

	@SuppressWarnings("unchecked")
	public static List<Object> decodeJsonToList(String json) {
		try {
			return objectMapper.readValue(json, List.class);
		} catch (JsonParseException e) {
			log.error("解析JSON错误：" + json, e);
		} catch (JsonMappingException e) {
			log.error("解析JSON错误：" + json, e);
		} catch (IOException e) {
			log.error("解析JSON错误：" + json, e);
		}
		return null;
	}

	/**
	 * Decode a json string to T object
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T decodeJson(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			log.error("解析JSON错误：" + json, e);
		} catch (JsonMappingException e) {
			log.error("解析JSON错误：" + json, e);
		} catch (IOException e) {
			log.error("解析JSON错误：" + json, e);
		}
		return null;
	}

	public static String jsonEncode(Object obj) {
		JsonGenerator jsonGenerator = null;
		StringWriter writer = null;
		try {
			writer = new StringWriter();
			jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(writer);
			objectMapper.writeValue(jsonGenerator, obj);
			return writer.toString();
		} catch (IOException ex) {
			log.error("生成JSON错误：" + obj.getClass(), ex);
		} finally {
			if (jsonGenerator != null) {
				try {
					jsonGenerator.close();
				} catch (IOException e) {
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}
}
