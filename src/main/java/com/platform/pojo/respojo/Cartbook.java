package com.platform.pojo.respojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/16/15:04
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cartbook {
    /**
     * 图书名
     */
    private String bookName;
    /**
     * 图书封面
     */
    private String img;
    /**
     * 出版社
     */
    private String publishName;
    /**
     * 图书价格
     */
    private BigDecimal price;
    /**
     * 购买数量
     */
    private Integer number;

    /**
     * 库存数量
     */
    private Integer stockNum;
}
