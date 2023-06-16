package cn.bdqn.entity;

import java.io.Serializable;

/**
* <p>
    * 学生与班级绑定表
    * </p>
*
* @author pb
* @since 2023-06-15
*/

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

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}
