package com.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.pojo.Type;
import com.platform.service.TypeService;
import com.platform.mapper.TypeMapper;
import org.springframework.stereotype.Service;

/**
* @author 邓桂材
* @description 针对表【type(图书类型表)】的数据库操作Service实现
* @createDate 2024-01-14 16:56:54
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

}




