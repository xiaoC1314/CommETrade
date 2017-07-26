package com.zhzx.uip.api.controller.manager;

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
import com.zhzx.dao.model.prod.ProdPlanModel;
import com.zhzx.dao.model.prod.ProdPropertyModel;
import com.zhzx.dao.support.Navigate;
import com.zhzx.dao.model.prod.ProdInfoModel;
import com.zhzx.uip.api.controller.BaseController;
import com.zhzx.uip.api.controller.product.ProductController;
import com.zhzx.uip.api.utils.DictionaryConfig;
import com.zhzx.uip.commons.module.ResponseToMa;
import com.zhzx.uip.commons.module.ResponseVo;
import com.zhzx.uip.service.manager.prod.ManagerService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Iterator;

/**
 * Created by dongwm on 2017/7/9.
 */
@Controller
@RequestMapping("/console")
public class ManagerController extends BaseController {
    private final static Logger log= Logger.getLogger(ProductController.class);

    @Autowired
    private ManagerService managerService;

    //图片上传网络地址前缀
    @Value("${prod.picture.prefixurl}")
    private String picUrl;

    //图片上传服务器保存地址前缀
    @Value("${upload.folder.prefixurl}")
    private String picSaveUrl;

    @Value("${admin.login.name}")
    private String loginName;

    @Value("${admin.login.pwd}")
    private String loginPwd;

    /**
     *
     * <b>功能：</b>管理员登录<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("login")
    public ModelAndView loginInit(HttpServletRequest request, HttpServletResponse response){

        return new ModelAndView("/login/login");
    }

    @RequestMapping("layout")
    public ModelAndView layOut(HttpServletRequest request, HttpServletResponse response){
        //设置登录标识 0，登录成功
        request.getSession().setAttribute("ISLOGIN","1");
        return new ModelAndView("/login/login");
    }

    /**
     *
     * <b>功能：</b>管理员登录后页面<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("dologin")
    public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response,String login ,String password){
        //登录验证
        if(!login.equals(loginName) || !password.equals(loginPwd)){
            return new ModelAndView("/login/error");
        }
        //设置登录标识 0，登录成功
        request.getSession().setAttribute("ISLOGIN","0");

        ModelAndView mav = new ModelAndView("/frameset");
        return mav;
    }

    /**
     *
     * <b>功能：</b>添加商品初始化页面<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("addprodinit")
    public ModelAndView addProdInfoInt(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //是否登录验证

//        DictionaryConfig.getInstance().getMapByTypeName("商品类别");


        ModelAndView mav = new ModelAndView("/prod/addprodinfo");
        return mav;
    }

    /**
     *
     * <b>功能：</b>通过条形码或二维码或手工录入方式将商品信息录入<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("addprodinfo")
    public ModelAndView addProdInfo(HttpServletRequest request, HttpServletResponse response,ProdInfo inPara){
        HttpSession session = request.getSession();
        //登录验证
        managerService.addProductList(inPara);

        ModelAndView mav = new ModelAndView("/prod/addprodinforesult");
        return mav;
    }

    /**
     *
     * <b>功能：</b>通过条形码或二维码或手工录入方式将商品信息录入<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @ResponseBody
    @RequestMapping("addprodinfoajax")
    public ResponseVo addProdInfoAjax(HttpServletRequest request, ProdInfo inPara){
        HttpSession session = request.getSession();
        //登录验证
        ResponseVo resp = null;
        try{
            resp = managerService.addProductList(inPara);
        }catch(Exception e){
            log.error(e);
        }
        return resp;
    }

    /**
     *
     * <b>功能：</b>通过条形码或二维码或手工录入方式将商品信息修改<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @ResponseBody
    @RequestMapping("modifyprodinfoajax")
    public ResponseVo modifyProdInfoAjax(HttpServletRequest request, ProdInfo inPara){
        HttpSession session = request.getSession();
        //登录验证
        ResponseVo resp = null;
        try{
            resp = managerService.modifyProductInfo(inPara);
        }catch(Exception e){
            log.error(e);
        }
        return resp;
    }

    /**
     *
     * <br>
     * <b>功能：</b>通过条形码或二维码或手工录入方式将商品信息录入<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @ResponseBody
    @RequestMapping("delprodinfoajax")
    public ResponseVo delProdinfoAjax(HttpServletRequest request, String ids){
        HttpSession session = request.getSession();
        //登录验证
        ResponseVo resp = null;
        try{
            resp = managerService.delProductInfo(ids);
        }catch(Exception e){
            log.error(e);
        }
        return resp;
    }

    /**
     *
     * <br>
     * <b>功能：</b>获取当前社区商品列表<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @ResponseBody
    @RequestMapping("prodlist")
    public ResponseToMa list(ProdInfoModel inPara,String page,String rows){
        ResponseToMa resp = null;
        Navigate navig = new Navigate();
        if(page!=null && !"".equals(page))
            navig.setPageId(Integer.parseInt(page));
        if(rows!=null && !"".equals(rows))
            navig.setPageSize(Integer.parseInt(rows));
        navig.setOrderField("update_time");//生效日期排序
        inPara.setNavigate(navig);


        try{
            resp = managerService.getProductList(inPara);
        }catch(Exception e){
            log.error(e);
        }
        return resp;
    }

    /**
     *
     * <br>
     * <b>功能：</b>支持同时上传多张图片（商品图片库和其它库分离，当前可以通过文件目录分离）init<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("uploadinit")
    public ModelAndView uploadProdInit(HttpServletRequest request, HttpServletResponse response,String prodNo){
        HttpSession session = request.getSession();
        //登录验证

//        prodNo = "10001";
        ModelAndView mav = new ModelAndView("/upload/uploadinit");
        mav.addObject("prodNo",prodNo);

        return mav;
    }

    /**
     *
     * <b>功能：</b>支持同时上传多张图片（商品图片库和其它库分离，当前可以通过文件目录分离）<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("uploadsubmit")
    public ModelAndView uploadProdSubmit(HttpServletRequest request, HttpServletResponse response,String prodNo){
        HttpSession session = request.getSession();
        //登录验证

        ModelAndView mav = new ModelAndView("/upload/uploadresult");
        if(StringUtils.isEmpty(prodNo)){
            mav.addObject("successflag","0");
            return mav;
        }
        try{
            //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
            CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if(multipartResolver.isMultipart(request))
            {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
                //获取multiRequest 中所有的文件名
                Iterator iter=multiRequest.getFileNames();
                while(iter.hasNext())
                {
                    //一次遍历所有文件
                    MultipartFile file=multiRequest.getFile(iter.next().toString());
                    if(file!=null)
                    {
                        String path=picSaveUrl+file.getOriginalFilename();
                        //上传
                        file.transferTo(new File(path));

                        String picpath = picSaveUrl+file.getOriginalFilename();
                        ProdProperty inPara = new ProdProperty();
                        inPara.setProdNo(prodNo);
                        inPara.setPropKey("picture");
                        inPara.setPropName("picture");
                        inPara.setPropValue(picpath);
                        inPara.setPropDiscribe("picture");
                        managerService.addProdProperty(inPara);
                    }
                }
            }
        } catch(Exception e){
            log.error(e);
        }

        mav.addObject("successflag","1");
        return mav;
    }

    /**
     *
     * <br>
     * <b>功能：</b>商品属性（颜色、款式等）信息录入或修改【web端】<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("modifyprodpropetyinit")
    public ModelAndView modifyProdPropetyInit(HttpServletRequest request, HttpServletResponse response,String prodNo){

        ModelAndView mav = new ModelAndView("/prod/modprodproperty");
        mav.addObject("prodNo",prodNo);
        return mav;
    }

    /**
     * ajax 获取商品属性列表
     * @param inPara
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping("prodprotylist")
    public ResponseToMa getProdPropetyList(ProdPropertyModel inPara, String page, String rows){
        ResponseToMa resp = null;
        Navigate navig = new Navigate();
        if(page!=null && !"".equals(page))
            navig.setPageId(Integer.parseInt(page));
        if(rows!=null && !"".equals(rows))
            navig.setPageSize(Integer.parseInt(rows));
        navig.setOrderField("prod_no");
        inPara.setNavigate(navig);
        try{
            resp = managerService.getProdPropertys(inPara);
        }catch(Exception e){
            log.error(e);
        }
        return resp;
    }

    /**
     * ajax 修改商品属性
     * @param inPara
     * @param opt 0.添加。1.修改，2，删除
     * @return
     */
    @ResponseBody
    @RequestMapping("modifyprodpropetyajax")
    public ResponseVo modifyProdpropetyAjax(HttpServletRequest request, ProdProperty inPara, String opt, String ids){
        HttpSession session = request.getSession();
        //登录验证
        ResponseVo resp = null;
        try{
            if("0".equals(opt)){
                resp = managerService.addProdProperty(inPara);
            } else if("1".equals(opt)){
                resp = managerService.modifyProdProperty(inPara);
            } else if("2".equals(opt)){
                resp = managerService.delProdProperty(ids);
            }
        }catch(Exception e){
            log.error(e);
        }
        return resp;
    }

    /**
     *
     * <br>
     * <b>功能：</b>商品库存信息录入或修改【web端】<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("modifyprodamountinfo")
    public ModelAndView modifyProdAmountInfo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证


        ModelAndView mav = new ModelAndView("/login/login");
        return mav;
    }

    /**
     *
     * <br>
     * <b>功能：</b>商品价格信息录入或修改【web端】<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("modifyprodpriceinfo")
    public ModelAndView modifyProdPriceInfo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证


        ModelAndView mav = new ModelAndView("/login/login");
        return mav;
    }

    /**
     *
     * <br>
     * <b>功能：</b>商品分类【web端】<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("modifyprodtypeinfo")
    public ModelAndView modifyProdTypeInfo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证


        ModelAndView mav = new ModelAndView("/login/login");
        return mav;
    }

    /**
     *
     * <br>
     * <b>功能：</b>商品排序【web端】<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("sortprodinfo")
    public ModelAndView sortProdInfo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证


        ModelAndView mav = new ModelAndView("/login/login");
        return mav;
    }

    /**
     *
     * <br>
     * <b>功能：</b>已支付列表<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("payorderinfo")
    public ModelAndView payOrderInfo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证


        ModelAndView mav = new ModelAndView("/login/login");
        return mav;
    }

    /**
     *
     * <br>
     * <b>功能：</b>订单信息编辑【web端】 init<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("prodorderinit")
    public ModelAndView prodOrderInfoInit(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证


        ModelAndView mav = new ModelAndView("/order/prodorderinit");
        return mav;
    }

    /**
     *
     * <br>
     * <b>功能：</b>订单列表及已支付列表【web端】 <br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @ResponseBody
    @RequestMapping("prodorderlist")
    public ResponseToMa prodOrderInfo(OrderInfoModel inPara, String page, String rows){
        ResponseToMa resp = null;
        Navigate navig = new Navigate();
        if(page!=null && !"".equals(page))
            navig.setPageId(Integer.parseInt(page));
        if(rows!=null && !"".equals(rows))
            navig.setPageSize(Integer.parseInt(rows));
        navig.setOrderField("create_time");
        inPara.setNavigate(navig);

        try{
            resp = managerService.queryOrderList(inPara);
        }catch(Exception e){
            log.error(e);
        }
        return resp;
    }


    /**
     * ajax 订单信息编辑
     * @param inPara
     * @param opt 0.修改订单状态。1.修改修改订单列表-购物清单
     * @return
     */
    @ResponseBody
    @RequestMapping("modifyorderajax")
    public ResponseVo modifyOrderInfoajax(HttpServletRequest request, OrderInfo inPara, ProdList prodList, String opt){
        HttpSession session = request.getSession();
        //登录验证
        ResponseVo resp = null;
        try{
            if("0".equals(opt)){
                resp = managerService.modifyOrderInfo(inPara);
            } else if("1".equals(opt)){
                resp = managerService.modifyOrderInfo(prodList);
            }
        }catch(Exception e){
            log.error(e);
        }
        return resp;
    }


    /**
     *
     * <br>
     * <b>功能：</b>订单信息编辑【web端】<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("modifyprodorderinfo")
    public ModelAndView modifyProdOrderInfo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证


        ModelAndView mav = new ModelAndView("/login/login");
        return mav;
    }

    /**
     *
     * <br>
     * <b>功能：</b>客户列表【web端】<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("querycustinit")
    public ModelAndView queryCustInit(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证

        ModelAndView mav = new ModelAndView("/cust/custinit");
        return mav;
    }

    @ResponseBody
    @RequestMapping("custinfolist")
    public ResponseToMa queryCustinfo(CustInfoModel inPara, String page, String rows) {
        ResponseToMa resp = null;
        Navigate navig = new Navigate();
        if (page != null && !"".equals(page))
            navig.setPageId(Integer.parseInt(page));
        if (rows != null && !"".equals(rows))
            navig.setPageSize(Integer.parseInt(rows));
        navig.setOrderField("create_time");
        inPara.setNavigate(navig);

        try {
            resp = managerService.queryCustInfoList(inPara);
        } catch (Exception e) {
            log.error(e);
        }
        return resp;
    }

    /**
     * <b>功能：</b>客户信息编辑【web端】<br>
     * ajax 订单信息编辑
     * @param inPara
     * @param opt 0.新增，1.修改。2.删除。
     * @return
     */
    @ResponseBody
    @RequestMapping("modifycustinfo")
    public ResponseVo modifyCustInfo(HttpServletRequest request, CustInfo inPara, String opt, String ids){
            HttpSession session = request.getSession();
            //登录验证
            ResponseVo resp = null;
            try{
                if("0".equals(opt)){
                    resp = managerService.addyCustInfo(inPara);
                } else if("1".equals(opt)){
                    inPara.setIsDelete("0");//正常
                    resp = managerService.modifyCustInfo(inPara);
                } else if("2".equals(opt)){
                    String[] idArray = ids.split(",");
                    for (String id:idArray) {
                        inPara = new CustInfo();
                        inPara.setId(id);
                        inPara.setIsDelete("1");//删除
                        resp = managerService.modifyCustInfo(inPara);
                    }
                }
            }catch(Exception e){
                log.error(e);
            }
            return resp;
    }

    /**
     *
     * <br>
     * <b>功能：</b>特殊活动后台设置功能【web端】<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("prodplaninfoinit")
    public ModelAndView prodPlaninfoInit(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证

        ModelAndView mav = new ModelAndView("/prodplan/prodplaninit");
        return mav;
    }

    @RequestMapping("prodplandetailinit")
    public ModelAndView prodPlanDetailinfoInit(HttpServletRequest request, HttpServletResponse response,String planNo){
        HttpSession session = request.getSession();
        //登录验证

        ModelAndView mav = new ModelAndView("/prodplan/prodplandetail");
        mav.addObject("planNo",planNo);
        return mav;
    }

    @ResponseBody
    @RequestMapping("queryprodplanajax")
    public ResponseToMa queryProdPlan(ProdPlanModel inPara, String page, String rows) {
        ResponseToMa resp = null;
        Navigate navig = new Navigate();
        if (page != null && !"".equals(page))
            navig.setPageId(Integer.parseInt(page));
        if (rows != null && !"".equals(rows))
            navig.setPageSize(Integer.parseInt(rows));
        navig.setOrderField("create_time");
        inPara.setNavigate(navig);

        try {
            resp = managerService.queryProdPlanList(inPara);
        } catch (Exception e) {
            log.error(e);
        }
        return resp;
    }

    @ResponseBody
    @RequestMapping("queryprodplandetailajax")
    public ResponseToMa queryProdPlanDetail(ProdPlanDetailModel inPara, String page, String rows) {
        ResponseToMa resp = null;
        Navigate navig = new Navigate();
        if (page != null && !"".equals(page))
            navig.setPageId(Integer.parseInt(page));
        if (rows != null && !"".equals(rows))
            navig.setPageSize(Integer.parseInt(rows));
        navig.setOrderField("order");
        inPara.setNavigate(navig);

        try {
            resp = managerService.queryProdPlanDetailList(inPara);
        } catch (Exception e) {
            log.error(e);
        }
        return resp;
    }

    /**
     * <b>功能：</b>活动信息编辑【web端】<br>
     * ajax 活动信息编辑
     * @param inPara
     * @param opt 0.新增，1.修改。2.删除。
     * @return
     */
    @ResponseBody
    @RequestMapping("modifyprodplan")
    public ResponseVo modifyProdPlan(HttpServletRequest request, ProdPlan inPara, String opt){
        HttpSession session = request.getSession();
        //登录验证
        ResponseVo resp = null;
        try{
            if("0".equals(opt)){
                resp = managerService.addProdPlan(inPara);
            } else if("1".equals(opt)){
                inPara.setStatus("1");//有效
                resp = managerService.modifyProdPlan(inPara);
            } else if("2".equals(opt)){
                inPara.setStatus("0");//失效
                resp = managerService.modifyProdPlan(inPara);
            }
        }catch(Exception e){
            log.error(e);
        }
        return resp;
    }

    /**
     * <b>功能：</b>活动信息详情编辑【web端】<br>
     * ajax 活动信息详情编辑
     * @param inPara
     * @param opt 0.新增，1.修改。2.删除。
     * @return
     */
    @ResponseBody
    @RequestMapping("modifyprodplandetail")
    public ResponseVo modifyProdPlanDetail(HttpServletRequest request, ProdPlanDetail inPara, String opt, String ids){
        HttpSession session = request.getSession();
        //登录验证
        ResponseVo resp = null;
        try{
            if("0".equals(opt)){
                resp = managerService.addProdPlanDetail(inPara);
            } else if("1".equals(opt)){
                resp = managerService.modifyProdPlanDetail(inPara);
            } else if("2".equals(opt)){
                resp = managerService.delProdPlanDetail(ids);
            }
        }catch(Exception e){
            log.error(e);
        }
        return resp;
    }

    /**
     *
     * <br>
     * <b>功能：</b>管理员设置【web端】<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("admininfo")
    public ModelAndView adminInfo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证


        ModelAndView mav = new ModelAndView("/login/login");
        return mav;
    }

}