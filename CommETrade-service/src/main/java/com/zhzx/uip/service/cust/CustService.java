package com.zhzx.uip.service.cust;

import com.zhzx.uip.api.cust.model.CustInfoPara;
import com.zhzx.uip.api.cust.model.RegisterParam;
import com.zhzx.uip.commons.module.ResponseVo;
import org.springframework.stereotype.Service;

@Service
public interface CustService {

	/**
	 * 查询所有客户的份额持有情况
	 * @param inPara
	 * @return
	 */
	public ResponseVo getCustInfoByphone(CustInfoPara inPara);


	/**
	 * 检测手机号码是否已注册
	 * @param phone
	 * @return
	 */
	boolean checkPhone(String phone);

	/**
	 * 用户注册
	 * @param param
	 * @return
	 */
	boolean register(RegisterParam param);

	/**
	 * 用户更新资料
	 * @param param
	 * @return
	 */
	ResponseVo profileUpdate(RegisterParam param);

	/**
	 * 用户更换密码
	 * @param phone
	 * @param password
	 * @param newPassword
	 * @return
	 */
	ResponseVo changePassword(String phone,String password,String newPassword);

}
