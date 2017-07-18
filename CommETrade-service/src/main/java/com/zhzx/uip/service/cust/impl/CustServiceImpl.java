package com.zhzx.uip.service.cust.impl;

import com.zhzx.dao.bean.cust.CustInfo;
import com.zhzx.dao.model.cust.CustInfoModel;
import com.zhzx.dao.service.cust.CustInfoService;
import com.zhzx.uip.api.cust.model.CustInfoPara;
import com.zhzx.uip.api.cust.model.RegisterParam;
import com.zhzx.uip.commons.enums.ErrorEnum;
import com.zhzx.uip.commons.module.ResponseVo;
import com.zhzx.uip.commons.utils.ResponseFactory;
import com.zhzx.uip.service.manager.cust.CustManager;
import com.zhzx.uip.service.cust.CustService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustServiceImpl implements CustService {
	private static final Logger logger = LoggerFactory.getLogger(CustServiceImpl.class);
	@Autowired
	private CustInfoService custInfoService;

	@Autowired
	private CustManager custManager;

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
			listret = custInfoService.selectByModel(model);
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
	public ResponseVo checkPhone(String phone) {
		ResponseVo responseVo;
		try {
			CustInfo custInfo = custManager.queryByPhone(phone);
			if (custInfo == null) {
				responseVo = ResponseFactory.buildSuccessResponse(true);
			} else {
				responseVo = ResponseFactory.buildFailResponse(ErrorEnum.COMM_PHONE_EXIST_ERR);
			}
		}catch (Exception e){
			logger.error("查询客户基本息失败："+e.getMessage());
			responseVo = ResponseFactory.buildFailResponse(ErrorEnum.COMM_ERROR);
		}
		return responseVo;
	}

	@Override
	public boolean register(RegisterParam param){
		CustInfo custInfo = initRegister(param);
		// TODO: 2017/7/9 校验短信验证码
		try {
			custInfoService.insert(custInfo);
		} catch (Exception e) {
			logger.error("用户["+param.getPhone()+"]注册失败！",e);
			return false;
		}
		return true;
	}

	@Override
	public ResponseVo profileUpdate(RegisterParam param){
		try {
			CustInfo custInfo = custManager.queryByPhone(param.getPhone());
			if (custInfo == null){
				return ResponseFactory.buildFailResponse(ErrorEnum.COMM_USER_NOT_EXIST_ERR);
			}
			if (!custInfo.getPassword().equals(param.getPassword())){
				return ResponseFactory.buildFailResponse(ErrorEnum.COMM_PASSWORD_INCORRECT_ERR);
			}
			CustInfo newCust = initRegister(param);
			newCust.setId(custInfo.getId());
			custInfoService.getMapper().updateBySelective(newCust);
		} catch (Exception e) {
			logger.error("用户["+param.getPhone()+"]更新资料失败！",e);
			return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ERROR);
		}
		return ResponseFactory.buildSuccessResponse(true);
	}

	@Override
	public ResponseVo changePassword(String phone,String password,String newPassword){
		try {
			CustInfo custInfo = custManager.queryByPhone(phone);
			if (custInfo == null){
				return ResponseFactory.buildFailResponse(ErrorEnum.COMM_USER_NOT_EXIST_ERR);
			}
			if (!custInfo.getPassword().equals(password)){
				return ResponseFactory.buildFailResponse(ErrorEnum.COMM_PASSWORD_INCORRECT_ERR);
			}
			CustInfo newCust = new CustInfo();
			newCust.setPhone(phone);
			newCust.setPassword(newPassword);
			newCust.setId(custInfo.getId());
			custInfoService.updateBySelective(newCust);
		} catch (Exception e) {
			logger.error("用户["+phone+"]更换密码失败！",e);
			return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ERROR);
		}
		return ResponseFactory.buildSuccessResponse(true);
	}

	@Override
	public ResponseVo login(String phone,String password,Integer type){
		try {
			CustInfo custInfo = custManager.queryByPhone(phone);
			if (custInfo == null){
				return ResponseFactory.buildFailResponse(ErrorEnum.COMM_USER_NOT_EXIST_ERR);
			}
			if (type ==1) {
				if (!custInfo.getPassword().equals(password)) {
					return ResponseFactory.buildFailResponse(ErrorEnum.COMM_PASSWORD_INCORRECT_ERR);
				}
			}else {
				// TODO: 2017/7/13 短信验证码校验
			}
		} catch (Exception e) {
			logger.error("用户["+phone+"]登录失败！",e);
			return ResponseFactory.buildFailResponse(ErrorEnum.COMM_ERROR);
		}
		return ResponseFactory.buildSuccessResponse(true);
	}

	private CustInfo initRegister(RegisterParam param) {
		CustInfo custInfo = new CustInfo();
		BeanUtils.copyProperties(param,custInfo);
		return custInfo;
	}


}
