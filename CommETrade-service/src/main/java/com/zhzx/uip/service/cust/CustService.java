package com.zhzx.uip.service.cust;

import com.zhzx.uip.api.cust.model.CustInfoPara;
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

}
