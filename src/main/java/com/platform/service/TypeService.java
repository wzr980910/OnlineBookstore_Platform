package com.platform.service;

import com.platform.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author 邓桂材
* @description 针对表【type(图书类型表)】的数据库操作Service
* @createDate 2024-01-14 16:56:54
*/
@Service
public interface TypeService extends IService<Type> {
    /**
     * 查询所有图书类别
     * @return
     */
    Map<String,Object> queryAllType(Integer currentPage, Integer size);

    /**
     * 通过父类id查询图书类别
     * @return
     */
    List<Type> queryByParentId(Long parentId);

    /**
     * 添加父类类别
     */
    Integer insertParentType(String parentType);

    /**
     * 添加子类类别
     * @param type
     * @return
     */
    Integer insertType(Type type);

    /**
     * 通过id删除类别
     */
    Integer deleteType(Long typeId);

    /**
     * 更新图书类别
     */
    Integer updateType(Type type);
}
