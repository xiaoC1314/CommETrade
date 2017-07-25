package com.zhzx.uip.api.controller.manager;

import com.zhzx.dao.bean.prod.ProdInfo;
import com.zhzx.dao.model.prod.ProdInfoModel;
import com.zhzx.dao.support.Navigate;
import com.zhzx.uip.api.controller.BaseController;
import com.zhzx.uip.api.controller.product.ProductController;
import com.zhzx.uip.api.utils.ExcelInUtils;
import com.zhzx.uip.api.utils.ExcelOutUtils;
import com.zhzx.uip.commons.module.ResponseToMa;
import com.zhzx.uip.commons.module.ResponseVo;
import com.zhzx.uip.service.manager.prod.ManagerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dongwm on 2017/7/9.
 */
@Controller
@RequestMapping("/console/upload")
public class UploadController extends BaseController {
    private final static Logger log= Logger.getLogger(ProductController.class);

    @Autowired
    private ManagerService managerService;

    /**
     * <b>功能：</b>上传excle文件， 导入对应数据到数据库 init<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("uploadexcleinit")
    public ModelAndView uploadProdInit(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //登录验证

        ModelAndView mav = new ModelAndView("/upload/uploadexcleinit");
        return mav;
    }

    /**
     * <b>功能：</b>上传excle文件， 导入对应数据到数据库 submit<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("loadexcel")
    public ModelAndView loadExcel(HttpServletRequest request, HttpServletResponse response,CommonsMultipartFile multipart) throws IOException {
        ExcelInUtils<ProdInfo> testExcel = null;
        List<ProdInfo> prodInfoList = null;

        //定义需要读取的数据
        String formart = "yyyy-MM-dd";
        String propertiesFileName = "config";
        String kyeName = "file_path";
        int sheetIndex = 0;
        Map<String, String> titleAndAttribute=null;
        Class<ProdInfo> clazz=ProdInfo.class;
        //定义对应的标题名与对应属性名
        titleAndAttribute=new HashMap<String, String>();
        titleAndAttribute.put("ID", "id");
        titleAndAttribute.put("NAME", "name");

        //调用解析工具包
        testExcel = new ExcelInUtils<ProdInfo>(formart);
        //解析excel，获取客户信息集合
        try {
            prodInfoList = testExcel.uploadAndRead(multipart, propertiesFileName, kyeName, sheetIndex, titleAndAttribute, clazz);
        } catch (Exception e) {
            log.error("读取Excel文件错误！",e);
        }
        if(prodInfoList != null && !prodInfoList.isEmpty()){
            for (ProdInfo prodinfo : prodInfoList) {
                //插入数据

            }
        }

        ModelAndView mav = new ModelAndView("/upload/uploadresult");
        return mav;
    }

    /**
     * <b>功能：</b>导出数据到 excle 文件 submit<br>
     * <b>作者：</b>dongwm<br>
     * <b>日期：</b> Dec 8, 2011 <br>
     * @return
     */
    @RequestMapping("exportexcel")
    public ModelAndView exportExcel(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> conditionMap = null;
        List<Object> dataSet = null;
        String fileName = "client";
        String title = "用户信息";
        String[] rowName = { "编号", "名称" };

        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        for (int i = 0; i < dataSet.size(); i++) {
            objs = new Object[rowName.length];
            objs[0] = i;
            objs[1] = dataSet.get(i);
            dataList.add(objs);
        }
        ExcelOutUtils excelOut  = new ExcelOutUtils(fileName,title, rowName, dataList,response);
        try {
            excelOut.export();
        } catch (Exception e) {
            log.error("写入Excle出错！", e);
        }
        ModelAndView mav = new ModelAndView("/upload/uploadresult");
        return mav;
    }
}