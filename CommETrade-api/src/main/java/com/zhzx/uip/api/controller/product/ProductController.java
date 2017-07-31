package com.zhzx.uip.api.controller.product;

import com.zhzx.dao.bean.common.Bdictionary;
import com.zhzx.dao.model.common.BdictionaryModel;
import com.zhzx.dao.model.prod.ProdCommentModel;
import com.zhzx.dao.model.prod.ProdInfoModel;
import com.zhzx.dao.model.prod.ProdPlanModel;
import com.zhzx.dao.model.prod.ProdPropertyModel;
import com.zhzx.dao.support.Navigate;
import com.zhzx.uip.api.utils.DictionaryConfig;
import com.zhzx.uip.commons.enums.ErrorEnum;
import com.zhzx.uip.commons.module.ResponseVo;
import com.zhzx.uip.service.prod.ProdQueryService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by dongwm on 2017/7/9.
 */
@Controller
@RequestMapping("/prodquery")
public class ProductController {
    private final static Logger log= Logger.getLogger(ProductController.class);

    @Autowired
    ProdQueryService prodQueryService;


    /**
     *
     * <br>
     * <b>功能：</b>获取当前社区商品列表<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @ResponseBody
    @RequestMapping("list")
    public ResponseVo list(ProdInfoModel inpara, Navigate navigate){
        inpara.setNavigate(navigate);
        ResponseVo resp = null;
        try{
            resp = prodQueryService.getProductList(inpara);
        }catch(Exception e){
            log.error(e);
        }
        return resp;
    }

    /**
     * 获取商品详情
     * @param inPara
     * @return
     */
    @ResponseBody
    @RequestMapping("proddetail")
    public ResponseVo getProdInfoDetail(ProdInfoModel inPara){
        ResponseVo resp = null;
        try{
            resp = prodQueryService.getProdInfoDetail(inPara);
        }catch(Exception e){
            log.error(e);
        }
        return resp;
    }

    /**
     * 获取商品分类
     * @param prodType
     * @return
     */
    @ResponseBody
    @RequestMapping("prodtypes")
    public ResponseVo getProdTypes(String prodType){
        ResponseVo resp = null;
        try{
            prodType = new String(prodType.getBytes("iso-8859-1"),"utf-8");
            List list = DictionaryConfig.getInstance().getItems(prodType);
            if (CollectionUtils.isNotEmpty(list)) {
                resp = new ResponseVo(true, ErrorEnum.COMM_SUCCESS.getErrorMsg(), ErrorEnum.COMM_SUCCESS.getErrorCode(), list);
            } else {
                resp = new ResponseVo(false, ErrorEnum.COMM_EMPTY_DATA.getErrorMsg(), ErrorEnum.COMM_EMPTY_DATA.getErrorCode());
            }
        }catch(Exception e){
            log.error("查询商品分类信息失败："+e.getMessage());
            resp = new ResponseVo(false, ErrorEnum.COMM_ERROR.getErrorMsg(), ErrorEnum.COMM_ERROR.getErrorCode());
        }
        return resp;
    }

    /**
     * 获取某一分类下的所有商品
     * @param prodTypeKey
     * @return
     */
    @ResponseBody
    @RequestMapping("prodsintype")
    public ResponseVo getProductListByType(String prodTypeKey,String prodTypeName,String prodTypeValue,Navigate navigate){
        ResponseVo resp = null;
        try{
            resp = prodQueryService.getProductListByType(prodTypeKey,prodTypeName,prodTypeValue,navigate);
        }catch(Exception e){
            log.error(e);
        }
        return resp;
    }

    /**
     * 获取热销、最新、打折促销、推荐商品
     * @param prodPlanModel
     * @return
     */
    @ResponseBody
    @RequestMapping("prodinplan")
    public ResponseVo getProductByPlan(ProdPlanModel prodPlanModel,Navigate navigate ){
        ResponseVo resp = null;
        try{
            resp = prodQueryService.getProductByPlan(prodPlanModel, navigate);
        }catch(Exception e){
            log.error(e);
        }
        return resp;
    }

    /**
     * 搜索商品
     * @param inPara
     * @return
     */
    @ResponseBody
    @RequestMapping("seachprods")
    public ResponseVo serchProductList(ProdInfoModel inPara){
        ResponseVo resp = null;
        try{
            resp = prodQueryService.serchProductList(inPara);
        }catch(Exception e){
            log.error(e);
        }
        return resp;
    }

    /**
     * 商品评价列表
     * @param inPara
     * @return
     */
    @ResponseBody
    @RequestMapping("prodcomments")
    ResponseVo getProductComment(ProdCommentModel inPara,Navigate navigate){
        ResponseVo resp = null;
        try{
            inPara.setNavigate(navigate);
            resp = prodQueryService.getProductComment(inPara);
        }catch(Exception e){
            log.error(e);
        }
        return resp;
    }
}
