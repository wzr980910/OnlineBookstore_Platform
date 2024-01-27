package com.platform.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    public static String dateCon(String dateString) {

        // 定义输入日期字符串的格式
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", java.util.Locale.ENGLISH);

        // 定义输出日期字符串的格式
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String outputDateString = null;
        try {
            // 将输入字符串解析为日期对象
            Date date = inputFormat.parse(dateString);

            // 将日期对象格式化为输出字符串
            outputDateString = outputFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDateString;
    }

}
