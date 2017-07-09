package com.zhzx.uip.service.cust.impl;

import com.zhzx.dao.bean.cust.CustInfo;
import com.zhzx.dao.model.cust.CustInfoModel;
import com.zhzx.dao.service.cust.CustInfoService;
import com.zhzx.uip.api.cust.model.CustInfoPara;
import com.zhzx.uip.commons.enums.ErrorEnum;
import com.zhzx.uip.commons.module.ResponseVo;
import com.zhzx.uip.service.cust.CustService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustServiceImpl implements CustService {
	private static final Logger logger = LoggerFactory.getLogger(CustServiceImpl.class);
	@Autowired
	private CustInfoService custInfoService;

	/**
	 * 获取汇款信息
	 * @param inPara
	 * @return
     */
	@Override
	public ResponseVo getCustInfoByphone(CustInfoPara inPara) {
		List<CustInfo> listret = null;
		ResponseVo responseVo = null;

		try {
            CustInfoModel model = new CustInfoModel();
            model.setPhone(inPara.getPhone());
            //listret = custInfoService.selectByModel(model);
			if (CollectionUtils.isNotEmpty(listret)) {
				responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), listret);
			} else {
				responseVo = new ResponseVo(false, ErrorEnum.COMM_EMPTY_DATA.getErrorMsg(), ErrorEnum.COMM_EMPTY_DATA.getErrorCode());
			}
		}catch (Exception e){
			logger.error("查询客户基本息失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		return responseVo;
	}

	@Override
	public boolean checkPhone(String phone) {
		long phoneNo = Long.parseLong(phone);
		CustInfo custInfo = (CustInfo)custInfoService.getMapper().selectByPhone(phoneNo);
		return custInfo != null;
	}
}
