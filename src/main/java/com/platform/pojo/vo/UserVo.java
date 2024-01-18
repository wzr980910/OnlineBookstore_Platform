package com.platform.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/18/9:20
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    private Long id;

    private String accountNumber;

    private String password;

    private String username;

    private String phoneNumber;

    private String picture;

    private Long gender;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;

    private Integer pageNum = 1;

    private Integer pageSize = 10;
}
