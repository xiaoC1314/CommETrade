package com.zhzx.uip.api.utils;

import com.zhzx.dao.bean.common.Bdictionary;
import com.zhzx.dao.model.common.BdictionaryModel;
import com.zhzx.dao.service.common.BdictionaryService;
import com.zhzx.uip.commons.utils.SpringContextUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;

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

        BdictionaryService bdictionaryService = (BdictionaryService) SpringContextUtil.getBean(BdictionaryService.class);
        List<Bdictionary> listret = null;
        try {
            BdictionaryModel model = new BdictionaryModel();
            listret = bdictionaryService.selectByModel(model);
            if(!listret.isEmpty()){
                Map config = null;
                Map configDse = null;
                boolean isBlock = false;
                String caption = null;
                String captionDes = null;
                String value = null;
                for (Bdictionary bean:listret) {
                    caption = bean.getCaption();
                    captionDes = caption+"说明";
                    value = bean.getValue();
                    if (value == null) {
                        value = "空";
                        isBlock = false;
                    }
                    if ("#".equals(value)) {
                        isBlock = true;
                        config = new LinkedHashMap();
                        configDse = new LinkedHashMap();
                        configDicts.put(caption, config);
                        configDicts.put(captionDes, configDse);
                    } else {
                        isBlock = false;
                    }
                    if (!isBlock) {
                        config.put(value, caption);
                        configDse.put(value, captionDes);
                    }
                }
            }
        }catch (Exception e){
            logger.error("字典信息获取失败："+e.getMessage());
        }
    }

    public Object getCaptionObject(String item, String value) {
        if (value == null)
            return null;
        value = value.trim();
        Map map = (Map) configDicts.get(item);
        if (map != null){
            return map.get(value);
        }
        return null;
    }

    public String getValue(String item, String caption) {
        if(caption == null){	//防止传入caption为空时导致的空指针异常
            return null;
        }
        //如果取不到字典时会返回当前的条件值，在某此情况中并不需要
        boolean isRespNull=false;
        if(item.toUpperCase().indexOf("|NULL")!=-1)
        {
            item =item.replaceAll("\\|NULL", "");
            item =item.replaceAll("\\|null", "");
            isRespNull=true;
        }
        caption = caption.trim();
        String value ="";
        if(!isRespNull)
        {
            value= caption;	//如果找不到，返回caption
        }
        Map map = (Map) configDicts.get(item);
        if (map != null && map.containsValue(caption)) {
            Set set = map.keySet();
            Iterator it = set.iterator();
            while (it.hasNext()) {
                String key = it.next().toString();
                if (caption.equals(map.get(key))){
                    value = key.trim();
                    break;	//找到第一个匹配的则中断
                }
            }
        }
        return value;
    }

    /**
     * 取得Item对应的段名中某记录的value对应的Caption，如取不到，返回value
     *
     * @param item
     * @param value
     * @return
     */
    public String getCaption(String item, String value) {
        if (value == null)
            return null;
        //如果取不到字典时会返回当前的条件值，在某此情况中并不需要
        boolean isRespNull=false;
        if(item.toUpperCase().indexOf("|NULL")!=-1)
        {
            item =item.replaceAll("\\|NULL", "");
            item =item.replaceAll("\\|null", "");
            isRespNull=true;
        }
        value=value.trim();
        String valString="";
        if (!isRespNull)
        {
            valString=value;
        }
        Map map = (Map) configDicts.get(item);
        if (map != null && map.get(value) != null){
            valString = map.get(value).toString().trim();
        }else if(map != null && value.indexOf(",")>-1){
            String[] vals = value.split(",");
            valString = getCaption(item, vals[0]);
            for(int i=1; i<vals.length; i++){
                valString += "," + getCaption(item, vals[i]);
            }
        }
        return valString;
    }

    public List getItemsToKey(String typeName) {
        List list = new ArrayList();
        if (StringUtils.isNotEmpty(typeName)) {
            typeName = typeName.trim();
            Map t = (Map) configDicts.get(typeName);
            if (t != null&&t.size()>0) {
                Set entrySet = t.entrySet();
                Iterator it = entrySet.iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    list.add(entry.getKey());
                }
            }
        }
        return list;
    }

    public List getItems(String typeName) {
        List list = new ArrayList();
        Map map = new LinkedHashMap();
        if (StringUtils.isNotEmpty(typeName)) {
            typeName = typeName.trim();
            Map t = (Map) configDicts.get(typeName);
            if (t != null) {
                map.putAll(t);
                Set entrySet = map.entrySet();
                Iterator it = entrySet.iterator();
                while (it.hasNext()) {
                    Map tmpmap = new HashMap();
                    Map.Entry entry = (Map.Entry) it.next();
                    tmpmap.put(entry.getKey(), entry.getValue());
                    list.add(tmpmap);
                }
            }
        }
        return list;
    }

    public Map getMapByTypeName(String typeName) {
        Map map = new LinkedHashMap();
        if (StringUtils.isNotEmpty(typeName)) {
            typeName = typeName.trim();
            Map t = (Map) configDicts.get(typeName);
            if (t != null) {
                map.putAll(t);
            }
        }
        return map;
    }

    /**
     * 获得对象的key组成的string。
     * @param map
     * @return
     */
    public String getValue(Map map) {
        Iterator it = map.entrySet().iterator();
        StringBuffer sb = new StringBuffer();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if(value==null){
                sb.append(key);
            }else{
                if(value.getClass().getName().indexOf("Map")!=-1){
                    sb.append(this.getValue((Map)value));
                }else if(value.getClass().getName().indexOf("String")!=-1){
                    sb.append(key.toString()).append(value.toString());
                }else if(value.getClass().getName().indexOf("List")!=-1){
                    sb.append(key.toString()).append(value.toString());
                }else{
                    sb.append(key.toString()).append(value.toString());
                }
            }
        }
        return sb.toString();
    }
    /**
     * 获得参数值
     * @return
     */
    public String toStringValue() {
        return this.getValue(this.configDicts);
    }
}
