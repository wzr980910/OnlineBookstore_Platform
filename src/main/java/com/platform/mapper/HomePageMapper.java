package com.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.pojo.HomePage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/23/20:32
 * @Description:
 */
@Mapper
public interface HomePageMapper extends BaseMapper<HomePage> {

    HomePage getUsersNumber();
    HomePage getBooksNumber();
    HomePage getOrdersNumber();
    HomePage getIncome();
    List<HomePage> getBooksSale();
    List<HomePage> ordersNumberByMonth();

}
