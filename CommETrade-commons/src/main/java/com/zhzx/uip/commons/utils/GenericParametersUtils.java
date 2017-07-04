package com.zhzx.uip.commons.utils;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GenericParametersUtils {

	//^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$
	//email 正则
	private static String EMAIL_REG = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$" ;
	//手机  正则
	private static String MOBILE_REG = "^((13[0-9])|(15[0-9])|(18[0-9])|(14[57]))\\d{8}$" ;
	//密码 正则 字母 数字  6到15 位
	private static String PASSWORD_REG = "^[a-zA-Z_0-9]{6,15}$" ;
	//用户名 正则 字母 数字 中文 
	private static String  USER_NAME_REG ="[\u4e00-\u9fa5\\w]+"    ;//"^[a-zA-Z_0-9\u4e00-\u9fa5]{4,15}$" ;
    //手机验证码正则
    private static String MOBILE_VERIFYCODE_REG = "^\\d{6}$" ;
											
	
	//昵称 正则 字母 数字 中文 2到15位
	private static String  USER_NICKNAME_REG = "^[a-zA-Z_0-9\u4e00-\u9fa5]{2,15}$" ;
	private static String URL_REG = "^\\b(((http|https?|ftp)://)+(.+))$*";
	
	/**
	 * 验证邮箱格式 长度不超过45位
	 * @param email
	 *            邮箱
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>  
	 */
	static final int EMAIL_LENGTH = 45 ;
	public static boolean  regEmail(String email){
		if(StringUtils.isBlank(email)){
			return false ;
		}
		
		boolean flag = email.length() > EMAIL_LENGTH ? false : true  ;
		if(flag)
			flag =  regStr(EMAIL_REG,email);
		
		return flag ;
	}
	
	/**
	 * 验证手机格式
	 * @param mobile
	 *            手机
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>  
	 */
	public static boolean regMobile(String mobile){
		if(StringUtils.isBlank(mobile)){
			return false ;
		 }
		return regStr(MOBILE_REG,mobile);
	}

    /**
     * 手机验证码格式
     * @param mobileCode
     * @return
     */
    public static boolean regMobileVerifyCode(String mobileCode){
        if(StringUtils.isBlank(mobileCode)){
            return false ;
        }
        return regStr(MOBILE_VERIFYCODE_REG,mobileCode);
    }
	
	public static boolean regUrl(String url){
		if(StringUtils.isBlank(url)){
			return false ;
		}
		return regStr(URL_REG,url);
	}
	
	 /**
	  * 验证密码条件
     * @param password
     *              密码
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>  
     */    
    public static boolean regPassword(String password){    
    	if(StringUtils.isBlank(password)){
			return false ;
		 }    	
        return regStr(PASSWORD_REG, password);    
    }  
    
    
	/**
	 * 验证用户名 条件
	 * @param userName
	 *            用户名
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>  
	 */
	public static boolean regUserName(String userName){
		if(StringUtils.isBlank(userName)){
			return false ;
		 }    	
        boolean flag = regStr(USER_NAME_REG, userName); 
        if(flag){
        	int userNameLength= count(userName);
        	if(userNameLength < 4 || userNameLength > 15) {
    			flag =  false ;
    		}
        }
        return flag ;
	}
	
	/**
	 * 计算字符串长度，汉字算两个字符
	 * @param str
	 * @return
	 */
	private static int count(String str) { 
	    if(str == null || str.length() == 0) { return 0; } 
	    int count = 0; 
	    char[] chs = str.toCharArray(); 
	    for(int i = 0; i < chs.length; i++) { 
	    	count += (chs[i] > 0xff) ? 2 : 1;
	    } 
	    return count; 
	}
	
	/**
	 * 验证用户名 条件
	 * @param nickName
	 *            用户名
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>  
	 */
	public static boolean regNickName(String nickName){
		if(StringUtils.isBlank(nickName)){
			return false ;
		 }    	
        return regStr(USER_NICKNAME_REG, nickName);    
	}
	
	/**
	 *  正则表达式匹配
	 * @param reg
	 *            正则
	 * @param str
	 *            匹配的字符串
	 * @return 匹配结构
	 */
	private static boolean regStr(String reg,String str){
		 Pattern p = Pattern.compile(reg);
		 Matcher m = p.matcher(str);
		 return m.matches();
	}
	
	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//       System.out.println(regUserName("中国中国中国中q!"));
//       System.out.println(regEmail("234234325treterttttttttttttttr@11123213123123123212.com"));
//       System.out.println(regStr("[http|https]+[://]+[0-9A-Za-z:/[-]_#[?][=][.][&]]*","http://www.sina.com"));
//
//	}

}
