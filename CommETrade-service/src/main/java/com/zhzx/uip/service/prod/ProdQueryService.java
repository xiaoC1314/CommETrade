package com.zhzx.uip.service.prod;

import com.zhzx.dao.model.prod.ProdCommentModel;
import com.zhzx.dao.model.prod.ProdInfoModel;
import com.zhzx.dao.model.prod.ProdPlanModel;
import com.zhzx.dao.model.prod.ProdPropertyModel;
import com.zhzx.uip.api.cust.model.RegisterParam;
import com.zhzx.uip.commons.module.ResponseVo;
import org.springframework.stereotype.Service;

@Service
public interface ProdQueryService {

	/**
	 * 获取当前社区商品列表
	 * @param inPara
	 * @return
	 */
	public ResponseVo getProductList(ProdInfoModel inPara);

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
	 * @param prodTypeKey
	 * @return
	 */
	public ResponseVo getProductListByType(String prodTypeKey,String prodTypeName);

	/**
	 * 获取热销、最新、打折促销、推荐商品
	 * @param prodPlanModel
	 * @return
	 */
	public ResponseVo getProductByPlan(ProdPlanModel prodPlanModel );

	/**
	 * 搜索商品
	 * @param inPara
	 * @return
	 */
	public ResponseVo serchProductList(ProdInfoModel inPara);

	/**
	 * 商品评价列表
	 * @param inPara
	 * @return
	 */
	ResponseVo getProductComment(ProdCommentModel inPara);
}
