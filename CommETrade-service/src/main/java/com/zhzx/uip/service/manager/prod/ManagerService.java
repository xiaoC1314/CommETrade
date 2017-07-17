package com.zhzx.uip.service.manager.prod;

import com.zhzx.dao.bean.prod.ProdInfo;
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
	public ResponseToMa getProductList(ProdInfoModel inPara, Navigate navig);

	/**
	 * 添加商品信息
	 * @param inPara
	 * @return
	 */
	public ResponseVo addProductList(ProdInfo inPara);

	/**
	 * 获取商品详情
	 * @param inPara
	 * @return
	 */
	public ResponseVo getProdPropertys(ProdPropertyModel inPara);

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

}
