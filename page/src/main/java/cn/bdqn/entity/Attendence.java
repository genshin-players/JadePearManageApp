package cn.bdqn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 出勤
 * </p>
 *
 * @author 
 * @since 2023-06-09
 */
public class Attendence implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 班级id
     */
    private Integer classId;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 日期，格式”yyyy-MM-dd HH:mm:sss“
     */
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
     @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;

    /**
     * 0.缺勤 1.迟到 2.在 3.请假
     */
    private Integer isPresent;

    /**
     * 汇报者id
     */
    private Integer reportUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(Integer isPresent) {
        this.isPresent = isPresent;
    }

    public Integer getReportUserId() {
        return reportUserId;
    }

    public void setReportUserId(Integer reportUserId) {
        this.reportUserId = reportUserId;
    }

    @Override
    public String toString() {
        return "Attendence{" +
        ", id = " + id +
        ", classId = " + classId +
        ", studentId = " + studentId +
        ", date = " + date +
        ", isPresent = " + isPresent +
        ", reportUserId = " + reportUserId +
        "}";
    }
}
