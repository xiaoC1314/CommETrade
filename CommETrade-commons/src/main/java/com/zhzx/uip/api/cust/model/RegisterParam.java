package com.zhzx.uip.api.cust.model;

import java.io.Serializable;

/**
 * Created by fu on 2017/7/8.
 */
public class RegisterParam implements Serializable{

    private static final long serialVersionUID = 520867985851171024L;
    /**
     * 短信验证码
     */
    private Integer smsCode;

    /**
     *电话
     */
    private String phone;

    /**
     *姓名
     */
    private String name;

    /**
     *密码
     */
    private String password;

    /**
     *头像图片
     */
    private String faceUrl;

    /**
     *昵称
     */
    private String nickName;

    /**
     *证件号码
     */
    private String idCard;

    /**
     *证件类型
     */
    private String idCardType;

    /**
     *电子邮箱
     */
    private String email;

    /**
     *住址
     */
    private String address;

    /**
     *qq号
     */
    private String qqNo;

    /**
     *微信号
     */
    private String wechatNo;

    public Integer getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(Integer smsCode) {
        this.smsCode = smsCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQqNo() {
        return qqNo;
    }

    public void setQqNo(String qqNo) {
        this.qqNo = qqNo;
    }

    public String getWechatNo() {
        return wechatNo;
    }

    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo;
    }
}
