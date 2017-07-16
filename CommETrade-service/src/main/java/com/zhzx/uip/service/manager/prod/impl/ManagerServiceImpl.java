package com.zhzx.uip.service.manager.prod.impl;

import com.zhzx.dao.bean.Bdictionary;
import com.zhzx.dao.bean.prod.*;
import com.zhzx.dao.common.Navigate;
import com.zhzx.dao.model.BdictionaryModel;
import com.zhzx.dao.model.prod.*;
import com.zhzx.dao.service.BdictionaryService;
import com.zhzx.dao.service.prod.*;
import com.zhzx.uip.commons.enums.ErrorEnum;
import com.zhzx.uip.commons.module.ResponseVo;
import com.zhzx.uip.service.manager.prod.ManagerService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
	private static final Logger logger = LoggerFactory.getLogger(ManagerServiceImpl.class);

	@Autowired
	private ProdInfoService prodInfoService;

	@Autowired
	private ProdPropertyService prodPropertyService;

	@Autowired
	private BdictionaryService bdictionaryService;

	@Autowired
	private ProdPlanDetailService prodPlanDetailService;

	@Autowired
	private ProdPlanService prodPlanService;

	@Autowired
	private ProdCommentService prodCommentService;



	/**
	 * 查询商品列表
	 * @param inPara
	 * @return
     */
	@Override
	public ResponseVo getProductList(ProdInfoModel inPara){
		List<ProdInfo> listProds = null;
		ResponseVo responseVo = null;
		try {
			listProds = queryProductList(inPara);
			if (CollectionUtils.isNotEmpty(listProds)) {
				responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), listProds);
			} else {
				responseVo = new ResponseVo(false, ErrorEnum.COMM_EMPTY_DATA.getErrorMsg(), ErrorEnum.COMM_EMPTY_DATA.getErrorCode());
			}
		}catch (Exception e){
			logger.error("查询商品基本信息失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		return responseVo;
	}

	/**
	 * 查询商品列表
	 * @param inPara
	 * @return
	 */
	@Override
	public ResponseVo addProductList(ProdInfo inPara){
		ResponseVo responseVo = null;
		try {
			prodInfoService.getMapper().insert(inPara);
		}catch (Exception e){
			logger.error("查询商品基本信息失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), null);
		return responseVo;
	}

	/**
	 * 获取商品详情
	 *
	 * @param inPara
	 * @return
	 */
	@Override
	public ResponseVo getProdPropertys(ProdPropertyModel inPara) {
		List<ProdProperty> prodDetail = null;
		ResponseVo responseVo = null;
		try {
			prodDetail = queryProdListByProperty(inPara);
			if (CollectionUtils.isNotEmpty(prodDetail)) {
				responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), prodDetail);
			} else {
				responseVo = new ResponseVo(false, ErrorEnum.COMM_EMPTY_DATA.getErrorMsg(), ErrorEnum.COMM_EMPTY_DATA.getErrorCode());
			}
		}catch (Exception e){
			logger.error("查询商品详情信息失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		return responseVo;
	}

	/**
	 * 根据商品属性查询
	 *
	 * @param inPara
	 * @return
	 */
	private List<ProdProperty> queryProdListByProperty(ProdPropertyModel inPara)throws Exception {
		List<ProdProperty> prodList = null;
		prodList = prodPropertyService.getMapper().selectByModel(inPara);
		return prodList;
	}

	/**
	 * 查询商品信息
	 *
	 * @param inPara
	 * @return
	 */
	private List<ProdInfo> queryProductList(ProdInfoModel inPara)throws Exception {
		List<ProdInfo> prodList = null;
		prodList = prodInfoService.getMapper().selectByModel(inPara);
		return prodList;
	}

	/**
	 * 获取商品分类
	 *
	 * @param prodType
	 * @return
	 */
	@Override
	public ResponseVo getProdTypes(String prodType) {
		List<Bdictionary> listret = null;
		ResponseVo responseVo = null;
		try {
			BdictionaryModel model = new BdictionaryModel();
			model.setCaption(prodType);
			listret = bdictionaryService.getMapper().selectByModel(model);
			if (CollectionUtils.isNotEmpty(listret)) {
				responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), listret);
			} else {
				responseVo = new ResponseVo(false, ErrorEnum.COMM_EMPTY_DATA.getErrorMsg(), ErrorEnum.COMM_EMPTY_DATA.getErrorCode());
			}
		}catch (Exception e){
			logger.error("查询商品分类信息失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		return null;
	}

	/**
	 * 获取某一分类下的所有商品
	 *
	 * @param prodTypeKey
	 * @return
	 */
	@Override
	public ResponseVo getProductListByType(String prodTypeKey,String prodTypeName) {
		ResponseVo responseVo = null;
		try {
			ProdPropertyModel inPara = new ProdPropertyModel();
			inPara.setPropKey(prodTypeKey);
			inPara.setPropName(prodTypeName);
			List<ProdProperty> prodDetail = queryProdListByProperty(inPara);
			if (CollectionUtils.isNotEmpty(prodDetail)) {
				StringBuffer prodids = new StringBuffer(" and id in ('");
				for (ProdProperty property:prodDetail) {
					prodids.append(property.getId()).append("',");
				}
				prodids.deleteCharAt(prodids.length() - 1);
				prodids.append(")");
				ProdInfoModel prodMode = new ProdInfoModel();
				Navigate navigate = new Navigate();
				navigate.setOtherCondition(prodids.toString());
				prodMode.setNavigate(navigate);
				List<ProdInfo> listProds = queryProductList(prodMode);

				responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), listProds);
			} else {
				responseVo = new ResponseVo(false, ErrorEnum.COMM_EMPTY_DATA.getErrorMsg(), ErrorEnum.COMM_EMPTY_DATA.getErrorCode());
			}
		}catch (Exception e){
			logger.error("查询某一分类下的所有商品信息失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}


		return null;
	}

	/**
	 * 获取热销、最新、打折促销、推荐商品
	 *
	 * @param prodPlanModel
	 * @return
	 */
	@Override
	public ResponseVo getProductByPlan(ProdPlanModel prodPlanModel ) {

		prodPlanModel.setStatus("1");
		ResponseVo responseVo = null;
		try {
			List<ProdPlan> prodPlan = prodPlanService.getMapper().selectByModel(prodPlanModel);
			if(prodPlan != null && prodPlan.size()>0){
				String planid = prodPlan.get(0).getId();
				ProdPlanDetailModel  prodPlanDetailModel= new ProdPlanDetailModel();
				prodPlanDetailModel.setPlanNo(planid);
				List<ProdPlanDetail> prodPlanDetails = prodPlanDetailService.getMapper().selectByModel(prodPlanDetailModel);
				if(prodPlanDetails != null && prodPlanDetails.size()>0){
					StringBuffer prodids = new StringBuffer(" and id in ('");
					for (ProdPlanDetail prodPlanDetail : prodPlanDetails) {
						prodids.append(prodPlanDetail.getProdNo()).append("',");
					}
					prodids.deleteCharAt(prodids.length() - 1);
					prodids.append(")");
					ProdInfoModel prodMode = new ProdInfoModel();
					Navigate navigate = new Navigate();
					navigate.setOtherCondition(prodids.toString());
					prodMode.setNavigate(navigate);
					List<ProdInfo> listProds = queryProductList(prodMode);

					responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), listProds);
				} else {
					responseVo = new ResponseVo(false, ErrorEnum.COMM_EMPTY_DATA.getErrorMsg(), ErrorEnum.COMM_EMPTY_DATA.getErrorCode());
				}
			} else {
				responseVo = new ResponseVo(false, ErrorEnum.COMM_EMPTY_DATA.getErrorMsg(), ErrorEnum.COMM_EMPTY_DATA.getErrorCode());
			}
		}catch (Exception e){
			logger.error("获取热销、最新、打折促销、推荐商品信息失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		return responseVo;
	}

	/**
	 * 搜索商品
	 *
	 * @param inPara
	 * @return
	 */
	@Override
	public ResponseVo serchProductList(ProdInfoModel inPara) {
		List<ProdInfo> listProds = null;
		ResponseVo responseVo = null;
		try {
			listProds = queryProductList(inPara);
			if (CollectionUtils.isNotEmpty(listProds)) {
				responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), listProds);
			} else {
				responseVo = new ResponseVo(false, ErrorEnum.COMM_EMPTY_DATA.getErrorMsg(), ErrorEnum.COMM_EMPTY_DATA.getErrorCode());
			}
		}catch (Exception e){
			logger.error("查询商品信息失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		return responseVo;
	}

}
