package com.zhzx.uip.service.manager.prod;

import com.zhzx.dao.bean.cust.Address;
import com.zhzx.dao.bean.cust.CustInfo;
import com.zhzx.dao.bean.order.OrderInfo;
import com.zhzx.dao.bean.order.ProdList;
import com.zhzx.dao.bean.prod.ProdInfo;
import com.zhzx.dao.bean.prod.ProdPlan;
import com.zhzx.dao.bean.prod.ProdPlanDetail;
import com.zhzx.dao.bean.prod.ProdProperty;
import com.zhzx.dao.model.cust.CustInfoModel;
import com.zhzx.dao.model.order.OrderInfoModel;
import com.zhzx.dao.model.prod.ProdPlanDetailModel;
import com.zhzx.dao.support.Navigate;
import com.zhzx.dao.model.prod.ProdInfoModel;
import com.zhzx.dao.model.prod.ProdPlanModel;
import com.zhzx.dao.model.prod.ProdPropertyModel;
import com.zhzx.uip.commons.module.ResponseToMa;
import com.zhzx.uip.commons.module.ResponseVo;
import org.springframework.stereotype.Service;

@Service
public interface ManagerService {

	/**
	 * 获取当前社区商品列表
	 * @param inPara
	 * @return
	 */
	public ResponseToMa getProductList(ProdInfoModel inPara);

	/**
	 * 添加商品信息
	 * @param inPara
	 * @return
	 */
	public ResponseVo addProductList(ProdInfo inPara);

	/**
	 * 获取商品属性
	 * @param inPara
	 * @return
	 */
	public ResponseToMa getProdPropertys(ProdPropertyModel inPara);


	/**
	 * 添加商品属性
	 * @param inPara
	 * @return
	 */
	public ResponseVo addProdProperty(ProdProperty inPara);

	/**
	 * 修改商品属性
	 * @param inPara
	 * @return
	 */
	public ResponseVo modifyProdProperty(ProdProperty inPara);


	/**
	 * 删除商品属性
	 * @param ids
	 * @return
	 */
	public ResponseVo delProdProperty(String ids);

	/**
	 * 获取商品分类
	 * @param prodType
	 * @return
	 */
	public ResponseVo getProdTypes(String prodType);

	/**
	 * 获取某一分类下的所有商品
	 * @param prodTypeName
	 * @return
	 */
	public ResponseVo getProductListByType(String prodTypeKey, String prodTypeName);

	/**
	 * 获取热销、最新、打折促销、推荐商品
	 * @param prodPlanModel
	 * @return
	 */
	public ResponseVo getProductByPlan(ProdPlanModel prodPlanModel);

	/**
	 * 搜索商品
	 * @param inPara
	 * @return
	 */
	public ResponseVo serchProductList(ProdInfoModel inPara);

	/**
	 * 删除商品
	 * @param ids
	 * @return
	 */
	public ResponseVo delProductInfo(String ids);

	/**
	 * 修改商品信息
	 * @param inPara
	 * @return
	 */
	public ResponseVo modifyProductInfo(ProdInfo inPara);

	/**
	 * 查询订单列表
	 * @param inPara
	 * @return
	 */
	ResponseToMa queryOrderList(OrderInfoModel inPara);

	/**
	 * 修改订单状态
	 * @param inPara
	 * @return
	 */
	public ResponseVo modifyOrderInfo(OrderInfo inPara);
	/**
	 * 修改订单列表-购物清单
	 * @param inPara
	 * @return
	 */
	public ResponseVo modifyOrderInfo(ProdList inPara);

	/**
	 * 查询客户列表
	 * @param inPara
	 * @return
	 */
	ResponseToMa queryCustInfoList(CustInfoModel inPara);

	/**
	 * 修改客户信息
	 * @param inPara
	 * @return
	 */
	public ResponseVo modifyCustInfo(CustInfo inPara);

	/**
	 * 新增客户信息
	 * @param inPara
	 * @return
	 */
	public ResponseVo addyCustInfo(CustInfo inPara);

	/**
	 * 修改客户地址信息
	 * @param inPara
	 * @return
	 */
	public ResponseVo modifyAddressInfo(Address inPara);

	/**
	 * 查询特殊活动列表
	 * @param inPara
	 * @return
	 */
	ResponseToMa queryProdPlanList(ProdPlanModel inPara);

	/**
	 * 查询特殊活动详情
	 * @param inPara
	 * @return
	 */
	ResponseToMa queryProdPlanDetailList(ProdPlanDetailModel inPara);

	/**
	 * 新增殊活动信息
	 * @param inPara
	 * @return
	 */
	public ResponseVo addProdPlan(ProdPlan inPara);

	/**
	 * 修改殊活动信息
	 * @param inPara
	 * @return
	 */
	public ResponseVo modifyProdPlan(ProdPlan inPara);


	/**
	 * 修改殊活动详情信息
	 * @param inPara
	 * @return
	 */
	public ResponseVo modifyProdPlanDetail(ProdPlanDetail inPara);

	/**
	 * 修改殊活动详情信息
	 * @param inPara
	 * @return
	 */
	public ResponseVo addProdPlanDetail(ProdPlanDetail inPara);


	/**
	 * 修改殊活动详情信息
	 * @param inPara
	 * @return
	 */
	public ResponseVo delProdPlanDetail(String ids);
}
