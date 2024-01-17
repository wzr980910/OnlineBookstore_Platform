package com.platform.util;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/16/15:49
 * @Description:ISBN生成工具类
 */
@Component
public class CreateISBNUtil {
    public static String createISBN(){
        // 随机生成前9位数字
        StringBuilder prefix = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            prefix.append(random.nextInt(10));
        }

        // 计算校验位
        int checksum = calculateChecksum(prefix.toString());

        // 构建完整的ISBN码
        StringBuilder isbn = new StringBuilder(prefix.toString());
        isbn.append(checksum);

        return isbn.toString();

    }

    //计算校验位
    public static int calculateChecksum(String digits) {
        int sum = 0;
        for (int i = 0; i < digits.length(); i++) {
            int digit = Character.getNumericValue(digits.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        int checksum = (10 - (sum % 10)) % 10;
        return checksum;
    }
}
