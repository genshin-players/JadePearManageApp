package cn.bdqn.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* <p>
    * 学生与班级绑定表
    * </p>
*
* @author pb
* @since 2023-06-15
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class StudentClass implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 学生id
            */
    private Integer studentId;

            /**
            * 班级id
            */
    private Integer classId;


}
