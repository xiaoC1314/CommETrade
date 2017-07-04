package com.zhzx.uip.commons.utils;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 统一校验工具类，抛出NetTradeException
 * Created by wangfeng on 2016/5/28.
 */
public class NetTradeAssert {


        public NetTradeAssert() {
        }

        public static void isTrue(boolean expression, String code, String message) {
            if(!expression) {
                throw new NetTradeException(code, message);
            }
        }


        public static void isNull(Object object, String code, String message) {
            if(object != null) {
                throw new NetTradeException(code, message);
            }
        }

        public static void notNull(Object object, String code, String message) {
            if(object == null) {
                throw new NetTradeException(code, message);
            }
        }


        public static void hasLength(String text, String code, String message) {
            if(!StringUtils.hasLength(text)) {
                throw new NetTradeException(code, message);
            }
        }


        public static void hasText(String text, String code, String message) {
            if(!StringUtils.hasText(text)) {
                throw new NetTradeException(code, message);
            }
        }


        public static void doesNotContain(String textToSearch, String substring, String code, String message) {
            if(StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.contains(substring)) {
                throw new NetTradeException(code, message);
            }
        }


        public static void notEmpty(Object[] array, String code, String message) {
            if(ObjectUtils.isEmpty(array)) {
                throw new NetTradeException(code, message);
            }
        }


        public static void noNullElements(Object[] array, String code, String message) {
            if(array != null) {
                Object[] arr$ = array;
                int len$ = array.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    Object element = arr$[i$];
                    if(element == null) {
                        throw new NetTradeException(code, message);
                    }
                }
            }

        }

        public static void notEmpty(Collection collection, String code, String message) {
            if(CollectionUtils.isEmpty(collection)) {
                throw new NetTradeException(code, message);
            }
        }

        public static void notEmpty(Map map, String code, String message) {
            if(CollectionUtils.isEmpty(map)) {
                throw new NetTradeException(code, message);
            }
        }



        public static void isInstanceOf(Class<?> type, Object obj, String code, String message) {
            notNull(type,  code, "Type to check against must not be null");
            if(!type.isInstance(obj)) {
                throw new NetTradeException(code, message);
            }
        }


        public static void isAssignable(Class<?> superType, Class<?> subType, String code, String message) {
            notNull(superType, code, "Type to check against must not be null");
            if(subType == null || !superType.isAssignableFrom(subType)) {
                throw new NetTradeException(code, message);
            }
        }

        public static void state(boolean expression, String code, String message) {
            if(!expression) {
                throw new NetTradeException(code, message);
            }
        }
}
