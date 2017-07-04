package com.zhzx.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by qlzg on 2016/6/16.
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();

    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }
    
    public static String getDataSourceKey() {  
        return dataSourceKey.get();  
    }  
    
    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }
}
