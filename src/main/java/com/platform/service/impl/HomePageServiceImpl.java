package com.platform.service.impl;

import com.platform.mapper.HomePageMapper;
import com.platform.pojo.HomePage;
import com.platform.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/23/20:31
 * @Description:
 */
@Service
public class HomePageServiceImpl implements HomePageService {

    private HomePageMapper homePageMapper;

    @Autowired
    public void setHomePageMapper(HomePageMapper homePageMapper){this.homePageMapper = homePageMapper;}

    @Override
    public Integer getUsersNumber() {
        //通过mapper层获取用户总数
        return homePageMapper.getUsersNumber().getTotalUsers();
    }

    @Override
    public Integer getBooksNumber() {
        //通过mapper层获取用户总数
        return homePageMapper.getBooksNumber().getTotalBooks();
    }

    @Override
    public Integer getOrdersNumber() {
        //通过mapper层获取用户总数
        return homePageMapper.getOrdersNumber().getTotalOrders();
    }

    @Override
    public BigDecimal getIncome() {
        //通过mapper层获取用户总数
        return homePageMapper.getIncome().getTotalIncome();
    }

    @Override
    public List<HomePage> getBooksSale() {
        //通过mapper层获取
        return homePageMapper.getBooksSale();
    }

    @Override
    public List<HomePage> ordersNumberByMonth() {
        return homePageMapper.ordersNumberByMonth();
    }
}
