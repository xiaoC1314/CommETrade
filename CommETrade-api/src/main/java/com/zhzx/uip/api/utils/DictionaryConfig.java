package com.zhzx.uip.api.utils;

import com.zhzx.dao.bean.common.Bdictionary;
import com.zhzx.dao.model.common.BdictionaryModel;
import com.zhzx.dao.service.common.BdictionaryService;
import com.zhzx.uip.commons.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取字典表信息
 * Created by Administrator on 2017/7/15.
 */
public class DictionaryConfig  implements Serializable {
    private static final long serialVersionUID = 3724037189805551148L;

    private static final Logger logger = LoggerFactory.getLogger(DictionaryConfig.class);

    private Map configDicts = null;//最终存放配置的Map，其中每个段存放于一个Map中，Map中每个键值对是一个catption(是值)和value(是键)

    private static DictionaryConfig instance = null;

    private DictionaryConfig() {
        init();
    }

    public void removeCache() {
        instance = null;
    }
    public synchronized static DictionaryConfig getInstance() {
        if (instance == null)
            instance = new DictionaryConfig();
        return instance;
    }
    public void init() {
        configDicts = configDicts != null ? configDicts : new HashMap();

        BdictionaryService bdictionaryService = (BdictionaryService) SpringContextUtil.getBean("BdictionaryService");
        List<Bdictionary> listret = null;
        try {
            BdictionaryModel model = new BdictionaryModel();
            listret = bdictionaryService.getMapper().selectByModel(model);
            if(!listret.isEmpty()){
                Map config = null;
                boolean isBlock = false;
                String caption = null;
                String value = null;
                for (Bdictionary bean:listret) {
                    caption = bean.getCaption();
                    value = bean.getValue();
                    if (value == null) {
                        value = "空";
                        isBlock = false;
                    }
                    if ("#".equals(value)) {
                        isBlock = true;
                        config = new LinkedHashMap();
                        configDicts.put(caption, config);
                    } else {
                        isBlock = false;
                    }
                    if (!isBlock) {
                        config.put(value, caption);
                    }
                }
            }
        }catch (Exception e){
            logger.error("字典信息获取失败："+e.getMessage());
        }
    }

}
