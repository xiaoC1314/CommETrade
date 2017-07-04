package com.zhzx.uip.commons.utils;

import com.google.common.collect.Sets;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpClientUtils {
	private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);
	
	public static final String USER_AGENT = "HttpClient/3.1/PP";
	public static final int UNKNOWN_HTTP_CODE = 600;

    private static HttpClient httpclient;

    static {
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(2000);
        connectionManager.getParams().setSoTimeout(4000);
        connectionManager.getParams().setMaxTotalConnections(1000);
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(100);
        httpclient = new HttpClient(connectionManager);
    }
	
	public static final String httpGet(String url){
        return httpGet(url, null, null);
	}
	
	public static final String httpGet(String url, Map<String, String> params){
		return httpGet(url, params, null);
	}


    public static final String httpGet(String url, Map<String, String> params, Map<String, String> headers){
        String fullurl = buildGetUrl(url, params);
        log.info("[HTTP][GET][URL][{}]", fullurl);
        GetMethod method = new GetMethod(fullurl);
        if(headers != null){
            for (Map.Entry<String, String> en : headers.entrySet()) {
                method.addRequestHeader(en.getKey(), en.getValue());
            }
        }
        method.addRequestHeader("User-Agent", USER_AGENT);
        int code = UNKNOWN_HTTP_CODE;
        try {
            code = httpclient.executeMethod(method);
            if(isValidHttpCode(code)){
                return readFullResponseBody(method);
            }else{
                log.error("Http response code(" + code + ") is not 200 when get " + fullurl + " with parameters " + params);
            }
        } catch (HttpException e) {
            log.error("Unexpected HttpException when http get " + fullurl + " with parameters " + params, e);
        } catch (IOException e) {
            log.error("Unexpected IOException when http get " + fullurl + " with parameters " + params, e);
        }
        return null;
    }

    public static final <T> T httpGetToObject(String url, Map<String, String> params,  Map<String, String> headers, Class<T> tClass){
        String response = HttpClientUtils.httpGet(url, params, headers);
        if(response == null){
            log.error("Failed to http get "+HttpClientUtils.buildGetUrl(url, params) + " for 3 times, please check your network");
            return null;
        }
        T responseMap = JSONUtils.decodeJson(response, tClass);
        if(responseMap == null){
            log.error("Some error happens when getting json from "+HttpClientUtils.buildGetUrl(url, params) + ", the repsonse is " + response);
            return null;
        }
        return responseMap;
    }



//    public static void main(String[] args) throws Exception {
//        TAInfoPara taInfoBean = new TAInfoPara();
//        taInfoBean.setChannel("1");
//        taInfoBean.setFormat("json");
//        taInfoBean.setFunction("T10");
//        taInfoBean.setMerid("InternalUsers");
//        taInfoBean.setSignmode("md5");
//        taInfoBean.setVersion("V1.0");
//        taInfoBean.setSignmsg("23");
//        String url = "http://172.16.128.33:8001/fundapi/restful/query/tainfo";
//        Map<String, String> map = org.apache.commons.beanutils.BeanUtils.describe(taInfoBean);
//        map.remove("class");
//        HSRespResult hsRespResult = httpGetToObject(url, map, null, HSRespResult.class);
//        System.out.print(hsRespResult);
//    }

    public static String httpPost(String url,  Map<String, String> params) {
        return httpPost(url, null, params,null);
    }
    
    public static String httpPost(String url,  Map<String, String> params, Map<String, String> headers) {
        return httpPost(url, null, params,headers);
    }

    public static String httpPost(String url, String sendData, Map<String, String> params, Map<String, String> headers) {
        PostMethod method = new PostMethod(url);
        int code = UNKNOWN_HTTP_CODE;
        try {
            if(!StringUtils.isBlank(sendData))
                method.setRequestEntity(new StringRequestEntity(sendData, null, "utf-8"));
            if(params != null){
                for (Map.Entry<String, String> en : params.entrySet()) {
                    if(en.getValue() != null){
                        method.addParameter(en.getKey(), en.getValue());
                    }
                }
            }
            if(headers != null){
                for (Map.Entry<String, String> en : headers.entrySet()) {
                    method.addRequestHeader(en.getKey(), en.getValue());
                }
            }
            method.addRequestHeader("User-Agent", USER_AGENT);
            method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            method.getParams().setParameter(HttpMethodParams.HTTP_URI_CHARSET, "UTF-8");
            code = httpclient.executeMethod(method);
            if(isValidHttpCode(code)){
                return readFullResponseBody(method);
            }else{
                String fullurl = buildGetUrl(url, params);
                log.error("Http response code(" + code + ") is not 200 when post " + fullurl + " with parameters " + params);
            }
        } catch (HttpException e) {
            String fullurl = buildGetUrl(url, params);
            log.error("Unexpected HttpException when http post " + fullurl + " with parameters " + params, e);
        } catch (IOException e) {
            String fullurl = buildGetUrl(url, params);
            log.error("Unexpected IOException when http post " + fullurl + " with parameters " + params, e);
        }
        return null;
    }


    public static final <T> T httpPostToObject(String url, Map<String, String> params, Class<T> clazz){
        String response = HttpClientUtils.httpPost(url, params, null);
        if(response == null){
            log.error("Failed to http get "+HttpClientUtils.buildGetUrl(url, params) + " for 3 times, please check your network");
            return null;
        }
        T responseMap = JSONUtils.decodeJson(response, clazz);
        if(responseMap == null){
            log.error("Some error happens when getting json from "+HttpClientUtils.buildGetUrl(url, params) + ", the repsonse is " + response);
            return null;
        }
        return responseMap;
    }



    private static final Set<Integer> INVALID_HTTP_CODES = Sets.newHashSet(499, 500, 502, 403, 404);
	public static final boolean isValidHttpCode(int code){
		return !INVALID_HTTP_CODES.contains(code);
	}

	public static String buildGetUrl(String url, Map<String, String> params) {
		return params == null || params.isEmpty() ? url : new StringBuilder(256).append(url).append("?").append(urlencode(params)).toString();
	}


	
	public static final Map<String, Object> httpPostToMap(String url, Map<String, String> params){
		String response = HttpClientUtils.httpPost(url, params, null);
		if(response == null){
			log.error("Failed to http get "+HttpClientUtils.buildGetUrl(url, params) + " for 3 times, please check your network");
			return null;
		}
		Map<String ,Object> responseMap = JSONUtils.decodeJsonToMap(response);
		if(responseMap == null){
			log.error("Some error happens when getting json from "+HttpClientUtils.buildGetUrl(url, params) + ", the repsonse is " + response);
			return null;
		}
		return responseMap;
	}
	
	public static final List<Object> httpPostToList(String url, Map<String, String> params){
		String response = HttpClientUtils.httpPost(url, params, null);
		if(response == null){
			log.error("Failed to http post "+HttpClientUtils.buildGetUrl(url, params) + " for 3 times, please check your network");
			return null;
		}
		List<Object> responseMap = JSONUtils.decodeJsonToList(response);
		if(responseMap == null){
			log.error("Some error happens when getting json from "+url+ " with post parameters " + params + ", the repsonse is " + response);
			return null;
		}
		return responseMap;
	}


    public static final String readFullResponseBody(HttpMethod method){
		BufferedReader reader = null;
		
		try{
			StringBuilder sb = new StringBuilder(256);
			reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),"utf-8"));
			for(String line = reader.readLine(); line != null; line = reader.readLine()){
				sb.append(line).append('\n');
			}
			return sb.toString().trim();
		}catch(IOException ex){
			log.error("Unexpected IOException when read http response", ex);
			return null;
		}finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {

                }
            }
        }
    }
	
	
	public static final String urlencode(String value){
		if(StringUtils.isBlank(value)){
			return "";
		}
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("Fail to encode string " + value + " with UTF-8");
			return value;
		}
		
	}
	
	public static final String urlencode(Map<String, String> params){
		if(params == null || params.isEmpty()){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, String> en : params.entrySet()){
			sb.append(urlencode(en.getKey())).append("=").append(urlencode(en.getValue())).append("&");
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}
	
	public static final boolean isValidLink(String link){
		
		URL url;
		try {
			 url = new URL(link);
			 HttpURLConnection connt = (HttpURLConnection)url.openConnection();
			 connt.setRequestMethod("HEAD");
			 String strMessage = connt.getResponseMessage();
            if(StringUtils.isBlank(strMessage)){
                return false;
            }
			 if (strMessage.compareTo("Not Found") == 0) {
				 return false;
			 }
			 connt.disconnect();
		} catch (MalformedURLException e) {
			log.error("url is not valid:" + link);
			return false;
		} catch (IOException e) {
			log.error("url is not valid:"+ link);
			return false;
		}
		return true;
	}

}
