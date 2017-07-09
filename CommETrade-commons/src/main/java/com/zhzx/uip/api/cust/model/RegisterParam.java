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
    private String faceurl;

    /**
     *昵称
     */
    private String nickname;

    /**
     *证件号码
     */
    private String idcard;

    /**
     *证件类型
     */
    private String idcardtype;

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
    private String qqno;

    /**
     *微信号
     */
    private String wechatno;

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

    public String getFaceurl() {
        return faceurl;
    }

    public void setFaceurl(String faceurl) {
        this.faceurl = faceurl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIdcardtype() {
        return idcardtype;
    }

    public void setIdcardtype(String idcardtype) {
        this.idcardtype = idcardtype;
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

    public String getQqno() {
        return qqno;
    }

    public void setQqno(String qqno) {
        this.qqno = qqno;
    }

    public String getWechatno() {
        return wechatno;
    }

    public void setWechatno(String wechatno) {
        this.wechatno = wechatno;
    }
}
