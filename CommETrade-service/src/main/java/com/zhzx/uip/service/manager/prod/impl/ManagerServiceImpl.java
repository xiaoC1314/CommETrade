package com.zhzx.uip.service.manager.prod.impl;

import com.zhzx.dao.bean.common.Bdictionary;
import com.zhzx.dao.bean.cust.Address;
import com.zhzx.dao.bean.cust.CustInfo;
import com.zhzx.dao.bean.order.OrderInfo;
import com.zhzx.dao.bean.order.OrderInfoDetail;
import com.zhzx.dao.bean.order.ProdList;
import com.zhzx.dao.bean.prod.*;
import com.zhzx.dao.model.common.BdictionaryModel;
import com.zhzx.dao.model.cust.CustInfoModel;
import com.zhzx.dao.model.order.OrderInfoModel;
import com.zhzx.dao.model.order.ProdListModel;
import com.zhzx.dao.service.common.BdictionaryService;
import com.zhzx.dao.service.cust.AddressService;
import com.zhzx.dao.service.cust.CustInfoService;
import com.zhzx.dao.service.order.OrderInfoService;
import com.zhzx.dao.service.order.ProdListService;
import com.zhzx.dao.support.Navigate;
import com.zhzx.dao.model.prod.*;
import com.zhzx.dao.service.prod.*;
import com.zhzx.uip.commons.enums.ErrorEnum;
import com.zhzx.uip.commons.enums.OrderStatusEnum;
import com.zhzx.uip.commons.module.ResponseToMa;
import com.zhzx.uip.commons.module.ResponseVo;
import com.zhzx.uip.commons.utils.BeanUtils;
import com.zhzx.uip.service.manager.prod.ManagerService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

	@Autowired
	OrderInfoService orderInfoService;

	@Autowired
	ProdListService prodListService;


	@Autowired
	private CustInfoService custInfoService;

	@Autowired
	AddressService addressService;

	/**
	 * 查询商品列表
	 * @param inPara
	 * @return
     */
	@Override
	public ResponseToMa getProductList(ProdInfoModel inPara){
		List<ProdInfo> listProds = null;
		ResponseToMa resp = null;
		try {
			listProds = queryProductList(inPara);
			if (CollectionUtils.isNotEmpty(listProds)) {
				resp = new ResponseToMa(inPara.getNavigate().getRowCount(),listProds);
			} else {
				resp = new ResponseToMa(0,null);
			}

		}catch (Exception e){
			logger.error("查询商品基本信息失败："+e.getMessage());
			resp = new ResponseToMa(0,null);
		}
		return resp;
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
			prodInfoService.insert(inPara);
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
	public ResponseToMa getProdPropertys(ProdPropertyModel inPara) {
		List<ProdProperty> prodDetail = null;
		ResponseToMa responseVo = null;
		try {
			prodDetail = queryProdListByProperty(inPara);
			if (CollectionUtils.isNotEmpty(prodDetail)) {
				responseVo = new ResponseToMa(inPara.getNavigate().getRowCount(), prodDetail);
			} else {
				responseVo = new ResponseToMa(0,null);
			}
		}catch (Exception e){
			logger.error("查询商品详情信息失败："+e.getMessage());
			responseVo = new ResponseToMa(0,null);
		}
		return responseVo;
	}

	/**
	 * 添加商品属性
	 *
	 * @param inPara
	 * @return
	 */
	@Override
	public ResponseVo addProdProperty(ProdProperty inPara) {
		ResponseVo responseVo = null;
		try {
			prodPropertyService.insert(inPara);
		}catch (Exception e){
			logger.error("查询商品基本信息失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), null);
		return responseVo;
	}

	/**
	 * 修改商品属性
	 *
	 * @param inPara
	 * @return
	 */
	@Override
	public ResponseVo modifyProdProperty(ProdProperty inPara) {
		ResponseVo responseVo = null;
		try {
			prodPropertyService.updateBySelective(inPara);
		}catch (Exception e){
			logger.error("修改商品属性信息失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), null);
		return responseVo;
	}

	/**
	 * 删除商品属性
	 *
	 * @param ids
	 * @return
	 */
	@Override
	public ResponseVo delProdProperty(String ids) {
		ResponseVo responseVo = null;
		try {
			String[] idArray = ids.split(",");
			for (String id:idArray) {
				ProdProperty inPara = new ProdProperty();
				inPara.setId(id);
				inPara.setStatus("0");//无效
				prodPropertyService.updateBySelective(inPara);
//				prodPropertyService.delete(id);
			}
		}catch (Exception e){
			logger.error("删除商品属性失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), null);
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
		prodList = prodPropertyService.selectByModelAsPage(inPara);
//		prodList = prodPropertyService.selectByModel(inPara);
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
		prodList = prodInfoService.selectByModelAsPage(inPara);
//		prodList = prodInfoService.selectByModel(inPara);
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
			listret = bdictionaryService.selectByModel(model);
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
			List<ProdPlan> prodPlan = prodPlanService.selectByModel(prodPlanModel);
			if(prodPlan != null && prodPlan.size()>0){
				String planid = prodPlan.get(0).getId();
				ProdPlanDetailModel  prodPlanDetailModel= new ProdPlanDetailModel();
				prodPlanDetailModel.setPlanNo(planid);
				List<ProdPlanDetail> prodPlanDetails = prodPlanDetailService.selectByModel(prodPlanDetailModel);
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

	/**
	 * 删除商品
	 * @param ids
	 * @return
	 */
	public ResponseVo delProductInfo(String  ids){
		ResponseVo responseVo = null;
		try {
			String[] idArray = ids.split(",");
			for (String id:idArray) {
				prodInfoService.delete(id);
			}
		}catch (Exception e){
			logger.error("删除商品基本信息失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), null);
		return responseVo;
	}

	/**
	 * 修改商品信息
	 * @param inPara
	 * @return
	 */
	public ResponseVo modifyProductInfo(ProdInfo inPara){
		ResponseVo responseVo = null;
		try {
			prodInfoService.updateBySelective(inPara);
		}catch (Exception e){
			logger.error("更新商品基本信息失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), null);
		return responseVo;
	}

	/**
	 * 查询商品列表
	 * @param inPara
	 * @return
	 */
	@Override
	public ResponseToMa queryOrderList(OrderInfoModel inPara){
		List<OrderInfoDetail> orderDetaillist = new ArrayList<OrderInfoDetail>();
		ResponseToMa resp = null;
		try {
			List<OrderInfo>  orderInfos =  orderInfoService.selectByModelAsPage(inPara);
			if (CollectionUtils.isNotEmpty(orderInfos)) {
				String orderNo = "";
				OrderInfoDetail orderInfoDetail = new OrderInfoDetail();
				for (OrderInfo orderInfo:orderInfos ) {
					BeanUtils.copy(orderInfo,orderInfoDetail);
					orderNo = orderInfo.getId();
					ProdListModel model = new ProdListModel();
					model.setOrderNo(orderNo);
					List<ProdList> prodList = prodListService.selectByModel(model);
					BeanUtils.copy(prodList.get(0),orderInfoDetail);
					orderInfoDetail.setProdId(prodList.get(0).getId());
					orderDetaillist.add(orderInfoDetail);
				}
				resp = new ResponseToMa(inPara.getNavigate().getRowCount(),orderDetaillist);
			} else {
				resp = new ResponseToMa(0,null);
			}
		}catch (Exception e){
			logger.error("查询订单信息失败："+e.getMessage());
			resp = new ResponseToMa(0,null);
		}
		return resp;
	}

	/**
	 * 修改订单状态
	 *
	 * @param inPara
	 * @return
	 */
	@Override
	public ResponseVo modifyOrderInfo(OrderInfo inPara) {
		ResponseVo responseVo = null;
		try {
			OrderInfo newOrder = new OrderInfo();
			newOrder.setId(inPara.getId());
			newOrder.setStatus(inPara.getStatus());
			orderInfoService.updateBySelective(newOrder);
		}catch (Exception e){
			logger.error("更新订单状态失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), null);
		return responseVo;
	}

	/**
	 * 修改订单列表-购物清单
	 *
	 * @param inPara
	 * @return
	 */
	@Override
	public ResponseVo modifyOrderInfo(ProdList inPara) {
		ResponseVo responseVo = null;
		try {
			prodListService.updateBySelective(inPara);
		}catch (Exception e){
			logger.error("修改购物清单失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), null);
		return responseVo;
	}

	/**
	 * 查询客户列表
	 *
	 * @param inPara
	 * @return
	 */
	@Override
	public ResponseToMa queryCustInfoList(CustInfoModel inPara) {
		List<CustInfo> custInfoList = null;
		ResponseToMa resp = null;
		try {
			custInfoList = ( List<CustInfo>)custInfoService.selectByModelAsPage(inPara);
			if (CollectionUtils.isNotEmpty(custInfoList)) {
				resp = new ResponseToMa(inPara.getNavigate().getRowCount(),custInfoList);
			} else {
				resp = new ResponseToMa(0,null);
			}
		}catch (Exception e){
			logger.error("查询客户信息失败："+e.getMessage());
			resp = new ResponseToMa(0,null);
		}
		return resp;
	}

	/**
	 * 修改客户信息
	 *
	 * @param inPara
	 * @return
	 */
	@Override
	public ResponseVo modifyCustInfo(CustInfo inPara) {
		ResponseVo responseVo = null;
		try {
			custInfoService.updateBySelective(inPara);
		}catch (Exception e){
			logger.error("更新客户信息失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), null);
		return responseVo;
	}

	/**
	 * 新增客户信息
	 *
	 * @param inPara
	 * @return
	 */
	@Override
	public ResponseVo addyCustInfo(CustInfo inPara) {
		ResponseVo responseVo = null;
		try {
			custInfoService.insert(inPara);
		}catch (Exception e){
			logger.error("新增客户信息失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), null);
		return responseVo;
	}

	/**
	 * 修改客户收货地址
	 *
	 * @param inPara
	 * @return
	 */
	@Override
	public ResponseVo modifyAddressInfo(Address inPara) {
		return null;
	}

	/**
	 * 查询特殊活动列表
	 *
	 * @param inPara
	 * @return
	 */
	@Override
	public ResponseToMa queryProdPlanList(ProdPlanModel inPara) {
		List<ProdPlan> prodPlanList = null;
		ResponseToMa resp = null;
		try {
			prodPlanList = ( List<ProdPlan>)prodPlanService.selectByModelAsPage(inPara);
			if (CollectionUtils.isNotEmpty(prodPlanList)) {
				resp = new ResponseToMa(inPara.getNavigate().getRowCount(),prodPlanList);
			} else {
				resp = new ResponseToMa(0,null);
			}
		}catch (Exception e){
			logger.error("查询活动信息失败："+e.getMessage());
			resp = new ResponseToMa(0,null);
		}
		return resp;
	}

	/**
	 * 查询特殊活动详情
	 *
	 * @param inPara
	 * @return
	 */
	@Override
	public ResponseToMa queryProdPlanDetailList(ProdPlanDetailModel inPara) {
		List<ProdPlanDetail> prodPlanDetailList = null;
		ResponseToMa resp = null;
		try {
			prodPlanDetailList = ( List<ProdPlanDetail>)prodPlanDetailService.selectByModelAsPage(inPara);
			if (CollectionUtils.isNotEmpty(prodPlanDetailList)) {
				resp = new ResponseToMa(inPara.getNavigate().getRowCount(),prodPlanDetailList);
			} else {
				resp = new ResponseToMa(0,null);
			}
		}catch (Exception e){
			logger.error("查询活动详情记录失败："+e.getMessage());
			resp = new ResponseToMa(0,null);
		}
		return resp;
	}

	/**
	 * 新增殊活动信息
	 *
	 * @param inPara
	 * @return
	 */
	@Override
	public ResponseVo addProdPlan(ProdPlan inPara) {
		ResponseVo responseVo = null;
		try {
			prodPlanService.insert(inPara);
		}catch (Exception e){
			logger.error("新增活动信息失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), null);
		return responseVo;
	}

	/**
	 * 修改殊活动信息
	 *
	 * @param inPara
	 * @return
	 */
	@Override
	public ResponseVo modifyProdPlan(ProdPlan inPara) {
		ResponseVo responseVo = null;
		try {
			prodPlanService.updateBySelective(inPara);
		}catch (Exception e){
			logger.error("修改活动信息失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), null);
		return responseVo;
	}

	/**
	 * 修改殊活动详情信息
	 *
	 * @param inPara
	 * @return
	 */
	@Override
	public ResponseVo modifyProdPlanDetail(ProdPlanDetail inPara) {
		ResponseVo responseVo = null;
		try {
			prodPlanDetailService.updateBySelective(inPara);
		}catch (Exception e){
			logger.error("修改活动详情记录失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), null);
		return responseVo;
	}

	/**
	 * 修改殊活动详情信息
	 *
	 * @param inPara
	 * @return
	 */
	@Override
	public ResponseVo addProdPlanDetail(ProdPlanDetail inPara) {
		ResponseVo responseVo = null;
		try {
			prodPlanDetailService.insert(inPara);
		}catch (Exception e){
			logger.error("新增活动详情记录失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), null);
		return responseVo;
	}

	/**
	 * 修改殊活动详情信息
	 *
	 * @param ids@return
	 */
	@Override
	public ResponseVo delProdPlanDetail(String ids) {
		ResponseVo responseVo = null;
		try {
			String[] idArray = ids.split(",");
			for (String id:idArray) {
				prodPlanDetailService.delete(id);
			}
		}catch (Exception e){
			logger.error("删除活动详情信息失败："+e.getMessage());
			responseVo = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
		}
		responseVo = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), null);
		return responseVo;
	}
}
