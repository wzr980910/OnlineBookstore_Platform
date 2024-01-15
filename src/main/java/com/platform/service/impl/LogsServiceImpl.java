package com.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.pojo.Logs;
import com.platform.service.LogsService;
import com.platform.mapper.LogsMapper;
import org.springframework.stereotype.Service;

/**
* @author 邓桂材
* @description 针对表【logs(日志表)】的数据库操作Service实现
* @createDate 2024-01-14 16:56:54
*/
@Service
public class LogsServiceImpl extends ServiceImpl<LogsMapper, Logs>
    implements LogsService{

}




