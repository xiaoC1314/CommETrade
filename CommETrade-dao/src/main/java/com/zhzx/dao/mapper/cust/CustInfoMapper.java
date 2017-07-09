package com.zhzx.dao.mapper.cust;

import com.zhzx.dao.mapper.BaseMapper;

/**
 * CustInfo Mapper
 * @author xiaoC
 *
 */
public interface CustInfoMapper<T> extends BaseMapper<T> {


    T selectByPhone(long phone);

    T selectByPhoneForUpdate(long phone);
}
