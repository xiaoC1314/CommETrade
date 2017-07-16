package com.zhzx.uip.api.controller.manager;

import com.zhzx.dao.bean.prod.ProdInfo;
import com.zhzx.uip.api.controller.BaseController;
import com.zhzx.uip.service.manager.prod.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.ldap.InitialLdapContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by dongwm on 2017/7/9.
 */
@Controller
@RequestMapping("/console")
public class ManagerController extends BaseController {

    @Autowired
    private ManagerService managerService;

    /**
     *
     * <br>
     * <b>功能：</b>管理员登录<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("login")
    public ModelAndView loginInit(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/login/login");
    }

    @RequestMapping("dologin")
    public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证


        ModelAndView mav = new ModelAndView("frameset");
        return mav;
    }


    /**
     *
     * <br>
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

    @RequestMapping("addprodinit")
    public ModelAndView addProdInfoInt(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //是否登录验证

        ModelAndView mav = new ModelAndView("/prod/addprodinfo");
        return mav;
    }

    /**
     *
     * <br>
     * <b>功能：</b>通过商品条形码对商品信息进行修改【web端】<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("modifyprodinfo")
    public ModelAndView modifyProdInfo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证


        ModelAndView mav = new ModelAndView("/login/login");
        return mav;
    }

    /**
     *
     * <br>
     * <b>功能：</b>支持同时上传多张图片（商品图片库和其它库分离，当前可以通过文件目录分离）<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("uploadprodpicinfo")
    public ModelAndView uploadProdpicInfo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证


        ModelAndView mav = new ModelAndView("/login/login");
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
    @RequestMapping("modifyprodpropetyinfo")
    public ModelAndView modifyProdPropetyInfo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证


        ModelAndView mav = new ModelAndView("/login/login");
        return mav;
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
     * <b>功能：</b>订单列表【web端】<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("prodorderinfo")
    public ModelAndView prodOrderInfo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证


        ModelAndView mav = new ModelAndView("/login/login");
        return mav;
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
    @RequestMapping("querycustinfo")
    public ModelAndView queryCustInfo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证


        ModelAndView mav = new ModelAndView("/login/login");
        return mav;
    }

    /**
     *
     * <br>
     * <b>功能：</b>客户信息编辑【web端】<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("modifycustinfo")
    public ModelAndView modifyCustInfo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证


        ModelAndView mav = new ModelAndView("/login/login");
        return mav;
    }

    /**
     *
     * <br>
     * <b>功能：</b>特殊活动后台设置功能【web端】<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("modifyprodplaninfo")
    public ModelAndView modifyProdplanInfo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证


        ModelAndView mav = new ModelAndView("/login/login");
        return mav;
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

}
