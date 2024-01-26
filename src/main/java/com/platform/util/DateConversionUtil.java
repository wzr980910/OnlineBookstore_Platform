package com.platform.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/25/18:08
 * @Description:处理日期格式
 */
@Component
public class DateConversionUtil {

    /**
     * 将日期格式转换成yyyy-MM-dd
     */
    public static String dateConversion(String dateTimeString){

        // 将字符串解析为 Instant
        Instant instant = Instant.parse(dateTimeString);

        // 转换为 LocalDate，使用默认时区或特定时区
        LocalDate localDate = instant.atZone(ZoneId.of("UTC")).toLocalDate();

        // 格式化为只包含年月日的字符串
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter);
    }

}
