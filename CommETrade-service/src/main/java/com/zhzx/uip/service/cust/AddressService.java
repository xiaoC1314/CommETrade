package com.zhzx.uip.service.cust;

import com.zhzx.uip.api.cust.model.AddParam;
import com.zhzx.uip.commons.module.ResponseVo;

/**
 * Created by fu on 2017/7/18.
 */
public interface AddressService {

    ResponseVo addAddress(AddParam param);
}
