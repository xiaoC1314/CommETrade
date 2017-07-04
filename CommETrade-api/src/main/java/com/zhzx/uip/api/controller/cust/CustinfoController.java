package com.zhzx.uip.api.controller.cust;

import com.zhzx.uip.api.cust.model.CustInfoPara;
import com.zhzx.uip.commons.module.ResponseVo;
import com.zhzx.uip.service.cust.CustService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


@Controller
public class CustinfoController extends MultiActionController{
	
	private final static Logger log= Logger.getLogger(CustinfoController.class);

    @Autowired
    CustService custService;
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>客户地址列表页面<br>
	 * <b>作者：</b>陈浩<br>
	 * <b>日期：</b> Dec 8, 2011 <br>
	 * @return
	 */
	@RequestMapping("/custinfo/list")
    @ResponseBody
	public ResponseVo list(CustInfoPara inpara){
        ResponseVo resp = null;
        try{
			resp = custService.getCustInfoByphone(inpara);
		}catch(Exception e){
			log.error(e);
		}
		return resp;
	}
}
