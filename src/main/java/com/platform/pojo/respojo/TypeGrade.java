package com.platform.pojo.respojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/22/9:34
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeGrade {
    private String type;
    private String parentType;
}
