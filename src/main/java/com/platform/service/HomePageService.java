package com.platform.service;

import com.platform.pojo.HomePage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/23/20:25
 * @Description:
 */
@Service
public interface HomePageService {
    Integer getUsersNumber();

    Integer getBooksNumber();

    Integer getOrdersNumber();

    BigDecimal getIncome();

    List<HomePage> getBooksSale();

    List<HomePage> ordersNumberByMonth();
}
