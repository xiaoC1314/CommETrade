package com.zhzx.dao.common;

import com.zhzx.uip.commons.utils.DESUtils;
import org.springframework.beans.factory.FactoryBean;

import java.util.Properties;
  
public class PropertiesEncryptFactoryBean implements FactoryBean {  
  
    private Properties properties;  
      
    public Object getObject() throws Exception {  
        return getProperties();  
    }  
  
    public Class getObjectType() {  
        return Properties.class;
    }  
  
    public boolean isSingleton() {  
        return true;  
    }  
  
    public Properties getProperties() {  
        return properties;  
    }  
  
    public void setProperties(Properties inProperties) {  
        this.properties = inProperties;  
        String originalUsername = properties.getProperty("user");  
        String originalPassword = properties.getProperty("password");
        properties.put("user", originalUsername);
        /*if (originalUsername != null){
            String newUsername = deEncryptUsername(originalUsername);  
            properties.put("user", newUsername);  
        }*/
        properties.put("password", originalPassword);
        /*if (originalPassword != null){
            String newPassword = deEncryptPassword(originalPassword);
            properties.put("password", newPassword);
        }  */
    }  
      
    private String deEncryptUsername(String originalUsername){  
        return deEncryptString(originalUsername);  
    }  
      
    private String deEncryptPassword(String originalPassword){  
        return deEncryptString(originalPassword);  
    }  
    //解密算法
    private String deEncryptString(String originalString){  
        return DESUtils.decrypt(originalString);
    }

    //加密算法
//    private  static String encryptString(String originalString){
//        return DESUtils.encrypt(originalString);
//    }

//    public static void main(String[] args) {
//        System.out.println(encryptString("nettrade"));
//        System.out.println(encryptString("fezr"));
//    }

    }