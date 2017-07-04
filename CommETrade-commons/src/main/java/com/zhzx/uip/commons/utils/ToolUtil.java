package com.zhzx.uip.commons.utils;

import com.zhzx.uip.commons.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ToolUtil {
    private static final Logger logger = LoggerFactory.getLogger(ToolUtil.class);

	private static Map<String, String> noteMap = null;

	static {
		noteMap = new HashMap<String, String>();
		noteMap.put(Constants.IBATIS_OPT_ADD_KEY, Constants.IBATIS_OPT_ADD_VALUE);
		noteMap.put(Constants.IBATIS_OPT_UPDATE_KEY, Constants.IBATIS_OPT_UPDATE_VALUE);
		noteMap.put(Constants.IBATIS_OPT_DELETE_KEY, Constants.IBATIS_OPT_DELETE_VALUE);
		noteMap.put(Constants.IBATIS_OPT_LIST_KEY, Constants.IBATIS_OPT_LIST_VALUE);
		noteMap.put(Constants.IBATIS_OPT_LISTCOUNT_KEY, Constants.IBATIS_OPT_LISTCOUNT_VALUE);
		noteMap.put(Constants.IBATIS_OPT_FIND_KEY, Constants.IBATIS_OPT_FIND_VALUE);
	}

	public static String getCommonDaoNodePath(String opt, Object obj) {
		// 组装节点如：com.isoftstone.dynamicform.obj.listObject
		StringBuffer nodePath = new StringBuffer();
		nodePath.append(Constants.IBATIS_SQL_PATH);
		nodePath.append(Constants.IBATIS_OPT_POINT);
		nodePath.append(obj.getClass().getSimpleName());
		nodePath.append(Constants.IBATIS_OPT_POINT);
		nodePath.append(StringUtil.formatterString(noteMap.get(opt), obj.getClass().getSimpleName()));
		logger.debug("ObjectNodePath：" + nodePath.toString());
		return nodePath.toString();
	}

}
