package com.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.pojo.Address;
import com.platform.pojo.vo.OrdersShowVo;
import com.platform.service.AddressService;
import com.platform.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 邓桂材
* @description 针对表【address(地址表)】的数据库操作Service实现
* @createDate 2024-01-14 17:33:39
*/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
    implements AddressService{
    private AddressMapper addressMapper;
    @Autowired
    public void setAddressServiceImpl(AddressMapper addressMapper){this.addressMapper = addressMapper;}

    @Override
    public int updateInfo(OrdersShowVo ordersShowVo) {
        //通过mapper层更新数据
        return addressMapper.updateInfo(ordersShowVo);
    }
}




