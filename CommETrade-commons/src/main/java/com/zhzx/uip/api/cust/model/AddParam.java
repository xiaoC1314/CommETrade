package com.zhzx.uip.api.cust.model;

/**
 * Created by fu on 2017/7/18.
 */
public class AddParam {

    /**
     *用户id
     */
    private String custNo;

    /**
     *地址
     */
    private String address;

    /**
     *详细地址
     */
    private String detailAddress;

    /**
     *收件人姓名
     */
    private String receiverName;

    /**
     *收件人电话
     */
    private String receiverPhone;

    /**
     *邮编
     */
    private String postCode;

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        return "AddParam{" +
                "custNo='" + custNo + '\'' +
                ", address='" + address + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }
}
