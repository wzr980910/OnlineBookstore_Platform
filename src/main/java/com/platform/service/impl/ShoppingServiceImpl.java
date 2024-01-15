package com.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.pojo.Shopping;
import com.platform.service.ShoppingService;
import com.platform.mapper.ShoppingMapper;
import org.springframework.stereotype.Service;

/**
* @author 邓桂材
* @description 针对表【shopping(购物车)】的数据库操作Service实现
* @createDate 2024-01-14 23:34:44
*/
@Service
public class ShoppingServiceImpl extends ServiceImpl<ShoppingMapper, Shopping>
    implements ShoppingService{

}




