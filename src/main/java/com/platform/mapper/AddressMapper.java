package com.platform.mapper;

import com.platform.pojo.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.pojo.vo.OrdersShowVo;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 邓桂材
* @description 针对表【address(地址表)】的数据库操作Mapper
* @createDate 2024-01-14 17:33:39
* @Entity com.bookStore.pojo.Address
*/
@Mapper
public interface AddressMapper extends BaseMapper<Address> {

    void updateInfo(OrdersShowVo ordersShowVo);

}




