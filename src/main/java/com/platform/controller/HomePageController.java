package com.platform.controller;

import com.platform.pojo.HomePage;
import com.platform.service.HomePageService;
import com.platform.util.result.RestResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/23/20:24
 * @Description:
 */
@Api(tags = "首页展示信息")
@RestController
@RequestMapping("/homePage")
public class HomePageController {

    private HomePageService homePageService;

    @Autowired
    public void setHomePageService(HomePageService homePageService){this.homePageService = homePageService;}

    @PostMapping("/getInfo")
    public RestResult getInfo(){
        //获取总用户数
        Integer totalUsers = homePageService.getUsersNumber();

        //获取总图书数
        Integer totalBooks = homePageService.getBooksNumber();

        //获取总订单数
        Integer totalOrders = homePageService.getOrdersNumber();

        //获取总收入
        BigDecimal totalIncome = homePageService.getIncome();

        //获取图书销量
        List<HomePage> booksSale = homePageService.getBooksSale();
        //用hashMap封装图书销量
        HashMap<String,Integer> booksSaleMap = new HashMap<>();
        for (int i = 0; i<booksSale.size(); i++){
            booksSaleMap.put(booksSale.get(i).getType(),booksSale.get(i).getTotalSale());
        }

        //根据月份查询订单数量
        List<HomePage> ordersNumber =  homePageService.ordersNumberByMonth();
        //封装月份
        int[] ordersArray = new int[12];
        for (int i = 0;i < ordersNumber.size(); i++){
            ordersArray[i] = ordersNumber.get(i).getOrdersNumber();
        }

        //封装返回值
        HashMap<String,Object> map = new HashMap<>();
        map.put("totalUsers",totalUsers);
        map.put("totalBooks",totalBooks);
        map.put("totalOrders",totalOrders);
        map.put("totalIncome",totalIncome);
        map.put("onechartData",booksSaleMap);
        map.put("twochartData",ordersArray);
        return RestResult.success(map);
    }
}
