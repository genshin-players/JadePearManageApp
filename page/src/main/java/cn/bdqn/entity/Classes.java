package cn.bdqn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 班级
 * </p>
 *
 * @author 
 * @since 2023-06-09
 */
public class Classes implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;

    /**
     * 教员id
     */
    private Integer instructorId;

    /**
     * 辅导员id(班主任)
     */
    private Integer adviserId;
    private Date createTime;

    /**
     * 班级类型id
     */
    private Integer subjectId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }

    public Integer getAdviserId() {
        return adviserId;
    }

    public void setAdviserId(Integer adviserId) {
        this.adviserId = adviserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "Classes{" +
        ", id = " + id +
        ", name = " + name +
        ", instructorId = " + instructorId +
        ", adviserId = " + adviserId +
        ", createTime = " + createTime +
        ", subjectId = " + subjectId +
        "}";
    }
}
