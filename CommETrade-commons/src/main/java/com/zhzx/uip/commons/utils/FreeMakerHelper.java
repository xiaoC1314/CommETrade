package com.zhzx.uip.commons.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

public class FreeMakerHelper {

	public static final Logger logger = LoggerFactory.getLogger(FreeMakerHelper.class);

	/**
	 * 获取模板配置
	 * 
	 * @return
	 * @throws java.io.IOException
	 */
	public static Template getTemplate(String filePath) throws IOException {
		Template template = null;
		Configuration cfg = new Configuration();
		// 加载模版
		WebApplicationContext context = ContextUtils.getWebApplicationContext();
		cfg.setServletContextForTemplateLoading(context.getServletContext(), "/");
		cfg.setEncoding(Locale.getDefault(), "UTF-8");
		// 指定模版路径
		template = cfg.getTemplate(Constants.FTL_BASE_DIR + filePath, "UTF-8");
		return template;
	}

	/**
	 * 生成字符串
	 * 
	 * @param template
	 *            模版
	 * @param data
	 *            一个Map的数据结果集
	 * @param data
	 *            一个Map的数据结果集
	 */
	public static String createString(String filePath, Map<String, Object> data) {		
		String result = "";
		try {
			result = createString(getTemplate(filePath),data);
		} catch (IOException e) {
			logger.error("根据freemarker模板生成内容出错: filePath: "+ filePath + " data:" + data,e);
		}
		
		return result;
	}
	
	/**
	 * 生成字符串
	 * 
	 * @param template
	 *            模版
	 * @param data
	 *            一个Map的数据结果集
	 * @param data
	 *            一个Map的数据结果集
	 */
	public static String createString(Template template, Map<String, Object> data) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Writer out = new OutputStreamWriter(baos,"UTF-8");

			// 处理模版
			template.process(data, out);
			out.flush();

			String edmString = baos.toString("UTF-8");
			out.close();

			return edmString;
		} catch (Exception e) {
            logger.error("异常", e);
		}

		return "";
	}

	/**
	 * 去除所有html元素
	 * 
	 * @param input
	 * @param length
	 * @return
	 */
	public static String splitAndFilterString(String input, int length) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		// 去掉所有html元素,
		String str = input.replaceAll("//&[a-zA-Z]{1,10};", "").replaceAll(
				"<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "");
		int len = str.length();
		if (len <= length) {
			return str;
		} else {
			str = str.substring(0, length);
			str += "......";
		}
		return str;
	}

	public static String splitAndFilterString(String input) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		// 去掉所有html元素,
		String str = input.replaceAll("//&[a-zA-Z]{1,10};", "").replaceAll(
				"<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "");
		return str;
	}
	
	/**
	 * 获取文本指定文字个数
	 * 
	 * @param input
	 * @param length
	 * @return
	 */
	public static String splitString(String input, int length) {
		if (input == null || input.trim().equals("")) {
			return "";
		}

		int len = input.length();
		if (len <= length) {
			return input;
		} else {
			input = input.substring(0, length);
			input += "...";
		}
		return input;
	}
}
