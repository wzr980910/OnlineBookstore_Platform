package com.platform.service;

import com.platform.pojo.Address;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.pojo.OrdersShow;
import com.platform.pojo.vo.OrdersShowVo;
import org.springframework.stereotype.Service;

/**
* @author wzr
* @description 针对表【address(地址表)】的数据库操作Service
* @createDate 2024-01-14 17:33:39
*/
@Service
public interface AddressService extends IService<Address> {

    int updateInfo(OrdersShowVo ordersShowVo);

}
