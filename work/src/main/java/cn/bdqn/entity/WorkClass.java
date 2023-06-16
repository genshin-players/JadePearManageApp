package cn.bdqn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author
 * @since 2023-06-15
 */
@TableName("member_work_class")
public class WorkClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer schedulesId;
    private Integer classId;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchedulesId() {
        return schedulesId;
    }

    public void setSchedulesId(Integer schedulesId) {
        this.schedulesId = schedulesId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "WorkClass{" +
        ", id = " + id +
        ", schedulesId = " + schedulesId +
        ", classId = " + classId +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        "}";
    }
}